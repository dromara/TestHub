package org.dromara.testhub.plugins.http.core;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.dromara.testhub.framework.exception.TestHubException;
import org.dromara.testhub.nsrule.core.executer.context.Context;
import org.dromara.testhub.nsrule.core.executer.mode.base.action.Param;
import org.dromara.testhub.plugins.http.actions.model.Body;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Entities;
import org.jsoup.parser.Parser;
import org.springframework.http.MediaType;

import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.*;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

public class SendHttp {
    public static JSONObject NONE_INFO = new JSONObject();
    public static Map<Integer, String> STATUS_DESC = new HashMap<>();

    static {
        STATUS_DESC.put(100, "Continue");
        STATUS_DESC.put(101, "Switching Protocols");
        STATUS_DESC.put(200, "OK");
        STATUS_DESC.put(201, "Created");
        STATUS_DESC.put(202, "Accepted");
        STATUS_DESC.put(204, "No Content");
        STATUS_DESC.put(300, "Multiple Choices");
        STATUS_DESC.put(301, "Moved Permanently");
        STATUS_DESC.put(302, "Found");
        STATUS_DESC.put(304, "Not Modified");
        STATUS_DESC.put(307, "Temporary Redirect");
        STATUS_DESC.put(400, "Bad Request");
        STATUS_DESC.put(401, "Unauthorized");
        STATUS_DESC.put(403, "Forbidden");
        STATUS_DESC.put(404, "Not Found");
        STATUS_DESC.put(405, "Method Not Allowed");
        STATUS_DESC.put(409, "Conflict");
        STATUS_DESC.put(410, "Gone");
        STATUS_DESC.put(415, "Unsupported Media Type");
        STATUS_DESC.put(500, "Internal Server Error");
        STATUS_DESC.put(502, "Bad Gateway");
        STATUS_DESC.put(503, "Service Unavailable");
        STATUS_DESC.put(504, "Gateway Timeout");
    }

    /**
     * 替换rest风格的参数
     */
    private static String replaceRests(String input, JSONObject map) {
        String output = input;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue().toString();
            String placeholder = "{" + key + "}";
            output = output.replace(placeholder, value);
        }
        return output;
    }



    public static ResData send(Context context, HttpModel httpModel, ResData resData){
        int timeout = getTimeout(httpModel);

        resData.setTimeout(timeout);


        JSONObject rests = Param.buildParams(context, httpModel.getRests(), context.getData());
        resData.setRests(rests);

        String url = httpModel.execUrl(context,rests);
        url = replaceRests(url,rests);


        String method = httpModel.getMethod();
        resData.setReqType(httpModel.getBody().getLanguage().toLowerCase());
        resData.setMethod(method);

        JSONObject params = Param.buildParams(context, httpModel.getParams(), context.getData());

        //params拼接
        url = appendURL(url, params);
        url = url.trim();
        resData.setUrl(url);
        resData.setParams(params);

        //headers计算
        JSONObject headers = getHeaders(context, httpModel);
        //设置cookies
        JSONObject cookies = getCookies(context,headers,httpModel);

        resData.setHeaders(headers);
        resData.setCookies(cookies);

        Object reqBody = getBody(context, httpModel);
        resData.setBody(reqBody);

        HttpClient httpClient;

        HttpRequest.Builder httpBuilder = HttpRequest.newBuilder()
                .timeout(Duration.ofSeconds(timeout))
                .uri(URI.create(url));
        httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(timeout))
                .build();

        initHeaders(httpBuilder, headers);
        initBody(httpBuilder, reqBody, method);

        HttpRequest request = httpBuilder.build();

        AtomicReference<Throwable> unknownErr = new AtomicReference<>();

        CompletableFuture<HttpResponse<byte[]>> responseFuture = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofByteArray());
        try {
            responseFuture.whenComplete((response, exception) -> {
                if (exception != null) {
                    resData.setError(true);
                    if (exception instanceof ExecutionException) {
                        Throwable cause = exception.getCause();
                        if (cause instanceof ConnectException) {
                            resData.setStatusCode("ConnectException");
                            resData.setStatusName("连接异常");
                        } else if (cause instanceof HttpConnectTimeoutException) {
                            resData.setStatusCode("ConnectTimeoutException");
                            resData.setStatusName("链接超时" + timeout + "秒");
                        } else if (cause instanceof HttpTimeoutException) {
                            resData.setStatusCode("HttpTimeoutException");
                            resData.setStatusName("返回超时" + timeout + "秒");
                        } else {
                            unknownErr.set(cause);
                        }
                    } else {
                        unknownErr.set(exception);
                    }
                }
            });
            HttpResponse<byte[]> response = responseFuture.get(timeout, TimeUnit.SECONDS);
            if (unknownErr.get() != null) {
                resData.setStatusCode(unknownErr.get().getClass().getSimpleName());
            }
            if (response == null) {
                resData.setStatusCode("NoDataException");
                resData.setStatusCode("未响应数据");
                return resData;
            }
            resData.setResponse(response);
            resData.setReHeaders(response.headers().map().entrySet().stream().collect(HashMap::new, (map, entry) -> map.put(entry.getKey(), String.join(";", entry.getValue())), HashMap::putAll));
            resData.setStatusCode(response.statusCode()+"");
            resData.setStatusName(getStatusCodeDescription(response.statusCode()));
            initResBody(response,resData);
            initCookies(response,resData);
            return resData;
        } catch (TimeoutException e) {
            resData.setError(true);
            resData.setStatusCode("TimeoutException");
            resData.setStatusName("返回超时" + timeout + "秒");
            responseFuture.cancel(true);
            return resData;
        } catch (ExecutionException e) {
            resData.setError(true);
            resData.setStatusCode("ExecutionException");
            return resData;
        } catch ( InterruptedException e) {
            resData.setError(true);
            resData.setStatusCode("InterruptedException");
            return resData;
        }
    }

    private static JSONObject getCookies(Context context,JSONObject headers, HttpModel httpModel) {
        if(!headers.containsKey("Cookie")){
            JSONObject cookies = Param.buildParams(context, httpModel.getCookies(), context.getData());
            if(!cookies.isEmpty()){
                StringBuilder cookieStr = new StringBuilder();
                for (String key : cookies.keySet()) {
                    cookieStr.append(key).append("=").append(cookies.getString(key)).append("; ");
                }
                headers.put("Cookie",cookieStr.toString());
            }
            return cookies;
        }
        return NONE_INFO;
    }

    private static JSONObject initCookies(HttpResponse<byte[]> response, ResData resData) {
        JSONObject cookieMap = new JSONObject();
        List<String> cookieList = response.headers().map().get("Set-Cookie");
        if(CollectionUtil.isNotEmpty(cookieList)){
            String cookieStr = cookieList.get(0);
            String[] cookies = cookieStr.split("; ");
            for (String cookie : cookies) {
                String[] parts = cookie.split("=", 2);
                String key = parts[0];
                String value = parts.length > 1 ? parts[1] : "";
                cookieMap.put(key, value);
            }
        }
        resData.setReCookies(cookieMap);
        return cookieMap;
    }
    private static void initResBody(HttpResponse<byte[]> response,ResData resData) {
        List<String> types = response.headers().map().get("Content-Type");
        if (CollectionUtil.isEmpty(types)) {
            types = List.of(MediaType.APPLICATION_JSON_VALUE);
        }
        List<String> splitTypes = new ArrayList<>();
        for (String type : types) {
            String[] parts = type.split(";");
            for (String part : parts) {
                splitTypes.add(part.trim());
            }
        }
        if (splitTypes.contains(MediaType.TEXT_HTML_VALUE)) {
            resData.setReType( "html");
            resData.setResData(formatHTMLString(new String(response.body(), StandardCharsets.UTF_8)));
        } else if (splitTypes.contains(MediaType.APPLICATION_XML_VALUE)) {
            resData.setReType( "xml");
            resData.setResData(formatHTMLString(new String(response.body(), StandardCharsets.UTF_8)));
        } else if (splitTypes.contains(MediaType.APPLICATION_JSON_VALUE)) {
            resData.setResData(JSON.parse(new String(response.body(), StandardCharsets.UTF_8)));
            resData.setReType( "json");
        } else {
            resData.setResData(new String(response.body(), StandardCharsets.UTF_8));
            resData.setReType( "text");
        }
    }
    /**
     * 计算Headers
     */
    private static JSONObject getHeaders(Context context, HttpModel httpModel) {
        Body body = httpModel.getBody();
        JSONObject headers = Param.buildParams(context, httpModel.getHeaders(), context.getData());
        if (Body.NONE.equalsIgnoreCase(body.getType()) || StringUtils.isNotEmpty(headers.getString("Content-Type"))) {
            headers.put("Content-Type", MediaType.APPLICATION_JSON_VALUE);
            return headers;
        }
        if (Body.RAW.equalsIgnoreCase(body.getType())) {
            if (Body.JSON.equalsIgnoreCase(body.getLanguage())) {
                headers.put("Content-Type", MediaType.APPLICATION_JSON_VALUE);
            } else if (Body.XML.equalsIgnoreCase(body.getLanguage())) {
                headers.put("Content-Type", MediaType.APPLICATION_XML_VALUE);
            } else if (Body.TEXT.equalsIgnoreCase(body.getLanguage())) {
                headers.put("Content-Type", MediaType.TEXT_PLAIN_VALUE);
            }
        } else if (Body.FROM_DATA.equalsIgnoreCase(body.getType())) {
            headers.put("Content-Type", MediaType.MULTIPART_FORM_DATA_VALUE);
        } else if (Body.X_WWW_FORM_URENCODED.equalsIgnoreCase(body.getType())) {
            headers.put("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        }

        return headers;
    }

    /**
     * 将params拼接 到Url中
     */
    public static String appendURL(String baseURL, JSONObject jsonObject){
        StringBuilder sb = new StringBuilder(baseURL);
        Set<String> keys = jsonObject.keySet();
        if (!keys.isEmpty()) {
            sb.append("?");
        }
        int num = 0;
        for (String key : keys) {
            String value = jsonObject.getString(key);
            try {
                sb.append(key).append("=").append(URLEncoder.encode(value, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
            }
            num++;
            if (num < keys.size()) {
                sb.append("&");
            }
        }
        return sb.toString();
    }

    /**
     * 获取请求体
     */
    private static Object getBody(Context context, HttpModel httpModel){
        Body body = httpModel.getBody();
        if (Body.RAW.equalsIgnoreCase(body.getType())) {
            String content = getContent(context,body);
            if (Body.JSON.equalsIgnoreCase(body.getLanguage())) {
                try {
                    return JSONObject.parseObject(content);
                } catch (Exception e) {
                    return JSONArray.parseArray(content);
                }
            } else if (Body.XML.equalsIgnoreCase(body.getLanguage())) {
                return formatHTMLString(content);
            } else if (Body.TEXT.equalsIgnoreCase(body.getLanguage())) {
                return content;
            }
        } else if (Body.FROM_DATA.equalsIgnoreCase(body.getType())) {
            return Param.buildParams(context, body.getDatas(), context.getData());
        }
        return NONE_INFO;
    }
    public static String formatHTMLString(String htmlString) {
        Document doc = Jsoup.parse(htmlString, "", Parser.xmlParser());
        doc.outputSettings().indentAmount(2);
        doc.outputSettings().prettyPrint(true);
        doc.outputSettings().escapeMode(Entities.EscapeMode.xhtml);
        return doc.html();
    }
    /**
     * 获取内容
     */
    public static String getContent(Context context,  Body body) {
        try {
            return body.getBound().build(context);
        } catch (TestHubException e) {
            throw e;
        } catch (Exception e) {
            throw new TestHubException("模板异常" + e.getMessage());
        }
    }

    /**
     * 填充请求体
     */
    private static void initBody(HttpRequest.Builder httpBuilder, Object bodyInfo, String method) {
        if (method.equalsIgnoreCase("POST")) {
            if (bodyInfo instanceof String || bodyInfo instanceof JSONArray || bodyInfo instanceof JSONObject) {
                httpBuilder.POST(HttpRequest.BodyPublishers.ofString(bodyInfo.toString(), StandardCharsets.UTF_8));
            } else {
                httpBuilder.GET();
            }
        } else if (method.equalsIgnoreCase("GET")) {
            httpBuilder.GET();
        } else if (method.equalsIgnoreCase("PUT")) {
            if (bodyInfo instanceof String) {
                httpBuilder.PUT(HttpRequest.BodyPublishers.ofString(bodyInfo.toString(), StandardCharsets.UTF_8));
            } else if (bodyInfo instanceof JSONObject) {
                httpBuilder.PUT(HttpRequest.BodyPublishers.ofString(JSONObject.toJSONString(bodyInfo), StandardCharsets.UTF_8));
            } else {
                httpBuilder.GET();
            }
        } else if (method.equalsIgnoreCase("HEAD")) {

        } else if (method.equalsIgnoreCase("DELETE")) {
            httpBuilder.DELETE();
        } else if (method.equalsIgnoreCase("PATCH")) {
        } else if (method.equalsIgnoreCase("OPTIONS")) {
        } else {
            httpBuilder.GET();
        }
    }

    private static void initHeaders(HttpRequest.Builder httpBuilder, JSONObject headers) {
        for (String key : headers.keySet()) {
            httpBuilder.header(key, headers.getString(key));
        }
    }

    public static int getTimeout(HttpModel httpModel) {
        return httpModel.getTimeout() > 0 ? httpModel.getTimeout() : 60;
    }
    public static String getStatusCodeDescription(int statusCode) {
        String description = STATUS_DESC.get(statusCode);
        if (description == null) {
            description = "Unknown status code";
        }
        return description;
    }

}
