package org.dromara.testhub.plugins.http.server.dto;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dromara.testhub.plugins.http.actions.model.Body;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel
public class HttpApiSendResDto {
    private int timeout;


    //body请求数据类型
    private String reqType;

    //请求方式
    private String method;

    //实际url
    private String url;

    //rest实际参数
    private JSONObject rests;

    //params实际参数
    private JSONObject params;

    //headers实际参数
    private JSONObject headers;

    //cookies实际参数
    private JSONObject cookies;

    //实际请求body
    private String body;




    //响应 header
    private Map<String,String> reHeaders;
    //响应 cookie
    private JSONObject reCookies;
    //响应数据类型
    private String reType= Body.JSON;
    //响应数据
    private String resData;

    private boolean error = false;
    private String statusCode;
    private String statusName;
}
