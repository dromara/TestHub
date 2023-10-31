package org.dromara.testhub.http.server.convert;

import org.dromara.testhub.http.server.dto.HttpTreeReqDto;
import org.dromara.testhub.http.server.repository.po.HttpTreeNodePo;
import org.dromara.testhub.sdk.dto.res.TreeNodeResDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


/**
 * @author yetier
 */
@Mapper(componentModel="spring")
public interface HttpTreeNodeConvert {

    HttpTreeNodePo req2po(HttpTreeReqDto reqDto);

    @Mapping(source = "id", target = "key")
    @Mapping(source = "parentId", target = "parentKey")
    TreeNodeResDto po2Res(HttpTreeNodePo po);
}

