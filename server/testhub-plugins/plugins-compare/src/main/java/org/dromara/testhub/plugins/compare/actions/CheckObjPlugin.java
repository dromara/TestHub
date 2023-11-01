package org.dromara.testhub.plugins.compare.actions;


import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.goddess.nsrule.core.executer.context.Context;
import com.goddess.nsrule.core.executer.mode.base.Result;
import com.goddess.nsrule.core.executer.mode.base.action.RunState;
import org.dromara.testhub.plugins.compare.actions.model.CheckObj;
import org.dromara.testhub.plugins.compare.actions.model.CheckResultObj;
import org.dromara.testhub.plugins.compare.actions.model.TestHubExecuteCheckObj;
import org.dromara.testhub.plugins.compare.actions.util.DiffTool;
import org.dromara.testhub.sdk.action.BaseExecuteResultHandler;
import org.dromara.testhub.sdk.action.BaseJsonExecuteParser;
import org.dromara.testhub.sdk.action.BaseXMLExecuteParser;
import org.dromara.testhub.sdk.action.Plugin;
import org.dromara.testhub.sdk.action.model.rule.TestHubAction;
import org.dromara.testhub.sdk.action.model.rule.TestHubExecute;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;


public class CheckObjPlugin implements Plugin {

    @Override
    public String getType() {
        return "CHECK_OBJ";
    }

    @Override
    public BaseXMLExecuteParser getXMLExecuteParser(){
        return new CheckObjXMLExecuteParser();
    }
    @Override
    public BaseJsonExecuteParser getJsonExecuteParser(){
        return new CheckObjJsonExecuteParser();
    }

    @Override
    public BaseExecuteResultHandler getExecuteResultHandler() {
        return new CheckObjExecuteResultHandler();
    }


    @Override
    public void execute(Context context, TestHubAction action, TestHubExecute execute, JSONObject paramDatas, RunState.Item runState) {
        TestHubExecuteCheckObj executeCheckObj = (TestHubExecuteCheckObj) execute;
        Result<Object> result2 = runState.getResult();
        Map<String, Boolean> reData = new HashMap<>();
        result2.setContent(reData);

        DiffTool diffTool = DiffTool.DiffToolBuilder.aDiffTool().withDeep(true).build();

        for (CheckObj checkObj : executeCheckObj.getCheckObjs()) {

            CheckResultObj checkResultObj = new CheckResultObj();
            checkResultObj.setCheckObj(checkObj);

            //记录结果
            reData.put(checkObj.getCode(), false);
            //记录运行参数
            runState.addRunParams(checkObj.getCode(), checkResultObj);

            Object cover = checkObj.getCoverFormula().apply(context).getContent();
            Object threshold = checkObj.getThresholdFormula().apply(context).getContent();

            if (cover != null && threshold != null) {
                try {
                    cover = JSONObject.parseObject(JSONObject.toJSONString(cover), TreeMap.class);

                } catch (Exception e) {
                    try {
                        cover = JSONArray.parseArray(JSONObject.toJSONString(cover), TreeMap.class);
                    } catch (Exception e2) {
                    }
                }
                checkResultObj.setCover(cover);

                try {
                    threshold = JSONObject.parseObject(JSONObject.toJSONString(threshold), TreeMap.class);
                } catch (Exception e) {
                    try {
                        threshold = JSONObject.parseArray(JSONObject.toJSONString(threshold), TreeMap.class);
                    } catch (Exception e2) {

                    }
                }
                checkResultObj.setThreshold(threshold);
                try {
                    Map<String, DiffTool.Node<Object>> nodeMap = diffTool.compare(cover, threshold);
                    if (!CollectionUtil.isEmpty(nodeMap)) {
                        checkResultObj.setMsg(nodeMap.keySet().stream().collect(Collectors.joining(", ")) + "存在不同");
                        result2.setBizError(true);
                    } else {
                        //成功了覆盖 记录结果
                        reData.put(checkObj.getCode(), true);
                        checkResultObj.setFlag(true);
                    }
                } catch (Exception e) {
                    checkResultObj.setMsg(e.getMessage());
                    result2.setBizError(true);
                }
            } else if (cover == null && threshold != null) {
                result2.setBizError(true);
            } else if (cover != null && threshold == null) {
                result2.setBizError(true);
            }

        }
    }

    public static void main(String[] args) {
        JSONObject cover = JSONObject.parseObject("{\"id\":3,\"orderCode\":\"123\",\"orderQur\":123,\"stkId\":\"123\",\"users\":[{\"code\":\"0001\",\"name\":\"vinc\"},{\"code\":\"0002\",\"name\":\"崔胜利\"}]}");
        JSONObject threshold = JSONObject.parseObject("{\"id\":2,\"orderCode\":\"123\",\"orderQur\":123,\"stkId\":\"123\",\"users\":[{\"code\":\"0001\",\"name\":\"vinc\"},{\"code\":\"0002\",\"name\":\"崔胜利\"}]}");
        DiffTool diffTool = DiffTool.DiffToolBuilder.aDiffTool().withDeep(true).build();
        Map<String, DiffTool.Node<Object>> nodeMap = diffTool.compare(cover, threshold);

        System.out.println(JSONObject.toJSONString(nodeMap));

    }


}
