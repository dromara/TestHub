package org.dromara.testhub.plugins.sql.actions;

import org.dromara.testhub.sdk.action.BaseXMLExecuteParser;
import org.dromara.testhub.sdk.action.model.rule.TestHubAction;
import org.dromara.testhub.sdk.action.model.rule.TestHubExecute;
import org.dromara.testhub.plugins.sql.actions.model.TestHubExecuteSql;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Element;


public class SqlXMLExecuteParser implements BaseXMLExecuteParser {
    @Override
    public TestHubExecute xml2model(Element element, TestHubExecute execute, TestHubAction action) {
        TestHubExecuteSql executeSql = new TestHubExecuteSql(execute);
        executeSql.setConKey(StringUtils.isBlank(element.attributeValue("conKey")) ? element.attributeValue("code") : element.attributeValue("conKey"));
        executeSql.setCommit(!StringUtils.isBlank(element.attributeValue("commit")) && Boolean.parseBoolean(element.attributeValue("commit")));
        return executeSql;
    }

    @Override
    public Element model2xml(Element element, TestHubExecute execute, TestHubAction action) {
        return null;
    }

}
