package org.dromara.testhub.sdk.action;

import org.dromara.testhub.sdk.action.model.rule.TestHubAction;
import org.dom4j.Element;

public interface BaseXMLActionParser {
    //解析xml配置的行为
    TestHubAction xml2Model(Element element, TestHubAction action);

    //将行为配置写入xml
    Element model2xml(Element element,TestHubAction action);
}
