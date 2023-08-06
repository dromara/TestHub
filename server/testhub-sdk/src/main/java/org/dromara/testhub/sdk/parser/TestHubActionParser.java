package org.dromara.testhub.sdk.parser;

import com.goddess.nsrule.core.executer.mode.base.action.Action;
import com.goddess.nsrule.core.parser.ActionParser;
import org.dromara.testhub.sdk.Plugin;
import org.dromara.testhub.sdk.PluginFactory;
import org.dromara.testhub.sdk.model.rule.TestHubAction;
import org.dom4j.Element;

public class TestHubActionParser implements ActionParser {

    @Override
    public Action parse(Object dataObj, Action action) {
        TestHubAction pipLineAction = new TestHubAction(action);
        Element item = (Element) dataObj;
        String type;
        type = item.attributeValue("type");
        pipLineAction.setType(type);
        Plugin plugin = PluginFactory.getHandler(type);
        return plugin.getXMLActionParser().xml2Model(item, pipLineAction);
    }

}
