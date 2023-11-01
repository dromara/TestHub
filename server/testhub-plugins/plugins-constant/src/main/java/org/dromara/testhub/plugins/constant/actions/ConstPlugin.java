package org.dromara.testhub.plugins.constant.actions;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.goddess.nsrule.core.constant.Constant;
import com.goddess.nsrule.core.executer.context.Context;
import com.goddess.nsrule.core.executer.mode.base.Result;
import com.goddess.nsrule.core.executer.mode.base.action.RunState;
import com.goddess.nsrule.core.executer.operation.Operation;
import org.dromara.testhub.plugins.constant.actions.model.TestHubActionConst;
import org.dromara.testhub.sdk.action.BaseDTOConvertor;
import org.dromara.testhub.sdk.action.BaseJsonActionParser;
import org.dromara.testhub.sdk.action.BaseXMLActionParser;
import org.dromara.testhub.sdk.action.Plugin;
import org.dromara.testhub.sdk.action.model.rule.TestHubAction;
import org.dromara.testhub.sdk.action.model.rule.TestHubExecute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConstPlugin implements Plugin {

    @Override
    public String getType() {
        return "CONST";
    }

    @Override
    public BaseXMLActionParser getXMLActionParser() {
        return new ConstXMLActionParser();
    }

    @Override
    public BaseJsonActionParser getJsonActionParser(){
        return new ConstJsonActionParser();
    }
    @Override
    public BaseDTOConvertor getDTOConvertor() {
        return new ConstDTOConvertor();
    }
    @Override
    public void execute(Context context, TestHubAction action, TestHubExecute execute, JSONObject paramDatas, RunState.Item runState) {
        TestHubActionConst actionConst = (TestHubActionConst)action;
        Result<Object> result =  runState.getResult();
        List<Map<String, Object>> redMaps = new ArrayList<>();
        result.setContent(redMaps);
        Map<String, Object> reData = new HashMap<>();
        String dataStr = actionConst.getBound().build(context);
        String dataType = action.getDataType();
        //基础数据类型
        boolean baseDataType=context.getProject().isBaseDataType(dataType);
        if(baseDataType){
            redMaps.add(reData);
            if(Constant.DataType.NUMBER.equalsIgnoreCase(dataType)){
                reData.put("val", Operation.getNumber(dataStr.trim()));
            }else if(Constant.DataType.STRING.equalsIgnoreCase(dataType)){
                reData.put("val", dataStr.trim());
            }else if(Constant.DataType.TIME_YMDHMS.equalsIgnoreCase(dataType)){
                reData.put("val",Operation.getTimeYdmhms(dataStr.trim()));
            }else if(Constant.DataType.TIME_YMD.equalsIgnoreCase(dataType)){
                reData.put("val", Operation.getTimeYdm(dataStr.trim()));
            }else if(Constant.DataType.TIME_HMS.equalsIgnoreCase(dataType)){
                reData.put("val", Operation.getTimeHms(dataStr.trim()));
            }
            return;
        }
        //Map 或者是自定义的类型
        JSONArray datas = null;
        JSONObject data = null;
        try {
            data =  JSONObject.parseObject(dataStr);
            datas = new JSONArray();
            datas.add(data);
        }catch (Exception e2){
            try {
                datas =  JSONArray.parseArray(dataStr);
            }catch (Exception e){
                redMaps.add(reData);
                reData.put("val",dataStr);
                return;
            }
        }
        if(datas==null){
            redMaps.add(reData);
            reData.put("val",dataStr);
            return;
        }

        if(action.getComplex()>0){
            for (int index=0;index<datas.size();index++){
                JSONObject item = datas.getJSONObject(index);
                redMaps.add(item.getInnerMap());
            }
        }else {
            JSONObject item = datas.getJSONObject(0);
            redMaps.add(item.getInnerMap());
        }

    }
}
