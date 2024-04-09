package org.dromara.testhub.nsrule.core.constant;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/3 22:32
 */
public enum ExceptionCode {

    EC_0001("0001", "找不到{}的解释器"),
    EC_0002("0002", "找不到rule-config"),
    EC_0003("0003", "加载器类路径异常:{},{}"),
    EC_0004("0004", "加载器创建实例异常:{},{}"),
    EC_0005("0005", "公式解析异常{}"),
    EC_0006("0006", "formulaBuilder公式解析器创建失败{}"),
    EC_0008("0008", "actionParser路径解析异常:{}"),
    EC_0009("0009", "executeParser路径解析异常:{}"),

    EC_0101("0101", "{}决策图{}找不到第一跳{}"),
    EC_0102("0102", "{}决策树{}执行异常"),
    EC_0103("0103", "{}决策图{}没有符合条件的链接结果"),
    //    EC_0104("0104","未能识别条件{}中的被比较的阀值类型"),
//    EC_0105("0105","未能识别条件{}中的阀值类型"),
    EC_0106("0106", "{}不支持的操作类型"),
    EC_0107("0107", "参数({})不能为空"),
    EC_0108("0108", "{}特殊实现{}"),
    EC_0109("0109", "{}行为{}的返回值类型{}找不到"),

    EC_0201("0201", "数据不能为空"),
    EC_0202("0202", "时间转换异常"),
    EC_0203("0203", "不支持的数据类型"),
    EC_0204("0204", "介于类判断阀值异常"),

    EC_0301("0301", "执行器失败:{}"),
    EC_0302("0302", "该执行器:{}找不到行为:{}"),

    EC_9999("9999", "异常");

    ExceptionCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public String getCode() {
        return code;
    }

    public final String code;

    public final String msg;
}
