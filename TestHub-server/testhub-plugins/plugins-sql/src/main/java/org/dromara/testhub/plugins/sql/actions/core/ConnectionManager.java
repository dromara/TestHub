package org.dromara.testhub.plugins.sql.actions.core;


import com.alibaba.druid.pool.DruidDataSource;
import org.dromara.testhub.framework.util.Key;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConnectionManager {

    private static Map<Key, Connection> connectionMap = new ConcurrentHashMap<>();
    private static Map<String, List<Key>> uuidKeys = new ConcurrentHashMap<>();
    private static Map<Key, DruidDataSource> druidDataSourceMap = new ConcurrentHashMap<>();
    public synchronized static DruidDataSource getDataSource(String url,String username,String password,String driver,long maxWaitMillis,int maxActive)  {
        Key key = new Key(url,username,password,driver,maxWaitMillis);
        DruidDataSource dataSource = druidDataSourceMap.get(key);
        if(dataSource == null){
            dataSource = new DruidDataSource();
            dataSource.setUrl(url);
            dataSource.setUsername(username);
            dataSource.setPassword(password);
            dataSource.setDriverClassName(driver);
            dataSource.setConnectionErrorRetryAttempts(1); // 失败后重连的次数
            dataSource.setBreakAfterAcquireFailure(true); // 请求失败之后中断
            dataSource.setMaxWait(maxWaitMillis);//最长等待时间（超时时间）
//            dataSource.timeBetweenEvictionRunsMillis();
            dataSource.setMaxActive(maxActive);
            druidDataSourceMap.put(key,dataSource);
        }
        return dataSource;
    }

    public synchronized static Connection getConnection(String uuid, Key key, DruidDataSource dataSource) throws SQLException {
        Connection connection = connectionMap.get(key);
        if(connection == null){
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            connectionMap.put(key,connection);
            List<Key> keys = uuidKeys.getOrDefault(uuid,new ArrayList<>());
            keys.add(key);
            uuidKeys.put(uuid,keys);
        }
        return connection;
    }

    public static Connection removeConnection(Key key) {
        return connectionMap.remove(key);
    }
    public synchronized static void removeConnection(String uuid) {
        List<Key> keys = uuidKeys.getOrDefault(uuid,new ArrayList<>());
        for(Key key:keys){
            Connection connection = connectionMap.remove(key);
            try {
                if(connection!=null){
                    connection.close();
                }
            } catch (SQLException e) {
                try {
                    connection.close();
                } catch (SQLException e2) {
                    throw new RuntimeException(e);
                }
            }
        }
    }





}
