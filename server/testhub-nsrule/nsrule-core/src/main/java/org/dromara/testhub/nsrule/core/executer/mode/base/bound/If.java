package org.dromara.testhub.nsrule.core.executer.mode.base.bound;


import org.dromara.testhub.nsrule.core.executer.context.Context;
import org.dromara.testhub.nsrule.core.executer.mode.ruleLine.Expression;
import org.dromara.testhub.nsrule.core.executer.mode.ruleLine.JavaActuator;

public class If extends Bound {
    private Expression expression;
    private Compose compose;

    public If() {
        type = "If";
    }

    @Override
    public String build(Context context) {
        boolean flag = JavaActuator.execute(context, expression);
        if (flag) {
            return compose.build(context);
        }
        return "";
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public Compose getCompose() {
        return compose;
    }

    public void setCompose(Compose compose) {
        this.compose = compose;
    }
}
