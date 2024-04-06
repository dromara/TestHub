package org.dromara.testhub.plugins.constant.actions;

import org.dromara.testhub.nsrule.core.constant.RuleException;
import org.dromara.testhub.nsrule.core.executer.mode.base.bound.FreeMarker;
import org.dromara.testhub.nsrule.core.parser.BoundParser;
import org.dromara.testhub.nsrule.core.parser.BoundParserFreeMarker;
import org.dromara.testhub.plugins.constant.actions.model.TestHubActionConst;
import org.dromara.testhub.sdk.action.BaseXMLActionParser;
import org.dromara.testhub.sdk.action.model.rule.TestHubAction;
import org.dom4j.Element;
import org.dom4j.tree.AbstractNode;

public class ConstXMLActionParser implements BaseXMLActionParser {
    private static BoundParser<String, FreeMarker> boundParser = new BoundParserFreeMarker();
    @Override
    public TestHubAction xml2Model(Element element, TestHubAction action) {
        TestHubActionConst actionConst = new TestHubActionConst(action);
        Element bound = element.element("bound");
        if(bound==null){
            throw new RuleException(action.getCode()+"必须包含bound");
        }

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
        actionConst.setBound(boundParser.parser(template.toString()));
        String text = bound.asXML();
        text =text.replace("<bound>","");
        text =text.replace("</bound>","");
        text =text.replace("<![CDATA[","");
        text =text.replace("]]>","");
        actionConst.setText(text);

        return actionConst;
    }

    @Override
    public Element model2xml(Element element, TestHubAction action) {
        return element;
    }
}
