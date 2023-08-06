package com.goddess.nsrule.core.executer.mode.base.function;

import com.alibaba.fastjson.JSONObject;
import com.goddess.nsrule.core.executer.context.Context;

public interface FunctionHandler {
    String getName();

    Object execute(Context context, JSONObject data);
}
