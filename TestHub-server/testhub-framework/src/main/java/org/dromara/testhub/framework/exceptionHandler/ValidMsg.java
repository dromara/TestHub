package org.dromara.testhub.framework.exceptionHandler;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 失败女神
 * @email: 18733123202@163.com
 * @date 2023/7/1 上午9:35
 */
@Data
public class ValidMsg implements Serializable {
    private static final long serialVersionUID = 1L;

    private String object;

    private String field;

    private String msg;

    public ValidMsg(String object, String field, String msg) {
        this.object = object;
        this.field = field;
        this.msg = msg;
    }

}
