/*
 * Copyright (c) luoZhiMin 2022.8.15.11.0.36
 */

package com.java.base.day.reflection.proxy;

/**
 * Created by IntelliJ IDEA.
 * 静态代理
 * 特点 代理类和被代理类在编译期间 就确认下来了
 * @Author : 志敏.罗
 * @create 2022/8/15 23:00
 */
public class StaticProxy {

    /*
        静态代理(接口 实现)
            特征：代理类和目标类的对象的类都是在编译期间确定下来的，不利于程序的拓展性 同时一个代理类只能为一个接口提供服务 这样一来程序中必然会产生过多的代理
               最好通过一个代理类来完成全部的代理功能

            用户：客户端类

            商家：代理，代理某个品牌的U盘

            厂家：目标类

            三者的关系：用户（客户端）  ----  商家（代理）----厂家（目标）

            商家和厂家都是卖U盘的，他们完成的功能是一致的，都是卖U盘
     */

    public static void main(String[] args) {
        //被代理类(相当于直接供货商)
        JSDUsbFactory jsdUsbFactory = new JSDUsbFactory();
        //代理类(相当于第三方)
        ProxyUsbFactory proxyUsbFactory = new ProxyUsbFactory(jsdUsbFactory);
        proxyUsbFactory.produceUsb();
        //生产完成之后代理去找客户销售
    }
}

interface UsbFactory{
    /**
     * 制造usb
     */
    void produceUsb();
}

/**
 * 代理类
 */
class ProxyUsbFactory implements UsbFactory{

    private UsbFactory factory;//用被代理类初始化

    public ProxyUsbFactory(UsbFactory factory){
        this.factory = factory;
    }

    @Override
    public void produceUsb() {
        System.out.println("代理工厂准备....");

        factory.produceUsb();//

        System.out.println("代理工厂开始收尾.....");
    }
}
/**
 * 被代理类
 */
class JSDUsbFactory implements UsbFactory{

    @Override
    public void produceUsb() {
        System.out.println("金士顿工厂生产了一批usb接口");
    }
}
