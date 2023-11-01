package org.dromara.testhub.server.domain.convert;

import cn.hutool.core.collection.CollectionUtil;
import com.goddess.nsrule.core.constant.Constant;
import com.goddess.nsrule.core.executer.context.RuleConfig;
import com.goddess.nsrule.core.executer.context.RuleProject;
import com.goddess.nsrule.core.executer.meta.MetaClass;
import com.goddess.nsrule.core.executer.meta.MetaProperty;
import com.goddess.nsrule.core.executer.mode.Rule;
import com.goddess.nsrule.core.executer.mode.base.action.Execute;
import com.goddess.nsrule.core.executer.mode.base.action.Inject;
import com.goddess.nsrule.core.executer.mode.base.action.Mapping;
import com.goddess.nsrule.core.executer.mode.base.action.Param;
import com.goddess.nsrule.flow.model.Flow;
import com.goddess.nsrule.flow.model.RuleFlow;
import org.dromara.testhub.framework.util.DateUtil;
import org.dromara.testhub.sdk.action.Plugin;
import org.dromara.testhub.sdk.action.PluginFactory;
import org.dromara.testhub.sdk.action.model.rule.TestHubAction;
import org.dromara.testhub.sdk.action.model.rule.TestHubExecute;
import org.dromara.testhub.server.core.rule.CacheManager;
import org.dromara.testhub.server.domain.dto.res.rule.*;
import org.dromara.testhub.server.infrastructure.repository.po.RulePo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RuleConvertor {
    public static RuleResDto ruleModel2Res(Rule model) {
        return ruleModel2Res(model, false, false, false, false);
    }

    public static RuleResDto ruleModelAll2Res(Rule model) {
        return ruleModel2Res(model, true, true, true, true);
    }
    public static RuleResDto ruleModelAll2Res(Rule model,RulePo rulePo) {
        RuleResDto ruleResDto =  ruleModel2Res(model, true, true, true, true);
        ruleResDto.setLastModifyTime(rulePo.getModifyTime());
        ruleResDto.setModifyTime(rulePo.getModifyTime());
        ruleResDto.setModifyUserId(rulePo.getModifyUserId());
        ruleResDto.setCreateUserId(rulePo.getCreateUserId());
        ruleResDto.setCreateTime(rulePo.getCreateTime());
        return ruleResDto;
    }

    public static RuleParamResDto paramModel2Res(Param param) {
        RuleParamResDto ruleParamResDto = new RuleParamResDto();
        ruleParamResDto.setId(param.getId());
        ruleParamResDto.setCode(param.getCode());
        ruleParamResDto.setName(param.getName());
        ruleParamResDto.setComplex(param.getComplex());
        ruleParamResDto.setNecessary(param.isNecessary());
        ruleParamResDto.setDataType(param.getDataType());
        ruleParamResDto.setData(param.getData());
        return ruleParamResDto;
    }
    public static List<RuleParamResDto> paramModel2ResList(List<Param> params) {
        List<RuleParamResDto> resDto = new ArrayList<>();
        for(Param param:params){
            resDto.add(paramModel2Res(param));
        }
        return resDto;
    }

    private static RulePropertyResDto metaPropertyModel2Res(MetaProperty metaProperty) {
        RulePropertyResDto propertyResDto = new RulePropertyResDto();
        propertyResDto.setCode(metaProperty.getCode());
        propertyResDto.setName(metaProperty.getName());
        propertyResDto.setDataType(metaProperty.getDataType());
        propertyResDto.setComplex(metaProperty.getComplex());
        propertyResDto.setIsComplex(metaProperty.getComplex() != null && metaProperty.getComplex() > 0 ? true : false);
        return propertyResDto;
    }

    private static RuleMetaClassResDto metaClassModel2Res(MetaClass metaClass) {
        RuleMetaClassResDto metaClassResDto = new RuleMetaClassResDto();
        metaClassResDto.setCode(metaClass.getCode());
        metaClassResDto.setName(metaClass.getName());
        List<RulePropertyResDto> propertyResDtos = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(metaClass.getProperties())) {
            Collection<MetaProperty> metaProperties = metaClass.getProperties();
            long ii = 0;
            for (MetaProperty metaProperty : metaProperties) {
                RulePropertyResDto propertyResDto = metaPropertyModel2Res(metaProperty);
                propertyResDto.setId(ii);
                propertyResDtos.add(propertyResDto);
                ii++;
            }
        }
        metaClassResDto.setPropertyResDtos(propertyResDtos);
        return metaClassResDto;
    }

    private static RuleResDto ruleModel2Res(Rule model, boolean paramsFlag, boolean metaClassFlag, boolean actionFlag, boolean flowFlag) {
        RuleResDto resDto = new RuleResDto();
        resDto.setId(model.getId());
        resDto.setCode(model.getCode());
        resDto.setName(model.getName());
        resDto.setModel(model.getModel());
        resDto.setProject(model.getProject());
        resDto.setFileName(model.getSource());

        resDto.setLastModifyTime(CacheManager.getFile(model.getCode()) == null ? LocalDateTime.now() : DateUtil.getDateTimeOfTimestamp(CacheManager.getFile(model.getCode()).lastModified()));
        boolean canRun = true;
        if (paramsFlag) {
            List<RuleParamResDto> params = new ArrayList<>();
            List<Param> params1 = model.getParams();
            long i = 0;
            for (Param param : params1) {
                RuleParamResDto ruleParamResDto = paramModel2Res(param);
                ruleParamResDto.setId(i);
                params.add(ruleParamResDto);
                if (ruleParamResDto.getNecessary()) {
                    canRun = false;
                }
                i++;
            }
            resDto.setParams(params);
        }
        resDto.setCanRun(canRun);
        if (metaClassFlag) {
            List<RuleMetaClassResDto> metaClasses = new ArrayList<>();
            if (CollectionUtil.isNotEmpty(model.getMetaClassMap())) {
                Collection<MetaClass> metaClasses1 = model.getMetaClassMap().values();
                long i = 0;
                for (MetaClass metaClass : metaClasses1) {
                    RuleMetaClassResDto metaClassResDto = metaClassModel2Res(metaClass);
                    metaClassResDto.setId(i);
                    metaClasses.add(metaClassResDto);
                    i++;
                }

            }
            resDto.setMetaClasses(metaClasses);
        }
        if (actionFlag) {
            List<RuleActionResDto> actionResDtos = new ArrayList<>();
            if (CollectionUtil.isNotEmpty(model.getActionMap())) {
                Collection<TestHubAction> actions = model.getActionMap().values();
                long i = 0;
                for (TestHubAction action : actions) {
                    RuleActionResDto actionResDto = ruleActionModel2Res(action);
                    actionResDto.setId(i);
                    actionResDtos.add(actionResDto);
                    i++;
                }
                resDto.setActions(actionResDtos);
            }
            resDto.setActions(actionResDtos);
        }
        if (flowFlag && Constant.RuleModel.FLOW.equalsIgnoreCase(model.getModel())) {
            List<RuleFlowResDto> flowResDtos = new ArrayList<>();
            if (CollectionUtil.isNotEmpty(((RuleFlow) model).getFlows())) {
                Collection<Flow> flows = ((RuleFlow) model).getFlows();
                long i = 0;
                for (Flow flow : flows) {

                    RuleFlowResDto ruleFlowResDto = ruleFlowModel2Res(model, flow);
                    ruleFlowResDto.setId(i);
                    flowResDtos.add(ruleFlowResDto);
                    i++;
                }
            }
            resDto.setFlows(flowResDtos);
        }

        return resDto;
    }

    public static RuleActionResDto ruleActionModel2Res(TestHubAction model) {
        RuleActionResDto resDto = new RuleActionResDto();
        resDto.setId(model.getId());
        resDto.setCode(model.getCode());
        resDto.setName(model.getName());
        resDto.setType(model.getType());
        resDto.setDataType(model.getDataType());
        resDto.setComplex(model.getComplex());

        List<RuleParamResDto> params = new ArrayList<>();
        List<Param> paramList = model.getParams();
        long i = 0;
        for (Param param : paramList) {
            RuleParamResDto ruleParamResDto = paramModel2Res(param);
            ruleParamResDto.setId(i);
            params.add(ruleParamResDto);
            i++;
        }
        resDto.setParams(params);

        List<RuleMappingResDto> mappingResDtos = new ArrayList<>();
        List<Mapping> mappings = model.getMappings();
        for (Mapping mapping : mappings) {
            RuleMappingResDto ruleMappingResDto = ruleMappingModel2Res(mapping);
            ruleMappingResDto.setId(i);
            mappingResDtos.add(ruleMappingResDto);
            i++;
        }
        resDto.setMappings(mappingResDtos);

        Plugin plugin = PluginFactory.getHandler(model.getType());
        resDto.setExtraInto(plugin.getJsonActionParser().model2json(model));
//        resDto.setExtraInto(plugin.getDTOConvertor().model2Res(model));

        return resDto;
    }

    public static RuleMappingResDto ruleMappingModel2Res(Mapping mapping) {
        RuleMappingResDto resDto = new RuleMappingResDto();
        resDto.setCode(mapping.getCode());
        resDto.setResult(mapping.getResult());
        return resDto;
    }

    public static RuleExecuteResDto ruleExecuteModel2Res(Rule rule, TestHubExecute model) {
        RuleExecuteResDto resDto = new RuleExecuteResDto();
        resDto.setCode(model.getCode());
        resDto.setName(model.getName());
        resDto.setActionCode(model.getActionCode());
//        resDto.setPlanErrorCode(model.getErrorCode());
//        resDto.setSleepTime(model.getSleepTime());
        resDto.setBlock(model.isBlock());

        long i = 0;
        List<RuleInjectResDto> resDtos = new ArrayList<>();
        List<Inject> injects = model.getInjects();
        for (Inject inject : injects) {
            RuleInjectResDto injectResDto = ruleInjectModel2Res(inject);
            injectResDto.setId(i);
            resDtos.add(injectResDto);
            i++;
        }
        resDto.setInjects(resDtos);

        TestHubAction testHubAction = (TestHubAction) rule.getActionMap().get(model.getActionCode());
        if (testHubAction == null) {
            RuleProject ruleProject = RuleConfig.getInstance().getProject(rule.getProject());
            testHubAction = (TestHubAction) ruleProject.getAction(model.getActionCode());
        }

        Plugin plugin = PluginFactory.getHandler(testHubAction.getType());
        resDto.setExtraInto(plugin.getDTOConvertor().model2Res(model));


//        List<RuleCheckObjResDto> checkObjs = new ArrayList<>();
//        if (CollectionUtil.isNotEmpty(model.getCheckObjs())) {
//            for (CheckObj checkObj : model.getCheckObjs()) {
//                RuleCheckObjResDto checkObjResDto = ruleCheckObjModel2Res(checkObj);
//                checkObjResDto.setId(i);
//                checkObjs.add(checkObjResDto);
//                i++;
//            }
//            resDto.setCheckObjs(checkObjs);
//        }
        return resDto;
    }


//
//    public static RuleCheckObjResDto ruleCheckObjModel2Res(CheckObj checkObj) {
//        RuleCheckObjResDto resDto = new RuleCheckObjResDto();
//        resDto.setCode(checkObj.getCode());
//        resDto.setName(checkObj.getName());
//        resDto.setMsg(checkObj.getMsg());
//        resDto.setCover(checkObj.getCover());
//        resDto.setThreshold(checkObj.getThreshold());
//        return resDto;
//    }


    public static RuleInjectResDto ruleInjectModel2Res(Inject inject) {
        RuleInjectResDto resDto = new RuleInjectResDto();
        resDto.setCode(inject.getCode());
        resDto.setData(inject.getData());
        return resDto;
    }

    public static RuleFlowResDto ruleFlowModel2Res(Rule rule, Flow model) {
        RuleFlowResDto flowResDto = new RuleFlowResDto();
        flowResDto.setCode(model.getCode());
        flowResDto.setName(model.getName());

        long i = 0;
        List<RuleExecuteResDto> executeResDtos = new ArrayList<>();
        List<Execute> executes = model.getExecutes();
        for (Execute execute : executes) {
            RuleExecuteResDto executeResDto = ruleExecuteModel2Res(rule, (TestHubExecute) execute);
            executeResDto.setId(i);
            executeResDtos.add(executeResDto);
            i++;
        }
        flowResDto.setExecutes(executeResDtos);
        return flowResDto;
    }


    public static RuleCodeResDto ruleModel2CodeRes(Rule model) {
        RuleCodeResDto resDto = new RuleCodeResDto();
        resDto.setCode(model.getCode());
        resDto.setName(model.getName());
        resDto.setModel(model.getModel());
        resDto.setProject(model.getProject());
        resDto.setFileName(model.getSource());
        return resDto;
    }
}
