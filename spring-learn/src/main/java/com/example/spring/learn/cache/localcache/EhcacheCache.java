package com.example.spring.learn.cache.localcache;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

public class EhcacheCache {
    private static final CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
            // 声明一个容量为20的堆内缓存
            .withCache("ehcacheInstance", CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, String.class, ResourcePoolsBuilder.heap(20)))
            .build(true);

    public static void main(String[] args) {
        // 获取Cache实例
        Cache<String, String> myCache = cacheManager.getCache("ehcacheInstance", String.class, String.class);

        // 读写缓存
        myCache.put("key", "value");
        System.out.println(myCache.get("key"));

        // 移除
        cacheManager.removeCache("myCache");
        cacheManager.close();
    }
}


