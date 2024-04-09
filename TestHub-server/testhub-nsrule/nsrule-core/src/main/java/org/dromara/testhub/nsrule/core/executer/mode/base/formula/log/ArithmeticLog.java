package org.dromara.testhub.nsrule.core.executer.mode.base.formula.log;

import org.dromara.testhub.nsrule.core.executer.mode.base.formula.FormulaNode;

public class ArithmeticLog  extends FormulaLog {
    private FormulaLog leftLog;
    private String op;
    private FormulaLog rightLog;
    public ArithmeticLog(FormulaNode node) {
        super(node);
    }


    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public FormulaLog getLeftLog() {
        return leftLog;
    }

    public void setLeftLog(FormulaLog leftLog) {
        this.leftLog = leftLog;
    }

    public FormulaLog getRightLog() {
        return rightLog;
    }

    public void setRightLog(FormulaLog rightLog) {
        this.rightLog = rightLog;
    }
}
