package org.dromara.testhub.sleep.model;

import com.goddess.nsrule.core.executer.mode.base.action.Execute;
import org.dromara.testhub.sdk.model.rule.TestHubExecute;

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
