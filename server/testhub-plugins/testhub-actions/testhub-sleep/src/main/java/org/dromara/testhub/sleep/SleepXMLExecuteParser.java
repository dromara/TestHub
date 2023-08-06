package org.dromara.testhub.sleep;


import org.dromara.testhub.sdk.BaseXMLExecuteParser;
import org.dromara.testhub.sdk.model.rule.TestHubAction;
import org.dromara.testhub.sdk.model.rule.TestHubExecute;
import org.dromara.testhub.sleep.model.TestHubExecuteSleep;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Element;

public class SleepXMLExecuteParser implements BaseXMLExecuteParser {

    @Override
    public TestHubExecute xml2model(Element element, TestHubExecute execute, TestHubAction action) {
        TestHubExecuteSleep executeSleep = new TestHubExecuteSleep(execute);
        String sleepTime = element.attributeValue("sleepTime");
        if (StringUtils.isNotEmpty(sleepTime)) {
            executeSleep.setSleepTime(Long.parseLong(sleepTime));
        }else {
            executeSleep.setSleepTime(1000L);
        }
        return executeSleep;
    }

    @Override
    public Element model2xml(Element element, TestHubExecute execute, TestHubAction action) {
        return null;
    }
}
