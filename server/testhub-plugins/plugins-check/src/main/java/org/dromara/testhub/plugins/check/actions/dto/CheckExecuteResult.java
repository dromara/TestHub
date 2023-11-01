package org.dromara.testhub.plugins.check.actions.dto;

import org.dromara.testhub.sdk.action.dto.ExecuteResult;
import org.dromara.testhub.sdk.action.model.rule.TestHubAction;
import org.dromara.testhub.sdk.action.model.rule.TestHubExecute;

import java.util.Map;

public class CheckExecuteResult extends ExecuteResult {

    private Map<String, CheckResultDto> reData;

    public CheckExecuteResult(TestHubAction action, TestHubExecute execute) {
        super(action, execute);
    }

    public Map<String, CheckResultDto> getReData() {
        return reData;
    }

    public void setReData(Map<String, CheckResultDto> reData) {
        this.reData = reData;
    }
}
