package com.example.spring.learn.cache.distributecache;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "redisson")
public class RedissonProperties {
    /**
     * 服务器地址
     */
    private String address;

    /**
     * 密码
     */
    private String password;

    /**
     * 数据库索引
     */
    private int database = 0;

    /**
     * 最小空闲连接量
     */
    private int connectionMinimumIdleSize = 0;

    /**
     * 最大连接池
     */
    private int connectionPoolSize = 64;

    /**
     * redis服务响应超时时间
     */
    private int timeout = 3000;

    /**
     * 连接到redis服务器超时时间
     */
    private int connectTimeout = 10000;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    public int getConnectionMinimumIdleSize() {
        return connectionMinimumIdleSize;
    }

    public void setConnectionMinimumIdleSize(int connectionMinimumIdleSize) {
        this.connectionMinimumIdleSize = connectionMinimumIdleSize;
    }

    public int getConnectionPoolSize() {
        return connectionPoolSize;
    }

    public void setConnectionPoolSize(int connectionPoolSize) {
        this.connectionPoolSize = connectionPoolSize;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }
}
