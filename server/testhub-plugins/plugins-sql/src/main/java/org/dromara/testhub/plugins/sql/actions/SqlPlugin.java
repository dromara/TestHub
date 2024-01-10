package org.dromara.testhub.plugins.sql.actions;


import com.alibaba.druid.DbType;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.sql.parser.SQLParserUtils;
import com.alibaba.druid.sql.parser.Token;
import com.alibaba.fastjson.JSONObject;
import org.dromara.testhub.nsrule.core.constant.RuleConstant;
import org.dromara.testhub.nsrule.core.executer.context.Context;
import org.dromara.testhub.nsrule.core.executer.mode.base.Result;
import org.dromara.testhub.nsrule.core.executer.mode.base.action.RunState;
import org.dromara.testhub.framework.exception.TestHubException;
import org.dromara.testhub.framework.util.Key;
import org.dromara.testhub.plugins.sql.actions.core.ConnectionManager;
import org.dromara.testhub.plugins.sql.actions.model.TestHubActionSql;
import org.dromara.testhub.plugins.sql.actions.model.TestHubExecuteSql;
import org.dromara.testhub.sdk.action.*;
import org.dromara.testhub.sdk.action.model.rule.TestHubAction;
import org.dromara.testhub.sdk.action.model.rule.TestHubExecute;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SqlPlugin implements Plugin {

    private static final Pattern PATTERN = Pattern.compile("(?<=;)(?![^\\(]*\\))");

    private long maxWaitMillis = 1000L;
    private int maxActive = 512;

    @Override
    public String getType() {
        return "SQL";
    }

    @Override
    public BaseXMLActionParser getXMLActionParser() {
        return new SqlXMLActionParser();
    }

    @Override
    public BaseJsonActionParser getJsonActionParser() {
        return new SqlJsonActionParser();
    }

    @Override
    public BaseRuleEndHandler getRuleEndHandler() {
        return uuid -> ConnectionManager.removeConnection(uuid);
    }

    @Override
    public BaseXMLExecuteParser getXMLExecuteParser() {
        return new SqlXMLExecuteParser();
    }

    @Override
    public BaseJsonExecuteParser getJsonExecuteParser() {
        return new SqlJsonExecuteParser();
    }

    @Override
    public BaseDTOConvertor getDTOConvertor() {
        return new SqlDTOConvertor();
    }

    @Override
    public void execute(Context context, TestHubAction action, TestHubExecute execute, JSONObject paramDatas, RunState.Item runState) throws Exception {
        Result<Object> result = runState.getResult();
        List<Map<String, Object>> redMaps = new ArrayList<>();
        result.setContent(redMaps);
        extendSQL(context, getSql(context, (TestHubActionSql) action), (TestHubExecuteSql) execute, redMaps, runState, result);
    }

    public String getSql(Context context, TestHubActionSql action) {
        try {
            return action.getBound().build(context);
        } catch (TestHubException e) {
            throw e;
        } catch (Exception e) {
            throw new TestHubException("模板异常" + e.getMessage());
        }
    }

    protected DruidDataSource getDruidDataSource(Context context, RunState.Item runState) {
        String url = context.getString("$.url");
        if (url == null) {
            throw new TestHubException("找不到数据库连接地址");
        }
        runState.addRunParams("url", url);
        String username = context.getString("$.username");
        if (username == null) {
            throw new TestHubException("找不到数据库连接账户信息");
        }
        runState.addRunParams("username", username);
        String password = context.getString("$.password");
        if (password == null) {
            throw new TestHubException("找不到数据库连接密码信息");
        }
        runState.addRunParams("password", password);
        String driver = context.getString("$.driver");
        if (driver == null) {
            throw new TestHubException("找不到数据库驱动");
        }
        int timeout = context.getInteger("$.timeout");
        runState.addRunParams("driver", driver);
        runState.addRunParams("timeout", timeout == 0 ? maxWaitMillis : timeout);

        return ConnectionManager.getDataSource(url, username, password, driver, timeout == 0 ? maxWaitMillis : timeout, maxActive);
    }

    protected Key getKey(Context context, TestHubExecuteSql execute) {
        String keyStr = null;
        if (RuleConstant.RuleModel.FLOW.equalsIgnoreCase(context.getRule().getModel())) {
            keyStr = context.getItemCode();
        } else if (RuleConstant.RuleModel.GRAPH.equalsIgnoreCase(context.getRule().getModel())) {
            keyStr = context.getItemCode();
        }
        if (StringUtils.isNotEmpty(execute.getConKey())) {
            return new Key(context.getUuid(), context.getString("$.url"), context.getString("$.username"), context.getString("$.password"), context.getRule().getCode(), keyStr, execute.getConKey());
        } else {
            return new Key(context.getUuid(), context.getString("$.url"), context.getString("$.username"), context.getString("$.password"), context.getRule().getCode(), keyStr);
        }
    }

    public List<Map<String, Object>> extendSQL(Context context, String sql, TestHubExecuteSql execute, List<Map<String, Object>> reData, RunState.Item runState, Result<Object> result) throws Exception {
        DruidDataSource datasource = getDruidDataSource(context, runState);
        runState.addRunParams("conKey", execute.getConKey());
        runState.addRunParams("commit", execute.getCommit());
        runState.addRunParams("sql", sql);

        Key key = getKey(context, execute);
        Connection connection = ConnectionManager.getConnection(context.getUuid(), key, datasource);
        Statement statement = connection.createStatement();

        List<String> sqls = splitSQLStatements(sql);

        if(sqls.size()>1){
            for(String item:sqls){
                statement.addBatch(item);
            }
            int[] num = statement.executeBatch();
            Map<String, Object> map = new HashMap<>();
            map.put("vals", num);
            reData.add(map);
        }else {
            boolean flag = isSelectSql(sql);
            if (flag) {
                ResultSet rs = statement.executeQuery(sql);
                ResultSetMetaData metaData = rs.getMetaData();
                String[] columnNames = new String[metaData.getColumnCount()];
                if (metaData.getColumnCount() > 0) {
                    for (int i = 1; i <= metaData.getColumnCount(); i++) {
//                    columnNames[i - 1] = metaData.getColumnName(i);
                        if (metaData.getColumnName(i).equals(metaData.getColumnLabel(i))) {
                            columnNames[i - 1] = metaData.getColumnLabel(i).toLowerCase();
                        } else {
                            columnNames[i - 1] = metaData.getColumnLabel(i);
                        }
                    }
                    while (rs.next()) {
                        Map<String, Object> map = new HashMap<>();
                        for (int i = 1; i <= metaData.getColumnCount(); i++) {
                            map.put(columnNames[i - 1], rs.getString(i));
                        }
                        reData.add(map);
                    }
                }
            } else {
                int num = statement.executeUpdate(sql);
                Map<String, Object> map = new HashMap<>();
                map.put("val", num);
                reData.add(map);
            }
        }
        if (execute.getCommit()) {
            connection.commit();
        }
        return reData;
    }

    private static boolean isSelectSql(String sql) {
        Token token = SQLParserUtils.createExprParser(sql, DbType.other).getLexer().token();
        if (token == Token.UPDATE || token == Token.DELETE) {
            return false;
        }
        return true;
    }

    public static List<String> splitSQLStatements(String sql) {
        List<String> sqlStatements = new ArrayList<>();

        Matcher matcher = PATTERN.matcher(sql);

        int startIndex = 0;
        while (matcher.find()) {
            String statement = sql.substring(startIndex, matcher.end()).trim();
            if (!statement.isEmpty()) {
                sqlStatements.add(statement.endsWith(";") ? statement.substring(0, statement.length() - 1) : statement);
            }
            startIndex = matcher.end();
        }

        // 添加剩余的部分（最后一个语句）
        String remaining = sql.substring(startIndex).trim();
        if (!remaining.isEmpty()) {
            sqlStatements.add(remaining.endsWith(";") ? remaining.substring(0, remaining.length() - 1) : remaining);
        }

        return sqlStatements;
    }
}
