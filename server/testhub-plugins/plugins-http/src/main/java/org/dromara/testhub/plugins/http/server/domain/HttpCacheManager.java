package org.dromara.testhub.plugins.http.server.domain;


import org.dromara.testhub.plugins.http.server.domain.cache.TreeCache;
import org.dromara.testhub.sdk.action.dto.res.TreeNodeResDto;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class HttpCacheManager {

    private static Map<String, TreeCache> treeMap = new ConcurrentHashMap<>();

    public static void put(String projectCode,TreeCache treeCache){
        treeMap.put(projectCode,treeCache);
    }
    public static TreeCache getTreeCache(String projectCode){
        return treeMap.get(projectCode);
    }

    public static List<TreeNodeResDto> getTree(String projectCode){
        return treeMap.get(projectCode).getTree();
    }



}
