package org.dromara.testhub.nsrule.core.constant;

import java.time.format.DateTimeFormatter;

/**
 * 常量枚举类
 *
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/3 21:29
 */
public class RuleConstant {
    public class RuleModel {
        public static final String FLOW = "Flow";//逻辑
        public static final String GRAPH = "Graph";//关系
    }

    public class ExpressionType {
        public static final String LOGIC = "logic";//逻辑
        public static final String RELATION = "relation";//关系
    }

    public class EnumType {
        public static final String FIXED = "FIXED";//固定
        public static final String DYNAMIC = "DYNAMIC";//动态
    }

    public class DateFormatter {
        public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
        public static final String YYYY_MM_DD = "yyyy-MM-dd";
        public static final String HH_MM_SS = "HH:mm:ss";
    }

    public static class Formatter {
        public static DateTimeFormatter ydmhms = DateTimeFormatter.ofPattern(DateFormatter.YYYY_MM_DD_HH_MM_SS);
        public static DateTimeFormatter ydm = DateTimeFormatter.ofPattern(DateFormatter.YYYY_MM_DD);
        public static DateTimeFormatter hdm = DateTimeFormatter.ofPattern(DateFormatter.HH_MM_SS);
    }


    /**
     * 数据类型
     */
    public class DataType {
        //数值
        public static final String NUMBER = "NUMBER";
        //字符串
        public static final String STRING = "STRING";
        //布尔
        public static final String BOLL = "BOLL";
        //时间年月日  yyyy-MM-dd
        public static final String TIME_YMD = "TIME_YMD";
        //时间年月日时分秒 yyyy-MM-dd HH:mm:ss
        public static final String TIME_YMDHMS = "TIME_YMDHMS";
        //时间时分秒  HH:mm:ss
        public static final String TIME_HMS = "TIME_HMS";
        //map
        public static final String MAP = "MAP";
    }
    public class ArithmeticType{
        public static final String ADD = "+";
        public static final String SUB = "-";
        public static final String MUL = "*";
        public static final String DIV = "/";
        public static final String REM = "%";
    }
    /**
     * 操作符
     */
    public class OperationType {
        public static final String GT = "gt";//大于
        public static final String LE = "le";//小于等于

        public static final String LT = "lt";//小于
        public static final String GE = "ge";//大于等于

        public static final String EQ = "eq";//等于
        public static final String NEQ = "neq";//不等于

        public static final String EQD = "eqd";//等于(不区分大小写)
        public static final String NEQD = "neqd";//不等于(不区分)

        public static final String IN = "in";//在集合
        public static final String NIN = "nin";//不在集合

        public static final String EN = "en";//为空
        public static final String NN = "nn";//不为空

        public static final String RE = "re";//正则
        public static final String NRE = "nre";//不正则

        public static final String CN = "cn";//包含
        public static final String NCN = "ncn";//不包含

        public static final String BE = "be";//介于(闭区间)
        public static final String NBE = "nbe";//不介于(开区间)

        public static final String BED = "bed";//介于(开区间)
        public static final String NBED = "nbed";//不介于(开区间)

        public static final String BEL = "bel";//介于(前闭后开)
        public static final String NBEL = "nbel";//介于(前闭后开)

        public static final String BER = "bel";//介于(前开后闭)
        public static final String NBER = "nbel";//介于(前开后闭)

        public static final String Like = "like";//模糊

        public static final String AND = "and";//短路与
        public static final String OR = "or";//短路或
        //public static final String NOT ="not";//非
        //public static final String XOR ="xor";//异或


        public static final String LP = "lp";//左括号
        public static final String RP = "rp";//右括号
    }


    public class ConfigType {
        public static final String XML = "xml";
    }


}
