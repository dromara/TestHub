package org.dromara.testhub.nsrule.core.executer.mode.base.action;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.dromara.testhub.nsrule.core.executer.context.Context;
import org.dromara.testhub.nsrule.core.executer.mode.BasePo;
import org.dromara.testhub.nsrule.core.executer.mode.base.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 行为
 */

public abstract class Action extends BasePo {


    private List<Param> params;
    private Map<String, Param> paramMap;
    private List<Mapping> mappings;
    private Map<String, Mapping> mappingMap;


    private int complex;
    private String dataType;


    public abstract void extend(Context context, JSONObject data, Execute execute, RunState.Item runState) throws Exception;

    public void execute(Context context, Execute execute, JSONObject paramsData, RunState.Item runState) {
        Result<Object> result = runState.getResult();
        //验证参数
        Param.checkParam(paramsData, getParams());
        JSONObject data = null;
        if (paramsData.isEmpty()) {
            data = Param.buildParams(context, getParams(), context.getData());
        } else {
            data = Param.buildParams(context, getParams(), paramsData);
        }
        context.pushData(data);
        try {
            extend(context, data, execute, runState);
        } catch (Exception e) {
            result.setException(e);
        }
        context.removeData();
        if(CollectionUtil.isEmpty(mappings)){

            if (complex < 1 && result.getContent() instanceof List) {
                if(((List<?>) result.getContent()).isEmpty()){
                    result.setContent(new ArrayList<>());
                }else {
                    result.setContent(((List<?>) result.getContent()).get(0));
                }
            } else if (complex > 0 && !(result.getContent() instanceof List)) {
                List<Object> datas = new ArrayList<>();
                datas.add(result.getContent());
                result.setContent(datas);
            }
        }else {
            if (complex < 1 && result.getContent() instanceof List) {
                if(((List<?>) result.getContent()).isEmpty()){
                    result.setContent(new ArrayList<>());
                }else {
                    result.setContent(Util.initOne(context,((List<Map<String, Object>>) result.getContent()).get(0),mappings,this.dataType));
                }
            } else if (complex > 0 && !(result.getContent() instanceof List)) {
                List<Object> datas = new ArrayList<>();
                datas.add(result.getContent());
                result.setContent(datas);
            }
        }

    }


    public int getComplex() {
        return complex;
    }

    public void setComplex(int complex) {
        this.complex = complex;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public List<Mapping> getMappings() {
        return mappings;
    }

    public void setMappings(List<Mapping> mappings) {
        if(CollectionUtil.isEmpty(mappings)){
            this.mappingMap = new HashMap<>();
            this.mappings = new ArrayList<>();
            return;
        }
        this.mappingMap = mappings.stream().collect(Collectors.toMap(Mapping::getCode, param -> param));
        this.mappings = mappings;
    }

    public Map<String, Param> getParamMap() {
        return paramMap;
    }

    public Map<String, Mapping> getMappingMap() {
        return mappingMap;
    }


    public List<Param> getParams() {
        return params;
    }

    public void setParams(List<Param> params) {
        if(CollectionUtil.isEmpty(params)){
            this.paramMap = new HashMap<>();
            this.params = new ArrayList<>();
            return;
        }
        this.params = params;
        this.paramMap = params.stream().collect(Collectors.toMap(Param::getCode, param -> param));
    }

}
