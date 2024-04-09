package org.dromara.testhub.nsrule.core.executer.mode.base.function.impl;

import com.alibaba.fastjson.JSONObject;
import org.dromara.testhub.nsrule.core.executer.context.Context;
import org.dromara.testhub.nsrule.core.executer.mode.base.function.FunctionHandler;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/11 21:16
 */
public class FunctionLength implements FunctionHandler {
    @Override
    public String getName() {
        return "length";
    }

    @Override
    public Object execute(Context context, JSONObject data) {
        if (data == null) {
            return null;
        }
        String attr1 = data.getString("attr1");
        return attr1.length();
    }
}
