package org.dromara.testhub.nsrule.core.executer.mode.ruleLine;

import org.dromara.testhub.nsrule.core.executer.context.Context;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/8/30 15:52
 */
public class RuleLine<T> {

    private T result;
    private Expression expression;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }


    public boolean execute(Context context) {
        return JavaActuator.execute(context, expression);
    }

    public boolean execute(Context context, JavaActuator.Log log) {
        return JavaActuator.execute(context, expression, log);
    }


}
