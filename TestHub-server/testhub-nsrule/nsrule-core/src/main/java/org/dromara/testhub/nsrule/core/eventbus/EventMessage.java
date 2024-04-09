package org.dromara.testhub.nsrule.core.eventbus;

import org.dromara.testhub.nsrule.core.executer.context.Context;


/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2023/4/16 19:00
 */
public class EventMessage {

    private Context context;
    private Object data;

    public EventMessage(Context context, Object data) {
        this.data = data;
        this.context = context;
    }


    public Object getData() {
        return data;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
