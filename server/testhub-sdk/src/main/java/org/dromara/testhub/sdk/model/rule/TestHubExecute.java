package org.dromara.testhub.sdk.model.rule;

import com.goddess.nsrule.core.executer.mode.base.action.Execute;
import lombok.Data;


@Data
public class TestHubExecute extends Execute {

    public TestHubExecute() {
    }

    public TestHubExecute(Execute execute) {
        super();
        super.setId(execute.getId());
        super.setAlias(execute.getAlias());
        super.setCode(execute.getCode());
        super.setName(execute.getName());
        super.setBlock(execute.isBlock());
        super.setDoCode(execute.getDoCode());
        super.setInjects(execute.getInjects());
        super.setInit(execute.isInit());
        super.setActionCode(execute.getActionCode());
    }

}
