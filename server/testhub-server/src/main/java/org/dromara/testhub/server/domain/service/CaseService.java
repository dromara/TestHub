package org.dromara.testhub.server.domain.service;

import org.dromara.testhub.sdk.action.dto.res.TreeNodeResDto2;
import org.dromara.testhub.server.domain.dto.req.other.RenameDto;
import org.dromara.testhub.server.domain.dto.req.other.RuleTreeReqDto;
import org.dromara.testhub.server.domain.dto.req.rule.*;
import org.dromara.testhub.server.domain.dto.res.ExecuteResult.ExecutionResult;
import org.dromara.testhub.server.domain.dto.res.rule.*;

import java.util.List;
import java.util.Map;

public interface CaseService {
    TreeNodeResDto2 rename(RenameDto renameDto);

    RuleResDto getOne(String code);

    String formatXml(String documentStr);

    RuleResDto parserXml(RuleDocumentReqDto documentReqDto);

    RuleResDto saveRuleDocument(RuleDocumentReqDto documentReqDto, String model);




}
