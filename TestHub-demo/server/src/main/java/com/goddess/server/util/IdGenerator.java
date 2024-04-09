package com.goddess.server.util;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Slf4j
@Component
public class IdGenerator {

    private long workerId = 0;

    private long datacenterId = 1;

    private Snowflake snowflake = IdUtil.createSnowflake(workerId, datacenterId);

    @PostConstruct
    public void init() {
        try {
            workerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
            log.info("当前机器的IP： {}, workerId: {}", NetUtil.getLocalhostStr(), workerId);
        } catch (Exception e) {
            log.error("获取当前机器workerId 异常", e);
            workerId = NetUtil.getLocalhostStr().hashCode();
        }
    }

    /**
     * 使用默认的workId 和 datacenter
     *
     * @return
     */
    public synchronized long snowflakeId() {
        return snowflake.nextId();
    }

    public synchronized String snowflakeIdStr() {
        return snowflake.nextId() + "";
    }

    /**
     * 使用自定义的workerId 和 datacenter
     *
     * @param workerId
     * @param datacenterId
     * @return
     */
    public synchronized long snowflakeId(long workerId, long datacenterId) {
        return IdUtil.createSnowflake(workerId, datacenterId).nextId();
    }

    public static void main(String[] args) {
        System.out.println(new IdGenerator().snowflakeId(1, 1));
    }
}

