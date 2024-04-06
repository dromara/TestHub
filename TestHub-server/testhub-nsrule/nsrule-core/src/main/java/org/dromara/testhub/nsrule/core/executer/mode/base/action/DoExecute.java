package org.dromara.testhub.nsrule.core.executer.mode.base.action;

import org.dromara.testhub.nsrule.core.executer.context.Context;

public interface DoExecute<T> {

    int getComplex();

    String getDataType();

    String getCode();

    Object exec(Context context);
}
