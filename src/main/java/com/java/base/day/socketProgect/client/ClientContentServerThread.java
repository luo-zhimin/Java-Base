/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.socketProgect.client;

import com.java.base.day.socketProgect.constant.CommonConstant;
import com.java.base.day.socketProgect.entry.Message;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.SneakyThrows;

import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 志敏.罗
 * @create 2022/7/18 21:56
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ClientContentServerThread extends Thread {
    //需要持有socket
    private Socket socket;

    private boolean loop = true;

    public ClientContentServerThread(Socket socket) {
        this.socket = socket;
    }

    /**
     *
     */
    @Override
    @SneakyThrows
    public void run() {
        //需要在台进行交换
        while (loop) {

//            System.out.println("客户端线程等待读取从服务器端发送的消息");
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            //如果服务器 没有发送 message对象 线程会阻塞
            Message message = (Message) objectInputStream.readObject();

            //判断message类型
            switch (message.getType()) {
                //返回在线用户列表
                case CommonConstant.MESSAGE_RETURN_ONLINE_FRIEND:
                    //取出在线列表
                    String[] onlineUsers = message.getContent().split(" ");
                    //need check user is hava login with 私聊
                    System.out.println("\n====当前在线用户列表====");
                    for (String onlineUser : onlineUsers) {
                        System.out.println("用户：" + onlineUser);
                    }
                    break;
                case CommonConstant.MESSAGE_COMMON_MES:
                    //客户端私聊 显示
                    System.out.println("\n"+message.getSendTime()+"： "+message.getSender() +" 对 "+message.getReceiver()+" 说 " + message.getContent());
                    break;
                case CommonConstant.MESSAGE_COMMON_EVERYBODY:
                    //客户端群聊 显示
                    System.out.println("\n"+message.getSendTime()+"： "+message.getSender() +" 对大家说 " + message.getContent());
                    break;
                case CommonConstant.MESSAGE_FILE:
                    //客户端文件 接收输出
                    FileOutputStream fileOutputStream = new FileOutputStream(message.getTargetPath());
                    fileOutputStream.write(message.getFileBytes());
                    System.out.println("\n"+message.getSendTime()+"： "+message.getSender() +" 给 " + message.getReceiver()+" 发送文件："+message.getSourcePath()+" 到 "+
                            message.getTargetPath());
                    fileOutputStream.flush();
                    System.out.println("保存文件 successful");
                    fileOutputStream.close();
                    break;
                case CommonConstant.MESSAGE_SERVER_NEWS:
                    //服务器新闻
                    System.out.println("\n服务器 对 大家说："+message.getContent());
                    break;
            }
        }
    }
}
