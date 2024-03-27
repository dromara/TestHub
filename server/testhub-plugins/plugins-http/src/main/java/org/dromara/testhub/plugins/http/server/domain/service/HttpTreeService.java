package org.dromara.testhub.plugins.http.server.domain.service;


import org.dromara.testhub.plugins.http.server.dto.*;
import org.dromara.testhub.sdk.action.dto.res.TreeNodeResDto2;

import java.util.Map;


public interface HttpTreeService {
    Map<String, TreeNodeResDto2> getTree(String projectCode);

    HttpApiResDto getOne(Long id);

    TreeNodeResDto2 rename(HttpRenameDto renameDto);

    HttpApiResDto saveApi(HttpApiReqDto reqDto, String model);
    TreeNodeResDto2 saveDir(HttpDirDto reqDto, String model);

    HttpApiSendResDto send(HttpApiReqDto reqDto) throws Exception;
}
