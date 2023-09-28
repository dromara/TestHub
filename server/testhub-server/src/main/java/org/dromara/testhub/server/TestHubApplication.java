package org.dromara.testhub.server;

import lombok.extern.slf4j.Slf4j;
import org.dromara.testhub.sdk.PluginClassLoader;
import org.dromara.testhub.sdk.PluginFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.File;
import java.net.URL;

@Slf4j
@EnableAsync
@SpringBootApplication(scanBasePackages={"org.dromara.testhub"})
public class TestHubApplication {

    public static void main(String[] args) throws Exception {


        SpringApplication.run(TestHubApplication.class, args);
    }
}
