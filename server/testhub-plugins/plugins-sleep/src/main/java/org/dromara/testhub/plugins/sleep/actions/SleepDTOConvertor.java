package org.dromara.testhub.plugins.sleep.actions;

import org.dromara.testhub.plugins.sleep.actions.model.TestHubExecuteSleep;
import org.dromara.testhub.sdk.action.BaseDTOConvertor;
import org.dromara.testhub.sdk.action.model.rule.TestHubAction;
import org.dromara.testhub.sdk.action.model.rule.TestHubExecute;

public class SleepDTOConvertor implements BaseDTOConvertor {
    @Override
    public Object model2Res(TestHubExecute execute) {
        TestHubExecuteSleep executeSleep = (TestHubExecuteSleep) execute;
        return executeSleep.getSleepTime();
    }

    @Override
    public Object model2Res(TestHubAction action) {
        return null;
    }
}
