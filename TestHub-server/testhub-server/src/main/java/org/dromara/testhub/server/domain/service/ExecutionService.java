package org.dromara.testhub.server.domain.service;

import org.dromara.testhub.server.domain.dto.req.rule.ExecutionXmlReqDto;
import org.dromara.testhub.server.domain.dto.res.ExecuteResult.ExecutionResult;

public interface ExecutionService {
    ExecutionResult executionXml(ExecutionXmlReqDto executionXmlReqDto) throws Exception;
}
