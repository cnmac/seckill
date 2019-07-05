package com.mooyle.common.redis;

public interface RedisKeyPrefix {

    int expireSeconds();

    String getPrefix();

}
