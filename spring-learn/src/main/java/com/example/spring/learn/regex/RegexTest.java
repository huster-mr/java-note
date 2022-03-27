package com.example.spring.learn.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {
    // 基础用法
    private static void func1() {
        Pattern pattern = Pattern.compile("^([-+]?\\d+(\\.\\d+)?)$");
        String content = "3.14";
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            System.out.println(content + "是数值");
        } else {
            System.out.println(content + "不是数值");
        }
    }

    public static void func2() {
        Pattern pattern = Pattern.compile("([\\d]{3})([\\w]{2})");
        String content = "123ab456cd";
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            System.out.println(matcher.group());
            System.out.println(matcher.group(0));
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
            System.out.println(matcher.start());
            System.out.println(matcher.end());
        }
    }

    public static void func3() {
        Pattern pattern = Pattern.compile("([\\d]{3})([\\w]{2})");
        String content = "123ab";
        Matcher matcher = pattern.matcher(content);
        if (matcher.matches()) {
            System.out.println(matcher.group(0));
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
            System.out.println(matcher.start());
            System.out.println(matcher.end());
        } else {
            System.out.println("can not matches");
        }
    }

//    // 超时情况
//    private static void func() {
//        String content = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
//        String regex = "(x+x+)+y";
//        Pattern pattern = Pattern.compile(regex);
////        Matcher matcher = pattern.matcher(content);
//        // 设置10s超时
//        Matcher matcher = RegularExpressionUtils.createMatcherWithTimeout(content, pattern, 10000);
//        System.out.println("start!");
//        try {
//            if (matcher.find()) {
//                System.out.println(matcher.group());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("end!");
//    }
//
//    private static void func4() {
//
//    }

    public static void main(String[] args) {
//        func1();

        func2();

//        func3();


    }
}
