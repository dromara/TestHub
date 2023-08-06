package com.goddess.nsrule.graph.model;

import com.goddess.nsrule.core.executer.context.Context;
import com.goddess.nsrule.core.executer.context.JudgePath;
import com.goddess.nsrule.core.executer.context.RuleConfig;
import com.goddess.nsrule.core.executer.context.RuleProject;
import com.goddess.nsrule.core.executer.mode.Rule;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.Executor;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2023/1/10 12:48
 */
public class GraphContext extends Context {
    private Graph nowGraph;
    //回溯栈
    private Stack<JudgePath> judgePathStack = new Stack<>();
    //执行路径
    private Queue<JudgePath> judgePathQueue = new LinkedList<>();

    public GraphContext(RuleProject ruleProject, Rule rule, String paramGroup, Executor executor) {
        super(ruleProject, rule, paramGroup, executor);
    }

    @Override
    public String getItemCode() {
        return nowGraph.getCode();
    }

    /**
     * 回溯栈出栈
     *
     * @return
     */
    public JudgePath revertLink() {
        if (this.judgePathStack.empty()) {
            return null;
        }
        JudgePath judgePath = this.judgePathStack.pop();
        return judgePath;
    }

    /**
     * 当flag等于true链接生效 记录 回溯栈，
     *
     * @return
     */
    public void execLink(Link link, boolean flag) {
        JudgePath judgePath = new JudgePath(this.nowGraph.getCode(), link.getBranchCode(), link.getCode(), flag);
        if (flag) {
            //加入回溯栈
            judgePathStack.push(judgePath);
        }
        //记录执行路径
        judgePathQueue.add(judgePath);
    }

    public void setNowGraph(Graph nowGraph) {
        this.nowGraph = nowGraph;
    }
}
