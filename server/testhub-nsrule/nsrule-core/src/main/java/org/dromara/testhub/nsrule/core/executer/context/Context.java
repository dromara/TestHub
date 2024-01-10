package org.dromara.testhub.nsrule.core.executer.context;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import org.dromara.testhub.nsrule.core.eventbus.AsyncEventBus;
import org.dromara.testhub.nsrule.core.eventbus.EventBus;
import org.dromara.testhub.nsrule.core.eventbus.EventMessage;
import org.dromara.testhub.nsrule.core.eventbus.EventType;
import org.dromara.testhub.nsrule.core.executer.mode.base.action.Param;
import org.dromara.testhub.nsrule.core.executer.mode.base.action.RunState;
import org.dromara.testhub.nsrule.core.executer.mode.Rule;
import org.apache.commons.lang3.StringUtils;
import org.dromara.testhub.nsrule.core.executer.mode.base.function.FunctionHandlerFactory;

import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * 决策上下文
 *
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/3 23:01
 */
public abstract class Context<T> {
    private static Executor baseExecutor = Executors.newSingleThreadScheduledExecutor();
    private String uuid;
    private Rule rule;
    private T result;
    private RuleProject project;
    //    private RuleConfig ruleConfig;
    private MetaContext metaContext;

    private JSONObject data;
    private Stack<JSONObject> dataStack = new Stack<>();
    private List<JSONObject> stackList = new ArrayList<>();

    private JSONObject runData = new JSONObject();
    private List<RunState> runStates = new ArrayList<>();
    private JSONObject ruleParams;
    private JSONObject globalParams;



    //事件通知
    private EventBus eventBus;

    public Context(RuleProject project, Rule rule, String envCode, Executor executor) {
        this.uuid = UUID.randomUUID().toString();
        if(StringUtils.isEmpty(envCode)){
            this.globalParams = new JSONObject();
        }else {
            List<Param> params = project.getGlobalParamMap().get(envCode);
            if(CollectionUtil.isEmpty(params)){
                this.globalParams = new JSONObject();
            }else {
                //这里说明环境编码存在
                this.globalParams = Param.buildParams(params);
            }
        }
        this.project = project;
        this.rule = rule;
        this.eventBus = new AsyncEventBus(executor == null ? baseExecutor : executor);
    }
    public Context(RuleProject project,String envCode) {
        this.uuid = UUID.randomUUID().toString();
        if(StringUtils.isEmpty(envCode)){
            this.globalParams = new JSONObject();
        }else {
            List<Param> params = project.getGlobalParamMap().get(envCode);
            if(CollectionUtil.isEmpty(params)){
                this.globalParams = new JSONObject();
            }else {
                //这里说明环境编码存在
                this.globalParams = Param.buildParams(params);
            }
        }
        this.project = project;
    }
    public Context(JSONObject params) {
        this.uuid = UUID.randomUUID().toString();
        this.globalParams = params;
        this.project = null;
    }

    public void register(Object observer) {
        eventBus.register(observer);
    }

    public void post(EventType event, Object data) {
        eventBus.post(event, new EventMessage(this, data));
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public JSONObject removeData() {
        JSONObject re = this.data;
        this.data = dataStack.pop();
        stackList.remove(data);
        return re;
    }

    public JSONObject getData() {
        return data;
    }

    public void pushData(JSONObject data) {
        dataStack.push(this.data);
        stackList.add(this.data);
        this.data = data;
    }

    public void pushData(Map<String, Object> data) {
        dataStack.push(this.data);
        stackList.add(this.data);
        this.data = new JSONObject(data);
    }

    public JSONObject getRunData() {
        //返回值不要被修改
        return runData;
    }

    public void putRunData(String key, Object values) {
        if (runData == null) {
            runData = new JSONObject();
        }
        runData.put(key, values);
    }

    public void putRunData(JSONObject json) {
        if (runData == null) {
            runData = new JSONObject();
        }
        runData.putAll(json);
    }

    public void putRuleParams(String key, Object values) {
        if (ruleParams == null) {
            ruleParams = new JSONObject();
        }
        ruleParams.put(key, values);
    }

    public void putRuleParams(JSONObject json) {
        if (ruleParams == null) {
            ruleParams = new JSONObject();
        }
        ruleParams.putAll(json);
    }

    public Object getObject(String path) {
        Object data = JSONPath.eval(this.data, path, false);
        if (data == null) {
            data = getByDataStack(path);
            if (data == null) {
                data = JSONPath.eval(this.ruleParams, path, false);
                if (data == null) {
                    data = JSONPath.eval(this.runData.get(getItemCode()), path, false);
                    if (data == null) {
                        data = JSONPath.eval(this.globalParams, path, false);
                    }
                }
            }
        }

        return data;
    }
    // 根据值栈 生成 参数
    public JSONObject getMergedParams(){
        JSONObject params = new JSONObject();
        if(this.globalParams!=null){
            params.putAll(this.globalParams);
        }
        if(this.ruleParams!=null){
            params.putAll(this.ruleParams);
        }
        for (JSONObject tData :stackList.stream()
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> {
                    Collections.reverse(list);
                    return list;
                }))) {
            if(tData!=null){
                params.putAll(tData);
            }
        }
        if(this.data!=null){
            params.putAll(this.data);
        }
        return params;
    }

    private Object getByDataStack(String path) {
        Object data = null;
        for (JSONObject tData :stackList.stream()
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> {
                    Collections.reverse(list);
                    return list;
                }))) {
            data = JSONPath.eval(tData, path, false);
            if (data != null) {
                return data;
            }
        }
        return data;
    }


    public String getString(String path) {
        Object data = getObject(path);
        if (data == null) {
            return null;
        }
        return data.toString();
    }

    public int getInteger(String path) {
        Object data = getObject(path);
        if (data == null) {
            return 0;
        }
        return Integer.parseInt(data.toString());
    }

    public Rule getRule() {
        return rule;
    }

    public MetaContext getMetaContext() {
        return metaContext;
    }

    public void setMetaContext(MetaContext metaContext) {
        this.metaContext = metaContext;
    }

    public JSONObject getGlobalParams() {
        return globalParams;
    }

    public void setGlobalParams(JSONObject globalParams) {
        this.globalParams = globalParams;
    }

    public abstract String getItemCode();

    public List<RunState> getRunStates() {
        return runStates;
    }

    public void addRunState(RunState runState) {
        this.runStates.add(runState);

    }

    public Map<String, RunState> getRunStateMap() {
        return this.runStates.stream().collect(Collectors.toMap(o -> o.getCode(), o -> o));
    }

    public String getUuid() {
        return uuid;
    }

    public RuleProject getProject() {
        return project;
    }
    public FunctionHandlerFactory getFunctionHandlerFactory() {
        return project.buildContext().getFunctionHandlerFactory();
    }
}
