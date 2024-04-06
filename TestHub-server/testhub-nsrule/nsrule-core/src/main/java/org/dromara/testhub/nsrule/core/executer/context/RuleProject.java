package org.dromara.testhub.nsrule.core.executer.context;

import org.dromara.testhub.nsrule.core.constant.ConstantUtil;
import org.dromara.testhub.nsrule.core.constant.RuleException;
import org.dromara.testhub.nsrule.core.executer.meta.MetaClass;
import org.dromara.testhub.nsrule.core.executer.meta.MetaEnum;
import org.dromara.testhub.nsrule.core.executer.mode.Rule;
import org.dromara.testhub.nsrule.core.executer.mode.base.action.Action;
import org.dromara.testhub.nsrule.core.executer.mode.base.action.Param;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

public class RuleProject {

    private Long id;
    //编码
    private String code;
    //名称
    private String name;


    private RuleConfig ruleConfig;

    //全局公参
    private Map<String, List<Param>> globalParamMap = new ConcurrentHashMap<>();

    //枚举
    private Map<String, MetaEnum> metaEnumMap = new ConcurrentHashMap<>();

    //规则对象类
    private Map<String, MetaClass> metaClassMap = new ConcurrentHashMap<>();
    private Map<String, MetaContext> metaContextMap = new ConcurrentHashMap<>();

    //行为
    private Map<String, Action> actionMap = new ConcurrentHashMap<>();

    //规则缓存
    private Map<String, Rule> ruleMap = new ConcurrentHashMap<>();


    public RuleProject(RuleConfig ruleConfig) {
        this.ruleConfig = ruleConfig;
    }


    public Context buildContext(String ruleCode, String paramGroup, Executor executor) {
        Rule rule = this.ruleMap.get(ruleCode);
        return rule.buildContext(this, paramGroup, executor);
    }

    public Context buildContext(String ruleCode, Executor executor) {
        return buildContext(ruleCode, null, executor);
    }

    public Context buildContext(String ruleCode) {
        return buildContext(ruleCode, null, null);
    }

    public Context buildContext() {
        return new Context(this, null, null, null) {
            @Override
            public String getItemCode() {
                return "";
            }
        };
    }

    public void putGlobalParams(String env,List<Param> params){
        globalParamMap.put(env,params);
    }
    public Map<String, List<Param>> getGlobalParamMap(){
        return globalParamMap;
    }

    public List<Param> getGlobalParams(String env) {
        return globalParamMap.get(env);
    }

    public Action getAction(String code) {
        return actionMap.get(code);
    }

    public void addRule(Rule rule) {
        MetaContext metaContext = new MetaContext(this.metaClassMap.values().stream().collect(Collectors.toList()), this.metaClassMap);
        this.metaContextMap.put(rule.getCode(), new MetaContext(metaContext, rule.getMetaClassMap(), rule, this));
        this.ruleMap.put(rule.getCode(), rule);
    }

    public MetaContext getMetaContextByRuleCode(String ruleCode) {
        return this.metaContextMap.get(ruleCode);
    }

    public List<Rule> getRules() {
        return ruleMap.values().stream().collect(Collectors.toList());
    }

    public void updateRule(Rule rule) {
        this.metaContextMap.put(rule.getCode(), new MetaContext(new MetaContext(this.metaClassMap.values().stream().collect(Collectors.toList()), this.metaClassMap), rule.getMetaClassMap(), rule, this));
        this.ruleMap.put(rule.getCode(), rule);
    }

    public boolean isBaseDataType(String dataType) {
        return ConstantUtil.isBaseDataType(dataType);
    }



    public MetaClass getMetaClassByDataType(String dataType) {
        if (ConstantUtil.isInnerDataType(dataType)) {
            return null;
        } else {
            return metaClassMap.get(dataType);
        }
    }

    public void setActions(List<Action> actions) {
        this.actionMap = new ConcurrentHashMap<>();
        for (Action action : actions) {
            this.actionMap.put(action.getCode(), action);
        }
    }
    public void addAction(Action action) {
        this.actionMap.put(action.getCode(),action);
    }

    public List<Action> getActions() {
        return this.actionMap.values().stream().collect(Collectors.toList());
    }

    public void setMetaEnums(List<MetaEnum> metaEnums) {
        this.metaEnumMap = new ConcurrentHashMap<>();
        for (MetaEnum metaEnum : metaEnums) {
            this.metaEnumMap.put(metaEnum.getCode(), metaEnum);
        }
    }


    public void setMetaClasses(List<MetaClass> metaClasses) {
        this.metaClassMap = new ConcurrentHashMap<>();
        for (MetaClass metaClass : metaClasses) {
            this.metaClassMap.put(metaClass.getCode(), metaClass);
        }
    }

    public Map<String, MetaClass> getMetaClassMap() {
        return metaClassMap;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RuleConfig getRuleConfig() {
        return ruleConfig;
    }
}
