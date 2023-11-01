package org.dromara.testhub.nsrule.core.parser;

import org.dromara.testhub.nsrule.core.executer.context.RuleConfig;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/4 16:16
 */
public interface RuleConfigBuilder<T> {
    RuleConfig build(T data) throws Exception;
}
