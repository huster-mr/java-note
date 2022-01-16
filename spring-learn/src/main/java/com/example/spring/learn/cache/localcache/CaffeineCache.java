package com.example.spring.learn.cache.localcache;


import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

public class CaffeineCache {
    private static final Cache<String, String> cache = Caffeine.newBuilder()
            .initialCapacity(5)
            .maximumSize(10)
            .expireAfterWrite(2, TimeUnit.SECONDS)
            .build();

    private static final LoadingCache<String, String> loadingCache = Caffeine.newBuilder()
            .initialCapacity(5)
            .maximumSize(10)
            .expireAfterWrite(2, TimeUnit.SECONDS)
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String s) throws Exception {
                    System.out.println("重新加载");
                    return "value_" + s;
                }
            });


    private static void cacheTest() throws Exception {
        String key = "key";
        String value = "value";
        cache.put(key, value);
        System.out.println(cache.getIfPresent(key));

        cache.invalidate(key);
        System.out.println(cache.getIfPresent(key));

        System.out.println(cache.get(key, CaffeineCache::getValueFromDB));

        Thread.sleep(3000);
        System.out.println(cache.getIfPresent(key));
    }

    private static void loadingCacheTest() throws Exception {
        // 测试，第一次，需加载
        System.out.println(loadingCache.get("hi"));
        // 测试，第二次，无需加载
        System.out.println(loadingCache.get("hi"));
        Thread.sleep(3000);

        // 测试，key过期，需要加载
        System.out.println(loadingCache.get("hi"));
    }

    public static void main(String[] args) throws Exception {
       cacheTest();

       loadingCacheTest();
    }

    private static String getValueFromDB(String key) {
        return "Value_from_DB_" + key;
    }
}
