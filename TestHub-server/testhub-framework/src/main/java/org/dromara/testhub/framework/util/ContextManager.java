package org.dromara.testhub.framework.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 上下文管理器
 * @author yetier
 */
public class ContextManager {
    private static InheritableThreadLocal<Map<String, Object>> context = new InheritableThreadLocal<>();

    /**
     * 清除上下文信息
     */
    public static void clearContext() {
        context.remove();
    }

    /**
     * 清除上下文信息
     * @param key
     */
    public static void clearContext(String key) {
        Map<String, Object> data = context.get();
        data.remove(key);
    }

    /**
     * 添加单个上下文项
     * @param key
     * @param value
     */
    public static void setContextItem(String key, Object value) {
        Map<String, Object> data = context.get();
        if (data == null) {
            data = new HashMap<>();
            context.set(data);
        }
        data.put(key, value);
    }

    /**
     * 获取单个上下文项
     * @param key
     * @return
     */
    public static Object getContextItem(String key) {
        Map<String, Object> data = context.get();
        if (data != null) {
            return data.get(key);
        }
        return null;
    }

    /**
     * 获取指定类类型的上下文值
     * @param key
     * @param valueType
     * @return
     * @param <T>
     */
    public static <T> T getContextItem(String key,Class<T> valueType) {
        Object val = getContextItem(key);
        if (val != null) {
            if (valueType.isInstance(val)) {
                return valueType.cast(val);
            }
        }
        return null;
    }
}
