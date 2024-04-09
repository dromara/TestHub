package org.dromara.testhub.plugins.http.actions.model;

import org.dromara.testhub.nsrule.core.executer.mode.base.action.Execute;
import org.dromara.testhub.nsrule.core.executer.mode.ruleLine.Expression;
import org.dromara.testhub.sdk.action.model.rule.TestHubExecute;

public class TestHubExecuteHttp extends TestHubExecute {
    private Expression expression;

    public TestHubExecuteHttp(Execute execute) {
        super(execute);
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }
}
