package org.dromara.testhub.plugins.http.actions;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONObject;
import org.dromara.testhub.plugins.http.actions.model.TestHubActionHttp;
import org.dromara.testhub.sdk.action.BaseDTOConvertor;
import org.dromara.testhub.sdk.action.model.rule.TestHubAction;
import org.dromara.testhub.sdk.action.model.rule.TestHubExecute;

public class HttpDTOConvertor implements BaseDTOConvertor {
    @Override
    public Object model2Res(TestHubExecute execute) {
        return null;
    }

    @Override
    public Object model2Res(TestHubAction action) {
        TestHubActionHttp model = (TestHubActionHttp)action;
        if(CollectionUtil.isNotEmpty(model.getHttpModel().getParams())){
            model.getHttpModel().getParams().forEach(obj -> obj.setId(Long.valueOf(model.getHttpModel().getParams().indexOf(obj)+"")));
        }
        if(CollectionUtil.isNotEmpty(model.getHttpModel().getHeaders())){
            model.getHttpModel().getHeaders().forEach(obj -> obj.setId(Long.valueOf(model.getHttpModel().getHeaders().indexOf(obj)+"")));
        }
        if(model.getHttpModel().getBody()!=null&&CollectionUtil.isNotEmpty(model.getHttpModel().getBody().getDatas())){
            model.getHttpModel().getBody().getDatas().forEach(obj -> obj.setId(Long.valueOf(model.getHttpModel().getBody().getDatas().indexOf(obj)+"")));
        }
        JSONObject extraInto = new JSONObject();
        extraInto.put(HttpJsonActionParser.HTTP_MODEL,model.getHttpModel());
        return extraInto;
    }

}
