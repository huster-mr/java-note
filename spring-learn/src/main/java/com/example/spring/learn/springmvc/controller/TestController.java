package com.example.spring.learn.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author
 * @Description
 * @Date 2021/12/12
 */
@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("hi")
    public String hi() {
        return "hi!你好啦!";
    }
}
