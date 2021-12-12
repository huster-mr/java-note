package com.example.spring.learn.springmvc.controller;

import com.example.spring.learn.aop.aspect.AspectTest;
import com.example.spring.learn.aop.interceptor.HfiTrace;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author
 * @Description
 * @Date 2021/12/12
 */
@RestController
@RequestMapping("/aop/test")
public class AopTestController {

    @GetMapping("annotation/aspect")
    @AspectTest
    public String annotationAspect() {
        return "annotationAspect";
    }

    @GetMapping("execution/aspect")
    public String executionAspect(String str) {
        return "executionAspect " + str;
    }

    @GetMapping("interceptor")
    @HfiTrace
    public String interceptorTest(String str) {
        return "interceptor " + str;
    }
}
