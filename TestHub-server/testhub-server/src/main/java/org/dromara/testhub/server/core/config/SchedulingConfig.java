package org.dromara.testhub.server.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
@EnableScheduling
public class SchedulingConfig {

    @Bean
    public ScheduledTaskRegistrar scheduledTaskRegistrar() {
        return new ScheduledTaskRegistrar();
    }
}
