package org.dromara.testhub.sql;

import com.alibaba.fastjson.JSONObject;
import org.dromara.testhub.sdk.BaseDTOConvertor;
import org.dromara.testhub.sdk.model.rule.TestHubAction;
import org.dromara.testhub.sdk.model.rule.TestHubExecute;
import org.dromara.testhub.sql.model.TestHubActionSql;
import org.dromara.testhub.sql.model.TestHubExecuteSql;

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
