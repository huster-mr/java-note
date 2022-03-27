package com.example.spring.learn.cache.distributecache;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(RedissonProperties.class)
public class RedissonConfig {

    private RedissonProperties redissonProperties;

    public RedissonConfig(RedissonProperties redissonProperties) {
        this.redissonProperties = redissonProperties;
    }

    @Bean
    @ConditionalOnProperty(prefix = "redisson", name = "type", havingValue = "stand-alone")
    public RedissonClient redissonClient() {
        /**
         * SingleServerConfig 单机部署配置
         * SentinelServersConfig 哨兵模式配置
         * ClusterServersConfig 集群部署配置
         * MasterSlaveServersConfig 主从部署配置
         */
        Config config = new Config();
        config.useSingleServer()
                .setAddress(redissonProperties.getAddress())
                .setDatabase(redissonProperties.getDatabase())
                .setPassword(redissonProperties.getPassword())
                .setConnectionPoolSize(redissonProperties.getConnectionPoolSize())
                .setConnectionMinimumIdleSize(redissonProperties.getConnectionMinimumIdleSize())
                .setTimeout(redissonProperties.getTimeout())
                .setConnectTimeout(redissonProperties.getConnectTimeout());
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }
}
