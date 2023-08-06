package org.dromara.testhub.server.domain.service.impl;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.dromara.testhub.common.exception.TestHubException;
import org.dromara.testhub.server.domain.dto.req.other.LoginReqDto;
import org.dromara.testhub.server.domain.dto.res.other.LoginResDto;
import org.dromara.testhub.server.domain.service.UserService;
import org.dromara.testhub.server.infrastructure.repository.dao.UserMapper;
import org.dromara.testhub.server.infrastructure.repository.po.UserPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service("userService")
public class UserServiceImpl implements UserService {
    public static final String LOGIN_USER_KEY = "loginUser";
    public static final String USER_KEY = "userId";
    @Autowired
    private UserMapper userMapper;

    @Override
    public LoginResDto login(LoginReqDto reqDto) {
        LoginResDto loginResDto = LoginResDto.builder()
                .userName(reqDto.getUserName())
                .password(reqDto.getPassword())
                .flag(false)
                .build();
        LambdaQueryWrapper<UserPo> userPoQueryWrapper = new LambdaQueryWrapper<>();
        userPoQueryWrapper.eq(UserPo::getUserName,reqDto.getUserName());
        UserPo userPo = userMapper.selectOne(userPoQueryWrapper);

        if(userPo == null){
            loginResDto.setMsg("用户不存在");
            return loginResDto;
        }

        boolean flag = BCrypt.checkpw(reqDto.getPassword(), userPo.getPassword());
        loginResDto.setFlag(flag);
        if(flag){
            SaLoginModel model = new SaLoginModel();
            StpUtil.login(userPo.getId(), model.setExtra(USER_KEY, userPo.getId()));
            StpUtil.getTokenSession().set(LOGIN_USER_KEY, userPo);

            loginResDto.setToken(StpUtil.getTokenValue());
            loginResDto.setMsg("成功");
            return loginResDto;
        }else {
            loginResDto.setMsg("密码不正确");
        }
        return loginResDto;
    }

    @Override
    public void outLogin() {
        StpUtil.logout();
    }

    @Override
    public LoginResDto register(LoginReqDto reqDto) {
        LambdaQueryWrapper<UserPo> userPoQueryWrapper = new LambdaQueryWrapper<>();
        userPoQueryWrapper.eq(UserPo::getUserName,reqDto.getUserName());
        UserPo userPo = userMapper.selectOne(userPoQueryWrapper);

        if(userPo != null){
            throw new TestHubException("用户名已存在");
        }
        userPo = new UserPo();
        userPo.setCode(reqDto.getUserName());
        userPo.setUserName(reqDto.getUserName());
        userPo.setPassword(BCrypt.hashpw(reqDto.getPassword()));
        userMapper.insert(userPo);
        LoginResDto loginResDto = LoginResDto.builder()
                .userName(reqDto.getUserName())
                .password(reqDto.getPassword())
                .build();
        return loginResDto;
    }
}
