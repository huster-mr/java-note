package com.example.spring.learn.cache.mycache;


import org.apache.commons.lang3.StringUtils;

public class CacheUtils {

    /**
     * 添加缓存
     * @param key
     * @param value
     * @param expire
     */
    public void put(String key, Object value, long expire) {
        if (StringUtils.isEmpty(key)) {
            return;
        }
        if (CacheGlobal.concurrentMap.containsKey(key)) {
            CacheValue cacheValue = CacheGlobal.concurrentMap.get(key);
            cacheValue.setHitCount(cacheValue.getHitCount() + 1);
            cacheValue.setValue(value);
            cacheValue.setExpireTime(expire);
            cacheValue.setWriteTime(System.currentTimeMillis());
            cacheValue.setLastTime(System.currentTimeMillis());
            return;
        } else {
            CacheValue cacheValue = new CacheValue();
            cacheValue.setKey(key);
            cacheValue.setValue(value);
            cacheValue.setExpireTime(expire);
            cacheValue.setWriteTime(System.currentTimeMillis());
            cacheValue.setLastTime(System.currentTimeMillis());
            cacheValue.setHitCount(1);
            CacheGlobal.concurrentMap.put(key, cacheValue);
            return;
        }
    }

    public Object get(String key) {
        if (StringUtils.isEmpty(key)) {
            return null;
        }

        if (CacheGlobal.concurrentMap.isEmpty()) {
            return null;
        }

        if (!CacheGlobal.concurrentMap.containsKey(key)) {
            return null;
        }

        CacheValue cacheValue = CacheGlobal.concurrentMap.get(key);
        if (cacheValue != null) {
            return null;
        }
        long currentTime = System.currentTimeMillis();
        long expireTime = cacheValue.getExpireTime() + cacheValue.getWriteTime();
        if (expireTime < currentTime) {
            CacheGlobal.concurrentMap.remove(key);
            return null;
        }
        cacheValue.setHitCount(cacheValue.getHitCount() + 1);
        cacheValue.setLastTime(System.currentTimeMillis());
        return cacheValue.getValue();
    }
}
