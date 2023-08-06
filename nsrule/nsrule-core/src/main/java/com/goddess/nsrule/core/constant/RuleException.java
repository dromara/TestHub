package com.goddess.nsrule.core.constant;

import cn.hutool.core.util.StrUtil;

/**
 * 业务异常
 *
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/3 22:29
 */
public class RuleException extends RuntimeException {
    private String msg;
    private String code = "500";

    public RuleException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMsg());
        this.msg = exceptionCode.getMsg();
        this.code = exceptionCode.getCode();
    }

    public RuleException(ExceptionCode exceptionCode, String... params) {
        super(handlerMsg(exceptionCode.getMsg(), params));
        this.msg = exceptionCode.getMsg();
        this.code = exceptionCode.getCode();
    }

    public RuleException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public RuleException(String msg, String[] params) {
        super(handlerMsg(msg, params));
        this.msg = super.getMessage();
    }

    public RuleException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public RuleException(String msg, String[] params, Throwable e) {
        super(handlerMsg(msg, params), e);
        this.msg = handlerMsg(msg, params);
    }

    public RuleException(String msg, String code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public RuleException(String msg, String[] params, String code) {
        super(handlerMsg(msg, params));
        this.msg = super.getMessage();
        this.code = code;
    }

    public RuleException(Throwable e) {
        super(e);
    }

    public RuleException(String msg, String code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public RuleException(String msg, String[] params, String code, Throwable e) {
        super(handlerMsg(msg, params), e);
        this.msg = super.getMessage();
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private static String handlerMsg(String msg, String[] params) {
        return StrUtil.format(msg, params);
    }
}
