package org.dromara.testhub.framework.exception;

/**
 * @author 失败女神
 * @email: 18733123202@163.com
 * @date 2023/7/1 19:35
 */
public class AppException extends RuntimeException {
    private String msg;
    private String code = "500";

    public AppException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public AppException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public AppException(String msg, String code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public AppException(String msg, String code, Throwable e) {
        super(msg, e);
        this.msg = msg;
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

    @Override
    public String toString() {
        return "AppException{" + "msg='" + msg + '\'' + ", code='" + code + '\'' + '}';
    }

    public String toJson() {
        return "{" + "msg='" + msg + '\'' + ", code='" + code + '\'' + '}';
    }

}
