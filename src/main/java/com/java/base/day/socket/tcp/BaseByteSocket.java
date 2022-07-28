/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.socket.tcp;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * Created by IntelliJ IDEA.
 * Socket TCP 字节流
 * @Author : 志敏.罗
 * @create 2022/7/15 23:22
 */
@SuppressWarnings({"all"})
public class BaseByteSocket {

    /**
     * 基本介绍
     *  1.套接字（Socket）开发网络应用程序被广泛采用，以至于成为事实上的标准。
     *  2.通信的两端都要有Socket，是两台机器间通信的端点
     *  3.网络通信其实就是Socket间的通信。
     *  4.Socket允许程序把网络连接当成一个流，数据在两个Socket间通过IO传输。
     *  5.一般主动发起通信的应用程序属客户端，等待通信请求的为服务端
     *  有俩种实现 TCP 可靠的 UDP 不可靠的
     * <p>
     * TCP网络通信编程
     *  1.基于客户端—服务端的网络通信
     *  2.底层使用的是TCP/IP协议
     *  3.应用场景举例客户端发送数据，服务端接受并显示控制台
     *  4.基于Socket的TCP编程
     * <p>
     * 细节：
     * TCP：
     *  serverSocket 可以通过accept方法 可以返回多个socket对象 多并发
     *  serverSocket 如果有客户端链接 则会返回一个socket对象 当没有链接的时候会阻塞 blocked  等待链接
     *  链接服务端 ip + port 如果链接成功 返回socket对象
     *  需要设置结束标记 shutdownOutput shutdownInput 否则会阻塞
     */
    @Test
    @SneakyThrows
    void server() {//服务端
        //监听端口 等待链接 要求端口没有被占用
        ServerSocket serverSocket = new ServerSocket(9999);
        //如果有客户端链接 则会返回一个socket对象 当没有链接的时候会阻塞 blocked  等待链接
        System.out.println("server 服务端监听端口 等待链接...");
        //serverSocket 可以通过accept方法 可以返回多个socket对象 多并发
        Socket socket = serverSocket.accept();//接收
//        System.out.println("server class "+socket.getClass());
        //拿输入流
        System.out.println("accept clinet");
        InputStream inputStream = socket.getInputStream();
        inputRead(inputStream);
        socket.shutdownInput();
        //交流 发送给客户端 发送完之后需要设置一个结束标记
        //获取输出流
        System.out.println("send client");
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hello,client".getBytes(StandardCharsets.UTF_8));
        socket.shutdownOutput();

        //finally close
        inputStream.close();
        outputStream.close();
        socket.close();
        serverSocket.close();
        System.out.println("server exit..");
    }

    /**
     * 客户端
     */
    @Test
    @SneakyThrows
    void client() {
        //链接服务端 ip + port 如果链接成功 返回socket对象
//        new Socket("8.131.100.64",9999); 链接远程服务器
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        System.out.println("socket client 链接成功～");
        //客户端发送信息 生成socket之后 拿到输出流对象
        OutputStream outputStream = socket.getOutputStream();
        System.out.println("write byte client to server");
        //写数据
        outputStream.write("hello,server".getBytes(StandardCharsets.UTF_8));
        socket.shutdownOutput();

        //交流 收到服务端的回发的信息 server
        //获取输入流
        System.out.println("accept server data");
        InputStream inputStream = socket.getInputStream();
        inputRead(inputStream);
        socket.shutdownInput();

        //finally close outputStream and socket
        outputStream.close();
        inputStream.close();
        socket.close();
        System.out.println("client exit..");
    }

    @SneakyThrows
    private void inputRead(InputStream inputStream) {
        //设置缓冲
        byte[] bytes = new byte[1024];
        int read;
        while ((read = inputStream.read(bytes)) != -1) {
            System.out.println(new String(bytes, 0, read));
        }
    }
}
