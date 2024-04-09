package org.dromara.testhub.nsrule.core.executer.meta;

import org.dromara.testhub.nsrule.core.constant.RuleConstant;

import java.util.HashMap;
import java.util.Map;

public class MetaJavaType {
    private static Map<Class, String> JAVA_META_MAP = new HashMap<>();

    static {
        JAVA_META_MAP.put(java.lang.Integer.class, RuleConstant.DataType.NUMBER);
        JAVA_META_MAP.put(java.lang.Short.class, RuleConstant.DataType.NUMBER);
        JAVA_META_MAP.put(java.lang.Long.class, RuleConstant.DataType.NUMBER);
        JAVA_META_MAP.put(java.lang.Float.class, RuleConstant.DataType.NUMBER);
        JAVA_META_MAP.put(java.lang.Double.class, RuleConstant.DataType.NUMBER);
        JAVA_META_MAP.put(java.math.BigDecimal.class, RuleConstant.DataType.NUMBER);

        JAVA_META_MAP.put(java.lang.String.class, RuleConstant.DataType.STRING);

        JAVA_META_MAP.put(java.lang.Boolean.class, RuleConstant.DataType.BOLL);

        JAVA_META_MAP.put(java.time.LocalDate.class, RuleConstant.DataType.TIME_YMD);
        JAVA_META_MAP.put(java.time.LocalTime.class, RuleConstant.DataType.TIME_HMS);
        JAVA_META_MAP.put(java.time.LocalDateTime.class, RuleConstant.DataType.TIME_YMDHMS);
    }

    public static String getDataType(Object data){
        return JAVA_META_MAP.getOrDefault(data.getClass(),RuleConstant.DataType.MAP);
    }
    public static boolean checkDataType(Object data,String dataType){
        return JAVA_META_MAP.getOrDefault(data.getClass(),RuleConstant.DataType.MAP).equalsIgnoreCase(dataType);
    }
}
