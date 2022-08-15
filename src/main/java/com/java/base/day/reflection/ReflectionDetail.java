/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.reflection;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * 反射详情
 *
 * @Author : 志敏.罗
 * @create 2022/7/22 17:48
 */
public class ReflectionDetail {

    /**
     * 反射可以完成
     *  1.在运行时判断任意一个对象所属的类
     *  2.在运行时构造任意一个类的对象
     *  3.在运行时得到任意一个类所具有的成员变量和方法
     *  4.在运行时调用任意一个对象的成员变量和方法
     *  5.生成动态代理
     * <p/>
     * 反射主要的类->这些类在java.lang.reflection
     *  1.java.lang.Class∶代表一个类，Class对象表示某个类加载后在堆中的对象
     *  2.java.lang.reflect.Method∶代表类的方法，Method对象表示某个类的方法
     *  3.java.lang.reflect.Field∶代表类的成员变量，Field对象表示某个类的成员变量  getField() 不可以得到私有的属性
     *  4.java.lang.reflect.Constructor∶代表类的构造方法，Constructor对象表示构造器 getConstructor() ()中可以指定构造器参数类型 不然就是无参构造
     *      含参数构造 传入的class对象 String.class 就是String类的class对象 入参是你构造器 含有的 参数 的 类对象
     * <p/>
     *  反射优点和缺点
     *    1.优点可以动态的创建和使用对象（也是框架底层核心），使用灵活，没有反射机制，框架技术就失去底层支撑。
     *    2.缺点∶使用反射基本是解释执行，对执行速度有影响.
     * <p/>
     *  反射调用优化-关闭访问检查
     *   1.java.lang.reflect 包 会继承 AccessibleObject  所以Method和Field、Constructor对象都有setAccessible()方法
     *   2.setAccessible作用是启动和禁用访问安全检查的开关
     *   3.参数值为true表示反射的对象在使用时取消访问检查，提高反射的效率。参数值为false则表示反射的对象执行访问检查
     */
    @SneakyThrows
    @Test
    void reflectionDetail01() {
        //创建properties对象
        Properties properties = new Properties();
        properties.load(Files.newInputStream(Paths.get("/Users/luozhimin/Desktop/Java/Java-Base/src/main/resources/test.properties")));
        String method = properties.getProperty("method");
        String classFullPath = properties.getProperty("classFullPath");
        //classLoad 加载 properties对象
        //ReflectionDetail.class.getClassLoader().getResourceAsStream("");

        //reflection
        Class<?> aClass = Class.forName(classFullPath);//加载 load class
        Object o = aClass.newInstance();//get instance
        Method aClassMethod = aClass.getMethod(method);//get method
        aClassMethod.invoke(o);//调用 可以传入 可变参数 Object... args

        //字段 field 得到name字段  getField() 不可以得到私有的属性
        Field age = aClass.getField("age");
        //传统方法 对象.成员变量  反射 成员变量.get(对象)
        System.out.println(age.get(o));
        System.out.println(Arrays.toString(aClass.getFields()));

        //构造器 Constructor ()中可以指定构造器参数类型 不然就是无参构造
        Constructor<?> constructor = aClass.getConstructor();
        System.out.println(constructor);//public com.java.base.day.reflection.Cat()

        //构造器可以帮助我们构建对象

        //含参数构造 传入的class对象 String.class 就是String类的class对象 入参是你构造器 含有的 参数 的 类对象
//        Constructor<?> constructor2 = aClass.getConstructor(String.class,int.class);
        Constructor<?> constructor2 = aClass.getConstructor(String.class);
        System.out.println(constructor2);//public com.java.base.day.reflection.Cat(java.lang.String)
    }


    /**
     * 反射调用的性能 和 优化
     */
    @Test
    @SneakyThrows
    void reflectionDetail02(){
        //传统方法调用 测试性能
        m2();//3 ms
        //放射调用
        m1();//968 ms
        //反射调用优化-关闭访问检查
        //1. java.lang.reflect 包 会继承 AccessibleObject  所以Method和Field、Constructor对象都有setAccessible（）方法
        //2. setAccessible作用是启动和禁用访问安全检查的开关
        //3. 参数值为true表示反射的对象在使用时取消访问检查，提高反射的效率。参数值为false则表示反射的对象执行访问检查

        //java.lang.reflect 包 会继承 AccessibleObject
//        Field
        m3();//740
    }

    /**
     * 反射
     */
    @SneakyThrows
    private void m1(){
        Class<?> aClass = Class.forName("com.java.base.day.reflection.Cat");
        Object o = aClass.newInstance();
        Method method = aClass.getMethod("hi");
        //9e
        long start = System.currentTimeMillis();
        for (int i = 0; i < 900000000; i++) {
            method.invoke(o);
        }
        long end = System.currentTimeMillis();
        System.out.println("反射方法调用 耗时="+(end-start));
    }

    /**
     * 传统方法
     */
    private void m2(){
        Cat cat = new Cat();
        //9e
        long start = System.currentTimeMillis();
        for (int i = 0; i < 900000000; i++) {
            cat.hi();
        }
        long end = System.currentTimeMillis();
        System.out.println("传统方法调用 耗时="+(end-start));
    }

    /**
     * 反射调用优化  accessible 哎k赛思包
     */
    @SneakyThrows
    private void m3(){
        Class<?> aClass = Class.forName("com.java.base.day.reflection.Cat");
        Object o = aClass.newInstance();
        Method method = aClass.getMethod("hi");
        method.setAccessible(true);//启动和禁用访问安全检查的开关 true 表示取消
        //9e
        long start = System.currentTimeMillis();
        for (int i = 0; i < 900000000; i++) {
            method.invoke(o);
        }
        long end = System.currentTimeMillis();
        System.out.println("反射调用优化 方法调用 耗时="+(end-start));
    }
}
