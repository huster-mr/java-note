package com.example.spring.learn.qlexpress;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Date;

public class User {
    private static final Logger logger = LoggerFactory.getLogger(User.class);
    private String name;
    private Integer age;
    private Date date;
    private String desc;

    public String getStr(String key) {
        return get(key);
    }

    public Double getDouble(String key) {
        String obj = get(key);
        return Double.parseDouble(obj);
    }

    public String get(String key) {
        String[] keys = key.split("\\.");
        return parse(keys, 0, desc);
    }

    public String parse(String[] key, int index, String content) {
        try {
            if (StringUtils.isBlank(content)) {
                return "";
            }
            JSONObject jsonObject = JSON.parseObject(content);
            String value = StringUtils.defaultIfBlank(jsonObject.getString(key[index]), "");
            if (key.length - 1 == index) {
                return value;
            }
            return parse(key, index + 1, value);
        } catch (Exception e) {
            logger.error("parse error, key:" + Arrays.asList(key) + ", index:" + index + ",content:" + content, e);
            return "";
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getMessage() {
        return name + "==" + age + "==" + date;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", date=" + date +
                '}';
    }
}
