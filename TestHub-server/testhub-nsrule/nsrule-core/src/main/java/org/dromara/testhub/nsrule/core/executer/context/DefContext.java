package org.dromara.testhub.nsrule.core.executer.context;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import org.apache.commons.lang3.StringUtils;
import org.dromara.testhub.nsrule.core.eventbus.AsyncEventBus;
import org.dromara.testhub.nsrule.core.eventbus.EventBus;
import org.dromara.testhub.nsrule.core.eventbus.EventMessage;
import org.dromara.testhub.nsrule.core.eventbus.EventType;
import org.dromara.testhub.nsrule.core.executer.mode.Rule;
import org.dromara.testhub.nsrule.core.executer.mode.base.action.Param;
import org.dromara.testhub.nsrule.core.executer.mode.base.action.RunState;
import org.dromara.testhub.nsrule.core.executer.mode.base.function.FunctionHandlerFactory;

import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * 默认决策上下文
 *
 * @author: 失败女神
 */
public class DefContext extends Context<Map<Object, Object>> {
    private static final JSONObject PARMAS = new JSONObject();

    public DefContext(JSONObject params) {
        super(params);
    }
    public DefContext() {
        super(PARMAS);
    }

    @Override
    public String getItemCode() {
        return "";
    }

    public FunctionHandlerFactory getFunctionHandlerFactory() {
        return FunctionHandlerFactory.getInstance();
    }
}
