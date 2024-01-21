package org.dromara.testhub.plugins.http.server.domain.service;


import org.dromara.testhub.plugins.http.server.dto.*;
import org.dromara.testhub.sdk.action.dto.res.TreeNodeResDto;

import java.util.List;


public interface HttpTreeService {
    List<TreeNodeResDto> getTree(String projectCode);

    HttpTreeNodeResDto getOne(Long id);

    TreeNodeResDto save(HttpTreeNodeReqDto reqDto);
    HttpApiSendResDto send(HttpTreeNodeReqDto reqDto) throws Exception;
}
