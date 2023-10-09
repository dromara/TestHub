package org.dromara.testhub.server.domain.service;

import org.dromara.testhub.server.domain.dto.req.rule.*;
import org.dromara.testhub.server.domain.dto.res.ExecuteResult.ExecutionResult;
import org.dromara.testhub.server.domain.dto.req.other.RuleTreeReqDto;
import org.dromara.testhub.server.domain.dto.res.rule.*;

import java.util.List;
import java.util.Map;

public interface ProjectService {
    RuleEnvironmentResDto saveEnvironment(RuleEnvironmentReqDto environmentReqDto,boolean updateFlag);
    void delEnvironment(RuleDelEnvironmentReqDto environmentReqDto);

    RuleProjectResDto getProject(String projectCode);

    List<RuleProjectSimpleResDto> getProjectList();

    List<RuleResDto> getRules(String projectCode, Map<String, Object> params);

    String formatXml(String documentStr);

    RuleResDto parserXml(RuleDocumentReqDto documentReqDto);

    ExecutionResult executionXml(ExecutionXmlReqDto executionXmlReqDto) throws Exception;

    RuleResDto saveRuleTree(RuleTreeReqDto ruleTreeReqDto);

    RuleResDto saveRuleDocument(RuleDocumentReqDto documentReqDto, String model);
}
