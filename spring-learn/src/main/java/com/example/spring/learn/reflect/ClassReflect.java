package com.example.spring.learn.reflect;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.stream.Stream;

@Component
public class ClassReflect extends ReflectBase implements Serializable,Cloneable {

    private String field1;

    private String field2;

    public String field3;

    public ClassReflect(String field1, String field2) {
        this.field1 = field1;
        this.field2 = field2;
    }

    private ClassReflect(String field1) {
        this.field1 = field1;
    }

    public ClassReflect() {

    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }

    public void publicFunc(String param) {
        System.out.println("publicFunc execute!");
    }

    private void privateFunc(String param) {
        System.out.println("privateFunc execute! param:" + param);
    }

    @Override
    public String toString() {
        return "ClassReflect{" +
                "field1='" + field1 + '\'' +
                ", field2='" + field2 + '\'' +
                ", field3='" + field3 + '\'' +
                ", baseField2='" + baseField2 + '\'' +
                '}';
    }

    public static void main(String[] args) throws Exception {
        // 三种获取Class对象的方式
        // 1.使用getClass()方法
        ClassReflect classReflect1 = new ClassReflect("1", "2");
        Class clazz1 = classReflect1.getClass();
        System.out.println("ClassReflect1 class: " + clazz1);

        // 2.使用类.class
        Class clazz2 = ClassReflect.class;
        System.out.println("classReflect2 class: " + clazz2);

        // 3.使用Class.forName()
        Class clazz3 = Class.forName("com.example.spring.learn.reflect.ClassReflect");
        System.out.println("classReflect3 class:" + clazz3);

        // 获取父类
        Class superClazz = clazz1.getSuperclass();
        System.out.println("super class: " + superClazz);

        // 获取接口
        Class[] clazzs = clazz1.getInterfaces();
        Stream.of(clazzs).forEach(clazz -> System.out.println("interface: " + clazz.getName()));

        // getConstructors 列举当前类的public构造方法
        Constructor[] constructors1 = clazz1.getConstructors();
        Stream.of(constructors1).forEach(constructor -> System.out.println("constructor1: " + constructor.getName()));
        // getDeclaredConstructors 列举当前类的包含private的构造方法
        Constructor[] constructors2 = clazz1.getDeclaredConstructors();
        Stream.of(constructors2).forEach(constructor -> System.out.println("constructor2: " + constructor));
        Constructor constructor3 = clazz1.getDeclaredConstructor(String.class);
        System.out.println("constructor3: " + constructor3);

        // getFields 列举当前类+超类的public字段
        Field[] fields = clazz1.getFields();
        Stream.of(fields).forEach(field -> System.out.println("field1: " + field));
        // getDeclaredFields 列举当前类的 包含private的字段
        Field[] fields1 = clazz1.getDeclaredFields();
        Stream.of(fields1).forEach(field -> System.out.println("field2: " + field));
        Field field3 = clazz1.getDeclaredField("field1");
        System.out.println("field3: " + field3);

        // getMethods 列举当前类+超类的public方法
        Method[] methods1 = clazz1.getMethods();
        Stream.of(methods1).forEach(method -> System.out.println("method1: " + method.getName()));
        Method[] methods2 = clazz1.getDeclaredMethods();
        Stream.of(methods2).forEach(method -> System.out.println("method2: " + method.getName()));
        Method method3 = clazz1.getDeclaredMethod("privateFunc", String.class);
        System.out.println("method3: " + method3.getName());

        // 如何通过newInstance实例化
        ClassReflect obj = (ClassReflect)clazz1.newInstance();
        obj.setField1("123");
        System.out.println(obj);
        // 如何通过invoke调用实力方法；
        method3.invoke(obj, "abc");

        // 如何根据构造实例化对象；
        ClassReflect obj1 = (ClassReflect) constructor3.newInstance("123abc");
        System.out.println(obj1);

        // 如何更改字段的值
        field3.set(obj1, "0000");
        System.out.println(obj1);

        // 获取注解
        Annotation[] annotations = clazz1.getAnnotations();
        Stream.of(annotations).forEach(annotation -> System.out.println("Annotation: " + annotation.toString()));

        // 获取修饰符
        int modifier = clazz1.getModifiers();
        System.out.println("modifier: " + Modifier.toString(modifier));

    }
}
