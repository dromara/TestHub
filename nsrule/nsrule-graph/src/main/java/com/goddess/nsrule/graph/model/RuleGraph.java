package com.goddess.nsrule.graph.model;

import com.alibaba.fastjson.JSONObject;
import com.goddess.nsrule.core.executer.context.Context;
import com.goddess.nsrule.core.executer.context.RuleConfig;
import com.goddess.nsrule.core.executer.context.RuleProject;
import com.goddess.nsrule.core.executer.meta.MetaProperty;
import com.goddess.nsrule.core.executer.mode.base.action.Param;
import com.goddess.nsrule.core.executer.mode.Rule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/9/18 17:47
 */
public class RuleGraph extends Rule<DecisionResult> {
    // 模式 Graph
    private List<Graph> graphs;
    private Map<String, Result> resultMap;


    @Override
    public Map<String, MetaProperty> getPropertyMap(RuleProject ruleProject) {
        return null;
    }

    @Override
    public GraphContext buildContext(RuleProject ruleProject, String paramGroup, Executor executor) {
        return new GraphContext(ruleProject, this, paramGroup, executor);
    }

    @Override
    public DecisionResult decision(Context context, JSONObject data) {
        GraphContext graphContext = (GraphContext) context;
        //验证参数
        Param.checkParam(data, getParams());
        //解析参数
        context.putRuleParams(Param.buildParams(context, getParams(), data));

        DecisionResult decisionResult = new DecisionResult();

        //获得决策图的每一个结果 决策图编码为Key 结果为val
        Map<String, String> graphResult = new HashMap<>();
        graphs.forEach(graph -> {
            graphResult.put(graph.getCode(), graph.decision(graphContext));
        });

        //只有一棵树 直接返回
        if (graphs.size() == 1) {
            Result result = resultMap.get(graphResult.get(graphs.get(0).getCode()));
            decisionResult.setDataType(result.getDataType());
            decisionResult.setContent(result.decision(context));
        }
        return decisionResult;
    }

    public void setGraphs(List<Graph> graphs) {
        this.graphs = graphs;
    }

    public void setResults(List<Result> results) {
        this.resultMap = results.stream().collect(Collectors.toMap(Result::getCode, o -> o));
    }

    public List<Graph> getGraphs() {
        return graphs;
    }
}
