package org.dromara.testhub.plugins.http.core;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

@Data
public class ResData {
    public static Map<String, String> NONE_INFO = new HashMap();
    private int timeout;


    //body请求数据类型
    private String reqType;

    //请求方式
    private String method;

    //实际url
    private String url;

    //rest实际参数
    private JSONObject rests = SendHttp.NONE_INFO;

    //params实际参数
    private JSONObject params = SendHttp.NONE_INFO;

    //headers实际参数
    private JSONObject headers = SendHttp.NONE_INFO;

    //cookies实际参数
    private JSONObject cookies = SendHttp.NONE_INFO;

    //实际请求body
    private Object body = new HashMap<>();


    //响应 headers
    private Map<String, String> reHeaders = NONE_INFO;
    //响应 cookies
    private JSONObject reCookies = SendHttp.NONE_INFO;
    //响应数据类型
    private String reType = "json";
    //响应数据
    private Object resData = new HashMap<>();

    private boolean error = false;
    private String statusCode;
    private String statusName;
    private HttpResponse<byte[]> response;
}
