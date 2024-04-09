package org.dromara.testhub.framework.trace;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author 失败女神
 * @email: 18733123202@163.com
 * @date 2021/4/9 下午10:57
 * @Copyright © 女神帮
 */
@Slf4j
@Component
public class TraceInterceptor implements HandlerInterceptor {

    public static final String TRACE_ID = "traceId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String traceId = request.getHeader(TRACE_ID);
        if (StringUtils.isEmpty(traceId)) {
            MDC.put(TRACE_ID, UUID.randomUUID().toString());
        } else {
            MDC.put(TRACE_ID, traceId);
        }
        return true;
    }

}
