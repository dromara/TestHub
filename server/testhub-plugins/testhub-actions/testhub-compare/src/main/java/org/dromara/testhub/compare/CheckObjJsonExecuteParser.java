package org.dromara.testhub.compare;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.dromara.testhub.compare.model.CheckObj;
import org.dromara.testhub.compare.model.TestHubExecuteCheckObj;
import org.dromara.testhub.sdk.BaseJsonExecuteParser;
import org.dromara.testhub.sdk.model.rule.TestHubAction;
import org.dromara.testhub.sdk.model.rule.TestHubExecute;

import java.util.List;


public class CheckObjJsonExecuteParser implements BaseJsonExecuteParser {
    private static String CHECK_OBJS = "checkObjs";

    @Override
    public TestHubExecute json2model(JSONObject element, TestHubExecute execute, TestHubAction action) {
        TestHubExecuteCheckObj executeCheck = new TestHubExecuteCheckObj(execute);
        String extendInfo = element.getString(CHECK_OBJS);
        List<CheckObj> checkObjs = JSONArray.parseArray(extendInfo, CheckObj.class);
        executeCheck.setCheckObjs(checkObjs);
        return executeCheck;
    }
    @Override
    public JSONObject model2json( TestHubExecute execute, TestHubAction action) {
        TestHubExecuteCheckObj executeCheck = (TestHubExecuteCheckObj)execute;
        JSONObject ruleLines = new JSONObject();
        ruleLines.put(CHECK_OBJS, JSON.parseArray(JSONObject.toJSONString( executeCheck.getCheckObjs())));
        return ruleLines;
    }
}
