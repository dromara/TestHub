package org.dromara.testhub.nsrule.core.parser;

import org.dromara.testhub.nsrule.core.executer.mode.base.bound.Bound;

public interface BoundParser<T,B extends Bound> {
    B parser(T dataObj);
}
