package com.goddess.nsrule.flow.model;

import com.goddess.nsrule.core.executer.context.Context;
import com.goddess.nsrule.core.executer.context.RuleProject;
import com.goddess.nsrule.core.executer.mode.Rule;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2023/1/10 12:53
 */
public class FlowContext extends Context<Map<String, List<Object>>> {
    private AtomicInteger stepNumber = new AtomicInteger(0);
    private int stepSum = 0;
    private Map<Thread, Flow> flowMap = new ConcurrentHashMap<>();

    public FlowContext(RuleProject project, Rule rule, String envCode, Executor executor) {
        super(project, rule, envCode, executor);
    }

    private List<String> skipFlows;

    public void setSkipFlows(List<String> skipFlows) {
        RuleFlow ruleFlow = (RuleFlow) super.getRule();
        if (skipFlows==null|| skipFlows.isEmpty() || skipFlows.size() < 1) {
            stepSum = ruleFlow.getStepNumber();
        } else {
            for (Flow flow : ruleFlow.getFlows()) {
                stepSum += flow.executes.size();
            }
        }
        this.skipFlows = skipFlows;
    }

    public List<String> getSkipFlows() {
        return skipFlows;
    }

    @Override
    public String getItemCode() {
        return getFlow().getCode();
    }

    public Flow getFlow() {
        return flowMap.get(Thread.currentThread());
    }

    public void setFlow(Flow flow) {
        flowMap.put(Thread.currentThread(), flow);
    }

    public void removeFlow() {
        flowMap.remove(Thread.currentThread());
    }

    public AtomicInteger getStepNumber() {
        return stepNumber;
    }

    public int getStepSum() {
        return stepSum;
    }
}
