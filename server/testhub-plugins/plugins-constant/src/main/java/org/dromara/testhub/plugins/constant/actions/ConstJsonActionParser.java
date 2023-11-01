package org.dromara.testhub.plugins.constant.actions;

import com.alibaba.fastjson.JSONObject;
import com.goddess.nsrule.core.executer.mode.base.bound.FreeMarker;
import com.goddess.nsrule.core.parser.BoundParser;
import com.goddess.nsrule.core.parser.BoundParserFreeMarker;
import org.dromara.testhub.plugins.constant.actions.model.TestHubActionConst;
import org.dromara.testhub.sdk.action.BaseJsonActionParser;
import org.dromara.testhub.sdk.action.model.rule.TestHubAction;
import org.apache.commons.lang3.StringUtils;


public class ConstJsonActionParser implements BaseJsonActionParser {
    private static BoundParser<String, FreeMarker> boundParser = new BoundParserFreeMarker();
    private static String BOUND = "bound";
    @Override
    public TestHubAction json2Model(JSONObject element, TestHubAction action) {
        TestHubActionConst actionConst = new TestHubActionConst(action);
        String boundStr = element.getString(BOUND);
        if(StringUtils.isEmpty(boundStr)){
            return actionConst;
        }

        actionConst.setBound(boundParser.parser(boundStr.toString()));
        actionConst.setText(boundStr);

        return actionConst;

    }

    @Override
    public JSONObject model2json(TestHubAction action) {
        TestHubActionConst actionConst = ( TestHubActionConst)action;
        JSONObject element = new JSONObject();
        element.put(BOUND,actionConst.getText());
        return element;
    }
}
