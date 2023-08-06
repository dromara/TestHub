package org.dromara.testhub.sleep;

import com.alibaba.fastjson.JSONObject;
import com.goddess.nsrule.core.executer.context.Context;
import com.goddess.nsrule.core.executer.mode.base.action.RunState;
import org.dromara.testhub.sdk.BaseDTOConvertor;
import org.dromara.testhub.sdk.BaseJsonExecuteParser;
import org.dromara.testhub.sdk.BaseXMLExecuteParser;
import org.dromara.testhub.sdk.Plugin;
import org.dromara.testhub.sdk.model.rule.TestHubAction;
import org.dromara.testhub.sdk.model.rule.TestHubExecute;
import org.dromara.testhub.sleep.model.TestHubExecuteSleep;

public class SleepPlugin implements Plugin {


    @Override
    public String getType() {
        return "SLEEP";
    }

    @Override
    public BaseXMLExecuteParser getXMLExecuteParser() {
        return new SleepXMLExecuteParser();
    }

    @Override
    public BaseJsonExecuteParser getJsonExecuteParser() {
        return new SleepJsonExecuteParser();
    }
    @Override
    public BaseDTOConvertor getDTOConvertor() {
        return new SleepDTOConvertor();
    }

    @Override
    public void execute(Context context, TestHubAction action, TestHubExecute execute, JSONObject data, RunState.Item runState) throws Exception {
        try {
            TestHubExecuteSleep executeSleep = (TestHubExecuteSleep)execute;
            Thread.sleep(executeSleep.getSleepTime());
            runState.addRunParams("sleepTime",executeSleep.getSleepTime());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
