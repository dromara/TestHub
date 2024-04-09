package org.dromara.testhub.plugins.compare.actions.dto;

import org.dromara.testhub.sdk.action.dto.ExecuteResult;
import org.dromara.testhub.sdk.action.model.rule.TestHubAction;
import org.dromara.testhub.sdk.action.model.rule.TestHubExecute;

import java.util.Map;

public class CheckObjExecuteResult extends ExecuteResult {

    private Map<String, Boolean> checkObjResult;

    public CheckObjExecuteResult(TestHubAction action, TestHubExecute execute) {
        super(action, execute);
    }


    public Map<String, Boolean> getCheckObjResult() {
        return checkObjResult;
    }

    public void setCheckObjResult(Map<String, Boolean> checkObjResult) {
        this.checkObjResult = checkObjResult;
    }
}
