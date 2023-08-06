package org.dromara.testhub.http.model;

import com.goddess.nsrule.core.executer.mode.base.action.Param;
import lombok.Data;

import java.util.List;

@Data
public class HttpModel {
    private String baseUrl;
    private String url;
    private String method;

    private List<Param> headers;
    private Body body;
    private List<Param> params;
    private List<Param> rests;

}
