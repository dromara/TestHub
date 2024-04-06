package org.dromara.testhub.framework.util;

import cn.dev33.satoken.stp.StpUtil;

/**
 * @author yetier
 */
public class UserUtil {
    public static final String USER_ID = "userId";
    public static final String ROLE_ADMIN = "admin";
    public static Long getCurrentUserId(){
        Long optId = ContextManager.getContextItem(USER_ID,Long.class);
        if(optId == null){
            return Long.parseLong(StpUtil.getLoginId().toString());
        }
        return optId;
    }
    public static boolean checkRole(String role){
        try {
            StpUtil.checkRole(role);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
