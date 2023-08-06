package com.goddess.nsrule.core.parser;

import com.goddess.nsrule.core.executer.mode.base.action.Action;

/**
 * 行为构建器
 */
public interface ActionParser {
    Action parse(Object dataObj, Action action);
}
