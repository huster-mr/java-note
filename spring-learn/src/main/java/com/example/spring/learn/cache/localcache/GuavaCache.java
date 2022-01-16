package com.example.spring.learn.cache.localcache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.util.Pair;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class GuavaCache {
    private static LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder()
            .maximumSize(10)
            .expireAfterWrite(2, TimeUnit.SECONDS)
            .refreshAfterWrite(1, TimeUnit.SECONDS)
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String key) throws Exception {
                    System.out.println("重新加载");
                    return "value_" + key;
                }
            });

    private static Cache<String, String> cache = CacheBuilder.newBuilder()
            // 初始容量
            .initialCapacity(5)
            // 最大容量
            .maximumSize(10)
            // 设置写缓存后的过期时间
            .expireAfterWrite(2, TimeUnit.SECONDS)
            // 设置写缓存的过期时间，很少使用
//            .expireAfterAccess(10, TimeUnit.SECONDS)
            .build();

    private static void cacheTest() throws Exception {
        Map<String, String> keyValueList = IntStream.range(0,10)
                .mapToObj((i) -> new Pair("key_" + i, "value_" + i))
                .collect(Collectors.toMap(x-> x.getKey().toString(), x->x.getValue().toString()));
        keyValueList.forEach((x,y) -> {
            System.out.println("cache put " + x);
            cache.put(x, y);
        });

        // 测试，超出容量后，淘汰的是哪个key
        cache.put("key_11", "value_11");
        List<String> keyList = keyValueList.keySet().stream().collect(Collectors.toList());
        keyList.add("key_11");
        keyList.forEach(k -> System.out.println(cache.getIfPresent(k)));

        Thread.sleep(3000);
        System.out.println("sleep 3秒");
        // 测试，所有key超时过期
        keyList.forEach(k -> System.out.println(cache.getIfPresent(k)));


        cache.put("key_12", "value_12");
        System.out.println(cache.getIfPresent("key_12"));
        // 测试，删除key
        cache.invalidate("key_12");
        System.out.println(cache.getIfPresent("key_12"));

        String key = "key_12";
        // 测试，如果key不存在，调用callable方法获取value并加载到cache中
        String value = cache.get(key, new Callable<String>() {
            @Override
            public String call() throws Exception {
                return getValueFromDB(key);
            }
        });
        System.out.println(value);

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
//        cacheTest();

        loadingCacheTest();
    }



    private static String getValueFromDB(String key) {
        return "value_from_DB_" + key;
    }

}
