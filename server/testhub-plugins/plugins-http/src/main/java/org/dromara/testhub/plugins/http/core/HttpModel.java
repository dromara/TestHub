package org.dromara.testhub.plugins.http.core;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.dromara.testhub.nsrule.core.constant.RuleException;
import org.dromara.testhub.nsrule.core.executer.context.Context;
import org.dromara.testhub.nsrule.core.executer.context.RuleConfig;
import org.dromara.testhub.nsrule.core.executer.mode.base.Result;
import org.dromara.testhub.nsrule.core.executer.mode.base.action.Param;
import lombok.Data;
import org.dromara.testhub.nsrule.core.executer.mode.base.formula.FormulaNode;
import org.dromara.testhub.plugins.http.actions.model.Body;

import java.util.Arrays;
import java.util.List;

@Data
public class HttpModel {
    private static List<String> ls = Arrays.asList("#","&",":","/");
    private String url;
    private FormulaNode urlFormulaNode;
    private String method;
    private int timeout;

    private List<Param> headers;
    private Body body;
    private List<Param> params;
    private List<Param> cookies;
    private List<Param> rests;

    public void setUrl(String url) {
        this.url = url;

        RuleConfig ruleConfig = RuleConfig.getInstance();
        if (StringUtils.isEmpty(url)) {
            throw new RuleException("http的请求url不能为空");
        } else {
            this.urlFormulaNode = ruleConfig.getFormulaBuilder().getFormulaNode(url);
        }
    }

    public String execUrl(Context context, JSONObject rests){
        try {
            if(!rests.isEmpty()){
                context.pushData(rests);
            }
            Result<Object> result = urlFormulaNode.apply(context);
            if(result.isFlag()){
                return url;
            }else {
                return result.getContent().toString();
            }
        }catch (Exception e){
            return url;
        }finally {
            if(!rests.isEmpty()){
                context.removeData();
            }
        }
    }
}
