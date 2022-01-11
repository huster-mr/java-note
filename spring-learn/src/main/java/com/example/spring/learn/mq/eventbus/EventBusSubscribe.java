package com.example.spring.learn.mq.eventbus;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EventBusSubscribe {
    private static final Logger logger = LoggerFactory.getLogger(EventBusSubscribe.class);

    @Subscribe
    public void processEventMessage(EventBusMessage message) {
        logger.info("processEventMessage EventBusMessage:{}", message);
    }
}
