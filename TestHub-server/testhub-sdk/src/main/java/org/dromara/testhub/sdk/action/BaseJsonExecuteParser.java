package org.dromara.testhub.sdk.action;

import com.alibaba.fastjson.JSONObject;
import org.dromara.testhub.sdk.action.model.rule.TestHubAction;
import org.dromara.testhub.sdk.action.model.rule.TestHubExecute;

public interface BaseJsonExecuteParser {

    TestHubExecute json2model(JSONObject element, TestHubExecute execute, TestHubAction action);

    JSONObject model2json( TestHubExecute execute, TestHubAction action);

}
