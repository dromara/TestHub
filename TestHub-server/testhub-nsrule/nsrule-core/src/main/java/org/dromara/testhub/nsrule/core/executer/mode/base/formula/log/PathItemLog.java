package org.dromara.testhub.nsrule.core.executer.mode.base.formula.log;

import org.dromara.testhub.nsrule.core.executer.mode.base.formula.PathNode;

import java.util.List;

public class PathItemLog {

    public String attrName;
    public List<FormulaLog> nodes;

    public PathItemLog(){}

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public List<FormulaLog> getNodes() {
        return nodes;
    }

    public void setNodes(List<FormulaLog> nodes) {
        this.nodes = nodes;
    }
}