package com.example.spring.learn.mq.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.List;

public interface KafkaMsgConsumeHandler {
    /**
     * 返回消息消费者的topic,允许多个topic对应一个Handler
     * @return
     */
    List<String> getTopic();

    /**
     * 消费消息
     * @param record
     * @return
     */
    Boolean handle(ConsumerRecord<String, String> record);
}
