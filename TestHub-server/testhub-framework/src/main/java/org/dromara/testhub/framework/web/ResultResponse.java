package org.dromara.testhub.framework.web;

import org.dromara.testhub.framework.trace.TraceInterceptor;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.slf4j.MDC;

import java.io.Serializable;

/**
 * @author 失败女神
 * @email: 18733123202@163.com
 * @date 2021/4/7 上午9:35
 * @Copyright © 女神帮
 */
@Data
public class ResultResponse<T> implements Serializable {

    /**
     * 业务处理结果说明，一般处理成功返回成功，处理失败返回失败说明。
     */
    @JsonProperty(index = 10)
    @ApiModelProperty(position = 10)
    private String code;
    /**
     * 提示消息
     */
    @JsonProperty(index = 20)
    @ApiModelProperty(position = 20)
    private String msg;
    /**
     * 链路ID
     */
    @JsonProperty(index = 30)
    @ApiModelProperty(position = 30)
    private String traceId;
    /**
     * 业务响应参数,具体由每个接口定义
     */
    @JsonProperty(index = 40)
    @ApiModelProperty(position = 40)
    private T data;

    @JsonProperty(index = 50)
    @ApiModelProperty(position = 50)
    private String detail;

    public ResultResponse() {

    }

    public ResultResponse(String code) {
        this.code = code;
    }

    public ResultResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
        this.traceId = MDC.get(TraceInterceptor.TRACE_ID);
    }

    public ResultResponse(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.traceId = MDC.get(TraceInterceptor.TRACE_ID);
    }

    public static <T> ResultResponse ok() {
        ResultResponse dto = new ResultResponse<T>();
        dto.code = "000000";
        dto.msg = "Success";
        dto.traceId = MDC.get(TraceInterceptor.TRACE_ID);
        return dto;
    }

    public static <T> ResultResponse ok(T data) {
        ResultResponse dto = new ResultResponse<T>();
        dto.code = "000000";
        dto.msg = "Success";
        dto.data = data;
        dto.traceId = MDC.get(TraceInterceptor.TRACE_ID);
        return dto;
    }

    public static <T> ResultResponse ok(String code, String msg, T data) {
        ResultResponse dto = new ResultResponse<T>();
        dto.code = code;
        dto.msg = msg;
        dto.data = data;
        dto.traceId = MDC.get(TraceInterceptor.TRACE_ID);
        return dto;
    }

    public static <T> ResultResponse error() {
        return error("500", "未知异常，请联系管理员");
    }

    public static <T> ResultResponse error(String msg) {
        return error("500", msg);
    }

    public static <T> ResultResponse error(String code, String msg) {
        ResultResponse dto = new ResultResponse<Void>();
        dto.code = code;
        dto.msg = msg;
        dto.traceId = MDC.get(TraceInterceptor.TRACE_ID);
        return dto;
    }
    public static <T> ResultResponse error(String code, String msg,String detail) {
        ResultResponse dto = new ResultResponse<Void>();
        dto.code = code;
        dto.msg = msg;
        dto.detail = detail;
        dto.traceId = MDC.get(TraceInterceptor.TRACE_ID);
        return dto;
    }

    public String toSimpleJson() {
        return "{" + "code='" + code + '\'' + ", msg='" + msg + '\'' + "}";
    }
}

