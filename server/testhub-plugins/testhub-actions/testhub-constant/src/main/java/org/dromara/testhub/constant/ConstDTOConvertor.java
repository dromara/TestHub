package org.dromara.testhub.constant;

import com.alibaba.fastjson.JSONObject;
import org.dromara.testhub.constant.model.TestHubActionConst;
import org.dromara.testhub.sdk.BaseDTOConvertor;
import org.dromara.testhub.sdk.model.rule.TestHubAction;
import org.dromara.testhub.sdk.model.rule.TestHubExecute;

public class ConstDTOConvertor implements BaseDTOConvertor {
    @Override
    public Object model2Res(TestHubExecute execute) {
       return new JSONObject();
    }

    @Override
    public Object model2Res(TestHubAction action) {
        JSONObject extraInto = new JSONObject();
        TestHubActionConst model = (TestHubActionConst)action;
        if(model.getBound()!=null){
            extraInto.put("bound",model.getText());
        }
        return extraInto;
    }

}
