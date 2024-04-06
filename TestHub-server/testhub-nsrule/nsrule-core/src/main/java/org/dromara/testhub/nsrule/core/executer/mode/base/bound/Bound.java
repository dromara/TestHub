package org.dromara.testhub.nsrule.core.executer.mode.base.bound;

import org.dromara.testhub.nsrule.core.executer.context.Context;

public abstract class Bound {
    protected String type;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public abstract String build(Context context);
}
