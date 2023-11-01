package org.dromara.testhub.framework.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Slf4j
@Component
public class IdGenerator {
    private int nodeId = 1;

    private SnowFlakeGenerator snowFlakeGenerator = new SnowFlakeGenerator(nodeId);


    @PostConstruct
    public void init() {
        log.info("当前机器的nodeID:{}",nodeId);
    }

    /**
     * 使用默认的workId 和 datacenter
     *
     * @return
     */
    public synchronized long snowflakeId() {
        return snowFlakeGenerator.nextId();
    }

    public synchronized String snowflakeIdStr() {
        return snowFlakeGenerator.nextId() + "";
    }



}

