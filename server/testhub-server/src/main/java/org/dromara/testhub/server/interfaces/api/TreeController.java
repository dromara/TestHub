package org.dromara.testhub.server.interfaces.api;

import org.dromara.testhub.server.domain.dto.req.other.TreeInfoReqDto;
import org.dromara.testhub.server.domain.dto.res.other.TreeNodeResDto;
import org.dromara.testhub.server.domain.service.TreeService;
import org.dromara.testhub.framework.web.ResultResponse;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Api(tags = {"类目树"})
@RestController
@RequestMapping("/api/tree")
public class TreeController {
    @Autowired
    private TreeService treeService;

    @ApiOperation(value = "获取项目中的树", tags = {"类目树"}, nickname = "getByTreeType")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "操作是否成功,000000:成功，否则失败")})
    @GetMapping(value = "/getByTreeType/{treeType}", produces = {"application/json"})
    public ResultResponse<List<TreeNodeResDto>> getByTreeType(@ApiParam(value = "树类型", required = true) @PathVariable("treeType") String treeType) {
        List<TreeNodeResDto> list = treeService.getByTreeType(treeType);
        return ResultResponse.ok(list);
    }

    @ApiOperation(value = "保存树", tags = {"类目树"}, nickname = "saveTree")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "000000:成功，否则失败")})
    @PostMapping(consumes = {"application/json"}, produces = {"application/json"})
    public ResultResponse<TreeNodeResDto> saveTree(@Valid @RequestBody TreeInfoReqDto treeInfoReqDto) {
        return ResultResponse.ok(treeService.save(treeInfoReqDto));
    }

    @ApiOperation(value = "更新树", tags = {"类目树"}, nickname = "updateTree")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "000000:成功，否则失败")})
    @PostMapping(value = "/{id}", consumes = {"application/json"}, produces = {"application/json"})
    public ResultResponse<TreeNodeResDto> updateTree(@ApiParam(value = "树ID", required = true) @PathVariable("id") Long id,@Valid @RequestBody TreeInfoReqDto treeInfoReqDto) {
        return ResultResponse.ok(treeService.update(id,treeInfoReqDto));
    }



}
