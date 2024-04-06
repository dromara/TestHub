package org.dromara.testhub.server.interfaces.api;

import cn.dev33.satoken.annotation.SaCheckRole;
import org.dromara.testhub.server.domain.dto.req.other.RuleTreeReqDto;
import org.dromara.testhub.server.domain.dto.req.rule.*;
import org.dromara.testhub.server.domain.dto.res.ExecuteResult.ExecutionResult;
import org.dromara.testhub.server.domain.dto.res.rule.*;
import org.dromara.testhub.server.domain.service.ProjectService;
import org.dromara.testhub.framework.web.ResultResponse;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Slf4j
@Api(tags = {"项目"})
@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;


    @SaCheckRole("user")
    @ApiOperation(value = "查询项目列表", tags = {"项目"}, nickname = "getProjectList")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作是否成功,000000:成功，否则失败")})
    @GetMapping(value = "/simpleList", produces = {"application/json"})
    public ResultResponse<List<RuleProjectSimpleResDto>> getProjectList() {
        List<RuleProjectSimpleResDto> list = projectService.getProjectList();
        return ResultResponse.ok(list);
    }

    @SaCheckRole("user")
    @ApiOperation(value = "查询项目详细信息", tags = {"项目"}, nickname = "getProject")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作是否成功,000000:成功，否则失败")})
    @GetMapping(value = "/one/{projectCode}", produces = {"application/json"})
    public ResultResponse<RuleProjectResDto> getProject(
            @ApiParam(value = "项目编码", required = true) @PathVariable("projectCode") String projectCode) {
        return ResultResponse.ok(projectService.getProject(projectCode));
    }

    @ApiOperation(value = "添加行为", tags = {"项目"}, nickname = "addAction")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作是否成功,000000:成功，否则失败")})
    @PostMapping(value = "/addAction", produces = {"application/json"})
    public ResultResponse<RuleActionResDto> addAction(@Valid @RequestBody RuleActionReqDto actionReqDto) {
        return ResultResponse.ok(projectService.saveAction(actionReqDto,false));
    }
    @ApiOperation(value = "添加行为", tags = {"项目"}, nickname = "updateAction")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作是否成功,000000:成功，否则失败")})
    @PostMapping(value = "/updateAction", produces = {"application/json"})
    public ResultResponse<RuleActionResDto> updateAction(@Valid @RequestBody RuleActionReqDto actionReqDto) {
        return ResultResponse.ok(projectService.saveAction(actionReqDto,true));
    }

    @SaCheckRole("admin")
    @ApiOperation(value = "添加环境", tags = {"项目"}, nickname = "addEnvironment")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作是否成功,000000:成功，否则失败")})
    @PostMapping(value = "/addEnvironment", produces = {"application/json"})
    public ResultResponse<RuleEnvironmentResDto> addEnvironment(@Valid @RequestBody RuleEnvironmentReqDto environmentReqDto) {
        return ResultResponse.ok(projectService.saveEnvironment(environmentReqDto,false));
    }
    @SaCheckRole("admin")
    @ApiOperation(value = "更新环境", tags = {"项目"}, nickname = "updateEnvironment")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作是否成功,000000:成功，否则失败")})
    @PostMapping(value = "/updateEnvironment", produces = {"application/json"})
    public ResultResponse<RuleEnvironmentResDto> updateEnvironment(@Valid @RequestBody RuleEnvironmentReqDto environmentReqDto) {
        return ResultResponse.ok(projectService.saveEnvironment(environmentReqDto,true));
    }

    @SaCheckRole("admin")
    @ApiOperation(value = "删除环境", tags = {"项目"}, nickname = "delEnvironment")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作是否成功,000000:成功，否则失败")})
    @PostMapping(value = "/delEnvironment", produces = {"application/json"})
    public ResultResponse<Boolean> updateEnvironment(@Valid @RequestBody RuleDelEnvironmentReqDto environmentReqDto) {
        projectService.delEnvironment(environmentReqDto);
        return ResultResponse.ok(true);
    }

    @SaCheckRole("user")
    @ApiOperation(value = "查询项目下规则列表", tags = {"项目"}, nickname = "getProjectRules")
    @ApiImplicitParams({@ApiImplicitParam(name = "qp-code-eq", value = "编码", paramType = "query", dataTypeClass = String.class),
            @ApiImplicitParam(name = "qp-name-eq", value = "名称", paramType = "query", dataTypeClass = String.class),})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作是否成功,000000:成功，否则失败")})
    @GetMapping(value = "/getProjectRules/{projectCode}", produces = {"application/json"})
    public ResultResponse<List<RuleResDto>> getProjectRules(@ApiParam(value = "项目编码", required = true) @PathVariable("projectCode") String projectCode,
                                                     @ApiIgnore @RequestParam Map<String, Object> params) {
        List<RuleResDto> resDtos = projectService.getRules(projectCode,params);
        return ResultResponse.ok(resDtos);
    }

//    @SaCheckRole("user")
//    @ApiOperation(value = "保存或更新规则", tags = {"项目"}, nickname = "saveRuleDocument")
//    @ApiResponses(value = {@ApiResponse(code = 200, message = "000000:成功，否则失败")})
//    @PostMapping(value = "/saveRuleDocument/{model}", consumes = {"application/json"}, produces = {"application/json"})
//    public ResultResponse<RuleResDto> saveRuleDocument(@ApiParam(value = "模式", required = true) @PathVariable("model") String model, @Valid @RequestBody RuleDocumentReqDto documentReqDto) {
//        return ResultResponse.ok(projectService.saveRuleDocument(documentReqDto, model));
//    }

//    @SaCheckRole("user")
//    @ApiOperation(value = "更新规则类目", tags = {"项目"}, nickname = "saveRuleTree")
//    @ApiResponses(value = {@ApiResponse(code = 200, message = "000000:成功，否则失败")})
//    @PostMapping(value = "/saveRuleTree", consumes = {"application/json"}, produces = {"application/json"})
//    public ResultResponse<RuleResDto> saveRuleTree( @Valid @RequestBody RuleTreeReqDto ruleTreeReqDto) {
//        return ResultResponse.ok(projectService.saveRuleTree(ruleTreeReqDto));
//    }
//
//    @SaCheckRole("user")
//    @ApiOperation(value = "格式化内容", tags = {"项目"}, nickname = "formatXml")
//    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作是否成功,000000:成功，否则失败")})
//    @PostMapping(value = "/formatXml", produces = {"application/json"})
//    public ResultResponse<String> formatXml(@RequestBody RuleDocumentReqDto documentReqDto) {
//        return ResultResponse.ok(projectService.formatXml(documentReqDto.getDocumentStr()));
//    }
//
//    @SaCheckRole("user")
//    @ApiOperation(value = "解析规则", tags = {"项目"}, nickname = "parserXml")
//    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作是否成功,000000:成功，否则失败")})
//    @PostMapping(value = "/parserXml", produces = {"application/json"})
//    public ResultResponse<RuleResDto> parserXml(@RequestBody RuleDocumentReqDto documentReqDto) {
//        return ResultResponse.ok(projectService.parserXml(documentReqDto));
//    }
//
//    @SaCheckRole("user")
//    @ApiOperation(value = "执行XML", tags = {"项目"}, nickname = "executionXml")
//    @ApiResponses(value = {@ApiResponse(code = 200, message = "000000:成功，否则失败")})
//    @PostMapping(value = "/executionXml", consumes = {"application/json"}, produces = {"application/json"})
//    public ResultResponse<ExecutionResult> executionXml(@RequestBody ExecutionXmlReqDto executionXmlReqDto)throws Exception {
//        return ResultResponse.ok(projectService.executionXml(executionXmlReqDto));
//    }

}
