package com.example.spring.learn.mq.kafka;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.SmartLifecycle;

import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class KafkaMsgConsumerClient implements SmartLifecycle {
    private static final Logger logger = LoggerFactory.getLogger(KafkaMsgConsumerClient.class);

    private KafkaConsumer<String, String> consumer;

    // group_id
    private static final String KAFKA_CONSUMER_GROUP_ID = "kafka-group-id";

    @Value("${kafka.ip.port}")
    private String kafkaIpPort;

    @Autowired
    private KafkaMsgConsumeHandlerFactory kafkaMsgConsumeHandlerFactory;

    private static ThreadFactory pullThreadFactory = new ThreadFactoryBuilder().setNameFormat("kafka_consumer_pull_thread_%d").build();
    private static ExecutorService pullThreadPool = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), pullThreadFactory);

    private static ThreadFactory handleThreadFactory = new ThreadFactoryBuilder().setNameFormat("kafka_consumer_handle_thread_%d").build();
    private static ExecutorService handleThreadPool = new ThreadPoolExecutor(10, 10, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), handleThreadFactory);

    public void init() {
        // 1.配置参数
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaIpPort);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, KAFKA_CONSUMER_GROUP_ID);
        // 2.创建消费者
        consumer = new KafkaConsumer<>(props);
        // 3.订阅主题topic
        List<String> consumerTopicList = Lists.newArrayList();
        // todo,添加需要订阅的topic
        // consumerTopicList.addAll();
        consumer.subscribe(consumerTopicList);
    }

    public void consumerMessage() {
        while (true) {
            try {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                handleConsumerRecords(records);
                consumer.commitAsync();
            } catch (Exception e) {
                logger.error("kafka consumerMessage error", e);
            }
        }
    }

    private void handleConsumerRecords(ConsumerRecords<String, String> records) {
        CountDownLatch countDownLatch = new CountDownLatch(records.count());
        for (ConsumerRecord<String, String> record : records) {
            submitConsumerTask(record, countDownLatch);
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            logger.error("handleConsumerRecords countDownLatch await error!", e);
        }
    }

    private void submitConsumerTask(ConsumerRecord<String, String> record, CountDownLatch countDownLatch) {
        handleThreadPool.execute(() -> {
            try {
                KafkaMsgConsumeHandler handler = kafkaMsgConsumeHandlerFactory.getHandler(record.topic());
                if (handler != null) {
                    handler.handle(record);
                } else {
                    logger.info("KafkaMsgConsumeHandler not found, topic:{}", record.topic());
                }
            } catch (Exception e) {
                logger.error("handle message error, topic: " + record.topic() + " key: " + record.key() + " value: " + record.value(), e);
            } finally {
                countDownLatch.countDown();
            }
        });
    }

    private static AtomicBoolean started = new AtomicBoolean(false);

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void start() {
        boolean start = started.compareAndSet(false, true);
        logger.info("kafka start result:{}", start);
        if (start) {
            try {
                init();
            } catch (Exception e) {
                throw new Error("start kafka consume error", e);
            }
        }
        if (start && consumer != null) {
            logger.info("kafka consumer start!");
            pullThreadPool.execute(() -> consumerMessage());
        }
    }

    @Override
    public void stop() {
        logger.info("kafka consumer stopping!");
        if (started.compareAndSet(true, false)) {
            if (consumer != null) {
                consumer.close();
            }
            pullThreadPool.shutdownNow();
        }
    }

    @Override
    public void stop(Runnable callback) {
        try {
            stop();
        } finally {
            callback.run();
        }
    }

    @Override
    public boolean isRunning() {
        return started.get();
    }

    @Override
    public int getPhase() {
        logger.info("kafka consumer getPhase");
        return 0;
    }
}
