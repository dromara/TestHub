package org.dromara.testhub.server.interfaces.api;

import cn.dev33.satoken.annotation.SaCheckRole;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.dromara.testhub.framework.web.ResultResponse;
import org.dromara.testhub.sdk.action.dto.res.TreeNodeResDto2;
import org.dromara.testhub.server.domain.dto.req.other.RenameDto;
import org.dromara.testhub.server.domain.dto.req.rule.ExecutionXmlReqDto;
import org.dromara.testhub.server.domain.dto.req.rule.RuleDocumentReqDto;
import org.dromara.testhub.server.domain.dto.res.ExecuteResult.ExecutionResult;
import org.dromara.testhub.server.domain.dto.res.rule.RuleResDto;
import org.dromara.testhub.server.domain.service.CaseService;
import org.dromara.testhub.server.domain.service.ExecutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Api(tags = {"用例"})
@RestController
@RequestMapping("/case")
public class CaseController {

    @Autowired
    private CaseService caseService;
    @Autowired
    private ExecutionService executionService;

    @ApiOperation(value = "重命名", tags = {"用例"}, nickname = "rename")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "000000:成功，否则失败")})
    @PostMapping(value = "/rename",consumes = {"application/json"}, produces = {"application/json"})
    public ResultResponse<TreeNodeResDto2> rename(@Valid @RequestBody RenameDto reqDto) {
        return ResultResponse.ok(caseService.rename(reqDto));
    }

    @ApiOperation(value = "获取树节点信息", tags = {"用例"}, nickname = "getOne")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作是否成功,000000:成功，否则失败")})
    @GetMapping(value = "/getOne/{code}", produces = {"application/json"})
    public ResultResponse<RuleResDto> getOne(@ApiParam(value = "code", required = true)
                                                @PathVariable("code")String code) {
        return ResultResponse.ok(caseService.getOne(code));
    }



    @SaCheckRole("user")
    @ApiOperation(value = "保存或更新规则", tags = {"项目"}, nickname = "saveRuleDocument")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "000000:成功，否则失败")})
    @PostMapping(value = "/saveRuleDocument/{model}", consumes = {"application/json"}, produces = {"application/json"})
    public ResultResponse<RuleResDto> saveRuleDocument(@ApiParam(value = "模式", required = true) @PathVariable("model") String model, @Valid @RequestBody RuleDocumentReqDto documentReqDto) {
        return ResultResponse.ok(caseService.saveRuleDocument(documentReqDto, model));
    }

    @ApiOperation(value = "格式化内容", tags = {"项目"}, nickname = "formatXml")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作是否成功,000000:成功，否则失败")})
    @PostMapping(value = "/formatXml", produces = {"application/json"})
    public ResultResponse<String> formatXml(@RequestBody RuleDocumentReqDto documentReqDto) {
        return ResultResponse.ok(caseService.formatXml(documentReqDto.getDocumentStr()));
    }

    @SaCheckRole("user")
    @ApiOperation(value = "解析规则", tags = {"项目"}, nickname = "parserXml")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作是否成功,000000:成功，否则失败")})
    @PostMapping(value = "/parserXml", produces = {"application/json"})
    public ResultResponse<RuleResDto> parserXml(@RequestBody RuleDocumentReqDto documentReqDto) {
        return ResultResponse.ok(caseService.parserXml(documentReqDto));
    }

    @SaCheckRole("user")
    @ApiOperation(value = "执行XML", tags = {"项目"}, nickname = "executionXml")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "000000:成功，否则失败")})
    @PostMapping(value = "/executionXml", consumes = {"application/json"}, produces = {"application/json"})
    public ResultResponse<ExecutionResult> executionXml(@RequestBody ExecutionXmlReqDto executionXmlReqDto)throws Exception {
        return ResultResponse.ok(executionService.executionXml(executionXmlReqDto));
    }

}
