package com.goddess.nsrule.core.executer.mode.base.action;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONObject;
import com.goddess.nsrule.core.executer.context.Context;
import com.goddess.nsrule.core.executer.meta.MetaObject;

import java.util.List;
import java.util.Map;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2023/1/10 16:01
 */
public class Util {
    public static JSONObject initOne(Context context, Map<String, Object> map, List<Mapping> mappings, String dataType) {
        JSONObject reJson = null;
        if (CollectionUtil.isEmpty(mappings)) {
            reJson = new JSONObject(map);
        } else {
            reJson = new JSONObject();
            for (Mapping mapping : mappings) {
                reJson.put(mapping.getResult(), map.get(mapping.getCode()));
            }
        }
        return new JSONObject(MetaObject.getMetaObject(context, reJson.getInnerMap(), dataType));
    }
}
