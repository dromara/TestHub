package org.dromara.testhub.server.interfaces.api;

import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.dromara.testhub.framework.web.ResultResponse;
import org.dromara.testhub.plugins.http.server.dto.HttpDirDto;
import org.dromara.testhub.sdk.action.dto.req.TreeDirDto;
import org.dromara.testhub.sdk.action.dto.res.TreeNodeResDto;
import org.dromara.testhub.sdk.action.dto.res.TreeNodeResDto2;
import org.dromara.testhub.server.domain.dto.req.other.RenameDto;
import org.dromara.testhub.server.domain.dto.req.other.TreeInfoReqDto;
import org.dromara.testhub.server.domain.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Slf4j
@Api(tags = {"类目树"})
@RestController
@RequestMapping("/tree")
public class TreeController {
    @Autowired
    private TreeService treeService;

    @ApiOperation(value = "获取HTTP树", tags = {"插件http"}, nickname = "getTree")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作是否成功,000000:成功，否则失败")})
    @GetMapping(value = "/getTree/{projectCode}", produces = {"application/json"})
    public ResultResponse<Map<String, TreeNodeResDto2>> getTree(@ApiParam(value = "项目编码", required = true)
                                                                @PathVariable("projectCode")String projectCode) {
        return ResultResponse.ok(treeService.getCaseTree(projectCode));
    }
    @ApiOperation(value = "保存树", tags = {"类目树"}, nickname = "saveTree")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "000000:成功，否则失败")})
    @PostMapping(value = "/saveTree/{model}",consumes = {"application/json"}, produces = {"application/json"})
    public ResultResponse<TreeNodeResDto> saveTree(@Valid @RequestBody TreeDirDto reqDto, @PathVariable("model")String model) {
        return ResultResponse.ok(treeService.saveCaseTree(reqDto,model));
    }

    @ApiOperation(value = "更新树", tags = {"类目树"}, nickname = "updateTree")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "000000:成功，否则失败")})
    @PostMapping(value = "/{id}", consumes = {"application/json"}, produces = {"application/json"})
    public ResultResponse<TreeNodeResDto> updateTree(@ApiParam(value = "树ID", required = true) @PathVariable("id") Long id,@Valid @RequestBody TreeInfoReqDto treeInfoReqDto) {
        return ResultResponse.ok(treeService.update(id,treeInfoReqDto));
    }

    @ApiOperation(value = "重命名", tags = {"类目树"}, nickname = "rename")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "000000:成功，否则失败")})
    @PostMapping(value = "/rename",consumes = {"application/json"}, produces = {"application/json"})
    public ResultResponse<TreeNodeResDto2> rename(@Valid @RequestBody RenameDto reqDto) {
        return ResultResponse.ok(treeService.rename(reqDto));
    }
}
