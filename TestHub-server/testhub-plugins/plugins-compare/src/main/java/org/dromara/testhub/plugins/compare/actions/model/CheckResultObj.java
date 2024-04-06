package org.dromara.testhub.plugins.compare.actions.model;

import lombok.Data;

@Data
public class CheckResultObj {

    private CheckObj checkObj;
    private Boolean flag;
    private String msg;
    private Object threshold;
    private Object cover;

}