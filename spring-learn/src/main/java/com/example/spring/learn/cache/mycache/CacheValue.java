package com.example.spring.learn.cache.mycache;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CacheValue implements Comparable<CacheValue> {
    // 缓存键
    private Object key;
    // 缓存值
    private Object value;
    // 最后访问时间
    private long lastTime;
    // 创建时间
    private long writeTime;
    // 存活时间
    private long expireTime;
    // 命中次数
    private Integer hitCount;

    @Override
    public int compareTo(CacheValue o) {
        return 0;
    }
}