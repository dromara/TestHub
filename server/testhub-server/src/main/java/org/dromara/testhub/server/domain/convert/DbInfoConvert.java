package org.dromara.testhub.server.domain.convert;

import com.alibaba.fastjson.JSONObject;
import com.goddess.nsrule.core.executer.meta.MetaClass;
import com.goddess.nsrule.core.executer.meta.MetaEnum;
import com.goddess.nsrule.core.executer.meta.MetaProperty;
import com.goddess.nsrule.core.executer.mode.base.action.Inject;
import com.goddess.nsrule.core.executer.mode.base.action.Mapping;
import com.goddess.nsrule.core.executer.mode.base.action.Param;
import com.goddess.nsrule.flow.model.Flow;
import com.goddess.nsrule.flow.model.RuleFlow;
import org.apache.commons.lang3.StringUtils;
import org.dromara.testhub.sdk.Plugin;
import org.dromara.testhub.sdk.PluginFactory;
import org.dromara.testhub.sdk.model.rule.TestHubAction;
import org.dromara.testhub.sdk.model.rule.TestHubExecute;
import org.dromara.testhub.server.domain.dto.req.rule.RuleParamReqDto;
import org.dromara.testhub.server.infrastructure.repository.po.*;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel="spring")
public interface DbInfoConvert {

    List<ParamPo> paramsReq2Po(List<RuleParamReqDto> params);
}

