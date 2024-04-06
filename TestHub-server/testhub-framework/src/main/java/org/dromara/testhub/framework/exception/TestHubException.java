package org.dromara.testhub.framework.exception;

import cn.hutool.core.util.StrUtil;

public class TestHubException extends AppException {
    private String msg;
    private String code = "500";


    public TestHubException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public TestHubException(String msg, String[] params) {
        super(handlerMsg(msg, params));
        this.msg = super.getMessage();
    }

    public TestHubException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public TestHubException(Exception e) {
        super(e.getMessage(), e);
        this.msg = e.getMessage();
    }

    public TestHubException(String msg, String[] params, Throwable e) {
        super(handlerMsg(msg, params), e);
        this.msg = handlerMsg(msg, params);
    }

    public TestHubException(String msg, String code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public TestHubException(String msg, String[] params, String code) {
        super(handlerMsg(msg, params));
        this.msg = super.getMessage();
        this.code = code;
    }

    public TestHubException(String msg, String code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public TestHubException(String msg, String[] params, String code, Throwable e) {
        super(handlerMsg(msg, params), e);
        this.msg = super.getMessage();
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private static String handlerMsg(String msg, String[] params) {
        return StrUtil.format(msg, params);
    }
}
