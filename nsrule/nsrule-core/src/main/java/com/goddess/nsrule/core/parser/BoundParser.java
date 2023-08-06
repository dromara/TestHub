package com.goddess.nsrule.core.parser;

import com.goddess.nsrule.core.executer.mode.base.bound.*;

public interface BoundParser<T,B extends Bound> {
    B parser(T dataObj);
}
