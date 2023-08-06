package com.goddess.nsrule.core.executer.mode.base.bound;

import com.goddess.nsrule.core.executer.context.Context;
import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.List;

public class Where extends Bound {
    private List<If> ifs;
    private Else elseNode;

    public Where() {
        type = "Where";
    }

    @Override
    public String build(Context context) {
        List<String> strs = new ArrayList<>();
        for (If ifNode : ifs) {
            String str = ifNode.build(context);
            if (str != "") {
                strs.add(str);
            }
        }
        if (strs.size() > 0) {
            return Joiner.on("").join(strs);
        } else if (elseNode != null) {
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

