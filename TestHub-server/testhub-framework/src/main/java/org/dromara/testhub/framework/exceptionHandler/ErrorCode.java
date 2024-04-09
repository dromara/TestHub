package org.dromara.testhub.framework.exceptionHandler;

/**
 * @author 失败女神
 * @email: 18733123202@163.com
 * @date 2023/7/1 上午9:35
 */
public abstract class ErrorCode {

    /**
     * 记录不存在
     */
    public static final String NOTFOUND = "001";

    /**
     * 不符合业务规则
     */
    public static final String DATA_ILLEGAL = "002";

    /**
     * 数据缺失
     */
    public static final String DATA_MISSING = "003";

    /**
     * 更新失败
     */
    public static final String UP_FAILED = "003";

    /**
     * 记录重复
     */
    public static final String DUPLICATION = "005";


    //需要登陆
    public static final String UNAUTHORIZED = "401";

    //没有权限
    public static final String FORBIDDEN = "403";

    /**
     * 成功
     */
    public static final String CODE = "000000";


}
