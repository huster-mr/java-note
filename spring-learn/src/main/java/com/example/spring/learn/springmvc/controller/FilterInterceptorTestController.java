package com.example.spring.learn.springmvc.controller;

import com.example.spring.learn.annotation.LoginRequired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author
 * @Description
 * @Date 2021/12/13
 */
@RestController
@RequestMapping("filter/interceptor/test")
public class FilterInterceptorTestController {

    @GetMapping("/cors")
    public String corsFilterTest() {
        return "filterTest";
    }

    @GetMapping("/loginRequired")
    @LoginRequired
    public String interceptorTest() {
        return "interceptorTest";
    }
}
