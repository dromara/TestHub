package org.dromara.testhub.nsrule.core.executer.mode.base.function;

import com.alibaba.fastjson.JSONObject;
import org.dromara.testhub.nsrule.core.executer.context.Context;

public interface FunctionHandler {
    String getName();

    Object execute(Context context, JSONObject data);
}
