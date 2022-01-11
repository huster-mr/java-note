package com.example.spring.learn.mq.eventbus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventBusPublish {
    @Autowired
    private CommonAsyncEventBus commonAsyncEventBus;

    public void publishMessage(String message) {
        EventBusMessage eventBusMessage = new EventBusMessage();
        eventBusMessage.setContent(message);
        commonAsyncEventBus.post(eventBusMessage);
    }
}
