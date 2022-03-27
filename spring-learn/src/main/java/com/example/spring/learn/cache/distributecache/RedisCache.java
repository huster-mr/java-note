package com.example.spring.learn.cache.distributecache;

import com.example.spring.learn.util.UuidUtil;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class RedisCache {
    private static final Logger logger = LoggerFactory.getLogger(RedisCache.class);
    private Jedis jedis;
    public static final int MAX_RETRY_TIMES = 100;

    // 版本一：
    // 请求锁
//    public boolean tryLock(String key, String requestId) {
//        Long result = jedis.setnx(key, requestId);
//        if (result == 1) {
//            return true;
//        }
//        return false;
//    }
//    // 释放锁
//    public void releaseLock(String key) {
//        jedis.del(key);
//    }
//    //业务逻辑中使用锁
//    public void doSomething() {
//        String lockKey=null,value=null;
//        try {
//            // 请求锁
//            if (tryLock(lockKey, value)) {
//                // 业务逻辑
//            }
//        } catch (Exception e) {
//        } finally {
//            releaseLock(lockKey);
//        }
//    }

    // 版本二
    // 请求锁
//    public boolean tryLock(String key, String requestId, int expireTime) {
//        long result = jedis.setnx(key, requestId);
//        if (result == 1) {
//            // 设置过期时间
//            jedis.expire(key, expireTime);
//            return true;
//        }
//        return false;
//    }
//    // 删除锁
//    public void releaseLock(String key) {
//        jedis.del(key);
//    }
//    // 业务逻辑中使用锁
//    public void doSomethings(String ... args){
//        String lockKey=null,value=null;
//        try {
//            if (tryLock(lockKey, value, 3)) {
//                // 业务逻辑
//            }
//        } catch (Exception e){
//
//        } finally {
//            releaseLock(lockKey);
//        }
//    }

    // 版本三
//    // 请求锁
//    public boolean tryLock(String key, String requestId, int expireTime){
//        SetParams setParams = new SetParams();
//        ;
//        String result = jedis.set(key, requestId, setParams.nx().ex(expireTime));
//        if ("OK".equals(result)) {
//            return true;
//        }
//        return false;
//    }
//    // 删除锁
//    public void releaseLock(String key) {
//        jedis.del(key);
//    }
//    // 在业务中使用锁
//    public void doSomethings(String ... args){
//        String lockKey=null,value=null;
//        try {
//            if (tryLock(lockKey, value, 3)) {
//                // 业务逻辑
//            }
//        } catch (Exception e){
//
//        } finally {
//            releaseLock(lockKey);
//        }
//    }

//版本四
//    // 请求锁
//    public boolean tryLock(String key, String requestId, int expireTime){
//        SetParams setParams = new SetParams();
//        String result = jedis.set(key, requestId, setParams.nx().ex(expireTime));
//        if ("OK".equals(result)) {
//            return true;
//        }
//        return false;
//    }
//    // 删除锁
//    public void releaseLock(String key) {
//        jedis.del(key);
//    }
//    // 在业务中使用锁
//    public void doSomethings(String ... args){
//        String lockKey=null;
//        String uuId = UUID.randomUUID().toString();
//        try {
//            if (tryLock(lockKey, uuId, 3)) {
//                // 业务逻辑
//            }
//        } catch (Exception e){
//
//        } finally {
//            // 判断客户端标识与存储的值是否一致，一致才能删除
//            if (uuId.equals(jedis.get(lockKey))) {
//                releaseLock(lockKey);
//            }
//        }
//    }

    // 版本五
//    // 请求锁
//    public boolean tryLock(String key, String requestId, int expireTime){
//        SetParams setParams = new SetParams();
//        String result = jedis.set(key, requestId, setParams.nx().ex(expireTime));
//        if ("OK".equals(result)) {
//            return true;
//        }
//        return false;
//    }
//    // 删除锁
//    public boolean releaseLock(String key, String owner) {
//        // 判断客户端标识与存储的值是否一致，一致才能删除
//        String script = "if redis.call('get', KEYS[1] == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
//        Object res = jedis.eval(script, Lists.newArrayList(key), Lists.newArrayList(owner));
//        if ("OK".equals(res)) {
//            return true;
//        }
//        return false;
//    }
//    // 在业务中使用锁
//    public void doSomethings(String ... args){
//        String lockKey=null;
//        String uuId = UUID.randomUUID().toString();
//        try {
//            if (tryLock(lockKey, uuId, 3)) {
//                // 业务逻辑
//            }
//        } catch (Exception e){
//
//        } finally {
//            releaseLock(lockKey, uuId);
//        }
//    }


    // 请求锁
    public boolean tryLock(String key, String owner, int expireTime){
        try {
            String res = jedis.set(key, owner, SetParams.setParams().nx().ex(expireTime));
            if ("OK".equals(res)) {
                return true;
            }
        } catch (Exception e) {
            logger.error("tryLock error " + key, e);
            releaseLock(key, owner);
        }
        return false;
    }
    public boolean acquireLockWithRetry(String key, String owner, int expireTimeSeconds, int retryTime) {
        if (retryTime > MAX_RETRY_TIMES) {
            retryTime = MAX_RETRY_TIMES;
        }
        while (retryTime > 0) {
            boolean res = tryLock(key, owner, expireTimeSeconds);
            if (res) {
                return true;
            }
            retryTime--;
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (Exception e) {
                logger.error("acquireLockWithRetry error", e);
            }
        }
        return false;
    }
    // 删除锁
    public boolean releaseLock(String key, String owner) {
        // 判断客户端标识与存储的值是否一致，一致才能删除
        String script = "if redis.call('get', KEYS[1] == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object res = jedis.eval(script, Lists.newArrayList(key), Lists.newArrayList(owner));
        if ("OK".equals(res)) {
            return true;
        }
        return false;
    }
    // 在业务中使用锁
    public void doSomethings(String ... args){
        String lockKey=null;
        String uuId = UUID.randomUUID().toString();
        try {
            if (tryLock(lockKey, uuId, 3)) {
                // 业务逻辑
            }
        } catch (Exception e){

        } finally {
            releaseLock(lockKey, uuId);
        }
    }

    public <T> T executeInClusterLock(String lockKey, int expiredSeconds, int retryTime, Supplier<T> supplier) {
        String uuid = UuidUtil.generateUuid();
        try {
            boolean res = acquireLockWithRetry(lockKey, uuid, expiredSeconds, retryTime);
            if (!res) {
                logger.info("executeInClusterLock failed, key:", lockKey);
            }
            return supplier.get();
        } finally {
            releaseLock(lockKey, uuid);
        }

    }


}
