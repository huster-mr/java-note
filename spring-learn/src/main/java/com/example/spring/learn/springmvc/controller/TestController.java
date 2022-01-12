package com.example.spring.learn.springmvc.controller;

import com.alibaba.fastjson.JSON;
import com.example.spring.learn.http.HttpInvokeClient;
import com.example.spring.learn.mq.eventbus.EventBusPublish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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

    @GetMapping("http/get")
    public String getHttpTest(String url, Integer timeoutMs) {
        return HttpInvokeClient.get(url, timeoutMs);
    }

    @GetMapping("http/post")
    public String postHttpTest(String url, Integer timeoutMs, String type, String jsonParam) {
        if (type.equals("json")) {
            return HttpInvokeClient.postWithJsonParam(url, jsonParam, timeoutMs);
        } else {
            Map<String, String> map = JSON.parseObject(jsonParam, Map.class);
            return HttpInvokeClient.post(url, map, timeoutMs);
        }
    }
}
