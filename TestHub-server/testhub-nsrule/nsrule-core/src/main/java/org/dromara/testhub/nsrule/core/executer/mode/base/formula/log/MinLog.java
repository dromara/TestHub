package org.dromara.testhub.nsrule.core.executer.mode.base.formula.log;

import org.dromara.testhub.nsrule.core.executer.mode.base.formula.MixNode;

import java.util.List;

public class MinLog extends FormulaLog {
    public List<FormulaLog> nodes;

    public MinLog(MixNode node) {
        super(node);
    }

    public List<FormulaLog> getNodes() {
        return nodes;
    }

    public void setNodes(List<FormulaLog> nodes) {
        this.nodes = nodes;
    }
}
