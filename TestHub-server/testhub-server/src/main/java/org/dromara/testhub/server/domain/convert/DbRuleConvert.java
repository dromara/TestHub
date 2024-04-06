package org.dromara.testhub.server.domain.convert;

import com.alibaba.fastjson.JSONObject;
import org.dromara.testhub.nsrule.core.executer.meta.MetaClass;
import org.dromara.testhub.nsrule.core.executer.meta.MetaEnum;
import org.dromara.testhub.nsrule.core.executer.meta.MetaProperty;
import org.dromara.testhub.nsrule.core.executer.mode.base.action.*;
import org.dromara.testhub.nsrule.flow.model.Flow;
import org.dromara.testhub.nsrule.flow.model.RuleFlow;
import org.dromara.testhub.sdk.action.Plugin;
import org.dromara.testhub.sdk.action.PluginFactory;
import org.dromara.testhub.sdk.action.model.rule.TestHubAction;
import org.dromara.testhub.sdk.action.model.rule.TestHubExecute;
import org.apache.commons.lang3.StringUtils;
import org.dromara.testhub.server.infrastructure.repository.po.*;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel="spring")
public interface DbRuleConvert {
    default TestHubAction actionPo2model(ActionPo po){
        Plugin plugin = PluginFactory.getHandler(po.getType());
        TestHubAction testHubAction = new TestHubAction();
        testHubAction.setType(po.getType());
        testHubAction.setId(po.getId());
        testHubAction.setName(po.getName());
        testHubAction.setComplex(po.getComplex());
        testHubAction.setCode(po.getCode());
        testHubAction.setDataType(po.getDataType());
        if(StringUtils.isEmpty(po.getExtendInfo())){
            return plugin.getJsonActionParser().json2Model(new JSONObject(), testHubAction);
        }else {
            return plugin.getJsonActionParser().json2Model(JSONObject.parseObject(po.getExtendInfo()), testHubAction);
        }
    };
    default TestHubExecute executePo2model(ExecutePo po, TestHubAction action){
        Plugin plugin = PluginFactory.getHandler(action.getType());
        TestHubExecute testHubExecute = new TestHubExecute();
        testHubExecute.setActionCode(po.getActionCode());
        testHubExecute.setId(po.getId());
        testHubExecute.setName(po.getName());
        testHubExecute.setCode(po.getCode());
        testHubExecute.setBlock(po.getBlock());
        testHubExecute.setInit(po.getInit());
        if(StringUtils.isNotEmpty(po.getExtendInfo())){
            return plugin.getJsonExecuteParser().json2model(JSONObject.parseObject(po.getExtendInfo()),testHubExecute, action);
        } else {
            return plugin.getJsonExecuteParser().json2model(new JSONObject(), testHubExecute, action);
        }
    };

    List<Param> paramPos2models(List<ParamPo> pos);
    Param paramPo2model(ParamPo po);

    List<Mapping> mappingPos2models(List<MappingPo> pos);
    Mapping mappingPo2model(MappingPo po);

    @org.mapstruct.Mapping(target = "items", ignore = true)
    List<MetaEnum> metaEnumPos2models(List<MetaEnumPo> pos);
    @org.mapstruct.Mapping(target = "items", ignore = true)
    MetaEnum metaEnumPo2model(MetaEnumPo po);

    List<MetaClass> metaClassPos2models(List<MetaClassPo> pos);
    MetaClass metaClassPo2model(MetaClassPo po);

    List<MetaProperty> metaPropertyPos2models(List<MetaPropertyPo> pos);
    MetaProperty metaPropertyPo2model(MetaPropertyPo po);

    RuleFlow rulePo2model(RulePo po);

    Flow flowPo2model(FlowPo po);
    List<Flow> flowsPo2models(List<FlowPo> pos);
    List<Inject> injectPos2models(List<InjectPo> pos);






    RulePo ruleModel2Po(RuleFlow model);
    List<ParamPo> paramModel2Pos(List<Param> params);

    @org.mapstruct.Mapping(target = "items", ignore = true)
    List<MetaEnumPo> metaEnumModel2Pos(List<MetaEnum> metaEnums);

    List<MetaClassPo> metaClassModel2Pos(List<MetaClass> metaClasses);
    MetaClassPo metaClassModel2Po(MetaClass metaClass);
    List<MetaPropertyPo> metaPropertyModel2Pos(List<MetaProperty> metaProperties);
    List<MappingPo> mappingModel2Pos(List<Mapping> mappings);
    List<InjectPo> injectModel2Pos(List<Inject> injects);
    FlowPo flowModel2Po(Flow flow);


    default ActionPo actionModel2Po(TestHubAction model){
        Plugin plugin = PluginFactory.getHandler(model.getType());
        ActionPo actionPo = new ActionPo();
        actionPo.setType(model.getType());
        actionPo.setId(model.getId());
        actionPo.setName(model.getName());
        actionPo.setComplex(model.getComplex());
        actionPo.setCode(model.getCode());
        actionPo.setDataType(model.getDataType());
        JSONObject extendInfo = plugin.getJsonActionParser().model2json(model);
        actionPo.setExtendInfo(extendInfo!=null?extendInfo.toJSONString():null);
        return actionPo;
    };
    default ExecutePo executeModel2Po(TestHubExecute model,TestHubAction action){
        Plugin plugin = PluginFactory.getHandler(action.getType());
        ExecutePo executePo = new ExecutePo();
        executePo.setActionCode(model.getActionCode());
        executePo.setId(model.getId());
        executePo.setName(model.getName());
        executePo.setCode(model.getCode());
        executePo.setBlock(model.isBlock());
        executePo.setInit(model.isInit());
        JSONObject extendInfo = plugin.getJsonExecuteParser().model2json(model,action);
        executePo.setExtendInfo(extendInfo!=null?extendInfo.toJSONString():null);
        return executePo;
    };
}

