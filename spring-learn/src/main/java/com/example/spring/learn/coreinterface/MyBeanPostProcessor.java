package com.example.spring.learn.coreinterface;

import com.example.spring.learn.springmvc.controller.TestController;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @Author
 * @Description 实现BeanPostProcessor后，每个bean实例化都会调用对应的两个方法，可以达到获取的bean的信息或者修改bean的目的
 * @Date 2021/12/19
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().equals(TestController.class)) {
            System.out.println("初始化前，postProcessAfterInitialization.... beanName:" + beanName + " className: " + bean.getClass().getName());
        }
        return bean;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().equals(TestController.class)) {
            System.out.println("初始化后，postProcessBeforeInitialization.... beanName:" + beanName + " className: " + bean.getClass().getName());
        }
        return bean;
    }
}
