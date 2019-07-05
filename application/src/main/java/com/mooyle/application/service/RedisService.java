package com.mooyle.application.service;

import com.alibaba.fastjson.JSON;
import com.mooyle.common.redis.RedisKeyPrefix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class RedisService {

    @Autowired
    private JedisPool jedisPool;


    public <T> boolean set(RedisKeyPrefix prefix, String key, T value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = beanToString(value);
            if (str == null || str.length() <= 0) {
                return false;
            }
            String realKey = prefix.getPrefix() + key;
            int second = prefix.expireSeconds();
            if (second <= 0) {
                jedis.set(realKey, str);
            } else {
                jedis.setex(realKey, second, str);
            }

            return true;
        } finally {
            returnToPool(jedis);
        }

    }

    public <T> T get(RedisKeyPrefix prefix, String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            String str = jedis.get(realKey);
            return stringToBean(str, clazz);
        } finally {
            returnToPool(jedis);
        }

    }

    public Long incr(RedisKeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            return jedis.incr(realKey);
        } finally {
            returnToPool(jedis);
        }

    }

    public Long decr(RedisKeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            return jedis.decr(realKey);
        } finally {
            returnToPool(jedis);
        }

    }

    public boolean exists(RedisKeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            return jedis.exists(realKey);
        } finally {
            returnToPool(jedis);
        }
    }

    private <T> String beanToString(T value) {
        if (value == null) {
            return null;
        }
        return JSON.toJSONString(value);
    }

    private <T> T stringToBean(String str, Class<T> clazz) {
        if (str == null || str.length() <= 0 || clazz == null) {
            return null;
        }
        return JSON.toJavaObject(JSON.parseObject(str), clazz);
    }

    private void returnToPool(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

}
