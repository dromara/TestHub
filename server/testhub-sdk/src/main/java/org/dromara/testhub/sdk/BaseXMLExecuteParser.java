package org.dromara.testhub.sdk;

import org.dromara.testhub.sdk.model.rule.TestHubAction;
import org.dromara.testhub.sdk.model.rule.TestHubExecute;
import org.dom4j.Element;

public interface BaseXMLExecuteParser {

    TestHubExecute xml2model(Element element, TestHubExecute execute, TestHubAction action);

    Element model2xml(Element element, TestHubExecute execute, TestHubAction action);

}
