package org.dromara.testhub.framework.interceptor;

import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Slf4j
public class ExtraParamInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        //ExtraParam extraParam = ExtraParamUtil.getExtraParam();
        //在这里要重新new 一个新的，不能用旧的，保证每个请求都重新实例化，以免线程池中同的线程被重用后还是获取了旧的extraparam
        ExtraParam extraParam = new ExtraParam();

        String saToken = StpUtil.getTokenValue();

        if (StringUtils.isNotEmpty(saToken)) {
            if (StpUtil.isLogin()) {
                extraParam.setSaToken(saToken);
                extraParam.setUserId(Long.parseLong(StpUtil.getLoginId().toString()));
            }
        }

        ExtraParamUtil.setExtraParam(extraParam);

        return super.preHandle(request, response, handler);
    }

}