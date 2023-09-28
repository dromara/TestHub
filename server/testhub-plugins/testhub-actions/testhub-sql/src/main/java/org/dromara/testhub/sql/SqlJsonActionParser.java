package org.dromara.testhub.sql;

import com.alibaba.fastjson.JSONObject;
import com.goddess.nsrule.core.executer.mode.base.bound.FreeMarker;
import com.goddess.nsrule.core.parser.BoundParser;
import com.goddess.nsrule.core.parser.BoundParserFreeMarker;
import org.dromara.testhub.sdk.BaseJsonActionParser;
import org.dromara.testhub.sdk.model.rule.TestHubAction;
import org.dromara.testhub.sql.model.TestHubActionSql;
import org.apache.commons.lang3.StringUtils;


public class SqlJsonActionParser implements BaseJsonActionParser {
    private static BoundParser<String, FreeMarker> boundParser = new BoundParserFreeMarker();
    private static String BOUND = "bound";

    @Override
    public TestHubAction json2Model(JSONObject element, TestHubAction action) {
        TestHubActionSql actionSql = new TestHubActionSql(action);
        String boundStr = element.getString(BOUND);
        if (StringUtils.isEmpty(boundStr)) {
            return actionSql;
        }

        actionSql.setBound(boundParser.parser(boundStr.toString()));
        actionSql.setText(boundStr);

        return actionSql;

    }

    @Override
    public JSONObject model2json(TestHubAction action) {
        TestHubActionSql actionSql = (TestHubActionSql) action;
        JSONObject element = new JSONObject();
        element.put(BOUND, actionSql.getText());
        return element;
    }
}
