package org.dromara.testhub.plugins.check.actions;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.goddess.nsrule.core.executer.mode.ruleLine.Expression;
import com.goddess.nsrule.core.executer.mode.ruleLine.RuleLine;
import org.dromara.testhub.plugins.check.actions.model.CheckItem;
import org.dromara.testhub.plugins.check.actions.model.TestHubExecuteCheck;
import org.dromara.testhub.sdk.action.BaseJsonExecuteParser;
import org.dromara.testhub.sdk.action.model.rule.TestHubAction;
import org.dromara.testhub.sdk.action.model.rule.TestHubExecute;

import java.util.ArrayList;
import java.util.List;

public class CheckJsonExecuteParser implements BaseJsonExecuteParser {
    private static String RULE_LINES = "ruleLines";

    @Override
    public TestHubExecute json2model(JSONObject element, TestHubExecute execute, TestHubAction action) {
        TestHubExecuteCheck executeCheck = new TestHubExecuteCheck(execute);
        String extendInfo = element.getString(RULE_LINES);
        List<RuleLine<CheckItem>> ruleLines = parseJsonString(extendInfo);
        executeCheck.setRuleLines(ruleLines);
        return executeCheck;
    }
    private static List<RuleLine<CheckItem>> parseJsonString(String jsonString) {
        List<RuleLine<CheckItem>> ruleLines = new ArrayList<>();

        JSONArray jsonArray = JSON.parseArray(jsonString);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            RuleLine<CheckItem> ruleLine = new RuleLine<>();
            ruleLine.setResult(jsonObject.getObject("result", CheckItem.class));
            ruleLine.setExpression(jsonObject.getObject("expression", Expression.class));

            ruleLines.add(ruleLine);
        }

        return ruleLines;
    }

    @Override
    public JSONObject model2json( TestHubExecute execute, TestHubAction action) {
        TestHubExecuteCheck executeCheck = (TestHubExecuteCheck)execute;
        JSONObject ruleLines = new JSONObject();
        ruleLines.put(RULE_LINES, JSON.parseArray(JSONObject.toJSONString( executeCheck.getRuleLines())));
        return ruleLines;
    }
}
