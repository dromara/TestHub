package org.dromara.testhub.framework.interceptor;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Optional;


public class ExtraParamUtil {

    private final static TransmittableThreadLocal<ExtraParam> extraParamLocal = new TransmittableThreadLocal<>();

    public static ExtraParam getExtraParam() {
        if (!Optional.ofNullable(extraParamLocal.get()).isPresent()) {
            extraParamLocal.set(new ExtraParam());
        }
        return extraParamLocal.get();
    }

    public static void setExtraParam(ExtraParam extraParam) {
        extraParamLocal.set(extraParam);
    }

    public final static Map<String, String> map = Maps.newHashMap();

    public static void destroy() {
        extraParamLocal.remove();
    }

//    public static void main(String[] args) {
//        // 测试0.ThreadLocal普通测试;
//        // 结论0: ThreadLocal下子线程获取不到父线程的值
//        ExtraParamUtil.getExtraParam().setUserId(1L);
//        System.out.println(ExtraParamUtil.getExtraParam().getUserId());
//
//        Thread thread = new MyThreadTo();
//        thread.start();
//        try {
//            thread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static class MyThreadTo extends Thread {
//        @Override
//        public void run() {
//            System.out.println(ExtraParamUtil.getExtraParam().getTraceId());
//        }
//    }
}
