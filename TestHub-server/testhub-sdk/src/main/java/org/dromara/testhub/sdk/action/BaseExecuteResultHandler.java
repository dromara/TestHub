package org.dromara.testhub.sdk.action;

import org.dromara.testhub.nsrule.core.executer.mode.base.action.RunState;
import org.dromara.testhub.sdk.action.model.HandlerResult;
import org.dromara.testhub.sdk.action.model.rule.TestHubAction;
import org.dromara.testhub.sdk.action.model.rule.TestHubExecute;

import java.util.HashMap;

public interface BaseExecuteResultHandler {
    HandlerResult handler(TestHubAction action, TestHubExecute execute, RunState.Item stateItem, HashMap<String, Object> flowData,Object executeData);
}
