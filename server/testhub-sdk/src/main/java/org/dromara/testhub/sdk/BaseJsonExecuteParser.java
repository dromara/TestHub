package org.dromara.testhub.sdk;

import com.alibaba.fastjson.JSONObject;
import org.dromara.testhub.sdk.model.rule.TestHubAction;
import org.dromara.testhub.sdk.model.rule.TestHubExecute;

public interface BaseJsonExecuteParser {

    TestHubExecute json2model(JSONObject element, TestHubExecute execute, TestHubAction action);

    JSONObject model2json( TestHubExecute execute, TestHubAction action);

}
