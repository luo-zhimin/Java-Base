/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.socket.udp;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 志敏.罗
 * @create 2022/7/16 23:19
 */
@SuppressWarnings({"all"})
public class BaseKnowUDPSocket {

    /**
     * 基本介绍
     * 1.类DatagramSocket和DatagramPacket【数据包/数据报】实现了基于UDP协议网络程序。
     * 2.UDP数据报通过数据报套接字DatagramSocket发送和接收，系统不保证UDP数据报一定能够安全送到目的地，也不能确定什么时候可以抵达。
     * 3.DatagramPacket对象封装了UDP数据报，在数据报中包含了发送端的IP地址和端口号以及接收端的IP地址和端口号。
     * 4.UDP协议中每个数据报都给出了完整的地址信息，因此无须建立发送方和接收方的连接
     * <p>
     * 基本流程
     * 1.核心的两个类/对象DatagramSocket与DatagramPacket
     * 2.建立发送端，接收端（没有服务端和客户端概念）
     * 3.发送数据前，建立数据包/报 DatagramPacket对象
     * 4.调用DatagramSocket的发送、接收方法
     * 5.关闭DatagramSocket
     *
     * 注意：
     *  接收端/发送端 是可逆的
     */
    @Test
    @SneakyThrows
    void UDPReceive() {//接收端
        //建立DatagramSocket对象 准备接收数据
        DatagramSocket datagramSocket = new DatagramSocket(9999);
        //解析数据对象->DatagramPacket
        //UDP 一个数据包最大是64k
        byte[] bytes = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(bytes,bytes.length);
        //准备接收数据 当有数据发送到这个端口时 才会接收到数据 不然会阻塞
        System.out.println("receive wait data....");
        datagramSocket.receive(datagramPacket);
        //进行拆包 解析数据
        System.out.println("receive read data");
        //实际接收到数据长度
        int length = datagramPacket.getLength();
        //接收到的数据
        byte[] data = datagramPacket.getData();
        System.out.println(new String(data,0,length));
        System.out.println("send data to part 9998");

        bytes = "好的，明天见".getBytes(StandardCharsets.UTF_8);
        datagramSocket.send(new DatagramPacket(bytes,bytes.length, InetAddress.getLocalHost(),9998));
        //finally close
        datagramSocket.close();
    }


    /**
     * 发送端
     */
    @Test
    @SneakyThrows
    void UDPSend() {
        //建立DatagramSocket对象 接收->发送 可逆 也准备接收数据
        DatagramSocket datagramSocket = new DatagramSocket(9998);
        //数据封装到->DatagramPacket
        //构建数据包
        System.out.println("send data to receive");
        byte[] bytes = "hello,明天吃火锅".getBytes(StandardCharsets.UTF_8);
        DatagramPacket datagramPacket = new DatagramPacket(bytes,bytes.length, InetAddress.getLocalHost(),9999);
        datagramSocket.send(datagramPacket);

        System.out.println("accept receive data to part 9999");
        bytes = new byte[1024];
        datagramPacket = new DatagramPacket(bytes,bytes.length);
        //准备接收数据 当有数据发送到这个端口时 才会接收到数据 不然会阻塞
        System.out.println("receive wait data....");
        datagramSocket.receive(datagramPacket);
        //进行拆包 解析数据
        System.out.println("receive read data");
        //实际接收到数据长度
        int length = datagramPacket.getLength();
        //接收到的数据
        byte[] data = datagramPacket.getData();
        System.out.println(new String(data,0,length));

        //finally close
        datagramSocket.close();
    }
}
