package org.dromara.testhub.server.interfaces.api;


import org.dromara.testhub.server.core.rule.CodeGenerateManager;
import org.dromara.testhub.framework.web.ResultResponse;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/system")
@Slf4j
public class SystemController {
    @Autowired
    private CodeGenerateManager codeGenerateManager;

    /**
     * 检测是否成功
     *
     * @return
     */
    @GetMapping
    public ResultResponse<String> get() {
        return ResultResponse.ok();
    }

    /**
     * 获取当前版本号
     *
     * @return
     */
    @GetMapping("/get-version-a")
    public ResultResponse<String> getVersion() {
        return ResultResponse.ok("1.0.0");
    }


    @PostMapping(value = "/getCode/{type}", consumes = {"application/json"}, produces = {"application/json"})
    public ResultResponse<String> getCode(@ApiParam(value = "类型", required = true) @PathVariable("type") String type) {
        return ResultResponse.ok(codeGenerateManager.getCode(type));
    }

}
