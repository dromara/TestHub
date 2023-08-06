package org.dromara.testhub.sleep;


import com.alibaba.fastjson.JSONObject;
import org.dromara.testhub.sdk.BaseJsonExecuteParser;
import org.dromara.testhub.sdk.model.rule.TestHubAction;
import org.dromara.testhub.sdk.model.rule.TestHubExecute;
import org.dromara.testhub.sleep.model.TestHubExecuteSleep;
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
