package com.mooyle.seckill.common.redis;

public abstract class RedisBasePrefix implements RedisKeyPrefix {

    private int expireSeconds;

    private String prefix;

    public RedisBasePrefix(String prefix) {
        this(0, prefix);
    }

    public RedisBasePrefix(int expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    @Override
    public int expireSeconds() {
        return expireSeconds;
    }

    @Override
    public String getPrefix() {
        String className = getClass().getName();
        return className + ":" + prefix;
    }
}
