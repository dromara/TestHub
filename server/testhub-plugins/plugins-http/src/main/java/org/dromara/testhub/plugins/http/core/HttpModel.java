package org.dromara.testhub.plugins.http.core;

import org.dromara.testhub.nsrule.core.executer.mode.base.action.Param;
import lombok.Data;
import org.dromara.testhub.plugins.http.actions.model.Body;

import java.util.List;

@Data
public class HttpModel {
    private String url;
    private String method;
    private int timeout;

    private List<Param> headers;
    private Body body;
    private List<Param> params;
    private List<Param> cookies;
    private List<Param> rests;

}
