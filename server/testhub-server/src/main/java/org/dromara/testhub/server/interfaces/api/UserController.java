package org.dromara.testhub.server.interfaces.api;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.dromara.testhub.server.domain.dto.req.other.LoginReqDto;
import org.dromara.testhub.server.domain.dto.res.other.CurrentUserResDto;
import org.dromara.testhub.server.domain.dto.res.other.LoginResDto;
import org.dromara.testhub.framework.web.ResultResponse;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.dromara.testhub.server.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Slf4j
@Api(tags = {"用户"})
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "登陆", tags = {"用户"}, nickname = "login")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "000000:成功，否则失败")})
    @PostMapping(value = "/login", consumes = {"application/json"}, produces = {"application/json"})
    public ResultResponse<LoginResDto> login(
            @ApiParam(value = "用户信息", required = true) @Valid @RequestBody LoginReqDto reqDto) {
        LoginResDto resDto = userService.login(reqDto);
        return ResultResponse.ok(resDto);
    }

    @ApiOperation(value = "退出登陆", tags = {"用户"}, nickname = "outLogin")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "000000:成功，否则失败")})
    @PostMapping(value = "/outLogin", produces = {"application/json"})
    public ResultResponse<Void> outLogin() {
        userService.outLogin();
        return ResultResponse.ok();
    }

    @SaCheckRole("admin")
    @ApiOperation(value = "注册用户", tags = {"用户"}, nickname = "register")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "000000:成功，否则失败")})
    @PostMapping(value = "/register", consumes = {"application/json"}, produces = {"application/json"})
    public ResultResponse<LoginResDto> register(
            @ApiParam(value = "用户信息", required = true) @Valid @RequestBody LoginReqDto reqDto) {
        return ResultResponse.ok(userService.register(reqDto));
    }

    @ApiOperation(value = "验证是否登陆", tags = {"用户"}, nickname = "checkLogin")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "000000:成功，否则失败")})
    @GetMapping(value = "/checkLogin", produces = {"application/json"})
    public ResultResponse<SaResult> checkLogin() {
        return ResultResponse.ok(StpUtil.isLogin());
    }


    @ApiOperation(value = "获取当前用户", tags = {"用户"}, nickname = "currentUser")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "000000:成功，否则失败")})
    @GetMapping(value = "/currentUser", produces = {"application/json"})
    public ResultResponse<CurrentUserResDto> currentUser() {
        LoginResDto resDto = new LoginResDto();
        resDto.setUserName("admin");
        resDto.setPassword("admin");
        resDto.setMsg("获取当前用户");
        return ResultResponse.ok(resDto);
    }
}
