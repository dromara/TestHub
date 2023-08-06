package org.dromara.testhub.server.core.rule;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.dromara.testhub.common.exception.TestHubException;
import org.dromara.testhub.server.domain.convert.DbRuleConvert;
import com.goddess.nsrule.core.executer.context.RuleProject;
import com.goddess.nsrule.core.executer.meta.MetaClass;
import com.goddess.nsrule.core.executer.meta.MetaEnum;
import com.goddess.nsrule.core.executer.meta.MetaProperty;
import com.goddess.nsrule.core.executer.mode.base.action.*;
import com.goddess.nsrule.flow.model.Flow;
import com.goddess.nsrule.flow.model.RuleFlow;
import org.dromara.testhub.common.util.IdGenerator;
import org.dromara.testhub.sdk.model.rule.TestHubAction;
import org.dromara.testhub.sdk.model.rule.TestHubExecute;
import com.google.common.collect.Lists;
import org.dromara.testhub.server.infrastructure.repository.dao.*;
import org.dromara.testhub.server.infrastructure.repository.po.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DbRuleManager {
    private int MAX_SIZE = 256;
    @Autowired
    private DbRuleConvert dbRuleConvert;
    @Autowired
    private IdGenerator idGenerator;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private EnvironmentMapper environmentMapper;
    @Autowired
    private ParamMapper paramMapper;
    @Autowired
    private ActionMapper actionMapper;
    @Autowired
    private MappingMapper mappingMapper;
    @Autowired
    private MetaEnumMapper metaEnumMapper;
    @Autowired
    private MetaClassMapper metaClassMapper;
    @Autowired
    private MetaPropertyMapper metaPropertyMapper;
    @Autowired
    private RuleMapper ruleMapper;
    @Autowired
    private FlowMapper flowMapper;
    @Autowired
    private ExecuteMapper executeMapper;
    @Autowired
    private InjectMapper injectMapper;
    @Autowired
    private UserMapper userMapper;

    public void setIdRuleInfo(RuleFlow rule) {
        //规则参数
        rule.getParams().forEach(param -> {
            param.setId(idGenerator.snowflakeId());
        });
        //规则枚举
        rule.getMetaEnums().forEach(param -> {
            param.setId(idGenerator.snowflakeId());
        });
        //规则 元对象 属性项
        rule.getMetaClasses().forEach(o -> {
            o.setId(idGenerator.snowflakeId());
            o.getProperties().forEach(o1 -> {
                o1.setId(idGenerator.snowflakeId());
            });
        });

        //规则 行为
        rule.getActions().forEach(action -> {
            action.setId(idGenerator.snowflakeId());
            action.getParams().forEach(param -> {
                param.setId(idGenerator.snowflakeId());
            });
            action.getMappings().forEach(mapping -> {
                mapping.setId(idGenerator.snowflakeId());
            });
        });

        //规则流程 步骤 注入
        rule.getFlows().forEach(o -> {
            o.setId(idGenerator.snowflakeId());
            o.getExecutes().forEach(o1 -> {
                o1.setId(idGenerator.snowflakeId());
                o1.getInjects().forEach(o2 -> {
                    o2.setId(idGenerator.snowflakeId());
                });
            });
        });
    }

    private void delete(RuleFlow rule) {
        //删除 非规则之外的一切
        List<Long> paramIds = rule.getParams().stream().map(Param::getId).collect(Collectors.toList());
        List<Long> metaEnumIds = rule.getMetaEnums().stream().map(MetaEnum::getId).toList();
        List<Long> metaClassIds = rule.getMetaClasses().stream().map(MetaClass::getId).toList();
        List<Long> metaPropertyIds = new ArrayList<>();
        for (MetaClass metaClass : rule.getMetaClasses()) {
            metaPropertyIds.addAll(metaClass.getProperties().stream().map(MetaProperty::getId).toList());
        }

        List<Long> mappingIds = new ArrayList<>();
        List<Long> actionIds = rule.getActions().stream().map(Action::getId).toList();
        for (Action action : rule.getActions()) {
            paramIds.addAll(action.getParams().stream().map(Param::getId).toList());
            mappingIds.addAll(action.getMappings().stream().map(Mapping::getId).toList());
        }

        List<Long> executeIds = new ArrayList<>();
        List<Long> injectIds = new ArrayList<>();
        List<Long> flowIds = rule.getFlows().stream().map(Flow::getId).toList();
        for (Flow flow : rule.getFlows()) {
            executeIds.addAll(flow.getExecutes().stream().map(Execute::getId).toList());
            for (Execute execute : flow.getExecutes()) {
                injectIds.addAll(execute.getInjects().stream().map(Inject::getId).toList());
            }
        }

        for (List<Long> ids : Lists.partition(paramIds, MAX_SIZE)) {
            paramMapper.physicsDeleteBatchIds(ids);
        }
        for (List<Long> ids : Lists.partition(metaEnumIds, MAX_SIZE)) {
            metaEnumMapper.physicsDeleteBatchIds(ids);
        }
        for (List<Long> ids : Lists.partition(metaClassIds, MAX_SIZE)) {
            metaClassMapper.physicsDeleteBatchIds(ids);
        }
        for (List<Long> ids : Lists.partition(metaPropertyIds, MAX_SIZE)) {
            metaPropertyMapper.physicsDeleteBatchIds(ids);
        }
        for (List<Long> ids : Lists.partition(actionIds, MAX_SIZE)) {
            actionMapper.physicsDeleteBatchIds(ids);
        }
        for (List<Long> ids : Lists.partition(mappingIds, MAX_SIZE)) {
            mappingMapper.physicsDeleteBatchIds(ids);
        }
        for (List<Long> ids : Lists.partition(flowIds, MAX_SIZE)) {
            flowMapper.physicsDeleteBatchIds(ids);
        }
        for (List<Long> ids : Lists.partition(executeIds, MAX_SIZE)) {
            executeMapper.physicsDeleteBatchIds(ids);
        }
        for (List<Long> ids : Lists.partition(injectIds, MAX_SIZE)) {
            injectMapper.physicsDeleteBatchIds(ids);
        }
    }

    public void updateRuleTreeId(RulePo rulePo){
        ruleMapper.updateById(rulePo);
    }

    public RulePo saveRule(RuleProject ruleProject, RuleFlow oldRule, RuleFlow rule, String fileContent,long treeId) {
        RulePo rulePo = dbRuleConvert.ruleModel2Po(rule);

        //删旧的 说明是新增
        if(oldRule!=null){
            RulePo dbRulePo = ruleMapper.selectById(rulePo.getId());
            if(dbRulePo.getCreateUserId()!=Long.parseLong(StpUtil.getLoginId().toString())){
                UserPo userPo = userMapper.selectById(dbRulePo.getCreateUserId());
                if(userPo==null){
                    throw new TestHubException("只能修改个人新增的用例");
                }else {
                    throw new TestHubException("只能修改个人新增的用例，请联系："+userPo.getUserName());
                }
            }
            delete(oldRule);
        }
        rulePo.setProjectCode(ruleProject.getCode());
        rulePo.setTreeId(treeId);
        rulePo.setFileContent(fileContent);
        //规则 参数
        List<ParamPo> paramPos = dbRuleConvert.paramModel2Pos(rule.getParams());
        paramPos.forEach(param -> {
            param.setOwnerType(Constant.OwnerType.RULE);
            param.setOwnerId(rulePo.getId());
        });

        //规则 枚举
        List<MetaEnumPo> metaEnumPos = dbRuleConvert.metaEnumModel2Pos(rule.getMetaEnums());
        metaEnumPos.forEach(o -> {
            o.setOwnerType(Constant.OwnerType.RULE);
            o.setOwnerId(rulePo.getId());
        });

        //规则 元对象  属性项
        List<MetaPropertyPo> metaPropertyPos = new ArrayList<>();
        List<MetaClassPo> metaClassPos = new ArrayList<>();
        for (MetaClass metaClass : rule.getMetaClasses()) {
            MetaClassPo metaClassPo = dbRuleConvert.metaClassModel2Po(metaClass);
            metaClassPo.setOwnerType(Constant.OwnerType.RULE);
            metaClassPo.setOwnerId(rulePo.getId());

            metaClassPos.add(metaClassPo);

            List<MetaPropertyPo> propertyPos = dbRuleConvert.metaPropertyModel2Pos(metaClass.getProperties());
            propertyPos.forEach(o -> {
                o.setMetaClassId(metaClassPo.getId());
            });

            metaPropertyPos.addAll(propertyPos);
        }

        //规则 行为
        List<ActionPo> actionPos = new ArrayList<>();
        // 映射器
        List<MappingPo> mappingPos = new ArrayList<>();
        for (Action action : rule.getActions()) {
            ActionPo actionPo = dbRuleConvert.actionModel2Po((TestHubAction) action);
            actionPo.setOwnerType(Constant.OwnerType.RULE);
            actionPo.setOwnerId(rulePo.getId());

            List<ParamPo> actionParamPos = dbRuleConvert.paramModel2Pos(action.getParams());
            actionParamPos.forEach(param -> {
                param.setOwnerType(Constant.OwnerType.ACTION);
                param.setOwnerId(actionPo.getId());
            });

            paramPos.addAll(actionParamPos);

            List<MappingPo> tempMappingPos = dbRuleConvert.mappingModel2Pos(action.getMappings());
            tempMappingPos.forEach(o -> {
                o.setActionId(actionPo.getId());
            });

            mappingPos.addAll(tempMappingPos);

            actionPos.add(actionPo);
        }

        //规则 流程 步骤 注入
        List<FlowPo> flowPos = new ArrayList<>();
        List<ExecutePo> executePos = new ArrayList<>();
        List<InjectPo> injectPos = new ArrayList<>();
        for (Flow flow : rule.getFlows()) {
            FlowPo flowPo = dbRuleConvert.flowModel2Po(flow);
            flowPo.setRuleCode(rule.getCode());
            flow.getExecutes().forEach(execute -> {

                Action action = rule.getAction(execute.getActionCode());
                if (action == null) {
                    action = ruleProject.getAction(execute.getActionCode());
                }
                //这里action不可能再空了，前边验证过了

                ExecutePo executePo = dbRuleConvert.executeModel2Po((TestHubExecute) execute, (TestHubAction) action);
                executePo.setOwnerType(Constant.OwnerType.FLOW);
                executePo.setOwnerId(flowPo.getId());
                executePo.setRuleCode(rule.getCode());

                List<InjectPo> tempInjectPos = dbRuleConvert.injectModel2Pos(execute.getInjects());
                tempInjectPos.forEach(o -> {
                    o.setRuleCode(rule.getCode());
                    o.setExecuteId(executePo.getId());
                });
                injectPos.addAll(tempInjectPos);
                executePos.add(executePo);
            });

            flowPos.add(flowPo);
        }
        if(oldRule!=null){
            ruleMapper.updateById(rulePo);
        }else {
            ruleMapper.insert(rulePo);
        }

        paramPos.forEach(data->{paramMapper.insert(data);});
        metaEnumPos.forEach(data->{metaEnumMapper.insert(data);});
        metaClassPos.forEach(data->{metaClassMapper.insert(data);});
        metaPropertyPos.forEach(data->{metaPropertyMapper.insert(data);});
        actionPos.forEach(data->{actionMapper.insert(data);});
        mappingPos.forEach(data->{mappingMapper.insert(data);});
        flowPos.forEach(data->{flowMapper.insert(data);});
        executePos.forEach(data->{executeMapper.insert(data);});
        injectPos.forEach(data->{injectMapper.insert(data);});

        return rulePo;

    }

    //获取项目列表
    public List<ProjectPo> getProjectPos() {
        return projectMapper.selectList(new QueryWrapper<>());
    }

    //获取项目下规则列表
    public List<RulePo> getRulePos(String projectCode) {
        QueryWrapper<RulePo> ruleQueryWrapper = new QueryWrapper<>();
        ruleQueryWrapper.eq("project_code", projectCode);
        return ruleMapper.selectList(ruleQueryWrapper);
    }

    //获取规则下流程列表
    public List<FlowPo> getFlowPos(List<RulePo> rulePos) {
        if (CollectionUtil.isEmpty(rulePos)) {
            return new ArrayList<>();
        }
        List<FlowPo> flowPos = new ArrayList<>();
        List<String> actionCodes = rulePos.stream().map(RulePo::getCode).distinct().toList();
        for (List<String> codes : Lists.partition(actionCodes, MAX_SIZE)) {
            QueryWrapper<FlowPo> flowQueryWrapper = new QueryWrapper<>();
            flowQueryWrapper.in("rule_code", codes);
            List<FlowPo> temps = flowMapper.selectList(flowQueryWrapper);
            flowPos.addAll(temps);
        }
        ;
        return flowPos;
    }

    //获取流程下步骤列表
    public List<ExecutePo> getExecutePos(List<FlowPo> flowPos) {
        if (CollectionUtil.isEmpty(flowPos)) {
            return new ArrayList<>();
        }
        List<ExecutePo> executePos = new ArrayList<>();
        List<Long> allIds = flowPos.stream().map(FlowPo::getId).toList();
        for (List<Long> ids : Lists.partition(allIds, MAX_SIZE)) {
            QueryWrapper<ExecutePo> executeQueryWrapper = new QueryWrapper<>();
            executeQueryWrapper.in("owner_id", ids);
            List<ExecutePo> temps = executeMapper.selectList(executeQueryWrapper);
            executePos.addAll(temps);
        }
        ;
        return executePos;
    }

    //获取流程下步骤列表
    public List<InjectPo> getInjectPos(List<ExecutePo> executePos) {
        if (CollectionUtil.isEmpty(executePos)) {
            return new ArrayList<>();
        }
        List<Long> ownerCodes = executePos.stream().map(ExecutePo::getId).toList();
        List<InjectPo> injectPos = new ArrayList<>();
        for (List<Long> ids : Lists.partition(ownerCodes, MAX_SIZE)) {
            QueryWrapper<InjectPo> injectQueryWrapper = new QueryWrapper<>();
            injectQueryWrapper.in("execute_id", ids);
            List<InjectPo> temps = injectMapper.selectList(injectQueryWrapper);
            injectPos.addAll(temps);
        }
        ;
        return injectPos;
    }

    //获取枚举列表
    public List<MetaEnumPo> getMetaEnumPos(String ownerType, List<Long> ownerCodes) {
        if (CollectionUtil.isEmpty(ownerCodes)) {
            return new ArrayList<>();
        }
        List<MetaEnumPo> metaEnumPos = new ArrayList<>();
        for (List<Long> codes : Lists.partition(ownerCodes, MAX_SIZE)) {
            QueryWrapper<MetaEnumPo> metaEnumQueryWrapper = new QueryWrapper<>();
            metaEnumQueryWrapper.and(wrapper -> wrapper
                    .eq("owner_type", ownerType)
                    .in("owner_id", codes)
            );
            metaEnumPos.addAll(metaEnumMapper.selectList(metaEnumQueryWrapper));
        }
        ;

        return metaEnumPos;
    }

    //获取元对象列表
    public List<MetaClassPo> getMetaClassPos(String ownerType, List<Long> ownerCodes) {
        if (CollectionUtil.isEmpty(ownerCodes)) {
            return new ArrayList<>();
        }
        List<MetaClassPo> metaClassPos = new ArrayList<>();
        for (List<Long> codes : Lists.partition(ownerCodes, MAX_SIZE)) {
            QueryWrapper<MetaClassPo> metaClassQueryWrapper = new QueryWrapper<>();
            metaClassQueryWrapper.and(wrapper -> wrapper
                    .eq("owner_type", ownerType)
                    .in("owner_id", codes)
            );
            metaClassPos.addAll(metaClassMapper.selectList(metaClassQueryWrapper));
        }
        ;
        return metaClassPos;
    }

    //获取元对象属性项列表
    public List<MetaPropertyPo> getMetaPropertyPos(List<MetaClassPo> metaClassPos) {
        if (CollectionUtil.isEmpty(metaClassPos)) {
            return new ArrayList<>();
        }
        List<Long> allIds = metaClassPos.stream().map(MetaClassPo::getId).toList();
        List<MetaPropertyPo> metaPropertyPos = new ArrayList<>();
        for (List<Long> ids : Lists.partition(allIds, MAX_SIZE)) {
            QueryWrapper<MetaPropertyPo> metaPropertyQueryWrapper = new QueryWrapper<>();
            metaPropertyQueryWrapper.in("meta_class_id", ids);
            metaPropertyPos.addAll(metaPropertyMapper.selectList(metaPropertyQueryWrapper));
        }
        ;
        return metaPropertyPos;
    }

    //获取系统级行为列表
    public List<ActionPo> getSystemActions() {
        QueryWrapper<ActionPo> actionQueryWrapper = new QueryWrapper<>();
        actionQueryWrapper.eq("owner_type", Constant.OwnerType.SYSTEM);
        return actionMapper.selectList(actionQueryWrapper);
    }

    //获取环境列表
    public List<EnvironmentPo> getEnvironmentPos() {
        return environmentMapper.selectList(new QueryWrapper<>());
    }

    //获取行为列表
    public List<ActionPo> getActions(String ownerType, List<Long> ownerCodes) {
        List<ActionPo> actionPos = new ArrayList<>();
        for (List<Long> codes : Lists.partition(ownerCodes, MAX_SIZE)) {
            QueryWrapper<ActionPo> actionQueryWrapper = new QueryWrapper<>();
            actionQueryWrapper.and(wrapper -> wrapper
                    .eq("owner_type", ownerType)
                    .in("owner_id", codes)
            );
            actionPos.addAll(actionMapper.selectList(actionQueryWrapper));
        }
        ;

        return actionPos;
    }

    public List<MappingPo> getMappingPos(List<ActionPo> actionPos) {
        if (CollectionUtil.isEmpty(actionPos)) {
            return new ArrayList<>();
        }
        List<MappingPo> mappingPos = new ArrayList<>();
        List<Long> allIds = actionPos.stream().map(ActionPo::getId).distinct().toList();
        for (List<Long> ids : Lists.partition(allIds, MAX_SIZE)) {
            QueryWrapper<MappingPo> mappingQueryWrapper = new QueryWrapper<>();
            mappingQueryWrapper.in("action_id", ids);
            List<MappingPo> tempMappingPos = mappingMapper.selectList(mappingQueryWrapper);
            mappingPos.addAll(tempMappingPos);
        }
        ;

        return mappingPos;
    }

    public List<ParamPo> getParamPos(List<Long> allIds) {
        if (CollectionUtil.isEmpty(allIds)) {
            return new ArrayList<>();
        }
        List<ParamPo> paramPos = new ArrayList<>();
        for (List<Long> ids : Lists.partition(allIds, MAX_SIZE)) {
            QueryWrapper<ParamPo> paramQueryWrapper = new QueryWrapper<>();
            paramQueryWrapper.in("owner_id", ids);
            List<ParamPo> tempParamPos = paramMapper.selectList(paramQueryWrapper);
            paramPos.addAll(tempParamPos);
        }

        return paramPos;
    }

    public DbRuleConvert getDbRuleConvert() {
        return dbRuleConvert;
    }
}
