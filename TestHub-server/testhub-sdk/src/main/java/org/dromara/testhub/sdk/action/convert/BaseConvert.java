package org.dromara.testhub.sdk.action.convert;

import org.dromara.testhub.nsrule.core.executer.mode.base.action.Param;
import org.dromara.testhub.sdk.action.dto.RuleParamReqDto;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel="spring")
public interface BaseConvert {

    List<Param> paramsReq2Mode(List<RuleParamReqDto> params);
}

