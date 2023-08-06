package org.dromara.testhub.http;


import com.goddess.nsrule.core.executer.mode.ruleLine.Expression;
import com.goddess.nsrule.parserXml.XMLRuleParser;
import org.dromara.testhub.http.model.TestHubExecuteHttp;
import org.dromara.testhub.sdk.BaseXMLExecuteParser;
import org.dromara.testhub.sdk.model.rule.TestHubAction;
import org.dromara.testhub.sdk.model.rule.TestHubExecute;
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
