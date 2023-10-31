package org.dromara.testhub.http.server.service;


import org.dromara.testhub.http.server.dto.HttpTreeReqDto;
import org.dromara.testhub.sdk.dto.res.TreeNodeResDto;


public interface HttpTreeService {
    TreeNodeResDto save(HttpTreeReqDto reqDto);
    TreeNodeResDto update(Long id,HttpTreeReqDto reqDto);
}
