package org.dromara.testhub.plugins.http.server.domain.service;


import org.dromara.testhub.plugins.http.core.HttpModel;
import org.dromara.testhub.plugins.http.server.dto.HttpApiReqDto;
import org.dromara.testhub.plugins.http.server.dto.HttpApiResDto;
import org.dromara.testhub.plugins.http.server.dto.HttpApiSendResDto;
import org.dromara.testhub.plugins.http.server.dto.HttpTreeReqDto;
import org.dromara.testhub.sdk.action.dto.res.TreeNodeResDto;

import java.util.List;


public interface HttpApiService {
    HttpApiSendResDto send(HttpApiReqDto reqDto) throws Exception;
    TreeNodeResDto save(HttpApiReqDto reqDto);
    HttpApiResDto getApi(Long id);
}
