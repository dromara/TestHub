package org.dromara.testhub.plugins.http.server.domain.service;


import org.dromara.testhub.plugins.http.server.dto.HttpTreeReqDto;
import org.dromara.testhub.sdk.action.dto.res.TreeNodeResDto;

import java.util.List;


public interface HttpTreeService {
    List<TreeNodeResDto> getTree(String projectCode);
    TreeNodeResDto save(HttpTreeReqDto reqDto);
    TreeNodeResDto update(Long id,HttpTreeReqDto reqDto);
}
