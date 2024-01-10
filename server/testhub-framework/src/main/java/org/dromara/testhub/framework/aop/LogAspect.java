package org.dromara.testhub.framework.aop;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 请求日志
 *
 * @author 失败女神
 * @email: 18733123202@163.com
 * @date 2021/4/7 下午12:02
 * @Copyright © 女神帮
 */
@Slf4j
@Component
@Aspect
public class LogAspect {


//    @Pointcut("execution(public *  org.dromara.testhub.*.interfaces.api.*Controller.*(..))")
@Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping) " +
        "|| @annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void log() {
    }

    @Around("log()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        log.info("===============================请求开始=============================");
        log.info("[请求IP]:{}", request.getRemoteAddr());
        log.info("[请求URL]:{}", request.getRequestURL());
        log.info("[请求方式]:{}", request.getMethod());
        log.info("[请求类名]:{},[请求方法名]:{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        String reqParam = RequestMethod.GET.name().equals(request.getMethod()) ?
                JSONObject.toJSONString(request.getParameterMap()) :
                JSONObject.toJSONString(exclude(joinPoint.getArgs()));
        log.info("[请求参数]:{}", reqParam);
        Long start = System.nanoTime();
        Object[] args = joinPoint.getArgs();
        Object retVal = joinPoint.proceed(args);
        Long end = System.nanoTime();
        log.info("[请求返回值]:{}", JSONObject.toJSONString(retVal));
        log.info("[请求耗时]:{}豪秒", TimeUnit.NANOSECONDS.toMicros(end-start)/1000);
        log.info("===============================请求结束=============================");
        return retVal;
    }
    private List<Object> exclude(Object[] os){
        List<Object> res = new ArrayList<>();
        for (Object o:os) {
            if(!(o instanceof MultipartFile)){
                res.add(o);
            }
        }
        return res;
    }
}
