package org.dromara.testhub.plugins.http.server.service;


import org.dromara.testhub.plugins.http.server.dto.HttpTreeReqDto;
import org.dromara.testhub.sdk.action.dto.res.TreeNodeResDto;


public interface HttpTreeService {
    TreeNodeResDto save(HttpTreeReqDto reqDto);
    TreeNodeResDto update(Long id,HttpTreeReqDto reqDto);
}
