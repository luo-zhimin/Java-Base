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
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by IntelliJ IDEA.
 * Socket TCP 字符流
 * @Author : 志敏.罗
 * @create 2022/7/16 15:52
 */
@SuppressWarnings({"all"})
public class BaseCharSocket {

    /**
     * 服务端
     */
    @Test
    @SneakyThrows
    void charSocketServer(){
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("listener charSocket ..");
        Socket accept = serverSocket.accept();
        //接收信息
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(accept.getInputStream())
        );
        System.out.println("accept char client message");
        String read;
        while ((read=bufferedReader.readLine())!=null){
            System.out.println(read);
        }
        accept.shutdownInput();

        BufferedWriter bufferedWriter = new BufferedWriter(
                new OutputStreamWriter(accept.getOutputStream())
        );
        System.out.println("send message server to client");
        bufferedWriter.write("hello,client 字符流");
        //插入一个换行符号 表示输出结束
        bufferedWriter.newLine();
        //手动刷新
        bufferedWriter.flush();
        accept.shutdownOutput();

        //accept close
        bufferedReader.close();
        bufferedWriter.close();
        accept.close();
        serverSocket.close();
    }

    /**
     * 客户端
     */
    @Test
    @SneakyThrows
    void charSocketClient(){
        Socket socket = new Socket(InetAddress.getLocalHost(),9999);
        BufferedWriter bufferedWriter = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream())
        );
        System.out.println("send message client to server");
        bufferedWriter.write("hello,server 字符流");
        //插入一个换行符号 表示输出结束
        bufferedWriter.newLine();
        //手动刷新
        bufferedWriter.flush();
        socket.shutdownOutput();

        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
        );
        System.out.println("accept char server message");
        String read;
        while ((read=bufferedReader.readLine())!=null){
            System.out.println(read);
        }
        socket.shutdownInput();

        //close
        bufferedWriter.close();
        bufferedReader.close();
        socket.close();
    }
}
