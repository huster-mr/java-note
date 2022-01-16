package com.example.spring.learn.cache.mycache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class CacheGlobal {
    public static ConcurrentMap<String, CacheValue> concurrentMap = new ConcurrentHashMap<>();
}
