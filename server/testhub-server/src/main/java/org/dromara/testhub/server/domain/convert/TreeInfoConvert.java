package org.dromara.testhub.server.domain.convert;

import org.dromara.testhub.plugins.http.server.dto.HttpDirDto;
import org.dromara.testhub.plugins.http.server.repository.po.HttpTreeNodePo;
import org.dromara.testhub.plugins.http.server.util.TreeUtil;
import org.dromara.testhub.sdk.action.dto.req.TreeDirDto;
import org.dromara.testhub.server.infrastructure.repository.po.TreeInfoPo;
import org.dromara.testhub.server.domain.dto.req.other.TreeInfoReqDto;
import org.dromara.testhub.sdk.action.dto.res.TreeNodeResDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel="spring")
public interface TreeInfoConvert {

    default TreeInfoPo reqDir2po(TreeDirDto reqDto){
        TreeInfoPo po = new TreeInfoPo();
        po.setId(reqDto.getId());
        po.setName(reqDto.getName());
        po.setNodeType(TreeUtil.TYPE_DIR);
        po.setParentId(reqDto.getParentId());
        return po;
    }

    TreeInfoPo req2po(TreeInfoReqDto reqDto);

    @Mapping(source = "id", target = "key")
    @Mapping(source = "parentId", target = "parentKey")
    TreeNodeResDto po2Res(TreeInfoPo po);
}

