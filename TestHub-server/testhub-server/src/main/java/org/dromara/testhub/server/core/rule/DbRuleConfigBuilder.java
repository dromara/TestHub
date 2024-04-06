package org.dromara.testhub.server.core.rule;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.dromara.testhub.nsrule.core.constant.ExceptionCode;
import org.dromara.testhub.nsrule.core.constant.RuleException;
import org.dromara.testhub.nsrule.core.executer.context.RuleConfig;
import org.dromara.testhub.nsrule.core.executer.context.RuleProject;
import org.dromara.testhub.nsrule.core.executer.meta.MetaClass;
import org.dromara.testhub.nsrule.core.executer.meta.MetaEnum;
import org.dromara.testhub.nsrule.core.executer.meta.MetaProperty;
import org.dromara.testhub.nsrule.core.executer.mode.Rule;
import org.dromara.testhub.nsrule.core.executer.mode.base.action.Action;
import org.dromara.testhub.nsrule.core.executer.mode.base.action.Execute;
import org.dromara.testhub.nsrule.core.executer.mode.base.action.Param;
import org.dromara.testhub.nsrule.core.parser.RuleConfigBuilder;
import org.dromara.testhub.nsrule.flow.model.Flow;
import org.dromara.testhub.nsrule.flow.model.RuleFlow;
import org.dromara.testhub.nsrule.parserXml.XmlActionDefaultParser;
import org.dromara.testhub.nsrule.parserXml.XmlExecuteDefaultParser;
import org.dromara.testhub.sdk.action.model.rule.TestHubAction;
import org.dromara.testhub.sdk.action.parser.TestHubActionParser;
import org.dromara.testhub.sdk.action.parser.TestHubExecuteParser;
import org.dromara.testhub.server.infrastructure.repository.po.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 数据库构建 RuleConfig
 */
@Slf4j
public class DbRuleConfigBuilder implements RuleConfigBuilder<DbRuleManager> {
    @Override
    public RuleConfig build(DbRuleManager dbRuleManager) throws Exception {

        //初始化
        RuleConfig ruleConfig = RuleConfig.getInstance();

        //解析自定义 actionParser解析器

        ruleConfig.setActionParser(new XmlActionDefaultParser(new TestHubActionParser()));

        //解析自定义 executeParser解析器
        ruleConfig.setExecuteParser(new XmlExecuteDefaultParser(new TestHubExecuteParser()));

        //加载系统级 行为
        List<ActionPo> actionPos = dbRuleManager.getSystemActions();

        //解析系统级 行为、映射器
        List<Action> actions = parseActions(dbRuleManager,ruleConfig,null,actionPos, new HashMap<>(),"系统级");
        ruleConfig.setActions(actions);

        // 解析项目配置
        List<RuleProject> projects = parseProjects(dbRuleManager, ruleConfig, actions);

        ruleConfig.setProjectMap(projects);

        return ruleConfig;
    }



    //解析项目
    public static List<RuleProject> parseProjects(DbRuleManager dbRuleManager, RuleConfig ruleConfig, List<Action> globalActions) throws Exception {
        List<RuleProject> projects = new ArrayList<>();
        List<ProjectPo> projectPos = dbRuleManager.getProjectPos();

        if (CollectionUtil.isEmpty(projectPos)) {
            RuleProject project = new RuleProject(ruleConfig);
            project.setCode("default");
            project.setName("默认项目");
            projects.add(project);
            return projects;
        }

        List<Long> projectIds = projectPos.stream().map(ProjectPo::getId).distinct().toList();

        //加载项目级 枚举
        List<MetaEnumPo> metaEnumPos = dbRuleManager.getMetaEnumPos(Constant.OwnerType.PROJECT, projectIds);
        Map<Long, List<MetaEnumPo>> metaEnumPoMap = CollectionUtil.isEmpty(metaEnumPos) ? new HashMap<>() : metaEnumPos.stream().collect(Collectors.groupingBy(MetaEnumPo::getOwnerId));

        //加载项目级 元数据
        List<MetaClassPo> metaClassPos = dbRuleManager.getMetaClassPos(Constant.OwnerType.PROJECT, projectIds);
        Map<Long, List<MetaClassPo>> metaClassPoMap = CollectionUtil.isEmpty(metaClassPos) ? new HashMap<>() : metaClassPos.stream().collect(Collectors.groupingBy(MetaClassPo::getOwnerId));

        //加载项目级 元数据属性项
        List<MetaPropertyPo> metaPropertyPos = dbRuleManager.getMetaPropertyPos(metaClassPos);
        Map<Long, List<MetaPropertyPo>> metaPropertyPoMap = CollectionUtil.isEmpty(metaPropertyPos) ? new HashMap<>() : metaPropertyPos.stream().collect(Collectors.groupingBy(MetaPropertyPo::getMetaClassId));

        //加载项目级 行为
        List<ActionPo> actionPos = dbRuleManager.getActions(Constant.OwnerType.PROJECT, projectIds);
        Map<Long, List<ActionPo>> actionPoMap = CollectionUtil.isEmpty(actionPos) ? new HashMap<>() : actionPos.stream().collect(Collectors.groupingBy(ActionPo::getOwnerId));

        //加载项目级 环境
        List<EnvironmentPo> environmentPos = dbRuleManager.getEnvironmentPos();
        Map<Long, List<EnvironmentPo>> environmentPoMap = CollectionUtil.isEmpty(environmentPos) ? new HashMap<>() : environmentPos.stream().collect(Collectors.groupingBy(EnvironmentPo::getProjectId));

        //加载项目级环境  参数
        List<Long> environmentIds = environmentPos.stream().map(EnvironmentPo::getId).distinct().toList();
        List<ParamPo> paramPos = dbRuleManager.getParamPos(environmentIds);
        Map<Long, List<ParamPo>> paramPoMap = CollectionUtil.isEmpty(paramPos) ? new HashMap<>() : paramPos.stream().collect(Collectors.groupingBy(ParamPo::getOwnerId));

        for (ProjectPo projectPo : projectPos) {
            RuleProject project = new RuleProject(ruleConfig);
            projects.add(project);

            project.setId(projectPo.getId());
            project.setCode(projectPo.getCode());
            project.setName(projectPo.getName());


            for(EnvironmentPo environmentPo:environmentPoMap.getOrDefault(projectPo.getId(),new ArrayList<>())){
                project.putGlobalParams(environmentPo.getCode(),dbRuleManager.getDbRuleConvert().paramPos2models(paramPoMap.getOrDefault(environmentPo.getId(),new ArrayList<>())));
            }

            project.setMetaEnums(parseMetaEnums(dbRuleManager, metaEnumPoMap.get(projectPo.getId())));

            project.setMetaClasses(parseMetaClass(dbRuleManager, metaClassPoMap.get(projectPo.getId()), metaPropertyPoMap));

            List<Action> actions = parseActions(dbRuleManager, ruleConfig,project, actionPoMap.get(projectPo.getId()), new HashMap<>(),"项目:" + projectPo.getName());
            //处理项目中的行为覆盖系统行为的问题
            List<String> actionCodes = actions.stream().map(Action::getCode).toList();
            for(Action action:globalActions){
                if(!actionCodes.contains(action.getCode())){
                    actions.add(action);
                }
            }
            project.setActions(actions);

            // 解析规则配置
            List<Rule> rules = parseRules(dbRuleManager, ruleConfig, project);

            for(Rule rule:rules){
                ruleConfig.addRule(rule);
                project.addRule(rule);
            }
        }

        return projects;

    }

    public static List<Rule> parseRules(DbRuleManager dbRuleManager, RuleConfig ruleConfig,  RuleProject project) {
        List<Rule> rules = new ArrayList<>();
        List<RulePo> rulePos = dbRuleManager.getRulePos(project.getCode());
        if (CollectionUtil.isEmpty(rulePos)) {
            return rules;
        }
        List<Long> ids = rulePos.stream().map(RulePo::getId).distinct().toList();

        //加载规则级 参数
        List<ParamPo> paramPos = dbRuleManager.getParamPos(ids);
        Map<Long, List<ParamPo>> paramPoMap = CollectionUtil.isEmpty(paramPos) ? new HashMap<>() : paramPos.stream().collect(Collectors.groupingBy(ParamPo::getOwnerId));

        //加载规则级 枚举
        List<MetaEnumPo> metaEnumPos = dbRuleManager.getMetaEnumPos(Constant.OwnerType.RULE, ids);
        Map<Long, List<MetaEnumPo>> metaEnumPoMap = CollectionUtil.isEmpty(metaEnumPos) ? new HashMap<>() : metaEnumPos.stream().collect(Collectors.groupingBy(MetaEnumPo::getOwnerId));

        //加载规则级 元数据
        List<MetaClassPo> metaClassPos = dbRuleManager.getMetaClassPos(Constant.OwnerType.RULE, ids);
        Map<Long, List<MetaClassPo>> metaClassPoMap = CollectionUtil.isEmpty(metaClassPos) ? new HashMap<>() : metaClassPos.stream().collect(Collectors.groupingBy(MetaClassPo::getOwnerId));

        //加载规则级 元数据属性项
        List<MetaPropertyPo> metaPropertyPos = dbRuleManager.getMetaPropertyPos(metaClassPos);
        Map<Long, List<MetaPropertyPo>> metaPropertyPoMap = CollectionUtil.isEmpty(metaPropertyPos) ? new HashMap<>() : metaPropertyPos.stream().collect(Collectors.groupingBy(MetaPropertyPo::getMetaClassId));

        //加载规则级 行为
        List<ActionPo> actionPos = dbRuleManager.getActions(Constant.OwnerType.RULE, ids);
        Map<Long, List<ActionPo>> actionPoMap = CollectionUtil.isEmpty(actionPos) ? new HashMap<>() : actionPos.stream().collect(Collectors.groupingBy(ActionPo::getOwnerId));

        //流程列表
        List<FlowPo> flowPos = dbRuleManager.getFlowPos(rulePos);
        Map<String, List<FlowPo>> flowPoMap = CollectionUtil.isEmpty(flowPos) ? new HashMap<>() : flowPos.stream().collect(Collectors.groupingBy(FlowPo::getRuleCode));

        //步骤列表
        List<ExecutePo> executePos = dbRuleManager.getExecutePos(flowPos);
        Map<Long, List<ExecutePo>> executePoMap = CollectionUtil.isEmpty(executePos) ? new HashMap<>() : executePos.stream().collect(Collectors.groupingBy(ExecutePo::getOwnerId));

        //注入列表
        List<InjectPo> injectPos = dbRuleManager.getInjectPos(executePos);
        Map<Long, List<InjectPo>> injectPoMap = CollectionUtil.isEmpty(injectPos) ? new HashMap<>() : injectPos.stream().collect(Collectors.groupingBy(InjectPo::getExecuteId));


        for (RulePo rulePo:rulePos){
            RuleFlow rule = dbRuleManager.getDbRuleConvert().rulePo2model(rulePo);
            try {

                rule.setParams(dbRuleManager.getDbRuleConvert().paramPos2models(paramPoMap.getOrDefault(rulePo.getId(),new ArrayList<>())));
                rule.setMetaEnums(parseMetaEnums(dbRuleManager, metaEnumPoMap.get(rulePo.getId())));
                rule.setMetaClasses(parseMetaClass(dbRuleManager, metaClassPoMap.get(rulePo.getId()), metaPropertyPoMap));

                rule.setActions(parseActions(dbRuleManager, ruleConfig,project, actionPoMap.get(rulePo.getId()),rule.getMetaClassMap() ,"规则:" + rulePo.getName()));

                rule.setProject(project.getCode());

                List<Flow> flows = new ArrayList<>();
                for (FlowPo flowPo:flowPoMap.get(rulePo.getCode())){
                    Flow flow = dbRuleManager.getDbRuleConvert().flowPo2model(flowPo);
                    List<Execute> executes = new ArrayList<>();
                    for (ExecutePo executePo:executePoMap.getOrDefault(flowPo.getId(),new ArrayList<>())){
                        Execute execute = dbRuleManager.getDbRuleConvert().executePo2model(executePo,getAction(project,rule,executePo.getActionCode()));
                        execute.setInjects(dbRuleManager.getDbRuleConvert().injectPos2models(injectPoMap.getOrDefault(executePo.getId(),new ArrayList<>())));
                        executes.add(execute);
                    }
                    flow.setExecutes(executes);
                    flows.add(flow);
                }
                rule.setFlows(flows);
                rules.add(rule);
            }catch (Exception exception){
                log.error("解析:{}失败",rule.getCode()+rule.getName(),exception);
            }

        }

        return rules;
    }
    private static TestHubAction getAction( RuleProject project,RuleFlow ruleFlow,String actionCode){
        if(ruleFlow.getActionMap().get(actionCode)==null){
            if(project.getAction(actionCode)==null){
                throw new RuleException(ruleFlow.getCode()+"中找不到行为"+actionCode);
            }else {
                return (TestHubAction) project.getAction(actionCode);
            }
        }else {
            return (TestHubAction) ruleFlow.getActionMap().get(actionCode);
        }
    }

    //解析 元对象 属性项
    public static List<MetaClass> parseMetaClass(DbRuleManager dbRuleManager,List<MetaClassPo> metaClassPos,Map<Long, List<MetaPropertyPo>> metaPropertyPoMap){
        if (CollectionUtil.isEmpty(metaClassPos)) {
            return new ArrayList<>();
        }
        return convertMetaClasses(dbRuleManager,metaClassPos, metaPropertyPoMap);
    }
    //解析 参数
    public static List<Param> parseParams(DbRuleManager dbRuleManager, List<Long> ids){
        if (CollectionUtil.isEmpty(ids)) {
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }

    //解析行为
    public static List<MetaEnum> parseMetaEnums(DbRuleManager dbRuleManager,List<MetaEnumPo> metaEnumPos){
        if (CollectionUtil.isEmpty(metaEnumPos)) {
            return new ArrayList<>();
        }
        return convertMetaEnums(dbRuleManager, metaEnumPos);
    }

    //解析行为
    public static List<Action> parseActions(DbRuleManager dbRuleManager, RuleConfig ruleConfig,RuleProject project, List<ActionPo> actionPos,Map<String, MetaClass> metaClassMap, String msg) {
        if (CollectionUtil.isEmpty(actionPos)) {
            return new ArrayList<>();
        }

        //加载映射器
        List<MappingPo> mappingPos = dbRuleManager.getMappingPos(actionPos);
        Map<Long, List<MappingPo>> mappingPoMap = CollectionUtil.isEmpty(mappingPos) ? new HashMap<>() : mappingPos.stream().collect(Collectors.groupingBy(MappingPo::getActionId));

        //解析行为、映射器
        List<Action> actions = convertActions(dbRuleManager, ruleConfig, project,actionPos,mappingPoMap,metaClassMap, msg);

        return actions;
    }

    //转换枚举
    public static List<MetaEnum> convertMetaEnums(DbRuleManager dbRuleManager, List<MetaEnumPo> metaEnumPos) {
        if (CollectionUtil.isEmpty(metaEnumPos)) {
            return new ArrayList<>();
        }
        List<MetaEnum> metaEnums = new ArrayList<>();
        for (MetaEnumPo metaEnumPo : metaEnumPos) {
            MetaEnum metaEnum = dbRuleManager.getDbRuleConvert().metaEnumPo2model(metaEnumPo);
            metaEnum.items = new ArrayList<>();
            List<MetaEnum.Item> items = JSONObject.parseArray(metaEnumPo.getItemStr(), MetaEnum.Item.class);
            metaEnum.items = items;
            metaEnums.add(metaEnum);
        }
        return metaEnums;
    }

    //转换元对象
    public static List<MetaClass> convertMetaClasses(DbRuleManager dbRuleManager, List<MetaClassPo> metaClassPos, Map<Long, List<MetaPropertyPo>> metaPropertyMap) {
        List<MetaClass> metaClasses = new ArrayList<>();
        if (CollectionUtil.isEmpty(metaClassPos)) {
            return metaClasses;
        }
        for (MetaClassPo metaClassPo : metaClassPos) {
            MetaClass metaClass = dbRuleManager.getDbRuleConvert().metaClassPo2model(metaClassPo);
            List<MetaProperty> metaProperties = dbRuleManager.getDbRuleConvert().metaPropertyPos2models(metaPropertyMap.get(metaClassPo.getId()));
            metaClass.setProperties(metaProperties);
            metaClasses.add(metaClass);
        }
        return metaClasses;
    }

    //转换行为
    public static List<Action> convertActions(DbRuleManager dbRuleManager, RuleConfig ruleConfig,RuleProject project,  List<ActionPo> actionPos,
                                              Map<Long, List<MappingPo>> mappingMap,Map<String, MetaClass> metaClassMap, String msg) {
        List<Action> actions = new ArrayList<>();
        if (CollectionUtil.isEmpty(actionPos)) {
            return actions;
        }
        List<ParamPo> paramPos = dbRuleManager.getParamPos(actionPos.stream().map(ActionPo::getId).distinct().toList());
        Map<Long, List<ParamPo>> paramMap = new HashMap<>();

        if (CollectionUtil.isNotEmpty(paramPos)) {
            paramMap = paramPos.stream().collect(Collectors.groupingBy(ParamPo::getOwnerId));
        }

        for (ActionPo actionPo : actionPos) {
            Action action = dbRuleManager.getDbRuleConvert().actionPo2model(actionPo);
            actions.add(action);
            if (!ruleConfig.isInnerDataType(action.getDataType())) {
                //非基础数据类型
                if(project==null){
                    //系统级直接异常
                    throw new RuleException(ExceptionCode.EC_0109, msg, action.getCode(), action.getDataType());
                }
                if (project.getMetaClassByDataType(action.getDataType()) == null) {
                    //还不是全局的
                    if (!metaClassMap.containsKey(action.getDataType())) {
                        //看看规则中也没有
                        throw new RuleException(ExceptionCode.EC_0109, msg, action.getCode(), action.getDataType());
                    }
                }
            }
            action.setMappings(dbRuleManager.getDbRuleConvert().mappingPos2models(mappingMap.get(actionPo.getId())));
            action.setParams(dbRuleManager.getDbRuleConvert().paramPos2models(paramMap.get(actionPo.getId())));
        }
        return actions;
    }
}
