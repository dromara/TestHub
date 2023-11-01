package org.dromara.testhub.nsrule.core.executer.mode.base.bound;

import org.dromara.testhub.nsrule.core.executer.context.Context;

public class Else extends Bound {
    public Compose compose;

    public Else() {
        type = "Compose";
    }

    @Override
    public String build(Context context) {
        return compose.build(context);
    }

    public Compose getCompose() {
        return compose;
    }

    public void setCompose(Compose compose) {
        this.compose = compose;
    }
}
