package org.dromara.testhub.sdk;

import org.dromara.testhub.sdk.model.rule.TestHubAction;
import org.dromara.testhub.sdk.model.rule.TestHubExecute;

public interface BaseDTOConvertor {
    Object model2Res(TestHubExecute execute);
    Object model2Res(TestHubAction action);
}
