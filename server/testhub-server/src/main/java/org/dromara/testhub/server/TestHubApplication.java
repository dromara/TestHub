package org.dromara.testhub.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@Slf4j
@EnableAsync
@SpringBootApplication(scanBasePackages={"org.dromara.testhub"})
public class TestHubApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(TestHubApplication.class, args);
    }
}
