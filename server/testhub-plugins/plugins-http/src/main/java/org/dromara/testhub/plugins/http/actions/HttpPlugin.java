package org.dromara.testhub.plugins.http.actions;

import com.alibaba.fastjson.JSONObject;
import org.dromara.testhub.framework.exception.TestHubException;
import org.dromara.testhub.nsrule.core.executer.context.Context;
import org.dromara.testhub.nsrule.core.executer.mode.base.action.RunState;
import org.dromara.testhub.nsrule.core.executer.mode.ruleLine.JavaActuator;
import lombok.extern.slf4j.Slf4j;
import org.dromara.testhub.plugins.http.core.HttpModel;
import org.dromara.testhub.plugins.http.actions.model.TestHubActionHttp;
import org.dromara.testhub.plugins.http.actions.model.TestHubExecuteHttp;
import org.dromara.testhub.plugins.http.core.ResData;
import org.dromara.testhub.plugins.http.core.SendHttp;
import org.dromara.testhub.sdk.action.*;
import org.dromara.testhub.sdk.action.model.rule.TestHubAction;
import org.dromara.testhub.sdk.action.model.rule.TestHubExecute;
import java.net.http.*;
import java.util.*;

@Slf4j
public class HttpPlugin implements Plugin {
    public static JSONObject NONE_INFO = new JSONObject();


    @Override
    public String getType() {
        return "HTTP";
    }

    @Override
    public BaseDTOConvertor getDTOConvertor() {
        return new HttpDTOConvertor();
    }

    @Override
    public BaseXMLExecuteParser getXMLExecuteParser() {
        return new HttpXMLExecuteParser();
    }

    @Override
    public BaseJsonExecuteParser getJsonExecuteParser() {
        return new HttpJsonExecuteParser();
    }

    @Override
    public BaseXMLActionParser getXMLActionParser() {
        return new HttpXMLActionParser();
    }

    @Override
    public BaseJsonActionParser getJsonActionParser() {
        return new HttpJsonActionParser();
    }

    @Override
    public void execute(Context context, TestHubAction action, TestHubExecute execute, JSONObject data, RunState.Item runState) throws Exception {
        TestHubActionHttp actionHttp = (TestHubActionHttp) action;
        TestHubExecuteHttp executeHttp = (TestHubExecuteHttp) execute;
        List<Map<String, Object>> reData = new ArrayList<>();
        runState.getResult().setContent(reData);

        JSONObject result = new JSONObject();
        result.put("data", NONE_INFO);
        result.put("headers", NONE_INFO);
        result.put("cookies", NONE_INFO);

        reData.add(result.getInnerMap());

        HttpModel httpModel = actionHttp.getHttpModel();
        ResData httpResData = new ResData();

        SendHttp.send(context,httpModel,httpResData);


        runState.getResult().setBizError(httpResData.isError());

        runState.addRunParams("reType", httpResData.getReType());
        runState.addRunParams("reqType", httpResData.getReqType());
        runState.addRunParams("method", httpResData.getMethod());
        runState.addRunParams("rests", httpResData.getRests());

        //params拼接
        runState.addRunParams("params", httpResData.getParams());

        //headers计算
        runState.addRunParams("headers", httpResData.getHeaders());
        runState.addRunParams("url", httpResData.getUrl());

        runState.addRunParams("body", httpResData.getBody());


        runState.addRunParams("statusCode", httpResData.getStatusCode());
        runState.addRunParams("statusName", httpResData.getStatusName());

        HttpResponse<byte[]> response = httpResData.getResponse();

        result.put("headers",httpResData.getReHeaders());

        if (response.statusCode() == 200) {
            result.put("data", httpResData.getResData());
            runState.addRunParams("reType", httpResData.getReType());
        } else {
            runState.getResult().setBizError(true);
        }
        result.put("cookies",httpResData.getReCookies());

        runState.addRunParams("result", result);


        if (executeHttp.getExpression() != null) {
            JavaActuator.Log log = new JavaActuator.Log();
            runState.addRunParams("errorLog", log);
            try {
                context.pushData(result);
                boolean flag = JavaActuator.execute(context, executeHttp.getExpression(), log);
                if (!flag) {
                    runState.getResult().setBizError(true);
                }
            } finally {
                context.removeData();
            }
        }
    }

}
