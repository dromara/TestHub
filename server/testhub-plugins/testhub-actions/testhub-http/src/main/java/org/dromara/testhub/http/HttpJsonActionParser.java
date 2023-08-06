package org.dromara.testhub.http;

import com.alibaba.fastjson.JSONObject;
import com.goddess.nsrule.core.executer.mode.base.bound.Bound;
import com.goddess.nsrule.core.executer.mode.base.bound.FreeMarker;
import com.goddess.nsrule.core.parser.BoundParser;
import com.goddess.nsrule.core.parser.BoundParserFreeMarker;
import org.dromara.testhub.http.model.Body;
import org.dromara.testhub.http.model.HttpModel;
import org.dromara.testhub.http.model.TestHubActionHttp;
import org.dromara.testhub.sdk.BaseJsonActionParser;
import org.dromara.testhub.sdk.model.rule.TestHubAction;
import org.apache.commons.lang3.StringUtils;


public class HttpJsonActionParser implements BaseJsonActionParser {
    private static String HTTP_MODEL = "httpModel";
    private static BoundParser<String, FreeMarker> boundParser = new BoundParserFreeMarker();

    @Override
    public TestHubAction json2Model(JSONObject element, TestHubAction action) {
        TestHubActionHttp actionHttp = new TestHubActionHttp(action);
        String extendInfo = element.getString(HTTP_MODEL);
        if (StringUtils.isEmpty(extendInfo)) {
            return actionHttp;
        }
        actionHttp.setHttpModel(JSONObject.parseObject(extendInfo, HttpModel.class));
        if (Body.ROW.equalsIgnoreCase(actionHttp.getHttpModel().getBody().getType())) {
            Bound bound = boundParser.parser(actionHttp.getHttpModel().getBody().getContent());
            actionHttp.getHttpModel().getBody().setBound(bound);
        }

        return actionHttp;
    }

    @Override
    public JSONObject model2json(TestHubAction action) {
        TestHubActionHttp actionHttp = (TestHubActionHttp) action;
        Bound bound = actionHttp.getHttpModel().getBody().getBound();
        actionHttp.getHttpModel().getBody().setBound(null);
        JSONObject element = new JSONObject();
        element.put(HTTP_MODEL, JSONObject.toJSONString(actionHttp.getHttpModel()));
        actionHttp.getHttpModel().getBody().setBound(bound);
        return element;
    }

}
