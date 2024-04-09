package org.dromara.testhub.nsrule.core.parser;

import org.dromara.testhub.nsrule.core.executer.context.RuleConfig;
import org.dromara.testhub.nsrule.core.executer.context.RuleProject;
import org.dromara.testhub.nsrule.core.executer.mode.Rule;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/4 09:36
 */
public interface RuleParser<T> {
    Rule parse(T data, RuleConfig ruleConfig) throws Exception;
}
