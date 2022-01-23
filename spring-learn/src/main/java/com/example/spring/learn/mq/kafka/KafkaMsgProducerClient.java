package com.example.spring.learn.mq.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Properties;

public class KafkaMsgProducerClient {
    private static final Logger logger = LoggerFactory.getLogger(KafkaMsgProducerClient.class);

    @Value("${kafka.ip.port}")
    private String kafkaIpPort;

    private Producer<String, String> producer;

    @PostConstruct
    public void init() {
        // 1.配置一系列参数
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaIpPort);
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.RETRIES_CONFIG, 1);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        // 2.创建生产者
        producer = new KafkaProducer<>(props);
    }

    public boolean sendMsg(String topic, String key, String value) {
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);
        try {
            // todo,是不是应该添加重试机制；
            producer.send(record);
            return true;
        } catch (Exception e) {
            logger.error("kafka sendMsg error, topic: " + topic + " key: " + key + " value: " + value, e);
            return false;
        }
    }

    @PreDestroy
    public void destroy() {
        producer.close();
    }
}
