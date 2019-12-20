package com.krry.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

public abstract class BaseRedis<T> {
    @Autowired
    protected RedisTemplate<String,Object> redisTemplate;

    @Resource
    protected HashOperations<String, String, T> hashOperations;

    @Resource
    protected ValueOperations<String, Object> valueOperations;

    protected abstract String getRedisKey();

    public void putString(String key,Object domain,long expire){
        valueOperations.set(key,domain);
        if(expire != -1){
            redisTemplate.expire(getRedisKey(),expire, TimeUnit.SECONDS);
        }
    }

    public Object getString(String key){
        return valueOperations.get(key);
    }
    public void put(String key,T domain,long expire){
        hashOperations.put(getRedisKey(),key,domain);
        if(expire != -1){
            redisTemplate.expire(getRedisKey(),expire, TimeUnit.SECONDS);
        }
    }

}
