package org.dromara.testhub.plugins.check.actions.model;

import com.goddess.nsrule.core.executer.mode.ruleLine.JavaActuator;
import lombok.Data;

@Data
public class CheckParamResult {
    private boolean flag;
    private String msg;
    private JavaActuator.Log log;
}