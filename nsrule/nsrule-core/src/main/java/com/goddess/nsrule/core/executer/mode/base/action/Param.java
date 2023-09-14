package com.goddess.nsrule.core.executer.mode.base.action;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.goddess.nsrule.core.constant.Constant;
import com.goddess.nsrule.core.executer.mode.BaseDataPo;
import com.goddess.nsrule.core.constant.ExceptionCode;
import com.goddess.nsrule.core.constant.RuleException;
import com.goddess.nsrule.core.executer.context.Context;
import com.goddess.nsrule.core.executer.mode.base.formula.DataNode;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 规则入参
 *
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/4 19:28
 */
public class Param extends BaseDataPo {

    //比传参数
    private boolean necessary;


    public boolean isNecessary() {
        return necessary;
    }

    public void setNecessary(boolean necessary) {
        this.necessary = necessary;
    }

    public static void checkParam(JSONObject data, List<Param> params) {
        if (ArrayUtil.isEmpty(params)) {
            return;
        }
        List<String> names = new ArrayList<>();
        Map<String, String> nameMap = params.stream().collect(Collectors.toMap(Param::getCode,param -> StrUtil.isEmpty(param.getName()) ? param.getCode() : param.getName()));
        //筛选出所有需要必须传入的的进行非空校验
        for (Param param : params.stream().filter(o -> o.isNecessary()).collect(Collectors.toList())) {
            Object value = data.get(param.getCode());
            if (value == null || value.toString().equals("")) {
                names.add(param.getCode() + ":" + nameMap.get(param.getCode()));
            }
        }
        if (!names.isEmpty()) {
            throw new RuleException(ExceptionCode.EC_0107, String.join(",", names));
        }
    }

    public static JSONObject buildParams(Context Context, List<Param> params) {
        return buildParams(Context, params, new JSONObject());
    }

    public static JSONObject buildParams(Context Context, List<Param> params, JSONObject data) {
        JSONObject json = new JSONObject();
        if (ArrayUtil.isEmpty(params)) {
            return json;
        }
        //第一优先使用 传入进来的
        for (Param param : params) {
            if(data !=null && data.containsKey(param.getCode())){
                json.put(param.getCode(),data.get(param.getCode()));
            }
        }

        //优先填充 输入值然后填充DataNode固定值
        for (Param param : params) {
            if(!json.containsKey(param.getCode())){
                if (data != null && data.get(param.getCode()) != null) {
                    json.put(param.getCode(), getData(param,data.get(param.getCode())));
                } else if (param.getDataFormulaNode() != null && DataNode.class.equals(param.getDataFormulaNode().getClass())) {
                    json.put(param.getCode(), getData(param,param.getDataFormulaNode().apply(Context).getContent()));
                }
            }
        }
        //最后填充 输入值然后非DataNode 的值
        for (Param param : params) {
            if(!json.containsKey(param.getCode())) {
                if (param.getDataFormulaNode() != null && !DataNode.class.equals(param.getDataFormulaNode().getClass())) {
                    json.put(param.getCode(), getData(param, param.getDataFormulaNode().apply(Context).getContent()));
                }
            }
        }
        return json;
    }
    private static Object getData(Param param ,Object data){
        switch (param.getDataType()){
            case Constant.DataType.NUMBER:return data==null?BigDecimal.ZERO:new BigDecimal(data.toString());
            case Constant.DataType.STRING:return data==null?"":data.toString();
            case Constant.DataType.BOLL:return data==null?false:Boolean.parseBoolean(data.toString());
            case Constant.DataType.TIME_YMD:return data==null?null:data.toString();
            case Constant.DataType.TIME_HMS:return data==null?null:data.toString();
            case Constant.DataType.TIME_YMDHMS:return data==null?null:data.toString();
        }
        return data;
    }

}
