package org.dromara.testhub.nsrule.core.executer.mode.base.bound;

import org.dromara.testhub.nsrule.core.executer.context.Context;


public class Trim extends Bound {
    private Compose compose;

    public Trim() {
        type = "Trim";
    }

    public Compose getCompose() {
        return compose;
    }

    public void setCompose(Compose compose) {
        this.compose = compose;
    }

    @Override
    public String build(Context context) {
        String str = compose.build(context);
        if (str == null) {
            return "";
        } else {
            return "\n" + str.replace("\n", "");
        }
    }
}
