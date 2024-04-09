package org.dromara.testhub.plugins.sleep.actions;


import org.dromara.testhub.plugins.sleep.actions.model.TestHubExecuteSleep;
import org.dromara.testhub.sdk.action.BaseXMLExecuteParser;
import org.dromara.testhub.sdk.action.model.rule.TestHubAction;
import org.dromara.testhub.sdk.action.model.rule.TestHubExecute;
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
