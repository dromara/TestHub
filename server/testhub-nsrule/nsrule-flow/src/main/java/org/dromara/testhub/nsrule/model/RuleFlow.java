package org.dromara.testhub.nsrule.model;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONObject;
import org.dromara.testhub.nsrule.core.constant.ExceptionCode;
import org.dromara.testhub.nsrule.core.constant.RuleException;
import org.dromara.testhub.nsrule.core.executer.context.Context;
import org.dromara.testhub.nsrule.core.executer.context.RuleProject;
import org.dromara.testhub.nsrule.core.executer.meta.MetaClass;
import org.dromara.testhub.nsrule.core.executer.meta.MetaProperty;
import org.dromara.testhub.nsrule.core.executer.mode.base.action.Action;
import org.dromara.testhub.nsrule.core.executer.mode.base.action.Execute;
import org.dromara.testhub.nsrule.core.executer.mode.base.action.Param;
import org.dromara.testhub.nsrule.core.executer.mode.Rule;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/9/18 17:49
 */
public class RuleFlow extends Rule<Map<String, List<Object>>> {
    private int stepNumber = 0;
    // 模式 Flow
    private List<Flow> flows;

    @Override
    public FlowContext buildContext(RuleProject project, String paramGroup, Executor executor) {
        return new FlowContext(project, this, paramGroup, executor);
    }

    @Override
    public Map<String, List<Object>> decision(Context context, JSONObject data) {
        //验证参数
        Param.checkParam(data, getParams());
        //解析参数
        context.putRuleParams(Param.buildParams(context, getParams(), data));
        FlowContext flowContext = (FlowContext) context;
        Map<String, List<Object>> reData = new HashMap<>();
        for (Flow flow : flows) {
            if (CollectionUtil.isNotEmpty(flowContext.getSkipFlows()) && flowContext.getSkipFlows().contains(flow.getCode())) {
                continue;
            }
            reData.put(flow.getCode(), flow.decision(flowContext));
        }
        return reData;
    }

    @Override
    public Map<String, MetaProperty> getPropertyMap(RuleProject ruleProject) {
        Map<String, MetaProperty> propertyMap = new HashMap<>();
        for (Flow flow : flows) {
            for (Execute execute : flow.executes) {
                Action action = getAction(execute.getActionCode());
                if (action == null) {
                    action = ruleProject.getAction(execute.getActionCode());
                    if (action == null) {
                        throw new RuleException(ExceptionCode.EC_0302, execute.getCode(), execute.getActionCode());
                    }
                }
                MetaClass metaClass = getMetaClassByDataType(action.getDataType());
                if (metaClass != null) {
                    for (MetaProperty property : metaClass.getProperties()) {
                        MetaProperty property2 = property.clone();
                        property2.setName(
                                (StringUtils.isNotEmpty(execute.getName()) ? execute.getName() : execute.getCode()) + "." +
                                        (StringUtils.isNotEmpty(property2.getName()) ? property2.getName() : property2.getCode())

                        );
                        propertyMap.put(flow.getCode() + "." + execute.getCode() + "." + property2.getCode(), property2);
                    }
                }
            }
        }
        return propertyMap;
    }

    public List<Flow> getFlows() {
        return flows;
    }

    public void setFlows(List<Flow> flows) {
        int i = 0;
        for (Flow flow : flows) {
            i += flow.executes.size();
        }
        stepNumber = i;
        this.flows = flows;
    }

    public int getStepNumber() {
        return stepNumber;
    }

}
