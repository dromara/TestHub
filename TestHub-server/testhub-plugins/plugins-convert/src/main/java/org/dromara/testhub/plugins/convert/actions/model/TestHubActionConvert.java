package org.dromara.testhub.plugins.convert.actions.model;

import org.dromara.testhub.sdk.action.model.rule.TestHubAction;
import lombok.Data;

import java.util.List;

@Data
public class TestHubActionConvert extends TestHubAction {

    private List<Convert> converts;

    public TestHubActionConvert(TestHubAction action){
        super();
        super.setCode(action.getCode());
        super.setName(action.getName());
        super.setRemark(action.getRemark());
        super.setParams(action.getParams());
        super.setMappings(action.getMappings());
        super.setDataType(action.getDataType());
        super.setComplex(action.getComplex());
        super.setType(action.getType());
    }
}
