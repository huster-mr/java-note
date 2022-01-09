package com.example.spring.learn.coreinterface;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author
 * @Description
 * @Date 2021/12/19
 */
@Component
public class MyInitializingBean implements InitializingBean, ApplicationContextAware {
    private ApplicationContext applicationContext;

    private static final Map<String, Filter> filterMap = new ConcurrentHashMap<>(16);

    @Autowired
    private MyApplicationContextAware myApplicationContextAware;

    public MyInitializingBean() {
        System.out.println("MyInitializingBean 构造方法！");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("MyInitializingBean 设置 ApplicationContext");
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("MyInitializingBean afterPropertiesSet");
        this.applicationContext.getBeansOfType(Filter.class)
                .values()
                .forEach(bean -> filterMap.put(bean.getClass().getSimpleName(), bean));
    }
}
