package com.example.spring.learn.mq.kafka;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.util.List;
import java.util.Map;

public class KafkaMsgConsumeHandlerFactory implements BeanPostProcessor {
    private static final Logger logger = LoggerFactory.getLogger(KafkaMsgConsumeHandlerFactory.class);

    private static Map<String,KafkaMsgConsumeHandler> topicHandlerMap = Maps.newHashMap();

    public KafkaMsgConsumeHandler getHandler(String topic) {
        return topicHandlerMap.get(topic);
    }

    private void register(KafkaMsgConsumeHandler handler) {
        List<String> topicList = handler.getTopic();
        topicList.forEach(topic -> topicHandlerMap.put(topic, handler));
        logger.info("KafkaMsgConsumeHandlerFactory register, topic:{}, handler:{}", topicList, handler.getClass().getName());
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        try {
            Class[] interfaces = bean.getClass().getInterfaces();
            for (Class clazz : interfaces) {
                if (KafkaMsgConsumeHandler.class.equals(clazz)) {
                    register((KafkaMsgConsumeHandler) bean);
                    return bean;
                }
            }
        } catch (Exception e) {
            logger.error("postProcessAfterInitialization error", e);
        }
        return bean;
    }
}
