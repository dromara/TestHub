package org.dromara.testhub.sdk.parser;

import com.goddess.nsrule.core.executer.mode.base.action.Action;
import com.goddess.nsrule.core.executer.mode.base.action.Execute;
import com.goddess.nsrule.core.parser.ExecuteParser;
import org.dromara.testhub.common.exception.TestHubException;
import org.dromara.testhub.sdk.Plugin;
import org.dromara.testhub.sdk.PluginFactory;
import org.dromara.testhub.sdk.model.rule.TestHubAction;
import org.dromara.testhub.sdk.model.rule.TestHubExecute;
import org.dom4j.Element;

import java.util.*;

public class TestHubExecuteParser extends ExecuteParser<Element> {

    public TestHubExecuteParser() {
        super(null);
    }

    @Override
    public Execute parse(Element item, Execute execute, Map<String, Action> actionMap) {
        TestHubExecute testHubExecute = new TestHubExecute(execute);
        TestHubAction testHubAction = (TestHubAction) actionMap.get(execute.getActionCode());
        Plugin plugin = PluginFactory.getHandler(testHubAction.getType());
        return plugin.getXMLExecuteParser().xml2model(item, testHubExecute,testHubAction);
    }

    @Override
    public Element reParse(Element item, Execute execute, Map<String, Action> actionMap) {
        TestHubExecute testHubExecute = (TestHubExecute) execute;
        TestHubAction testHubAction = (TestHubAction) actionMap.get(execute.getActionCode());
        if (testHubAction == null) {
            throw new TestHubException("步骤" + execute.getCode() + "要执行的行为" + execute.getActionCode() + "找不到");
        }
        Plugin plugin = PluginFactory.getHandler(testHubAction.getType());
        return plugin.getXMLExecuteParser().model2xml(item, testHubExecute,testHubAction);
    }

}
