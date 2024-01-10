package org.dromara.testhub.plugins.convert.actions;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import org.dromara.testhub.nsrule.core.executer.context.Context;
import org.dromara.testhub.nsrule.core.executer.mode.base.Result;
import org.dromara.testhub.nsrule.core.executer.mode.base.action.RunState;
import org.dromara.testhub.plugins.convert.actions.model.Convert;
import org.dromara.testhub.plugins.convert.actions.model.TestHubActionConvert;
import org.dromara.testhub.plugins.convert.actions.util.JSONAttrUtil;
import org.dromara.testhub.sdk.action.BaseJsonActionParser;
import org.dromara.testhub.sdk.action.BaseXMLActionParser;
import org.dromara.testhub.sdk.action.Plugin;
import org.dromara.testhub.sdk.action.model.rule.TestHubAction;
import org.dromara.testhub.sdk.action.model.rule.TestHubExecute;

import java.util.*;

public class ConvertPlugin implements Plugin {
    private static String TYPE_DEL = "DEL";
    private static String TYPE_PUT = "PUT";
    public static List<String> TYPES = Arrays.asList(new String[]{TYPE_DEL,TYPE_PUT});

    @Override
    public String getType() {
        return "CONVERT";
    }

    @Override
    public BaseXMLActionParser getXMLActionParser() {
        return new ConvertXMLActionParser();
    }

    @Override
    public BaseJsonActionParser getJsonActionParser(){return new ConvertJsonActionParser();}


    @Override
    public void execute(Context context, TestHubAction action, TestHubExecute execute, JSONObject paramDatas, RunState.Item runState) {
        TestHubActionConvert actionConst = (TestHubActionConvert)action;
        Result<Object> result = runState.getResult();
        List<Map<String, Object>> redMaps = new ArrayList<>();
        result.setContent(redMaps);
        Object sourceObj = context.getObject("$.source");
        if(sourceObj==null){
            return;
        }
        try {
            JSONObject.toJSONString(sourceObj);
        }catch (Exception e){
            Map<String, Object> reData = new HashMap<>();
            reData.put("val",sourceObj);
            redMaps.add(reData);
            return;
        }

        if(sourceObj instanceof List){
            int max = 1;
            JSONArray datas =  JSONArray.parseArray(JSONObject.toJSONString(sourceObj));
            if(action.getComplex()>=1){
                max = datas.size();
            }
            for(int index=0;index<max;index++){
                Map<String, Object> reData = new HashMap<>();
                redMaps.add(reData);
                JSONObject data = new JSONObject();
                data.put("index",index);
                data.put("item",datas.get(index));
                context.pushData(data);
                execOne(context,actionConst,reData,runState,index);
                context.removeData();
            }
        }else {
            Map<String, Object> reData = new HashMap<>();
            redMaps.add(reData);
            JSONObject data = new JSONObject();
            data.put("index",0);
            data.put("item",sourceObj);
            context.pushData(data);
            execOne(context,actionConst,reData,runState,0);
            context.removeData();
        }
    }
    private void execOne(Context context, TestHubActionConvert action, Map<String, Object> reData, RunState.Item runState,int index){
        List<Convert> converts = action.getConverts();
        Object sourceObj = context.getObject("$.source");
        Object item = context.getObject("$.item");
        JSONObject itemJson = null;
        try {
            itemJson = JSONObject.parseObject(JSONObject.toJSONString(item));
        }catch (Exception e){
            reData.put("val",item);
            return;
        }
        for(Convert convert:converts){
            if(TYPE_DEL.equalsIgnoreCase(convert.getType())){
                del(itemJson,convert,runState,index);
            }else if(TYPE_PUT.equalsIgnoreCase(convert.getType())){
                put(context,itemJson,convert,runState,index);
            }
        }
        reData.putAll(itemJson.getInnerMap());
    }
    private void del(JSONObject item, Convert convert, RunState.Item runState, int index){
        String[] pars = convert.getCode().trim().split("].");
        if(pars.length==1){
            //xxx[*]      xxxx[1]     xxxx[1,2]     xxxx
            //直接操作一个属性项的
            delAttr(item,pars[0]);
            Map<String,List<String>> map = new HashMap<>();
            map.put(convert.getCode(), Arrays.asList(new String[]{pars[0]}));
            runState.addRunParams(index+"",map);
        }else {
            //存在列表的
            List<String> paths = JSONAttrUtil.getAttrs(Arrays.asList(pars),item);
            Map<String,List<String>> map = new HashMap<>();
            map.put(convert.getCode(),paths);
            runState.addRunParams(index+"",map);
            for(String p:paths){
                delAttr(item,p);
            }
        }
    }

    private void delAttr(JSONObject item,String path){
        try {
            JSONPath.remove(item,path);
        }catch (Exception e){
            e.printStackTrace();
            //尝试去删除 找不到抛出异常 也认为是删除了
        }
    }
    private void put(Context context, JSONObject item,Convert convert, RunState.Item runState,int index){
        Object data = null;
        if(convert.getDataFormula()==null){
            data = convert.getData();
        }else {
            data = convert.getDataFormula().apply(context).getContent();
        }
        String[] pars = convert.getCode().trim().split("].");
        if(pars.length==1){
            //xxx[*]      xxxx[1]     xxxx[1,2]     xxxx
            //直接操作一个属性项的
            try {
                JSONPath.set(item,pars[0],data);
                Map<String,List<String>> map = new HashMap<>();
                map.put(convert.getCode(),Arrays.asList(new String[]{pars[0]}));
                runState.addRunParams(index+"",map);
            }catch (Exception e){
                //设置的属性不存在，并且不是在根下 则会抛出这种错误 这个时候让他设置失败就好了
            }
        }else {
            //存在列表的
            List<String> paths = JSONAttrUtil.getAttrs(Arrays.asList(pars),item);
            Map<String,List<String>> map = new HashMap<>();
            map.put(convert.getCode(),paths);
            runState.addRunParams(index+"",map);
            for(String path:paths){
                put(item,path,data);
            }
        }
    }
    private void put(JSONObject item,String path,Object data){
        String basePath = path.substring(0,path.lastIndexOf("["));
        String indexStr = path.substring(path.lastIndexOf("[")+1,path.lastIndexOf("]"));
        String attr = path.substring(path.lastIndexOf(".")+1);
        List<Object> temps = (List<Object>)JSONPath.eval(item,basePath);
        JSONPath.remove(item,basePath);
//        JSONObject temp = (JSONObject)temps.get(Integer.parseInt(indexStr));
        //关键这里一定要新new一个不然会乱  神奇 神奇 神奇
        Map<String,Object> temp =new HashMap<>(((Map<String,Object>) temps.remove(Integer.parseInt(indexStr))));
        temp.put(attr,data);
        temps.add(Integer.parseInt(indexStr),temp);
        JSONPath.set(item,basePath,temps);
    }
}
