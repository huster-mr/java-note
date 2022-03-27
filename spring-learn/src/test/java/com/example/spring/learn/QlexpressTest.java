package com.example.spring.learn;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.spring.learn.qlexpress.Engine;
import com.example.spring.learn.qlexpress.User;
import com.google.common.collect.Lists;
import com.ql.util.express.DefaultContext;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
// 验证QLExpress原生支持的一些运算符和自定义运算符
public class QlexpressTest {

    @Autowired
    private Engine engine;

    @Test
    // 验证算术运算符 +, -, *, /, %, mod, ++, --
    public void test1() throws Exception {
        DefaultContext<String, Object> context = new DefaultContext<>();
        context.put("a", 1);
        context.put("b", 2);
        context.put("c", 3);
        context.put("d", 1);
        String express = "a + b * c / d";
        Object res = engine.getRunner().execute(express, context, null, true, false);
        // 输出7
        System.out.println(res);
    }

    @Test
    // 验证比较和逻辑运算 <, >, <=, >=, ==, !=, <> ; &&, ||, !
    public void test2() throws Exception {
        DefaultContext<String, Object> context = new DefaultContext<>();
        context.put("k1", "abc");
        context.put("k2", 5);
        String express = "k1 < \"abcd\" && k2==5";
        Object res = engine.getRunner().execute(express, context, null, true, false);
        System.out.println(res);
    }

    @Test
    // 验证类似sql的运算符：in, like
    public void test3() throws Exception {
        DefaultContext<String, Object> context = new DefaultContext<>();
        context.put("a", 2);
        // 括号包含多个元素的形式
        String express = "a in (3, 2, 4, 5)";
        Object res = engine.getRunner().execute(express, context, null, true, false);
        System.out.println(res);

        List<String> list = Lists.newArrayList("hi", "hello");
        context.put("b", "hello");
        context.put("l", list);
        // 直接传递List变量的形式
        String express1 = "b in l";
        Object res1 = engine.getRunner().execute(express1, context, null, true, false);
        System.out.println(res1);

        context.put("name", "abcdefg");
        // 跟MySQL中的like还是有细微差别的
        String express2 = "name like \"%cde\"";
        Object res2 = engine.getRunner().execute(express2, context, null, true, false);
        System.out.println(res2);
    }

    @Test
    // 验证运行一段代码
    public void test4() throws Exception {
        DefaultContext<String, Object> context = new DefaultContext<>();
        List<Integer> list = Lists.newArrayList(1,2,3,4);
        context.put("list", list);
        String express = "sum=0;for(i=0;i<list.size();i++){sum=sum+list.get(i);} return sum;";
        Object res = engine.getRunner().execute(express, context, null, true, false);
        System.out.println(res);
    }

    @Test
    // 验证对象操作
    public void test5() throws Exception {
        User user = new User();
        user.setName("jack");
        user.setAge(10);
        user.setDate(new Date());
        DefaultContext<String, Object> context = new DefaultContext<>();
        context.put("u", user);
        // 直接返回的user对象
        String express = "u";
        Object res = engine.getRunner().execute(express, context, null, true, false);
        System.out.println(res);

        // 可以直接调用User中的public方法，其中u.message会默认调用其getter方法,User类必须是public，不然会报错；
        String express1 = "u.getName() + \" ||| \" + u.message";
        Object res1 = engine.getRunner().execute(express1, context, null, true, false);
        System.out.println(res1);

        //系统自动会import java.lang.*,import java.util.*; 可以引入其他的包
        String express2 = "import org.apache.commons.lang3.time.DateUtils; return DateUtils.addYears(u.getDate(), 1);";
        Object res2 = engine.getRunner().execute(express2, context, null, true, false);
        System.out.println(res2);

        // 处理json格式的内容
        user.setDesc("\n" +
                "{\n" +
                "\t\"name\":\"jack\",\n" +
                "\t\"age\":20,\n" +
                "\t\"education\":{\n" +
                "\t\t\"university\":\"huster\",\n" +
                "\t\t\"high_school\":\"shizhu\"\n" +
                "\t}\n" +
                "}");
        context.put("json", user);
        String express3 = "json.getStr(\"education.university\") + \" ||| \" + json.getDouble(\"age\")";
        Object res3 = engine.getRunner().execute(express3, context, null, true, false);
        System.out.println(res3);
    }

    @Test
    // 验证自定义操作 + 覆盖操作
    public void test6() throws Exception {
        DefaultContext<String, Object> context = new DefaultContext<>();
        // 验证字符串的contains
        context.put("a", "abc");
        String express1 = "a contains \"ab\"";
        Object res1 = engine.getRunner().execute(express1, context, null, true, false);
        System.out.println(res1);

        // 验证集合的contains
        context.put("b", Lists.newArrayList("abcd", "abcde"));
        String express2 = "b contains \"abcd\"";
        Object res2 = engine.getRunner().execute(express2, context, null, true, false);
        System.out.println(res2);

        // 验证字符串的not_contains
        context.put("c", "abc");
        String express3 = "a not_contains \"ab\"";
        Object res3 = engine.getRunner().execute(express3, context, null, true, false);
        System.out.println(res3);

        // 验证集合的not_contains
        context.put("d", Lists.newArrayList("abcd", "abcde"));
        String express4 = "b not_contains \"abcd\"";
        Object res4 = engine.getRunner().execute(express4, context, null, true, false);
        System.out.println(res4);

        // 验证not_in
        context.put("e", "abc");
        String express5 = "e not_in (\"ab\",\"abc\",\"abcd\")";
        Object res5 = engine.getRunner().execute(express5, context, null, true, false);
        System.out.println(res5);

        // 验证in
        context.put("f", "abc");
        String express6 = "f in (\"ab\", \"abc\", \"abcd\")";
        Object res6 = engine.getRunner().execute(express6, context, null, true, false);
        System.out.println(res6);

        // 验证startWith
        context.put("h", "ABCDEF");
        String express7 = "h startWith \"ABC\"";
        Object res7 = engine.getRunner().execute(express7, context, null, true, false);
        System.out.println(res7);

        // 验证覆盖操作+
        String express8 = "1 + 2 + 3";
        Object res8 = engine.getRunner().execute(express8, context, null, true, false);
        System.out.println(res8);
    }

    @Test
    // 验证自定义函数
    public void test7() throws Exception {
        String express = "join(1, 2, 3)";
        Object res = engine.getRunner().execute(express, null, null, true, false);
        System.out.println(res);
    }

}
