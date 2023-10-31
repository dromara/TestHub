package org.dromara.testhub.server;

import lombok.extern.slf4j.Slf4j;
import org.dromara.testhub.sdk.PluginClassLoader;
import org.dromara.testhub.sdk.PluginFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.File;
import java.net.URL;

@Slf4j
@EnableAsync
@MapperScan(value={"org.dromara.testhub.*.*.repository.dao"})
@SpringBootApplication(scanBasePackages={"org.dromara.testhub"})
public class TestHubApplication {

    public static void main(String[] args) {

        SpringApplication.run(TestHubApplication.class, args);
    }
}
