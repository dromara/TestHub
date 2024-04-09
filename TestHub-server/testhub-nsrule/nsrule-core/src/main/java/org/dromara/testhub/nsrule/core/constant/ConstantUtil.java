package org.dromara.testhub.nsrule.core.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/9/27 18:40
 */
public class ConstantUtil {
    public static List<String> dataTypes = new ArrayList<>();

    static {
        dataTypes.add(RuleConstant.DataType.NUMBER);
        dataTypes.add(RuleConstant.DataType.STRING);
        dataTypes.add(RuleConstant.DataType.BOLL);
        dataTypes.add(RuleConstant.DataType.TIME_YMD);
        dataTypes.add(RuleConstant.DataType.TIME_YMDHMS);
        dataTypes.add(RuleConstant.DataType.TIME_HMS);
    }

    public static boolean isBaseDataType(String dataType) {
        return dataTypes.contains(dataType.toUpperCase());
    }

    public static boolean isInnerDataType(String dataType) {
        boolean flag = isBaseDataType(dataType);
        if (flag == false) {
            flag = RuleConstant.DataType.MAP.equalsIgnoreCase(dataType);
        }
        return flag;
    }
}
