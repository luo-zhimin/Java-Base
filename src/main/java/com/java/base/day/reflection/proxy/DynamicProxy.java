/*
 * Copyright (c) luoZhiMin 2022.8.15.11.0.57
 */

package com.java.base.day.reflection.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * 代理 动态代理 (接口 代理类(动态) 被代理类)
 * @Author : 志敏.罗
 * @create 2022/8/15 22:45
 */
public class DynamicProxy {

    /*
        动态代理
     */
    public static void main(String[] args) {
        //被代理类对象
        SuperMan superMan = new SuperMan();
        //代理类
        Human proxyInstance = (Human) ProxyFactory.getProxyInstance(superMan);
        //当通过代理类对象调用方法时 会自动调用被代理类中同名的方法
        String belief = proxyInstance.getBelief();
        System.out.println(belief);
        proxyInstance.eat("大饼");

        System.out.println("-------------");

        JSDUsbFactory jsdUsbFactory = new JSDUsbFactory();
        UsbFactory usbFactory = (UsbFactory) ProxyFactory.getProxyInstance(jsdUsbFactory);
        usbFactory.produceUsb();
    }
}

interface Human{
    /**
     * 心眼
     */
    String getBelief();

    void eat(String food);
}

/**
 * 被代理类
 */
class SuperMan implements Human{

    @Override
    public String getBelief() {
        return "I belief I can fly";
    }

    @Override
    public void eat(String food) {
        System.out.println("我喜欢吃"+food);
    }
}
/*
    要实现动态代理 要解决问题
       一：如何根据加载到内存中的被代理类 动态创建一个代理类及其对象
       二：当通过代理类的对象调用的，如何动态调用代理类的同名方法
 */
class ProxyFactory {

    /**
     * 调用方法返回一个代理类对象
     * @param object 被代理类对象
     * @return 代理类对象
     */
    public static Object getProxyInstance(Object object){
        /*
                 newProxyInstance(ClassLoader loader,类加载器
                                  Class<?>[] interfaces, 该类实现的所有的接口
                                  InvocationHandler h) 调用处理程序 invoke 真正执行动态代理效果
         */
        MyInvocationHandler handler = new MyInvocationHandler();
        handler.bind(object);
        return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), handler);
    }
}
class MyInvocationHandler implements InvocationHandler{

    private Object object;//代理类对象 多态  使用被代理类赋值

    public void bind(Object object){
        this.object = object;
    }

    //当我们通过代理类的对象 调用方法A时，就会自动调用如下的方法
    //将被代理类要执行的方法 声明在invoke里面
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //method 代理类对象调用的方法，此方法也就作为 被代理类对象要调用的方法
        // object 被代理的对象
        if (args!=null && args.length>0) {
            System.out.println(Arrays.toString(args));
        }
        return method.invoke(object,args);
    }
}