package com.ds.dss.common.utils;

import java.util.UUID;

public class SnowflakeIdWorker
{
    private final long twepoch = 1420041600000L;
    private final long workerIdBits = 5L;
    private final long datacenterIdBits = 5L;
    private final long maxWorkerId = 31L;
    private final long maxDatacenterId = 31L;
    private final long sequenceBits = 12L;
    private final long workerIdShift = 12L;
    private final long datacenterIdShift = 17L;
    private final long timestampLeftShift = 22L;
    private final long sequenceMask = 4095L;
    private long workerId;
    private long datacenterId;
    private long sequence;
    private long lastTimestamp;
    
    public SnowflakeIdWorker(final long workerId, final long datacenterId) {
        this.sequence = 0L;
        this.lastTimestamp = -1L;
        if (workerId > 31L || workerId < 0L) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", 31L));
        }
        if (datacenterId > 31L || datacenterId < 0L) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", 31L));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }
    
    public synchronized long nextId() {
        long timestamp = this.timeGen();
        if (timestamp < this.lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", this.lastTimestamp - timestamp));
        }
        if (this.lastTimestamp == timestamp) {
            this.sequence = (this.sequence + 1L & 0xFFFL);
            if (this.sequence == 0L) {
                timestamp = this.tilNextMillis(this.lastTimestamp);
            }
        }
        else {
            this.sequence = 0L;
        }
        this.lastTimestamp = timestamp;
        return timestamp - 1420041600000L << 22 | this.datacenterId << 17 | this.workerId << 12 | this.sequence;
    }
    
    protected long tilNextMillis(final long lastTimestamp) {
        long timestamp;
        for (timestamp = this.timeGen(); timestamp <= lastTimestamp; timestamp = this.timeGen()) {}
        return timestamp;
    }
    
    protected long timeGen() {
        return System.currentTimeMillis();
    }
    
    public static void main(final String[] args) {
        String netIpKey = "-" + UUID.randomUUID();
        String netIpKe2= "-" + UUID.randomUUID();
        System.out.println("1>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+netIpKey);
        System.out.println("2>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+netIpKe2);

//        final SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0L, 0L);
//
//        final long id = idWorker.nextId();
//        System.out.println(id);
//        System.out.println(id);
//        for (int i = 0; i < 10; ++i) {
//            final long id = idWorker.nextId();
//            System.out.println(Long.toBinaryString(id));
//            System.out.println(id);
//        }
    }
}
