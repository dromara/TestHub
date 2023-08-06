package com.goddess.nsrule.core.executer.mode.base.action;

import com.goddess.nsrule.core.executer.context.Context;

public interface DoExecute<T> {

    int getComplex();

    String getDataType();

    String getCode();

    Object exec(Context context);
}
