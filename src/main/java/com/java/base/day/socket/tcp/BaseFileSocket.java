/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.socket.tcp;
import com.java.base.day.socket.util.StreamUtils;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * Created by IntelliJ IDEA.
 * TCP socket 文件流
 *
 * @Author : 志敏.罗
 * @create 2022/7/16 16:27
 */
@SuppressWarnings({"all"})
public class BaseFileSocket {

    private String path = "/Users/luozhimin/Desktop/File/daily/socket.png";

    /**
     * 文件流socket 服务端
     */
    @Test
    @SneakyThrows
    void fileSocketServer() {
        //建立监听
        ServerSocket serverSocket = new ServerSocket(9999);
        //建立链接
        Socket socket = serverSocket.accept();
        FileOutputStream fileOutputStream = new FileOutputStream(
                BaseFileSocket.class.getResource("/").getFile()+"socket.png"
        );
        System.out.println("start assept file");
        //服务端接收图片
        BufferedInputStream bufferedInputStream = new BufferedInputStream(socket.getInputStream());

        byte[] byteArray = StreamUtils.streamToByteArray(bufferedInputStream);
        fileOutputStream.write(byteArray);

        fileOutputStream.flush();
        socket.shutdownInput();
        fileOutputStream.close();

        System.out.println("start send messgae");
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
        bufferedOutputStream.write("收到图片".getBytes(StandardCharsets.UTF_8));
        bufferedOutputStream.flush();
        socket.shutdownOutput();

        bufferedInputStream.close();
        bufferedOutputStream.close();
        socket.close();
        serverSocket.close();
    }


    /**
     * 文件流socket 客户端
     */
    @Test
    @SneakyThrows
    void fileSocketClient() {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);

        //读取文件
        byte[] bytes = StreamUtils.streamToByteArray(new FileInputStream(path));

        System.out.println("client send file to server");
        //准备输出
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
        bufferedOutputStream.write(bytes);
        bufferedOutputStream.flush();
        socket.shutdownOutput();

        //需要收到server的回信
        BufferedInputStream bufferedInputStream = new BufferedInputStream(socket.getInputStream());
        bytes = StreamUtils.streamToByteArray(bufferedInputStream);
        System.out.println(new String(bytes));
        socket.shutdownInput();
        System.out.println("assept server messgae");

        bufferedInputStream.close();
        bufferedOutputStream.close();
        socket.close();
    }

    @Test
    void netstatTest(){

    }
}
