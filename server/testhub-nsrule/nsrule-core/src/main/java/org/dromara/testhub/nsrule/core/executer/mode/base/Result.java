package org.dromara.testhub.nsrule.core.executer.mode.base;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/9/27 19:02
 */
public class Result<T> {
    //false 代表程序运行没有出异常
    private boolean flag = false;
    //false 代表业务异常
    private boolean bizError = false;
    private Exception exception;
    private T content;
    private BaseLog log;

    public Result() {
    }

    public Result(Exception exception, T content) {
        this.exception = exception;
        this.flag = exception == null ? false : true;
        this.bizError = this.flag ? this.flag : false;
        this.content = content;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
        this.flag = exception == null ? false : true;
        this.bizError = this.flag ? true : false;
    }

    public BaseLog getLog() {
        return log;
    }

    public void setLog(BaseLog log) {
        this.log = log;
    }

    public boolean isFlag() {
        return flag;
    }

    public T getContent() {
        return content;
    }

    public void setBizError(boolean bizError) {
        this.bizError = bizError;
    }

    public boolean isBizError() {
        return bizError;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
