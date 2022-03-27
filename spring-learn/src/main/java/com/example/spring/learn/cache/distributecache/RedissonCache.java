package com.example.spring.learn.cache.distributecache;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class RedissionCache {

    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer().getAddress()
    }

    public void action() {
        String key = "biz_key";
        RLock lock = redissonClient.getlog
    }
}
