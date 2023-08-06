package com.goddess.nsrule.core.executer.mode.base.function.impl;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.goddess.nsrule.core.executer.context.Context;
import com.goddess.nsrule.core.executer.mode.base.function.FunctionHandler;
import com.goddess.nsrule.core.executer.operation.Operation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/11 21:16
 */
public class FunctionSize implements FunctionHandler {
    @Override
    public String getName() {
        return "size";
    }

    @Override
    public Object execute(Context context, JSONObject data) {
        if (data == null) {
            return -1;
        }
        try {
            ArrayList attr1 = data.getObject("attr1", ArrayList.class);

            return attr1.size();
        } catch (JSONException e) {
            try {
                List<String> attr1 = Operation.getList(data.getObject("attr1", Object.class));
                return attr1.size();
            } catch (Exception e1) {
                e.printStackTrace();
                return 0;
            }

        }

    }
}
