package org.dromara.testhub.plugins.sleep.actions;


import com.alibaba.fastjson.JSONObject;
import org.dromara.testhub.plugins.sleep.actions.model.TestHubExecuteSleep;
import org.dromara.testhub.sdk.action.BaseJsonExecuteParser;
import org.dromara.testhub.sdk.action.model.rule.TestHubAction;
import org.dromara.testhub.sdk.action.model.rule.TestHubExecute;
import org.apache.commons.lang3.StringUtils;

public class SleepJsonExecuteParser implements BaseJsonExecuteParser {
    private static String SLEEP_TIME = "sleepTime";

    @Override
    public TestHubExecute json2model(JSONObject element, TestHubExecute execute, TestHubAction action) {
        TestHubExecuteSleep executeSleep = new TestHubExecuteSleep(execute);
        String sleepTime = element.getString(SLEEP_TIME);
        if (StringUtils.isNotEmpty(sleepTime)) {
            executeSleep.setSleepTime(Long.parseLong(sleepTime));
        }else {
            executeSleep.setSleepTime(1000L);
        }
        return executeSleep;
    }

    @Override
    public JSONObject model2json(TestHubExecute execute, TestHubAction action) {
        TestHubExecuteSleep executeSleep = (TestHubExecuteSleep)execute;
        JSONObject extendInfo = new JSONObject();
        extendInfo.put(SLEEP_TIME,executeSleep.getSleepTime());
        return extendInfo;
    }
}
