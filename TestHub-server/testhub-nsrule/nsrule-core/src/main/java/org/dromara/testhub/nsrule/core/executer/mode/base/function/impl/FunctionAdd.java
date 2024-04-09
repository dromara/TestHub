package org.dromara.testhub.nsrule.core.executer.mode.base.function.impl;

import com.alibaba.fastjson.JSONObject;
import org.dromara.testhub.nsrule.core.executer.context.Context;
import org.dromara.testhub.nsrule.core.executer.mode.base.function.FunctionHandler;

import java.math.BigDecimal;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/11 21:16
 */
public class FunctionAdd implements FunctionHandler {
    @Override
    public String getName() {
        return "add";
    }

    @Override
    public Object execute(Context context, JSONObject data) {
        if (data == null) {
            return null;
        }
        BigDecimal result = BigDecimal.ZERO;
        for (String key : data.keySet()) {
            result = result.add(data.getBigDecimal(key));
        }
        return result;
    }
}
