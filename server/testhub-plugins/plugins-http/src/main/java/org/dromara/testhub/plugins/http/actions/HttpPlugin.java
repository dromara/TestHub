package org.dromara.testhub.plugins.http.actions;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.goddess.nsrule.core.executer.context.Context;
import com.goddess.nsrule.core.executer.mode.base.action.Param;
import com.goddess.nsrule.core.executer.mode.base.action.RunState;
import com.goddess.nsrule.core.executer.mode.ruleLine.JavaActuator;
import org.dromara.testhub.framework.exception.TestHubException;
import org.dromara.testhub.plugins.http.actions.model.Body;
import org.dromara.testhub.plugins.http.actions.model.HttpModel;
import org.dromara.testhub.plugins.http.actions.model.TestHubActionHttp;
import org.dromara.testhub.plugins.http.actions.model.TestHubExecuteHttp;
import org.dromara.testhub.sdk.action.*;
import org.dromara.testhub.sdk.action.model.rule.TestHubAction;
import org.dromara.testhub.sdk.action.model.rule.TestHubExecute;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Entities;
import org.jsoup.parser.Parser;
import org.springframework.http.MediaType;

import java.net.URI;
import java.net.http.*;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

public class HttpPlugin implements Plugin {
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

    @Override
    public String getType() {
        return "HTTP";
    }

    @Override
    public BaseDTOConvertor getDTOConvertor() {
        return new HttpDTOConvertor();
    }

    @Override
    public BaseXMLExecuteParser getXMLExecuteParser() {
        return new HttpXMLExecuteParser();
    }

    @Override
    public BaseJsonExecuteParser getJsonExecuteParser() {
        return new HttpJsonExecuteParser();
    }

    @Override
    public BaseXMLActionParser getXMLActionParser() {
        return new HttpXMLActionParser();
    }

    @Override
    public BaseJsonActionParser getJsonActionParser() {
        return new HttpJsonActionParser();
    }

    @Override
    public void execute(Context context, TestHubAction action, TestHubExecute execute, JSONObject data, RunState.Item runState) throws Exception {
        TestHubActionHttp actionHttp = (TestHubActionHttp) action;
        TestHubExecuteHttp executeHttp = (TestHubExecuteHttp) execute;
        List<Map<String, Object>> reData = new ArrayList<>();
        runState.getResult().setContent(reData);

        JSONObject result = new JSONObject();
        result.put("data", NONE_INFO);
        result.put("headers", NONE_INFO);
        reData.add(result.getInnerMap());

        HttpModel httpModel = actionHttp.getHttpModel();
        String url = httpModel.getUrl();

        String method = httpModel.getMethod();
        runState.addRunParams("reType", "json");
        runState.addRunParams("reqType", httpModel.getBody().getLanguage().toLowerCase());
        runState.addRunParams("method", method);

        JSONObject rests = Param.buildParams(context, httpModel.getRests(), context.getData());
        runState.addRunParams("rests", rests);
        //Rest替换
        url = replaceRests(url, rests.getInnerMap());

        JSONObject params = Param.buildParams(context, httpModel.getParams(), context.getData());

        //params拼接
        url = appendURL(url, params);
        url = url.trim();
        runState.addRunParams("params", params);

        //headers计算
        JSONObject headers = getHeaders(context, httpModel);
        runState.addRunParams("headers", headers);
        runState.addRunParams("url", url);

        Object reqBody = getBody(context, httpModel, data, actionHttp);
        runState.addRunParams("body", reqBody);


        HttpClient httpClient;

        HttpRequest.Builder httpBuilder = HttpRequest.newBuilder()
                .timeout(Duration.ofSeconds(getTimeout(httpModel)))
                .uri(URI.create(url));
        httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(getTimeout(httpModel)))
                .build();

        initHeaders(httpBuilder, headers);
        initBody(httpBuilder, reqBody, httpModel);

        HttpRequest request = httpBuilder.build();

        result.put("data", NONE_INFO);
        result.put("headers", NONE_INFO);

        AtomicReference<Throwable> unknownErr = new AtomicReference<>();

        CompletableFuture<HttpResponse<String>> responseFuture = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        try {
            responseFuture.whenComplete((response, exception) -> {
                if (exception != null) {
                    runState.getResult().setBizError(true);
                    if (exception instanceof ExecutionException) {
                        Throwable cause = exception.getCause();
                        if (cause instanceof java.net.ConnectException) {
                            runState.addRunParams("statusCode", "ConnectException");
                            runState.addRunParams("statusName", "连接异常");
                        } else if (cause instanceof java.net.http.HttpConnectTimeoutException) {
                            runState.addRunParams("statusCode", "ConnectTimeoutException");
                            runState.addRunParams("statusName", "链接超时" + getTimeout(httpModel) + "秒");
                        } else if (cause instanceof java.net.http.HttpTimeoutException) {
                            runState.addRunParams("statusCode", "TimeoutException");
                            runState.addRunParams("statusName", "返回超时" + getTimeout(httpModel) + "秒");
                        } else {
                            unknownErr.set(cause);
                        }
                    } else {
                        unknownErr.set(exception);
                    }
                } else {
                    //成功
                    execResult(response, result, runState, httpModel, context, executeHttp);
                }
            });
            HttpResponse<String> response = responseFuture.get(getTimeout(httpModel), TimeUnit.SECONDS);
            if (response == null && unknownErr.get() == null) {
                throw new Exception(unknownErr.get());
            }

        } catch (TimeoutException e) {
            runState.addRunParams("statusCode", "TimeoutException");
            runState.addRunParams("statusName", "返回超时" + getTimeout(httpModel) + "秒");
            // 在超时时，你可以选择取消异步操作
            responseFuture.cancel(true);
        } catch (ExecutionException | InterruptedException e) {
            throw e;
        }


//                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
//                execResult(response,result,runState,httpModel);


    }

    private void execResult(HttpResponse<String> response, JSONObject result, RunState.Item runState, HttpModel httpModel, Context context, TestHubExecuteHttp executeHttp) {
        result.put("headers", response.headers().map().entrySet().stream().collect(HashMap::new, (map, entry) -> map.put(entry.getKey(), String.join(";", entry.getValue())), HashMap::putAll));
        runState.addRunParams("statusCode", response.statusCode());
        runState.addRunParams("statusName", getStatusCodeDescription(response.statusCode()));

        if (response.statusCode() == 200) {
            Map<String, Object> reMap = getResBody(response, httpModel);
            result.put("data", reMap.get("data"));
            runState.addRunParams("reType", reMap.get("type"));
        } else {
            runState.getResult().setBizError(true);
        }
        runState.addRunParams("result", result);

        if (executeHttp.getExpression() != null) {
            JavaActuator.Log log = new JavaActuator.Log();
            runState.addRunParams("errorLog", log);
            try {
                context.pushData(result);
                boolean flag = JavaActuator.execute(context, executeHttp.getExpression(), log);
                if (!flag) {
                    runState.getResult().setBizError(true);
                }
            } finally {
                context.removeData();
            }
        }
    }

    private Map<String, Object> getResBody(HttpResponse<String> response, HttpModel httpModel) {
        Map<String, Object> reMap = new HashMap<>();
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
            reMap.put("type", "html");
            reMap.put("data", formatHTMLString(response.body()));
        } else if (splitTypes.contains(MediaType.APPLICATION_XML_VALUE)) {
            reMap.put("type", "xml");
        } else if (splitTypes.contains(MediaType.APPLICATION_JSON_VALUE)) {
            reMap.put("data", JSON.parse(response.body()));
            reMap.put("type", "json");
        } else {
            reMap.put("type", "text");
            reMap.put("data", response.body());
        }
        return reMap;
    }

    public static String formatHTMLString(String htmlString) {
        Document doc = Jsoup.parse(htmlString, "", Parser.xmlParser());
        doc.outputSettings().indentAmount(2);
        doc.outputSettings().prettyPrint(true);
        doc.outputSettings().escapeMode(Entities.EscapeMode.xhtml);
        return doc.html();
    }

    public static String getStatusCodeDescription(int statusCode) {
        String description = STATUS_DESC.get(statusCode);
        if (description == null) {
            description = "Unknown status code";
        }
        return description;
    }

    /**
     * 填充headers
     */
    private void initHeaders(HttpRequest.Builder httpBuilder, JSONObject headers) {
        for (String key : headers.keySet()) {
            httpBuilder.header(key, headers.getString(key));
        }
    }

    /**
     * 填充请求体
     */
    private void initBody(HttpRequest.Builder httpBuilder, Object bodyInfo, HttpModel httpModel) {
        String method = httpModel.getMethod();
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


    /**
     * 计算Headers
     */
    private JSONObject getHeaders(Context context, HttpModel httpModel) {
        Body body = httpModel.getBody();
        JSONObject headers = Param.buildParams(context, httpModel.getHeaders(), context.getData());
        if (Body.NONE.equalsIgnoreCase(body.getType()) || StringUtils.isNotEmpty(headers.getString("Content-Type"))) {
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
     * 获取请求体
     */
    private Object getBody(Context context, HttpModel httpModel, JSONObject data, TestHubActionHttp action) throws Exception {
        Body body = httpModel.getBody();
        if (Body.RAW.equalsIgnoreCase(body.getType())) {
            String content = getContent(context, data, body, action);
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

    /**
     * 替换rest风格的参数
     */
    private static String replaceRests(String input, Map<String, Object> map) {
        String output = input;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue().toString();
            String placeholder = "{" + key + "}";
            output = output.replace(placeholder, value);
        }
        return output;
    }

    /**
     * 将params拼接 到Url中
     */
    public static String appendURL(String baseURL, JSONObject jsonObject) {
        StringBuilder sb = new StringBuilder(baseURL);
        Set<String> keys = jsonObject.keySet();
        if (!keys.isEmpty()) {
            sb.append("?");
        }
        int num = 0;
        for (String key : keys) {
            String value = jsonObject.getString(key);
            sb.append(key).append("=").append(value);
            num++;
            if (num < keys.size()) {
                sb.append("&");
            }
        }
        return sb.toString();
    }

    /**
     * 获取内容
     */
    public String getContent(Context context, JSONObject param, Body body, TestHubActionHttp action) throws Exception {
        try {
            return body.getBound().build(context);
        } catch (TestHubException e) {
            throw e;
        } catch (Exception e) {
            throw new TestHubException("模板异常" + e.getMessage());
        }
    }

    public int getTimeout(HttpModel httpModel) {
        return httpModel.getTimeout() > 0 ? httpModel.getTimeout() : 60;
    }

}
