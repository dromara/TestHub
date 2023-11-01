package org.dromara.testhub.nsrule.core.executer.mode.base.bound;

import org.dromara.testhub.nsrule.core.executer.context.Context;

import java.util.List;

public class Choice extends Bound {
    private List<If> ifs;
    private Else elseNode;

    public Choice() {
        type = "Choice";
    }

    @Override
    public String build(Context context) {
        for (If ifNode : ifs) {
            String str = ifNode.build(context);
            if (str != "") {
                return str;
            }
        }
        if (elseNode != null) {
            return elseNode.build(context);
        } else {
            return "";
        }
    }

    public Else getElseNode() {
        return elseNode;
    }

    public void setElseNode(Else elseNode) {
        this.elseNode = elseNode;
    }

    public List<If> getIfs() {
        return ifs;
    }

    public void setIfs(List<If> ifs) {
        this.ifs = ifs;
    }
}

