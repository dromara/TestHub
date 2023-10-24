package org.dromara.testhub.convert;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.dromara.testhub.convert.model.Convert;
import org.dromara.testhub.convert.model.TestHubActionConvert;
import org.dromara.testhub.sdk.BaseJsonActionParser;
import org.dromara.testhub.sdk.model.rule.TestHubAction;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class ConvertJsonActionParser implements BaseJsonActionParser {
    private static String CONVERTS = "converts";
    @Override
    public TestHubAction json2Model(JSONObject element, TestHubAction action) {
        TestHubActionConvert actionConvert = new TestHubActionConvert(action);
        String extendInfo = element.getString(CONVERTS);
        if(StringUtils.isEmpty(extendInfo)){
            return actionConvert;
        }

        List<Convert> converts = JSONArray.parseArray(extendInfo, Convert.class);
        actionConvert.setConverts(converts);

        return actionConvert;
    }

    @Override
    public JSONObject model2json(TestHubAction action) {
        TestHubActionConvert actionConvert = ( TestHubActionConvert)action;
        JSONObject element = new JSONObject();
        element.put(CONVERTS, actionConvert.getConverts());
        return element;
    }

}
