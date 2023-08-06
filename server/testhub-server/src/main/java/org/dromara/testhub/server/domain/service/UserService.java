package org.dromara.testhub.server.domain.service;


import org.dromara.testhub.server.domain.dto.all.http.HttpReqDto;
import org.dromara.testhub.server.domain.dto.all.http.edit.HttpInfoBaseDto;
import org.dromara.testhub.server.domain.dto.req.other.LoginReqDto;
import org.dromara.testhub.server.domain.dto.res.other.LoginResDto;

public interface UserService {
    LoginResDto login(LoginReqDto reqDto);
    void outLogin();

    LoginResDto register(LoginReqDto reqDto);

}
