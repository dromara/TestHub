package org.dromara.testhub.common.util;

import java.net.NetworkInterface;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Enumeration;

public class SnowFlakeGenerator {
    /**
     * 长度15
     */
    private static final int TOTAL_BITS = 53;
    /**
     * 毫秒级时间戳，可以用(2^41-1)/(1000*60*60*24*365)=69.7年，即2015+69.7=2084年
     */
    private static final int EPOCH_BITS = 41;
    /**
     * 最大31台，0-31一共32台
     */
    private static final int NODE_ID_BITS = 5;
    /**
     * 每毫秒自增最大127，0-127，一共128个
     */
    private static final int SEQUENCE_BITS = 7;

    /**
     * 最大节点
     * 移位算法可以很快的计算出几位二进制数所能表示的最大十进制数
     */
    private static final int maxNodeId = (int) (~(-1L << NODE_ID_BITS));
    /**
     * 最大自增数
     */
    private static final int maxSequence = (int) (~(-1L << SEQUENCE_BITS));

    /**
     * 偏移量，增大可用时间大概2015-1970=45年
     * 开始时间，2015-01-01T00:00:00Z
     */
    private static final long CUSTOM_EPOCH = 1420070400000L;

    /**
     * 当前节点
     */
    private volatile int nodeId;

    /**
     * 最后时间戳
     */
    private volatile long lastTimestamp = -1L;
    /**
     * 当前id，每次均+1
     */
    private volatile int sequence = 0;

    /**
     * 时间戳左移，空出节点和序列号位
     */
    private int EPOCH_SHIFT = TOTAL_BITS - EPOCH_BITS;
    /**
     * 节点位左移，空出序列号位
     */
    private int NODE_ID_SHIFT = TOTAL_BITS - EPOCH_BITS - NODE_ID_BITS;

    /**
     * 主动设置节点
     *
     * @param nodeId
     */
    public SnowFlakeGenerator(int nodeId) {
        if (nodeId < 0 || nodeId > maxNodeId) {
            throw new IllegalArgumentException(String.format("NodeId must be between %d and %d", 0, maxNodeId));
        }
        this.nodeId = nodeId;
    }

    /**
     * 生成节点默认id
     */
    public SnowFlakeGenerator() {
        this.nodeId = createNodeId();
    }

    /**
     * 获取下一个id
     *
     * @return
     */
    public synchronized long nextId() {
        //当前时间戳
        long currentTimestamp = timestamp();

        //出现时间回拨
        if (currentTimestamp < lastTimestamp) {
            //换个节点
            nodeId = createNodeId(true);
        }

        //当前轮
        if (currentTimestamp == lastTimestamp) {
            sequence = (sequence + 1) & maxSequence;
            if (sequence == 0) {
                //id用完，等下一毫秒
                currentTimestamp = waitNextMillis(currentTimestamp);
            }
        } else {
            //每轮开始
            sequence = 0;
        }

        lastTimestamp = currentTimestamp;

        //时间戳左移8位，即乘以2的8次方
        long id = currentTimestamp << EPOCH_SHIFT;
        //节点id左移3位，或运算拼id到一起
        id |= nodeId << NODE_ID_SHIFT;
        //或运算拼id到一起
        id |= sequence;
        //41bits|5bits|7bits
        return id;
    }


    /**
     * 获取秒
     *
     * @return
     */
    private static long timestamp() {
        return Instant.now().toEpochMilli() - CUSTOM_EPOCH;
    }

    private int createNodeId() {
        return createNodeId(false);
    }

    /**
     * 根据设备地址生成节点
     *
     * @return
     */
    private int createNodeId(boolean random) {
        int nodeId;
        try {
            if (random) {
                nodeId = (new SecureRandom().nextInt());
            } else {
                StringBuilder sb = new StringBuilder();
                Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
                while (networkInterfaces.hasMoreElements()) {
                    NetworkInterface networkInterface = networkInterfaces.nextElement();
                    byte[] mac = networkInterface.getHardwareAddress();
                    if (mac != null) {
                        for (int i = 0; i < mac.length; i++) {
                            sb.append(String.format("%02X", mac[i]));
                        }
                    }
                }
                //hash
                nodeId = sb.toString().hashCode();
            }
        } catch (Exception ex) {
            nodeId = (new SecureRandom().nextInt());
        }
        //取余
        nodeId = nodeId & maxNodeId;
        if (nodeId == this.nodeId) {
            //重新随机
            nodeId = createNodeId(true);
        }
        return nodeId;
    }

    /**
     * 等一毫秒
     *
     * @param currentTimestamp
     * @return
     */
    private long waitNextMillis(long currentTimestamp) {
        while (currentTimestamp == lastTimestamp) {
            currentTimestamp = timestamp();
        }
        return currentTimestamp;
    }

    public static void main(String[] args) {
        SnowFlakeGenerator snowFlakeGenerator = new SnowFlakeGenerator();
        System.out.println(snowFlakeGenerator.nextId());
    }
}
