package com.mooyle.seckill.common.redis;

public interface RedisKeyPrefix {

    public int expireSeconds();

    public String getPrefix();


}
