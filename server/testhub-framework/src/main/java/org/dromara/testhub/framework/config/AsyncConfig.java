package org.dromara.testhub.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@EnableAsync // 开启多线程, 项目启动时自动创建
@Configuration
public class AsyncConfig {
    @Bean("ruleEventExecutor")
    public ThreadPoolTaskExecutor ruleEventExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        int availProcessors = Runtime.getRuntime().availableProcessors();
        // 设置核心线程数
        executor.setCorePoolSize(availProcessors);
        // 设置最大线程数
        executor.setMaxPoolSize(20);
        // 设置队列大小
        executor.setQueueCapacity(1000);
        // 设置线程活跃时间(秒)
        executor.setKeepAliveSeconds(60);
        // 设置线程名前缀+分组名称
        executor.setThreadNamePrefix("ruleEventExecutor-");
        executor.setThreadGroupName("ruleEventExecutorsGroup-");
        // 所有任务结束后关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 初始化
        executor.initialize();
        return executor;
    }
}