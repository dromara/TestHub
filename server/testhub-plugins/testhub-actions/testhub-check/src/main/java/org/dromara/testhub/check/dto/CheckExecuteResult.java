package org.dromara.testhub.check.dto;

import org.dromara.testhub.sdk.dto.ExecuteResult;
import org.dromara.testhub.sdk.model.rule.TestHubAction;
import org.dromara.testhub.sdk.model.rule.TestHubExecute;

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
