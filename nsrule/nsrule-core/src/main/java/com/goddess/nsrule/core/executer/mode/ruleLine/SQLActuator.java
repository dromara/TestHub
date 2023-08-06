package com.goddess.nsrule.core.executer.mode.ruleLine;

import com.goddess.nsrule.core.constant.Constant;
import com.goddess.nsrule.core.executer.context.Context;
import com.goddess.nsrule.core.executer.meta.MetaProperty;
import com.goddess.nsrule.core.executer.operation.Operation;
import com.goddess.nsrule.core.executer.operation.OperationFactory;
import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vinc
 */

public class SQLActuator {

    private Expression expression;

    public SQLActuator(Expression expression) {
        this.expression = expression;
    }

    public String execute(Context context, boolean logFlag) {
        //逻辑
        if (Constant.ExpressionType.LOGIC.equals(expression.getExpressionType())) {
            return execute(expression.getOperationCode(), expression.getSubExpression(), context, logFlag);
        } else {

            // 1>2  1被比较的阀值 >操作符 2阀值
            Operation operation = OperationFactory.getOperation(expression.getOperationCode());

            Object cover = expression.getCoverFormula().apply(context).getContent();
            Object threshold = null;
            if (!operation.isOneOp()) {
                threshold = expression.getThresholdFormula().apply(context).getContent();
            }

            //关系
            return operation.executeStr(expression.getDataType(), expression.getCoverComplex(), cover, expression.getThresholdComplex(), threshold);

        }
    }

    //关系
    private String execute(String operationCode, MetaProperty property, Object attrVal, List<String> params) {
        return null;
    }

    //逻辑
    private String execute(String operationCode, List<Expression> subExpressions, Context context, boolean logFlag) {
        List<String> sqls = new ArrayList<>();
        if ("or".equalsIgnoreCase(operationCode)) {
            for (Expression expression : subExpressions) {
                sqls.add(new SQLActuator(expression).execute(context, logFlag));
            }
            return "(" + Joiner.on(" or ").join(sqls) + ")";
        } else {
            for (Expression expression : subExpressions) {
                sqls.add(new SQLActuator(expression).execute(context, logFlag));
            }
            return "(" + Joiner.on(" and ").join(sqls) + ")";
        }
    }
}
