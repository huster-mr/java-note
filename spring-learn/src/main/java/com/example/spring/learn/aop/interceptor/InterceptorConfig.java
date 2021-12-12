package com.example.spring.learn.aop.interceptor;

import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author
 * @Description
 * @Date 2021/12/13
 */
@Configuration
public class InterceptorConfig {
    private static final String TRACE_EXECUTION = "execution(* com.example.spring.learn.springmvc.controller.AopTestController.interceptorTest(..))";
    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisor1() {
        TracingInterceptor tracingInterceptor = new TracingInterceptor();

        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(TRACE_EXECUTION);

        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(pointcut);
        advisor.setAdvice(tracingInterceptor);
        return advisor;
    }
}
