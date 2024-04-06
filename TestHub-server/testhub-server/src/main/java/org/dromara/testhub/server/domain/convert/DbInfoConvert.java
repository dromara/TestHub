package org.dromara.testhub.server.domain.convert;

import org.dromara.testhub.sdk.action.dto.RuleParamReqDto;
import org.dromara.testhub.server.infrastructure.repository.po.*;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel="spring")
public interface DbInfoConvert {

    List<ParamPo> paramsReq2Po(List<RuleParamReqDto> params);
}

