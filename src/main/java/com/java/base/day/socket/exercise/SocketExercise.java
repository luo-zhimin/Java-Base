/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.socket.exercise;

import com.java.base.day.socket.util.StreamUtils;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 * Socket 练习
 *
 * @Author : 志敏.罗
 * @create 2022/7/17 14:40
 */
@SuppressWarnings({"all"})
public class SocketExercise {

    private String path = "/Users/luozhimin/Desktop/File/package/分享资料";

    /**
     * 字符流 while 需要处理 数据通道 一次说完之后 什么时候结束
     */
    @Test
    @SneakyThrows
    void server() {
        ServerSocket serverSocket = new ServerSocket(9999);
        Socket socket = serverSocket.accept();
        BufferedWriter bufferedWriter = null;
        BufferedReader bufferedReader = null;
        int count = 0;

        while (true) {
            //read
            bufferedReader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            String read = bufferedReader.readLine();
            System.out.println("server read " + read);
            String waitOut;
            switch (read) {
                case "name":
                    waitOut = "我是Tom";
                    break;
                case "hobby":
                    waitOut = "编写程序";
                    break;
                default:
                    waitOut = "你说什么？";
            }
            socket.shutdownInput();

            //send
            bufferedWriter = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())
            );
            bufferedWriter.write(waitOut);
            bufferedWriter.flush();
            socket.shutdownOutput();
            if (count >= 3) {
                break;
            }
        }

        //close
        bufferedReader.close();
        bufferedWriter.close();
        serverSocket.close();
        socket.close();
    }

    @SneakyThrows
    static void client() {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        BufferedWriter bufferedWriter = null;
        BufferedReader bufferedReader = null;
        int count = 0;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            count++;
            System.out.println("您请说：");
            String next = scanner.next();
            //send
            bufferedWriter = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())
            );
            bufferedWriter.write(next);
            bufferedWriter.flush();
            socket.shutdownOutput();

            //read
            bufferedReader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            String read = bufferedReader.readLine();
            System.out.println(read);
            socket.shutdownInput();
            if (count >= 3) {
                break;
            }
        }

        //close
        bufferedReader.close();
        bufferedWriter.close();
        socket.close();
    }

    @Test
    @SneakyThrows
    void recevie() {
        DatagramSocket datagramSocket = new DatagramSocket(8888);
        byte[] bytes = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
        //receive
        datagramSocket.receive(datagramPacket);
        int length = datagramPacket.getLength();
        byte[] data = datagramPacket.getData();
        String receiveData = new String(data, 0, length);
        String waitOut = "what?";
        if (receiveData.equals("四大名著")) {
            waitOut = "<<西游记>>,<<三国演义>>,<<红楼梦>>,<<水浒传>>";
        }
        System.out.println(waitOut);
        bytes = waitOut.getBytes(StandardCharsets.UTF_8);
        //send
        datagramPacket = new DatagramPacket(bytes, bytes.length, InetAddress.getLocalHost(), 8887);
        datagramSocket.send(datagramPacket);

        datagramSocket.close();
    }

    //    @Test
    @SneakyThrows
    static void send() {
        DatagramSocket datagramSocket = new DatagramSocket(8887);
        //send
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入你的问题：");
        String next = scanner.next();
        if (next == "") return;
        byte[] bytes = next.getBytes(StandardCharsets.UTF_8);
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, InetAddress.getLocalHost(), 8888);
        datagramSocket.send(datagramPacket);

        //receive
        bytes = new byte[1024];
        datagramPacket = new DatagramPacket(bytes, bytes.length);
        //receive
        datagramSocket.receive(datagramPacket);
        int length = datagramPacket.getLength();
        byte[] data = datagramPacket.getData();
        String receiveData = new String(data, 0, length);
        System.out.println(receiveData);
        datagramSocket.close();
    }


    @Test
    @SneakyThrows
    void fileServer() {
        ServerSocket serverSocket = new ServerSocket(9999);
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        String streamToString = StreamUtils.streamToString(inputStream);
        socket.shutdownInput();
        if (StringUtils.isBlank(streamToString)){return;}
        String fileMp3 = getFileMp3(streamToString);

        //send
        byte[] bytes = StreamUtils.streamToByteArray(new FileInputStream(fileMp3));
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(bytes);
        outputStream.flush();
        socket.shutdownOutput();

        //close
        inputStream.close();
        outputStream.close();
        socket.close();
        serverSocket.close();
    }

    /**
     * 文件下载
     */
    @SneakyThrows
    static void fileClient() {
        Socket socket = new Socket(InetAddress.getLocalHost(),9999);
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入音乐名称：");
        String next = scanner.next();
        OutputStream outputStream = socket.getOutputStream();
        byte[] bytes = next.getBytes(StandardCharsets.UTF_8);
        outputStream.write(bytes);
        outputStream.flush();
        socket.shutdownOutput();

        //receive ready download
        InputStream inputStream = socket.getInputStream();
        bytes = StreamUtils.streamToByteArray(inputStream);
        FileOutputStream fileOutputStream = new FileOutputStream(
                SocketExercise.class.getResource("/").getFile()+"music.mp3"
        );
        fileOutputStream.write(bytes);
        fileOutputStream.flush();

        inputStream.close();
        outputStream.close();
        fileOutputStream.close();
        socket.close();
    }

    @Test
    String getFileMp3(String name) {
        File file = new File(path);
        if (file.exists()) {
            String[] files = file.list();
            for (String fff : files) {
                if (fff.contains(".mp3") && fff.contains(name)) {
                    return path + "/" + fff;
                }
            }
        }
        return path+"/"+"无名.mp3";
    }

    public static void main(String[] args) {
//        client();
//        send();
       fileClient();
    }
}
