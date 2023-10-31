package org.dromara.testhub.server.domain.convert;

import org.dromara.testhub.server.infrastructure.repository.po.TreeInfoPo;
import org.dromara.testhub.server.domain.dto.req.other.TreeInfoReqDto;
import org.dromara.testhub.sdk.dto.res.TreeNodeResDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel="spring")
public interface TreeInfoConvert {

    TreeInfoPo req2po(TreeInfoReqDto reqDto);

    @Mapping(source = "id", target = "key")
    @Mapping(source = "parentId", target = "parentKey")
    TreeNodeResDto po2Res(TreeInfoPo po);
}

