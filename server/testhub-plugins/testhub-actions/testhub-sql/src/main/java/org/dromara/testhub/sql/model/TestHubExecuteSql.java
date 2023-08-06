package org.dromara.testhub.sql.model;

import com.goddess.nsrule.core.executer.mode.base.action.Execute;
import org.dromara.testhub.sdk.model.rule.TestHubExecute;

public class TestHubExecuteSql extends TestHubExecute {
    //数据库事务ID
    private String conKey;

    public TestHubExecuteSql(Execute execute) {
        super(execute);
    }

    public String getConKey() {
        return conKey;
    }

    public void setConKey(String conKey) {
        this.conKey = conKey;
    }
}
