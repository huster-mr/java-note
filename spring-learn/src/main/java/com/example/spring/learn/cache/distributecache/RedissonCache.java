package com.example.spring.learn.cache.distributecache;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

/**
 * 业务中使用redisson
 */
public class RedissonCache {
    private static final Logger logger = LoggerFactory.getLogger(RedissonCache.class);

    @Autowired
    private RedissonClient redissonClient;

    public void fun() {
        String key = "biz_key";
        // 按key名称返回锁实例，非公平的可重入锁
        RLock lock = redissonClient.getLock(key);
        boolean tryLock = false;
        try{
            // 获取锁
            tryLock = lock.tryLock(30, 180, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            logger.error("lock tryLock error", e);
        }
        if (tryLock) {
            try {
                // 业务逻辑
            } catch (Exception e){
            } finally {
                // 最后需要释放锁
                lock.unlock();
            }
        }
    }
}
