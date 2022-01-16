package com.example.spring.learn.cache.mycache;

import java.util.concurrent.TimeUnit;

public class ExpireThread implements Runnable {
    @Override
    public void run() {
        while(true) {
            try {
                //  每10秒检测一次过期key
                TimeUnit.SECONDS.sleep(10);
                expireCache();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void expireCache() {
        System.out.println("检测缓存是否过期缓存");
        for (String key : CacheGlobal.concurrentMap.keySet()) {
            CacheValue cacheValue = CacheGlobal.concurrentMap.get(key);
            long currentTime = System.currentTimeMillis();
            long expireTime = cacheValue.getExpireTime() + cacheValue.getWriteTime();
            if (expireTime > currentTime) {
                continue;
            }
            // 清除过期key
            CacheGlobal.concurrentMap.remove(key);
        }
    }
}
