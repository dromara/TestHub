package org.dromara.testhub.framework.config;

import org.dromara.testhub.framework.interceptor.ExtraParamInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebInterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 加入框架的拦截器，用于处理header中的公共参数
        registry.addInterceptor(new ExtraParamInterceptor()).addPathPatterns("/**");
    }

}
