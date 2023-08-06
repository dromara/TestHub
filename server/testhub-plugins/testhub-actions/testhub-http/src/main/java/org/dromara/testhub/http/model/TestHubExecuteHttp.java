package org.dromara.testhub.http.model;

import com.goddess.nsrule.core.executer.mode.base.action.Execute;
import com.goddess.nsrule.core.executer.mode.ruleLine.Expression;
import org.dromara.testhub.sdk.model.rule.TestHubExecute;

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
