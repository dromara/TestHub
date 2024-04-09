package org.dromara.testhub.plugins.sleep.actions.model;

import org.dromara.testhub.nsrule.core.executer.mode.base.action.Execute;
import org.dromara.testhub.sdk.action.model.rule.TestHubExecute;

public class TestHubExecuteSleep extends TestHubExecute {
    private Long sleepTime;

    public Long getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(Long sleepTime) {
        this.sleepTime = sleepTime;
    }

    public TestHubExecuteSleep(Execute execute) {
        super(execute);
    }
}
