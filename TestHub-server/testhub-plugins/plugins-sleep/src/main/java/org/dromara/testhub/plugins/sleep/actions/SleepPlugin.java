package org.dromara.testhub.plugins.sleep.actions;

import com.alibaba.fastjson.JSONObject;
import org.dromara.testhub.nsrule.core.executer.context.Context;
import org.dromara.testhub.nsrule.core.executer.mode.base.action.RunState;
import org.dromara.testhub.plugins.sleep.actions.model.TestHubExecuteSleep;
import org.dromara.testhub.sdk.action.BaseDTOConvertor;
import org.dromara.testhub.sdk.action.BaseJsonExecuteParser;
import org.dromara.testhub.sdk.action.BaseXMLExecuteParser;
import org.dromara.testhub.sdk.action.Plugin;
import org.dromara.testhub.sdk.action.model.rule.TestHubAction;
import org.dromara.testhub.sdk.action.model.rule.TestHubExecute;

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
