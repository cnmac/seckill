package com.mooyle.common.redis;

public class UserKey extends RedisBasePrefix {

    public UserKey(String prefix) {
        super(prefix);
    }

    public UserKey(int expire, String prefix) {
        super(expire, prefix);
    }

    public static UserKey getPrefixById = new UserKey("id");
    public static UserKey getPrefixByName = new UserKey("nm");


}
