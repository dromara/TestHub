package org.dromara.testhub.sdk.action.model.rule;

import com.alibaba.fastjson.JSONObject;
import org.dromara.testhub.nsrule.core.executer.context.Context;
import org.dromara.testhub.nsrule.core.executer.mode.base.action.Action;
import org.dromara.testhub.nsrule.core.executer.mode.base.action.Execute;
import org.dromara.testhub.nsrule.core.executer.mode.base.action.RunState;
import org.dromara.testhub.sdk.action.Plugin;
import org.dromara.testhub.sdk.action.PluginFactory;
import lombok.Data;

@Data
public class TestHubAction extends Action {
    public TestHubAction() {
    }

    public TestHubAction(Action action) {
        super();
        super.setId(action.getId());
        super.setCode(action.getCode());
        super.setName(action.getName());
        super.setRemark(action.getRemark());
        super.setParams(action.getParams());
        super.setMappings(action.getMappings());
        super.setDataType(action.getDataType());
        super.setComplex(action.getComplex());
    }

    private String type;


    @Override
    public void extend(Context decisionContext, JSONObject data, Execute execute, RunState.Item runState) throws Exception {
        Plugin plugin = PluginFactory.getHandler(type);
        plugin.execute(decisionContext, this, (TestHubExecute) execute, data, runState);
    }

}
