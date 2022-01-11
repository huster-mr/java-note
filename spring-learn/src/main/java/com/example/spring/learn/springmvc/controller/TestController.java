package com.example.spring.learn.springmvc.controller;

import com.example.spring.learn.mq.eventbus.EventBusPublish;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private EventBusPublish eventBusPublish;

    @GetMapping("hi")
    public String hi() {
        return "hi!你好啦!";
    }

    @GetMapping("eventBus")
    public String eventBus(String message) {
        eventBusPublish.publishMessage(message);
        return "ok";
    }
}
