package com.example.spring.learn.mq.eventbus;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import jdk.nashorn.internal.objects.annotations.Constructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.concurrent.*;

@Component
public class CommonAsyncEventBus implements BeanPostProcessor {
    private static final Logger logger = LoggerFactory.getLogger(CommonAsyncEventBus.class);

    private EventBus eventBus;
    private ExecutorService executorService;

    @PostConstruct
    public void init(){
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("CommonAsyncEventBus-pool-%d").build();
        executorService = new ThreadPoolExecutor(10, 20, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), threadFactory);
        eventBus = new AsyncEventBus("", executorService);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        try {
            Method[] methods = bean.getClass().getDeclaredMethods();
            for (Method method : methods) {
                Subscribe annotation = AnnotationUtils.findAnnotation(method, Subscribe.class);
                if (annotation != null) {
                    eventBus.register(bean);
                    logger.info("eventBus register success, beanName:{}", beanName);
                    return bean;
                }
            }
        }catch (Exception e) {
            logger.error("eventBus register error", e);
        }
        return bean;
    }

    public void post(Object event) {
        eventBus.post(event);
    }
}
