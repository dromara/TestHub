package org.dromara.testhub.nsrule.core.executer.mode.base.formula;

import org.dromara.testhub.nsrule.core.constant.RuleConstant;
import org.dromara.testhub.nsrule.core.constant.RuleException;
import org.dromara.testhub.nsrule.core.executer.context.Context;
import org.dromara.testhub.nsrule.core.executer.mode.base.Result;
import org.dromara.testhub.nsrule.core.executer.mode.base.formula.log.ArithmeticLog;
import org.dromara.testhub.nsrule.core.executer.mode.base.formula.log.FormulaLog;
import org.dromara.testhub.nsrule.core.executer.operation.Operation;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ArithmeticNode extends FormulaNode {
    public static String TYPE = "DATA";

    private FormulaNode left;
    private String op;
    private FormulaNode right;

    public ArithmeticNode(String text) {
        //用于算数运算的只能是数字
        super(TYPE, text);
    }

    @Override
    public Result<Object> doApply(Context context, boolean isLog) {
        Result<Object> result = new Result<>();
        ArithmeticLog log = new ArithmeticLog(this);

        log.setOp(op);

        Result<Object> resultLeft = left.apply(context, isLog);
        Object leftData = resultLeft.getContent();
        log.setLeftLog((FormulaLog) resultLeft.getLog());


        Result<Object> resultRight = right.apply(context, isLog);
        Object rightData = resultRight.getContent();
        log.setRightLog((FormulaLog) resultRight.getLog());

        BigDecimal leftNumber = getData(leftData);
        BigDecimal rightNumber = getData(rightData);
        Object res = null;
        switch (op) {
            case RuleConstant.ArithmeticType.ADD:
                res = leftNumber.add(rightNumber).stripTrailingZeros();
                break;
            case RuleConstant.ArithmeticType.SUB:
                res = leftNumber.subtract(rightNumber).stripTrailingZeros();
                break;
            case RuleConstant.ArithmeticType.MUL:
                res = leftNumber.multiply(rightNumber).stripTrailingZeros();
                break;
            case RuleConstant.ArithmeticType.DIV:
                res = leftNumber.divide(rightNumber, 4, RoundingMode.HALF_UP).stripTrailingZeros();
                break;
            case RuleConstant.ArithmeticType.REM:
                res = leftNumber.remainder(rightNumber).stripTrailingZeros();
                break;
            default:
                throw new RuleException("算数运算不支持" + op);
        }
        result.setContent(res);
        log.setData(res);
        result.setLog(isLog ? log : null);
        return result;
    }

    private BigDecimal getData(Object data) {
        try {
            return Operation.getNumber(data);
        } catch (Exception e) {
            throw new RuleException("算数运算的只能是数字");
        }
    }


    public FormulaNode getLeft() {
        return left;
    }

    public void setLeft(FormulaNode left) {
        this.left = left;
    }

    public FormulaNode getRight() {
        return right;
    }

    public void setRight(FormulaNode right) {
        this.right = right;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }
}
