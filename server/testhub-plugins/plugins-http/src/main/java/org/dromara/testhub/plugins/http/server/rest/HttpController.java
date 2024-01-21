package org.dromara.testhub.plugins.http.server.rest;

import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.dromara.testhub.framework.web.ResultResponse;
import org.dromara.testhub.plugins.http.server.dto.*;
import org.dromara.testhub.plugins.http.server.domain.service.HttpTreeService;
import org.dromara.testhub.sdk.action.dto.res.TreeNodeResDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author yetier
 */
@Slf4j
@Api(tags = {"插件http"})
@RestController
@RequestMapping("/plugin/http")
public class HttpController {

    @Autowired
    private HttpTreeService httpTreeService;

    @ApiOperation(value = "发送", tags = {"插件http"}, nickname = "sendApi")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "000000:成功，否则失败")})
    @PostMapping(value = "/api/send",consumes = {"application/json"}, produces = {"application/json"})
    public ResultResponse<HttpApiSendResDto> sendApi(@Valid @RequestBody HttpTreeNodeReqDto reqDto)throws Exception {
        return ResultResponse.ok(httpTreeService.send(reqDto));
    }

    @ApiOperation(value = "获取HTTP树", tags = {"插件http"}, nickname = "getTree")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作是否成功,000000:成功，否则失败")})
    @GetMapping(value = "/getTree/{projectCode}", produces = {"application/json"})
    public ResultResponse<List<TreeNodeResDto>> getTree(@ApiParam(value = "项目编码", required = true)
                                              @PathVariable("projectCode")String projectCode) {
        return ResultResponse.ok(httpTreeService.getTree(projectCode));
    }

    @ApiOperation(value = "获取树节点信息", tags = {"插件http"}, nickname = "getOne")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作是否成功,000000:成功，否则失败")})
    @GetMapping(value = "/getOne/{id}", produces = {"application/json"})
    public ResultResponse<HttpTreeNodeResDto> getOne(@ApiParam(value = "id", required = true)
                                             @PathVariable("id")Long id) {
        return ResultResponse.ok(httpTreeService.getOne(id));
    }

    @ApiOperation(value = "保存树", tags = {"插件http"}, nickname = "save")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "000000:成功，否则失败")})
    @PostMapping(value = "/save",consumes = {"application/json"}, produces = {"application/json"})
    public ResultResponse<TreeNodeResDto> save(@Valid @RequestBody HttpTreeNodeReqDto reqDto) {
        return ResultResponse.ok(httpTreeService.save(reqDto));
    }



}
