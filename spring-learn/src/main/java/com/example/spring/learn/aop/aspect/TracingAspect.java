package com.example.spring.learn.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author
 * @Description
 * @Date 2021/12/12
 */
@Aspect
@Component
public class TracingAspect {
    private static final Logger logger = LoggerFactory.getLogger(TracingAspect.class);

    @Pointcut("@annotation(com.example.spring.learn.aop.aspect.AspectTest)")
    void annotationPoint() {}

    @Before("annotationPoint()")
    public void doBefore(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        String declaringTypeName = signature.getDeclaringTypeName();
        String funcName = signature.getName();
        logger.info("注解AspectTest开始执行了，即将执行的方法为：{}, 属于{}包", funcName, declaringTypeName);
    }


    @Pointcut("execution(* com.example.spring.learn.springmvc.controller.AopTestController.executionAspect(..))")
    void executionPoint() {}

    @Before("executionPoint()")
    public void before() {
        logger.info("TracingAspect executionPoint before ...");
    }

    @After("executionPoint()")
    public void after() {
        logger.info("TracingAspect executionPoint after ...");
    }

    @AfterReturning("executionPoint()")
    public void afterReturning() {
        logger.info("TracingAspect executionPoint afterReturning ...");
    }

    @Around("executionPoint()")
    public Object around(ProceedingJoinPoint joinPoint) {
        try {
            // 请求的 类、方法名
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();

            Object[] args = joinPoint.getArgs();
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            Method method = methodSignature.getMethod();
            Annotation[] annotations = method.getAnnotations();
            logger.info("TracingAspect executionPoint around before ...");
            Object obj = joinPoint.proceed();
            logger.info("TracingAspect executionPoint around, className:{}, methodName:{}, annotations:{}, args:{},",
                    className, methodName, Stream.of(annotations).map(Annotation::toString).collect(Collectors.joining(" ")), Stream.of(args).map(Object::toString).collect(Collectors.joining(" ")));
            logger.info("TracingAspect executionPoint around after ...");
            return obj;
        } catch (Throwable e) {
            logger.error("TracingAspect around error", e);
            return null;
        }
    }

}
