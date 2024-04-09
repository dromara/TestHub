package org.dromara.testhub.nsrule.core.executer.mode;

import com.alibaba.fastjson.JSONObject;
import org.dromara.testhub.nsrule.core.executer.context.Context;
import org.dromara.testhub.nsrule.core.executer.context.RuleProject;
import org.dromara.testhub.nsrule.core.executer.context.RuleConfig;
import org.dromara.testhub.nsrule.core.executer.meta.MetaClass;
import org.dromara.testhub.nsrule.core.executer.meta.MetaEnum;
import org.dromara.testhub.nsrule.core.executer.meta.MetaProperty;
import org.dromara.testhub.nsrule.core.executer.mode.base.action.Action;
import org.dromara.testhub.nsrule.core.executer.mode.base.action.Param;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

/**
 * 规则执行器
 *
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/3 22:01
 */
public abstract class Rule<T> extends BasePo {
    // 模式
    private String model;
    //源数据
    private String source;
    //所属项目
    private String project;

    private List<Param> params;
    private Map<String, Param> paramMap;

    //枚举
    private Map<String, MetaEnum> metaEnumMap;

    //规则对象类
    private Map<String, MetaClass> metaClassMap;
    //行为
    private Map<String, Action> actionMap;


    public abstract T decision(Context context, JSONObject data);

    public abstract Context buildContext(RuleProject project, String paramGroup, Executor executor);

    //用于解析 元对象上下文
    public abstract Map<String, MetaProperty> getPropertyMap(RuleProject ruleProject);


    public Rule() {
    }


    public List<Param> getParams() {
        return params;
    }

    public void setParams(List<Param> params) {
        this.params = params;
        this.paramMap = params.stream().collect(Collectors.toMap(Param::getCode, o -> o));
    }

    public Action getAction(String code) {
        return actionMap.get(code);
    }

    public void setActions(List<Action> actions) {
        this.actionMap = new HashMap<>();
        for (Action action : actions) {
            this.actionMap.put(action.getCode(), action);
        }
    }

    public Map<String, Action> getActionMap() {
        return actionMap;
    }

    public List<Action> getActions(){
        return new ArrayList<>(actionMap.values());
    }

    public MetaClass getMetaClassByDataType(String dataType) {
        return metaClassMap.get(dataType);
    }

    public void setMetaClasses(List<MetaClass> metaClasses) {
        this.metaClassMap = new HashMap<>();
        for (MetaClass metaClass : metaClasses) {
            this.metaClassMap.put(metaClass.getCode(), metaClass);
        }
    }
    public List<MetaClass> getMetaClasses(){
        return new ArrayList<>(metaClassMap.values());
    }
    public List<MetaEnum> getMetaEnums(){
        return new ArrayList<>(metaEnumMap.values());
    }

    public void setMetaEnums(List<MetaEnum> metaEnums) {
        this.metaEnumMap = new HashMap<>();
        for (MetaEnum metaEnum : metaEnums) {
            this.metaEnumMap.put(metaEnum.getCode(), metaEnum);
        }
    }

    public Map<String, MetaEnum> getMetaEnumMap() {
        return metaEnumMap;
    }

    public Map<String, MetaClass> getMetaClassMap() {
        return metaClassMap;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Map<String, Param> getParamMap() {
        return paramMap;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }
}
