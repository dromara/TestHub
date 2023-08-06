package org.dromara.testhub.sdk;

import com.alibaba.fastjson.JSONObject;
import org.dromara.testhub.sdk.model.rule.TestHubAction;

public interface BaseJsonActionParser {
    //解析xml配置的行为
    TestHubAction json2Model(JSONObject element, TestHubAction action);

    //将行为配置写入json
    JSONObject model2json(TestHubAction action);
}
