/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.reflection.classload_;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 * 类加载演示
 * @Author : 志敏.罗
 * @create 2022/7/23 14:46
 */
public class ClassLoad_ {

    /**
     * 类加载基本说明
     * 反射机制是java实现动态语言的关键，也就是通过反射实现类动态加载。
     * 1.静态加载∶编译时加载相关的类，如果没有则报错，依赖性太强
     * 2.动态加载运行时加载需要的类，如果运行时不用该类，即使不存在该类，则不报错，降低了依赖性
     * <p/>
     * 类加载时机
     * 1.当创建对象时（new）//静态加载
     * 2.当子类被加载时，父类也加载//静态加载
     * 3.调用类中的静态成员时//静态加载
     * 4.通过反射//动态加载
     */
    @Test
    @SneakyThrows
    void classLoad01() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入key: ");
        String key = scanner.next();
        switch (key) {
            case "1":
                //静态加载 依赖性高 不管用不用 都进行加载
                W w = new W();
                w.cry();
                System.out.println("静态加载");
                break;
            case "2":
                //动态加载 用的时候加载 降低了依赖性
                System.out.println("动态加载");
                Class<?> aClass = Class.forName("com.java.base.day.reflection.classload_.M");
                Object o = aClass.newInstance();
                Method method = aClass.getMethod("hi");
                method.invoke(o);
                break;
            default:
                System.out.println("do nothing....");
        }
    }

    /**
     * 类加载过程 加载-连接{验证/准备/解析}-初始化
     * <p>
     * 加载阶段
     *  JVM在该阶段的主要目的是将字节码从不同的数据源（可能是class文件、也可能是jar包，甚至网络）转化为二进制字节流加载到内存中，并生成一个代表该类的java.lang.Class对象
     *  (生成元数据到方法区 并且创建一个class对象)
     * 连接阶段-验证
     *  1.目的是为了确保Class 文件的字节流中包含的信息符合当前虚拟机的要求，并且不会危害虚拟机自身的安全。
     *  2.包括∶文件格式验证（是否以魔数 oxcafebabe开头）、元数据验证、字节码验证和符号引用验证
     *  3.可以考虑使用-Xverify∶none参数来关闭大部分的类验证措施，缩短虚拟机类加载的时间。
     * 连接阶段-准备
     *  JVM会在该阶段对静态变量， 分配内存并默认初始化（对应数据类型的默认初始值，如0、0L、null、false等）。这些变量所使用的内存都将在方法区中进行分配
     * <p>
     * 连接阶段-解析
     *  虚拟机将常量池内的符号引用替换为直接引用的过程
     * 初始化阶段
     *  1.到初始化阶段，才真正开始执行类中定义的Java程序代码，此阶段是执行<clinit>（）方法的过程。
     *  2.<clinit>（）方法是由编译器按语句在源文件中出现的顺序，依次自动收集类中的所有 静态变量 的赋值动作和 静态代码块 中的语句，并进行合并
     * 3.虚拟机会保证一个类的<clinit>（）方法在多线程环境中被正确地加锁、同步，如果多个线程同时去初始化一个类，那么只会有一个线程去执行这个类的<clinit>（）方法，
     * 其他线程都需要阻塞等待，直到活动线程执行<clinit>（）方法完毕
     */
    @Test
    @SneakyThrows
    void classLoad02() {
//        Car car = new Car();
        //加载W 类 并生成W的class对象
        //连接 num = 0
        //初始化阶段
        //依次自动收集类中的所有 静态变量 的赋值动作和 静态代码块 中的语句，并进行合并
        /*
            clinit(){
                System.out.println("w static block 执行");
                num =300;
                int num =100
            }
            合并后
            System.out.println("w static block 执行");
            int num =100
        */
//        new W();
//        System.out.println(W.num);//100

        //加载类的时候 是有同步机制控制的
//        Class<?> aClass = Class.forName("com.java.base.day.reflection.classload_.W");
        new W();
        /*
            有这个机制 才可以保证某个类在内存中只有一份class对象
            protected Class<?> loadClass(String name, boolean resolve)
                throws ClassNotFoundException
            {
                synchronized (getClassLoadingLock(name)) {.....}
             }
       */
    }
}

//因为W 静态加载
class W {

    //初始化阶段 依次自动收集类中的所有 静态变量 的赋值动作和 静态代码块 中的语句，并进行合并
    static {
        System.out.println("w static block 执行");
        num =300;
    }

    static int num =100;

    public W(){
        System.out.println("w() 构造器执行");
    }

    //属性-成员变量
    //链接阶段-准备 属性处理
    //n1 是实例属性 不是静态属性 所以在准备阶段 不会分配内存
    //n2 是静态变量 会分配内存 n2 是默认初始化 0
    //n3 是static final 是常量 他和静态变量不一样 因为一旦赋值就不变 所以n3=30
    public int n1 =10;
    public static int n2 =20;//显示初始化 20
    public static final int n3 =30;

    void cry() {
        System.out.println("high...");
    }
}

//动态...
class M {
    public void hi() {
        System.out.println("你好呀...");
    }
}
