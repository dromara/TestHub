package org.dromara.testhub.http;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONObject;
import com.goddess.nsrule.core.executer.mode.base.bound.Bound;
import com.goddess.nsrule.core.executer.mode.base.bound.FreeMarker;
import com.goddess.nsrule.core.parser.BoundParser;
import com.goddess.nsrule.core.parser.BoundParserFreeMarker;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.dromara.testhub.http.model.Body;
import org.dromara.testhub.http.model.HttpModel;
import org.dromara.testhub.http.model.TestHubActionHttp;
import org.dromara.testhub.sdk.BaseJsonActionParser;
import org.dromara.testhub.sdk.model.rule.TestHubAction;
import org.apache.commons.lang3.StringUtils;


public class HttpJsonActionParser implements BaseJsonActionParser {
    public static String HTTP_MODEL = "httpModel";
    private static BoundParser<String, FreeMarker> boundParser = new BoundParserFreeMarker();

    @Override
    public TestHubAction json2Model(JSONObject element, TestHubAction action) {
        TestHubActionHttp actionHttp = new TestHubActionHttp(action);
        String extendInfo = element.getString(HTTP_MODEL);
        if (StringUtils.isEmpty(extendInfo)) {
            return actionHttp;
        }
        actionHttp.setHttpModel(JSONObject.parseObject(extendInfo, HttpModel.class));
        if (Body.RAW.equalsIgnoreCase(actionHttp.getHttpModel().getBody().getType())) {
            Bound bound = boundParser.parser(actionHttp.getHttpModel().getBody().getContent());
            actionHttp.getHttpModel().getBody().setBound(bound);
        }
        initDataFormulaNode(actionHttp);
        return actionHttp;
    }

    @Override
    public JSONObject model2json(TestHubAction action) {
        TestHubActionHttp actionHttp = JSONObject.parseObject(JSONObject.toJSONString(action),TestHubActionHttp.class);
        actionHttp.getHttpModel().getBody().setBound(null);
        if(CollectionUtil.isNotEmpty(actionHttp.getHttpModel().getRests())){
            actionHttp.getHttpModel().getRests().forEach(o->o.setDataFormulaNode(null));
        }
        if(CollectionUtil.isNotEmpty(actionHttp.getHttpModel().getHeaders())){
            actionHttp.getHttpModel().getHeaders().forEach(o->o.setDataFormulaNode(null));
        }
        if(CollectionUtil.isNotEmpty(actionHttp.getHttpModel().getParams())){
            actionHttp.getHttpModel().getParams().forEach(o->o.setDataFormulaNode(null));
        }
        if(actionHttp.getHttpModel().getBody()!=null){
            if(CollectionUtil.isNotEmpty(actionHttp.getHttpModel().getBody().getDatas())){
                actionHttp.getHttpModel().getBody().getDatas().forEach(o->o.setDataFormulaNode(null));
            }
        }

        JSONObject element = new JSONObject();
        element.put(HTTP_MODEL, actionHttp.getHttpModel());

        return element;
    }

    private void initDataFormulaNode(TestHubActionHttp actionHttp){
        if(CollectionUtil.isNotEmpty(actionHttp.getHttpModel().getRests())){
            actionHttp.getHttpModel().getRests().forEach(o->o.setData(o.getData()));
        }
        if(CollectionUtil.isNotEmpty(actionHttp.getHttpModel().getHeaders())){
            actionHttp.getHttpModel().getHeaders().forEach(o->o.setData(o.getData()));
        }
        if(CollectionUtil.isNotEmpty(actionHttp.getHttpModel().getParams())){
            actionHttp.getHttpModel().getParams().forEach(o->o.setData(o.getData()));
        }
        if(actionHttp.getHttpModel().getBody()!=null){
            if(CollectionUtil.isNotEmpty(actionHttp.getHttpModel().getBody().getDatas())){
                actionHttp.getHttpModel().getBody().getDatas().forEach(o->o.setData(o.getData()));
            }
            actionHttp.getHttpModel().getBody().arrange();
        }
    }

}
