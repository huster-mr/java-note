package com.example.spring.learn.aop.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author
 * @Description
 * @Date 2021/12/13
 */
public class TracingInterceptor implements MethodInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(TracingInterceptor.class);

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Method method = methodInvocation.getMethod();
        String simpleName = methodInvocation.getMethod().getDeclaringClass().getSimpleName();
        String methodName = method.getName();
        Annotation[] annotations = method.getAnnotations();

        logger.info("TracingInterceptor simpleName:{}, methodName:{}, annotationName:{}", simpleName, methodName, Stream.of(annotations).map(Annotation::toString).collect(Collectors.joining(" ")));


        HfiTrace annotation = method.getAnnotation(HfiTrace.class);
        if (annotation != null) {
            logger.info("HfiTrace 注解的方法");
        }
        Object res = methodInvocation.proceed();
        logger.info("TracingInterceptor methodName:{}, result:{}", methodName, res);
        return res;
    }
}