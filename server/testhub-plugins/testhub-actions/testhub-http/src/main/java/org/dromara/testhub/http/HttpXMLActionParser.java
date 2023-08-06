package org.dromara.testhub.http;

import com.goddess.nsrule.core.constant.RuleException;
import com.goddess.nsrule.core.executer.mode.base.bound.FreeMarker;
import com.goddess.nsrule.core.parser.BoundParser;
import com.goddess.nsrule.core.parser.BoundParserFreeMarker;
import com.goddess.nsrule.parserXml.XMLRuleConfigBuilder;
import org.dromara.testhub.http.model.Body;
import org.dromara.testhub.http.model.HttpModel;
import org.dromara.testhub.http.model.TestHubActionHttp;
import org.dromara.testhub.sdk.BaseXMLActionParser;
import org.dromara.testhub.sdk.model.rule.TestHubAction;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Element;
import org.dom4j.tree.AbstractNode;

public class HttpXMLActionParser implements BaseXMLActionParser {
    private static BoundParser<String, FreeMarker> boundParser = new BoundParserFreeMarker();
    @Override
    public TestHubAction xml2Model(Element element, TestHubAction action) {
        TestHubActionHttp actionHttp = new TestHubActionHttp(action);
        actionHttp.setHttpModel(parseHttpModel(element.element("httpModel"), action));
        return actionHttp;
    }

    @Override
    public Element model2xml(Element element, TestHubAction action) {
        return element;
    }


    private HttpModel parseHttpModel(Element element, TestHubAction action) {
        HttpModel httpModel = new HttpModel();
        httpModel.setUrl(element.attributeValue("url"));
        httpModel.setMethod(element.attributeValue("method"));
        httpModel.setHeaders(XMLRuleConfigBuilder.parseParams(element.element("headers")));
        httpModel.setParams(XMLRuleConfigBuilder.parseParams(element.element("params")));
        httpModel.setRests(XMLRuleConfigBuilder.parseParams(element.element("rests")));
        httpModel.setBody(parseBody(element.element("body"), action));
        return httpModel;
    }
    private Body parseBody(Element element, TestHubAction action) {
        Body body = new Body();
        if (element == null) {
            body.setType(Body.NONE);
            return body;
        }
        body.setType(element.attributeValue("type"));
        if (StringUtils.isEmpty(body.getType()) || Body.NONE.equalsIgnoreCase(body.getType())) {
            body.setType(Body.NONE);
            return body;
        }
        if (!Body.TYPES.contains(body.getType())) {
            throw new RuleException(action.getCode() + "中Body的type存在不支持的类型:" + body.getType());
        }
        if (Body.FROM_DATA.equalsIgnoreCase(body.getType())) {
            body.setDatas(XMLRuleConfigBuilder.parseParams(element));
        } else if (Body.X_WWW_FORM_URENCODED.equalsIgnoreCase(body.getType())) {
            body.setDatas(XMLRuleConfigBuilder.parseParams(element));
        } else if (Body.ROW.equalsIgnoreCase(body.getType())) {
            body.setLanguage(StringUtils.isEmpty(element.attributeValue("language"))?"json":element.attributeValue("language"));
            Element bound = element.element("bound");
            StringBuilder template = new StringBuilder();
            for (Object node : bound.content()) {
                int type = ((AbstractNode) node).getNodeType();
                if (type == 3 || type == 4) {
                    //Text //CDATA
                    String text = ((AbstractNode) node).getText();
                    template.append(text);
                } else if (type == 1) {
                    throw new RuleException("bound中仅支持Text和CDATA");
                }
            }
            body.setBound(boundParser.parser(template.toString()));

            String text = bound.asXML();
            text =text.replace("<bound>","");
            text =text.replace("</bound>","");
            text =text.replace("<![CDATA[","");
            text =text.replace("]]>","");
            body.setContent(text);
        }

        return body;
    }

}
