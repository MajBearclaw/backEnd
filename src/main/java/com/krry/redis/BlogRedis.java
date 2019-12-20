package com.krry.redis;

import com.krry.entity.Blog;
import org.springframework.stereotype.Repository;

@Repository
public class BlogRedis extends BaseRedis<Blog> {
    private static final String REDIS_KEY = "com.krry.redis.BlogRedis";
    @Override
    protected String getRedisKey(){
        return  REDIS_KEY;
    }
}
