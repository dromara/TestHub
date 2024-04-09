package org.dromara.testhub.nsrule.core.parser;

import org.dromara.testhub.nsrule.core.executer.mode.base.action.Action;

/**
 * 行为构建器
 */
public interface ActionParser {
    Action parse(Object dataObj, Action action);
}
