package org.dromara.testhub.sleep;

import org.dromara.testhub.sdk.BaseDTOConvertor;
import org.dromara.testhub.sdk.model.rule.TestHubAction;
import org.dromara.testhub.sdk.model.rule.TestHubExecute;
import org.dromara.testhub.sleep.model.TestHubExecuteSleep;

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
