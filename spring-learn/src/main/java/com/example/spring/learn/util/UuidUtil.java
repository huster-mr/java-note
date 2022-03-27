package com.example.spring.learn.util;

import org.apache.kafka.common.Uuid;

public class UuidUtil {
    public static String generateUuid() {
        String uuid = Uuid.randomUuid().toString().replaceAll("-", "");
        return uuid;
    }
}
