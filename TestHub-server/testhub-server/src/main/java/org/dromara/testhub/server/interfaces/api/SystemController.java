package org.dromara.testhub.server.interfaces.api;


import io.swagger.annotations.*;
import org.dromara.testhub.framework.util.IdGenerator;
import org.dromara.testhub.server.core.rule.CodeGenerateManager;
import org.dromara.testhub.framework.web.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.dromara.testhub.server.domain.dto.req.other.InfoReqDto;
import org.dromara.testhub.server.domain.dto.res.other.VersionResDto;
import org.dromara.testhub.sdk.action.dto.res.RuleParamResDto;
import org.dromara.testhub.server.domain.service.SettingService;
import org.dromara.testhub.server.domain.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Api(tags = {"系统"})
@RestController
@RequestMapping("/system")
@Slf4j
public class SystemController {
    @Autowired
    private CodeGenerateManager codeGenerateManager;

    @Autowired
    private SettingService settingService;

    @Autowired
    private SystemService systemService;

    @Autowired
    private IdGenerator idGenerator;


    @ApiOperation(value = "获取当前版本号", tags = {"系统"}, nickname = "getVersion")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作是否成功,000000:成功，否则失败")})
    @GetMapping(value = "/getVersion", produces = {"application/json"})
    public ResultResponse<VersionResDto> getVersion() {
        return ResultResponse.ok(settingService.getVersion());
    }


    @ApiOperation(value = "获取升级历史", tags = {"系统"}, nickname = "getHistoryVersion")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作是否成功,000000:成功，否则失败")})
    @GetMapping(value = "/getHistoryVersion", produces = {"application/json"})
    public ResultResponse<List<VersionResDto>> getHistoryVersion() {
        return ResultResponse.ok(settingService.getHistoryVersion());
    }

    /**
     * 检测是否成功
     *
     * @return
     */
    @GetMapping
    public ResultResponse<String> get() {
        return ResultResponse.ok();
    }

    @ApiOperation(value = "生成编码", tags = {"系统"}, nickname = "getCode")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作是否成功,000000:成功，否则失败")})
    @PostMapping(value = "/getCode/{type}", consumes = {"application/json"}, produces = {"application/json"})
    public ResultResponse<String> getCode(@ApiParam(value = "类型", required = true) @PathVariable("type") String type) {
        return ResultResponse.ok(codeGenerateManager.getCode(type));
    }

    @ApiOperation(value = "生成ID", tags = {"系统"}, nickname = "getId")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作是否成功,000000:成功，否则失败")})
    @GetMapping(value = "/getId", produces = {"application/json"})
    public ResultResponse<Long> getId() {
        return ResultResponse.ok(idGenerator.snowflakeId());
    }

    @ApiOperation(value = "参数json转xml", tags = {"系统"}, nickname = "paramsJson2Xml")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作是否成功,000000:成功，否则失败")})
    @PostMapping(value = "/paramsJson2Xml",consumes = {"application/json"}, produces = {"application/json"})
    public ResultResponse<String> paramsJson2Xml(@Valid @RequestBody InfoReqDto reqDto) {
        return ResultResponse.ok(systemService.paramsJson2Xml(reqDto.getInfo()));
    }

    @ApiOperation(value = "参数xml转json", tags = {"系统"}, nickname = "paramsXml2Json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作是否成功,000000:成功，否则失败")})
    @PostMapping(value = "/paramsXml2Json",consumes = {"application/json"}, produces = {"application/json"})
    public ResultResponse<List<RuleParamResDto>> paramsXml2Json(@Valid @RequestBody InfoReqDto reqDto) {
        return ResultResponse.ok(systemService.paramsXml2Json(reqDto.getInfo()));
    }

}
