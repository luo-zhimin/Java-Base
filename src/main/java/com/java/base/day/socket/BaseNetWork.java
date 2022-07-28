/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.socket;

import lombok.SneakyThrows;
import java.net.InetAddress;

/**
 * Created by IntelliJ IDEA.
 * 网络编程
 * @Author : 志敏.罗
 * @create 2022/7/14 21:08
 */
public class BaseNetWork {

    /**
     * 网络编程 InetAddress
     * 1.获取本地的InetAddress getLocalHost{机器名字/本级Ip地址}
     * 2.根据指定主机名/域名获取ip地址对象 getByName
     * 3.获取InetAddress对象的主机名 getHostName
     * 4.获取InetAddress对象的地址 getHostAddress
     */
    @SneakyThrows
    public static void main(String[] args) {
        //获取本地的InetAddress
        InetAddress inetAddress = InetAddress.getLocalHost();//luozhimindeMacBook-Pro.local/127.0.0.1
        System.out.println(inetAddress);
        //根据指定主机名获取ip地址对象
        InetAddress address = InetAddress.getByName("luozhimindeMacBook-Pro.local");
        System.out.println("host "+address);
        //域名
        InetAddress yuMing = InetAddress.getByName("www.matools.com");
        System.out.println("域名 "+yuMing);

        //主机地址
        System.out.println("主机地址 "+yuMing.getHostAddress());

        //对象的主机名
        System.out.println("对象的主机名 "+yuMing.getHostName());
    }
}
