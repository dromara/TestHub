package org.dromara.testhub.sdk.action.model.rule;

import lombok.Data;
import org.dromara.testhub.nsrule.core.executer.mode.base.action.Execute;


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
