package org.dromara.testhub.check.model;

import com.goddess.nsrule.core.executer.mode.base.action.Execute;
import com.goddess.nsrule.core.executer.mode.ruleLine.RuleLine;
import org.dromara.testhub.sdk.model.rule.TestHubExecute;

import java.util.List;

public class TestHubExecuteCheck extends TestHubExecute {
    private List<RuleLine<CheckItem>> ruleLines;

    public List<RuleLine<CheckItem>> getRuleLines() {
        return ruleLines;
    }

    public void setRuleLines(List<RuleLine<CheckItem>> ruleLines) {
        this.ruleLines = ruleLines;
    }

    public TestHubExecuteCheck(Execute execute) {
        super(execute);
    }
}
