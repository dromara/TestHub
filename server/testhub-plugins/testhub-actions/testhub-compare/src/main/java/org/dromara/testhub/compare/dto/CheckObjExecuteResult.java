package org.dromara.testhub.compare.dto;

import org.dromara.testhub.sdk.dto.ExecuteResult;
import org.dromara.testhub.sdk.model.rule.TestHubAction;
import org.dromara.testhub.sdk.model.rule.TestHubExecute;

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
