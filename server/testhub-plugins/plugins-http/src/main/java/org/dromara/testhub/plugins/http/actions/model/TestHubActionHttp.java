package org.dromara.testhub.plugins.http.actions.model;

import org.dromara.testhub.plugins.http.core.HttpModel;
import org.dromara.testhub.sdk.action.model.rule.TestHubAction;

public class TestHubActionHttp extends TestHubAction {
    private HttpModel httpModel;
    public TestHubActionHttp(){

    }
    public TestHubActionHttp(TestHubAction action){
        super();
        super.setId(action.getId());
        super.setCode(action.getCode());
        super.setName(action.getName());
        super.setRemark(action.getRemark());
        super.setParams(action.getParams());
        super.setMappings(action.getMappings());
        super.setDataType(action.getDataType());
        super.setComplex(action.getComplex());
        super.setType(action.getType());
    }

    public HttpModel getHttpModel() {
        return httpModel;
    }

    public void setHttpModel(HttpModel httpModel) {
        this.httpModel = httpModel;
    }
}
