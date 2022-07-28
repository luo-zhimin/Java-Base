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
import com.java.base.day.socketProgect.entry.User;
import lombok.SneakyThrows;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by IntelliJ IDEA.
 * 完成用户登陆认证和注册
 *
 * @Author : 志敏.罗
 * @create 2022/7/18 21:41
 */
public class UserClientService {

    /**
     * 方便其他地方使用
     */
    User user = new User();

    private Socket socket;


    /**
     * 校验用户 并且存储
     *
     * @param userId   用户名字
     * @param password 密码
     * @return 是否通过
     */
    @SneakyThrows
    public boolean checkUser(String userId, String password) {
        //init user
        user.setUserId(userId);
        user.setPassword(password);
        //链接客户端 发送user对象 客户端
        socket = new Socket(InetAddress.getLocalHost(), 9999);
        //拿到对象流
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        //发送user对象
        objectOutputStream.writeObject(user);

        //读取服务端回送的 message
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Message message = (Message) objectInputStream.readObject();

        if (message.getType().equals(CommonConstant.MESSAGE_LOGIN_SUCCESS)) {
            //创建一个和服务器端 保持通讯的线程 ClientContentServerThread
            //client thread start
            ClientContentServerThread serverThread = new ClientContentServerThread(socket);
            serverThread.start();
            //放入集合
            ManageClientContentThread.addClientServerThread(userId, serverThread);
            return true;
        } else {
            //登陆失败 关闭socket
            socket.close();
        }
        return false;
    }

    /**
     * 向服务器请求在线用户列表
     *
     * @return userList
     */
    @SneakyThrows
    public void getOnLineUserList() {
        //构建message对象
        Message message = new Message();
        message.setType(CommonConstant.MESSAGE_GET_ONLINE_FRIEND);
        message.setSender(user.getUserId());
        //send server need socket get outputStream  manageClientContentThread has userThreadSocket
        sendMessageByUser(message);
    }

    /**
     * 向服务端发送提出指令
     */
    @SneakyThrows
    public void logout() {
        Message message = new Message();
        //必须告诉是那个客户端退出
        message.setSender(user.getUserId());
        //客户端 退出
        message.setType(CommonConstant.MESSAGE_CLIENT_EXIT);
        //准备发送
        sendMessageByUser(message);
        System.out.println(user.getUserId()+" 退出了系统");
        //结束进程
        System.exit(0);
    }

    @SneakyThrows
    private void sendMessageByUser(Message message){
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                ManageClientContentThread
                        .getClientThread(user.getUserId())
                        .getSocket().getOutputStream()
        );
        objectOutputStream.writeObject(message);
    }


    public boolean checkUserIsOnline(String userId){
        return ManageClientContentThread.getKeys().contains(userId);
    }
}
