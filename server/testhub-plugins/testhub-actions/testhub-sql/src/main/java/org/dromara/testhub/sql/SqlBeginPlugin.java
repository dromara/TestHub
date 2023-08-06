package org.dromara.testhub.sql;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSONObject;
import com.goddess.nsrule.core.executer.context.Context;
import com.goddess.nsrule.core.executer.mode.base.Result;
import com.goddess.nsrule.core.executer.mode.base.action.RunState;
import org.dromara.testhub.common.util.Key;
import org.dromara.testhub.sdk.BaseXMLActionParser;
import org.dromara.testhub.sdk.model.rule.TestHubAction;
import org.dromara.testhub.sdk.model.rule.TestHubExecute;
import org.dromara.testhub.sql.model.TestHubExecuteSql;
import org.dromara.testhub.sql.core.ConnectionManager;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SqlBeginPlugin extends SqlPlugin {
    @Override
    public String getType() {
        return "SQL_BEGIN";
    }
    @Override
    public  BaseXMLActionParser getXMLActionParser() {
        return new BaseXMLActionParser() {
            @Override
            public TestHubAction xml2Model(Element element, TestHubAction action) {
                return action;
            }

            @Override
            public Element model2xml(Element element, TestHubAction action) {
                return element;
            }
        };
    }
    @Override
    public void execute(Context context, TestHubAction action, TestHubExecute execute, JSONObject data, RunState.Item runState) {
        Result<Object> result = runState.getResult();
        List<Map<String,Object>> redMaps = new ArrayList<>();
        result.setContent(redMaps);
        DruidDataSource datasource = getDruidDataSource(context,runState);
        try {
            Key key = getKey(context,(TestHubExecuteSql) execute);
            ConnectionManager.getConnection(context.getUuid(),key, datasource);
            runState.addRunParams("conKey",((TestHubExecuteSql) execute).getConKey());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
