package org.dromara.testhub.plugins.http.actions;

import com.alibaba.fastjson.JSONObject;
import com.goddess.nsrule.core.executer.mode.ruleLine.Expression;
import org.dromara.testhub.plugins.http.actions.model.TestHubExecuteHttp;
import org.dromara.testhub.sdk.action.BaseJsonExecuteParser;
import org.dromara.testhub.sdk.action.model.rule.TestHubAction;
import org.dromara.testhub.sdk.action.model.rule.TestHubExecute;
import org.apache.commons.lang3.StringUtils;

public class HttpJsonExecuteParser implements BaseJsonExecuteParser {
    private static String EXPRESSION = "expression";

    @Override
    public TestHubExecute json2model(JSONObject element, TestHubExecute execute, TestHubAction action) {
        TestHubExecuteHttp executeHttp = new TestHubExecuteHttp(execute);
        String extendInfo = element.getString(EXPRESSION);
        if(StringUtils.isNotEmpty(extendInfo)){
            executeHttp.setExpression(JSONObject.parseObject(extendInfo,Expression.class));
        }
        return executeHttp;
    }

    @Override
    public JSONObject model2json( TestHubExecute execute, TestHubAction action) {
        TestHubExecuteHttp executeHttp = (TestHubExecuteHttp)execute;
        JSONObject ruleLines = new JSONObject();
        if(executeHttp.getExpression()!=null){
            ruleLines.put(EXPRESSION, JSONObject.toJSONString(executeHttp.getExpression()));
        }
        return ruleLines;
    }
}
