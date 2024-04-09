package org.dromara.testhub.server.domain.service.impl;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import org.dromara.testhub.server.infrastructure.repository.dao.UserMapper;
import org.dromara.testhub.server.infrastructure.repository.po.UserPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义权限加载接口实现类
 */
@Component
public class StpInterfaceImpl implements StpInterface {
    @Autowired
    private UserMapper userMapper;

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        List<String> list = new ArrayList<String>();
        return list;
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        List<String> list = new ArrayList<String>();
        UserPo userPo = userMapper.selectById(Long.parseLong(loginId.toString()));
        if(userPo == null){
            return list;
        }
        list.add("user");
        if(userPo.getUserName().equals("admin")){
            list.add("admin");
        }
        return list;
    }
}
