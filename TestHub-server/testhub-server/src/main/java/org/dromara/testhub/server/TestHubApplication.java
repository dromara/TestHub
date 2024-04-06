package org.dromara.testhub.server;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@Slf4j
@EnableAsync
@MapperScan(
        value={
                "org.dromara.testhub.server.infrastructure.repository.dao",
                "org.dromara.testhub.plugins.*.server.repository.dao"
})
@SpringBootApplication(scanBasePackages={"org.dromara.testhub"})
public class TestHubApplication {

    public static void main(String[] args) {

        SpringApplication.run(TestHubApplication.class, args);
    }
}
