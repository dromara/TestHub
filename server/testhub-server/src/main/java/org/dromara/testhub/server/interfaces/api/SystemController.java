package org.dromara.testhub.server.interfaces.api;


import io.swagger.annotations.*;
import org.dromara.testhub.server.core.rule.CodeGenerateManager;
import org.dromara.testhub.framework.web.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.dromara.testhub.server.domain.dto.res.other.VersionResDto;
import org.dromara.testhub.server.domain.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

}
