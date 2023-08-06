package com.goddess.nsrule.core.executer.meta;

import com.goddess.nsrule.core.constant.Constant;
import com.goddess.nsrule.core.constant.ConstantUtil;
import com.goddess.nsrule.core.executer.context.Context;
import com.goddess.nsrule.core.executer.operation.Operation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/4 18:33
 */
public class MetaObject {
    public static List<Map<String, Object>> getMetaObjects(Context context, List<Map<String, Object>> datas, String dataType) {
        if (Constant.DataType.MAP.equalsIgnoreCase(dataType)) {
            return datas;
        }
        List<Map<String, Object>> reDatas = new ArrayList<>();
        for (Map<String, Object> data : datas) {
            Map<String, Object> reData = getMetaObject(context, data, dataType);
            reDatas.add(reData);
        }
        return reDatas;
    }

    public static Map<String, Object> getMetaObject(Context context, Map<String, Object> data, String dataType) {
        if (Constant.DataType.MAP.equalsIgnoreCase(dataType)) {
            return data;
        }
        MetaClass metaClass = context.getRule().getMetaClassByDataType(dataType);
        if (metaClass == null) {
            metaClass = context.getProject().getMetaClassByDataType(dataType);
        }
        Map<String, Object> reData = new HashMap<>();
        if (metaClass != null) {
            for (MetaProperty property : metaClass.getProperties()) {
                if (ConstantUtil.isBaseDataType(property.getDataType())) {
                    //是基础数据类型
                    reData.put(property.getCode(), getBaseData(context, property.getComplex(), property.getDataType(), data.get(property.getCode())));
                } else if (Operation.isList(property.getComplex())) {
                    //是自定义对象 列表
                    reData.put(property.getCode(), getMetaObjects(context, (List<Map<String, Object>>) data.get(property.getCode()), property.getDataType()));
                } else {
                    //是自定义对象 非列表
                    Map<String, Object> subData = (Map<String, Object>) data.get(property.getCode());
                    reData.put(property.getCode(), MetaObject.getMetaObject(context, subData, property.getDataType()));
                }
            }
        } else if (ConstantUtil.isBaseDataType(dataType)) {
            //是基础数据类型
            if (data.isEmpty()) {
                //空的
                reData.put("val", getBaseData(context, 0, dataType, null));
            } else {
                boolean flag = true;
                for (String key : data.keySet()) {
                    if (flag) {
                        flag = false;
                        Object val = getBaseData(context, 0, dataType, data.get(key));
                        reData.put("val", val);
                        reData.put(key, val);
                    }
                }
            }

        } else {
            throw new RuntimeException("数据类型不存在" + dataType);
        }
        return reData;
    }

    public static Object getBaseData(Context context, int complex, String dataType, Object data) {
        if (Operation.isList(complex)) {
            switch (dataType) {
                case Constant.DataType.BOLL:
                    return Operation.getBollList(data);
                case Constant.DataType.NUMBER:
                    return Operation.getNumberList(data);
                case Constant.DataType.STRING:
                    return Operation.getList(data);
                case Constant.DataType.TIME_YMD:
                    return Operation.getTimeYdmList(data);
                case Constant.DataType.TIME_YMDHMS:
                    return Operation.getTimeYdmhmsList(data);
                case Constant.DataType.TIME_HMS:
                    return Operation.getTimeHmsList(data);
                default:
                    List<Object> reObjects = new ArrayList<>();
                    reObjects.add(data);
                    return reObjects;
            }
        } else {
            switch (dataType) {
                case Constant.DataType.BOLL:
                    if (data instanceof Boolean) {
                        return data;
                    }
                    return Operation.getBoll(data);
                case Constant.DataType.NUMBER:
                    if (data instanceof BigDecimal) {
                        return data;
                    }
                    if (data instanceof Double) {
                        return data;
                    }
                    if (data instanceof Integer) {
                        return data;
                    }
                    return Operation.getNumber(data);
                case Constant.DataType.STRING:
                    if (data instanceof String) {
                        return data;
                    }
                    return Operation.getList(data).get(0);
                case Constant.DataType.TIME_YMD:
                    return Operation.getTimeYdm(data);
                case Constant.DataType.TIME_YMDHMS:
                    return Operation.getTimeYdmhms(data);
                case Constant.DataType.TIME_HMS:
                    return Operation.getTimeHms(data);
            }
            return data;
        }

    }


}
