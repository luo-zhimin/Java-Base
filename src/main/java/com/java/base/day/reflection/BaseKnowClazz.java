/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.reflection;

import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 志敏.罗
 * @create 2022/7/21 22:47
 */
@SuppressWarnings({"all"})
public class BaseKnowClazz {

    /**
     * 反射之前 可以实现 ->准备反射说明
     * reflection 润凡蓝k神
     */
    @Test
    void reflectionQuestion() throws
            IOException, ClassNotFoundException,
            InstantiationException, IllegalAccessException,
            NoSuchMethodException, InvocationTargetException
    {
        //根据配置文件re.properties指定信息，创建Cat对象并调用方法hi

        //传统方法 new 对象 调用 方法
        Properties properties = new Properties();
        //load
        properties.load(new FileReader("/Users/luozhimin/Desktop/Java/Java-Base/src/main/resources/test.properties"));
        //read
        String classFullPath = properties.getProperty("classFullPath");
        String method = properties.getProperty("method");
        System.out.println(classFullPath + " - " + method);

        //创建对象 new 类名() .... classFullPath...String  传统方法 ❌ 走不下去了
        //todo OCP  通过外部文件配置，在不修改源码情况下，来控制程序，也符合设计模式的ocp原则（开闭原则∶不修改源码，扩容功能）

        //使用发射机制解决 快速入门
        //1.加载类 返回Class类型的对象 Class.forName()
        Class<?> aClass = Class.forName(classFullPath);
        //2.通过 class对象 得到加载的类 classFullPath 的对象实例  aClass.newInstance()
        Object o = aClass.newInstance();
        System.out.println("reflection 运行类型= " + o.getClass());//运行类型
        //3.得到加载类的 method对象 aClass.getMethod() 万物皆对象
        Method classMethod = aClass.getMethod(method);
        //4.通过 classMethod 调用方法 即通过方法的对象来实现调用对象  传统 对象.方法()  发射机制 方法.invoke(对象)
        classMethod.invoke(o);
    }

    //1.反射机制允许程序在执行期借助于 Reflection API取得任何类的内部信息（比如成员变量，构造器，成员方法等等），并能操作对象的属性及方法。反射在设计模式和框架底层都会用到
    //2.加载完类之后，在堆中就产生了一个Class类型的对象（一个类只有一个Class对象），这个对象包含了类的完整结构信息。通过这个对象得到类的结构。
    // 这个Class对象就像一面镜子，透过这个镜子看到类的结构，所以，形象的称之为反射

}
