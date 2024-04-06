package org.dromara.testhub.plugins.sql.actions;


import com.alibaba.fastjson.JSONObject;
import org.dromara.testhub.sdk.action.BaseJsonExecuteParser;
import org.dromara.testhub.sdk.action.model.rule.TestHubAction;
import org.dromara.testhub.sdk.action.model.rule.TestHubExecute;
import org.dromara.testhub.plugins.sql.actions.model.TestHubExecuteSql;
import org.apache.commons.lang3.StringUtils;

public class SqlJsonExecuteParser implements BaseJsonExecuteParser {
    private static String CON_KEY = "conKey";
    private static String COMMIT = "commit";

    @Override
    public TestHubExecute json2model(JSONObject element, TestHubExecute execute, TestHubAction action) {
        TestHubExecuteSql executeSql = new TestHubExecuteSql(execute);
        executeSql.setConKey(StringUtils.isBlank(element.getString(CON_KEY)) ? execute.getCode() : element.getString(CON_KEY));
        return executeSql;
    }

    @Override
    public JSONObject model2json( TestHubExecute execute, TestHubAction action) {
        TestHubExecuteSql executeSql = (TestHubExecuteSql)execute;
        JSONObject extendInfo = new JSONObject();
        extendInfo.put(CON_KEY,executeSql.getConKey());
        extendInfo.put(COMMIT,executeSql.getCommit());
        return extendInfo;
    }
}
