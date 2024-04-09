package org.dromara.testhub.sdk.action;

import org.dromara.testhub.sdk.action.model.rule.TestHubAction;
import org.dromara.testhub.sdk.action.model.rule.TestHubExecute;

public interface BaseDTOConvertor {
    Object model2Res(TestHubExecute execute);

    Object model2Res(TestHubAction action);
}
