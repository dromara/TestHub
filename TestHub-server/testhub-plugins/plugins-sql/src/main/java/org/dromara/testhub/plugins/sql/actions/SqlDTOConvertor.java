package org.dromara.testhub.plugins.sql.actions;

import com.alibaba.fastjson.JSONObject;
import org.dromara.testhub.sdk.action.BaseDTOConvertor;
import org.dromara.testhub.sdk.action.model.rule.TestHubAction;
import org.dromara.testhub.sdk.action.model.rule.TestHubExecute;
import org.dromara.testhub.plugins.sql.actions.model.TestHubActionSql;
import org.dromara.testhub.plugins.sql.actions.model.TestHubExecuteSql;

public class SqlDTOConvertor implements BaseDTOConvertor {
    @Override
    public Object model2Res(TestHubExecute execute) {
        TestHubExecuteSql model = (TestHubExecuteSql)execute;
        return model.getConKey();
    }

    @Override
    public Object model2Res(TestHubAction action) {
        JSONObject extraInto = new JSONObject();
        TestHubActionSql model = (TestHubActionSql)action;
        if(model.getBound()!=null){
            extraInto.put("bound",model.getText());
        }
        return extraInto;
    }

}
