package org.dromara.testhub.plugins.http.actions;


import org.dromara.testhub.nsrule.core.executer.mode.ruleLine.Expression;
import org.dromara.testhub.nsrule.parserXml.XMLRuleParser;
import org.dromara.testhub.plugins.http.actions.model.TestHubExecuteHttp;
import org.dromara.testhub.sdk.action.BaseXMLExecuteParser;
import org.dromara.testhub.sdk.action.model.rule.TestHubAction;
import org.dromara.testhub.sdk.action.model.rule.TestHubExecute;
import org.dom4j.Element;

public class HttpXMLExecuteParser implements BaseXMLExecuteParser {

    @Override
    public TestHubExecute xml2model(Element element, TestHubExecute execute, TestHubAction action) {
        TestHubExecuteHttp executeHttp = new TestHubExecuteHttp(execute);
        Element expressionDom = element.element("expression");
        if(expressionDom!=null){
            Expression expression = XMLRuleParser.parseExpression(expressionDom);
            executeHttp.setExpression(expression);
        }
        return executeHttp;
    }

    @Override
    public Element model2xml(Element element, TestHubExecute execute, TestHubAction action) {
        return null;
    }
}
