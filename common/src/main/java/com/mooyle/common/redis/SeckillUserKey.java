package com.mooyle.common.redis;

public class SeckillUserKey extends RedisBasePrefix {

    public static final int TOKEN_EXPIRE = 3600 * 24;

    private SeckillUserKey(int expireSecond, String prefix) {
        super(expireSecond, prefix);
    }

    public static SeckillUserKey token = new SeckillUserKey(TOKEN_EXPIRE, "tk");

}
