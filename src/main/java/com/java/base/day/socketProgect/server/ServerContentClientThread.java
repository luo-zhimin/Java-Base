/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.socketProgect.server;

import com.java.base.day.socketProgect.constant.CommonConstant;
import com.java.base.day.socketProgect.entry.Message;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.SneakyThrows;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 志敏.罗
 * @create 2022/7/18 22:34
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ServerContentClientThread extends Thread {

    private Socket socket;

    /**
     * 连接到客户端的用户id
     */
    private String userId;

    private boolean loop = true;

    public static List<Message> messages = new ArrayList<>();

    public ServerContentClientThread(Socket socket, String userId) {
        this.socket = socket;
        this.userId = userId;
    }

    /**
     * 处理客户端消息 发送和接收消息
     */
    @Override
    @SneakyThrows
    public void run() {
        while (loop) {
            System.out.println("服务端线程等待 " + userId + " 读取从客户端端发送的消息");
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            Message message = (Message) objectInputStream.readObject();

            switch (message.getType()) {
                //返回在线用户列表
                case CommonConstant.MESSAGE_GET_ONLINE_FRIEND:
                    System.out.println(message.getSender() + " 要在线用户列表");
                    //放入在线用户
                    String users = ManageServerThread.getOnlineUsers();
                    Message onlineUserMessage = new Message();
                    onlineUserMessage.setContent(users);
                    onlineUserMessage.setType(CommonConstant.MESSAGE_RETURN_ONLINE_FRIEND);
                    onlineUserMessage.setReceiver(message.getSender());
                    //返回给客户端
                    objectOutputStream.writeObject(onlineUserMessage);
                    break;
                case CommonConstant.MESSAGE_CLIENT_EXIT:
                    //client exit
                    System.out.println(message.getSender() + " 准备退出系统");

                    //关闭对应的socket 对应客户端
                    ManageServerThread.getServerThread(message.getSender()).getSocket().close();

                    //把这个线程移除
                    ManageServerThread.removeServerThread(message.getSender());

                    //退出while 循环
                    loop = false;

                    break;
                case CommonConstant.MESSAGE_COMMON_MES:
                    //私聊 根据message对象获取接收方线程
                    assembleMessage(message);
                    System.out.println(message.getSendTime() + "：" + message.getSender() + " 对 " + message.getReceiver() + "进行私聊");
                    break;
                case CommonConstant.MESSAGE_COMMON_EVERYBODY:
                    //群聊 找到除了发送者所有的 在线线程 转发
                    Set<String> receivers = ManageServerThread.getKeysExceptOwn(message.getSender());
                    sendMessage(receivers,message);
                    System.out.println(message.getSendTime() + "：" + message.getSender() + " 对大家说");
                    break;
                case CommonConstant.MESSAGE_FILE:
                    assembleMessage(message);
                    System.out.println(message.getSendTime() + "：" + message.getSender() + " 对 "+message.getReceiver()+"发送文件，文件大小 "+message.getFileLength());
                    break;
//                case CommonConstant.MESSAGE_SERVER_NEWS:
//                    //write 直接发送
//                    Set<String> keys = ManageServerThread.getKeys();
//                    //需要构建 新的message对象
//                    sendMessage(keys,null);
//                    break;
            }
        }
    }

    /**
     * 私聊 + 文件
     * @param message message对象
     */
    @SneakyThrows
    private void assembleMessage(Message message){
        ServerContentClientThread serverThread = ManageServerThread.getServerThread(message.getReceiver());
        if (serverThread == null) {
            messages.add(message);
            System.out.println("当前用户不在线 暂存离线记录...");
            return;
        }
        ObjectOutputStream speckOnlyOne = new ObjectOutputStream(serverThread.getSocket().getOutputStream());
        //转发 如果用户不在线可以保存 数据库
        speckOnlyOne.writeObject(message);
    }

    @SneakyThrows
    public static void sendMessage(Set<String> receivers,Message message) {
        for (String r : receivers) {
            ObjectOutputStream receiverOutputStream = new ObjectOutputStream(
                    ManageServerThread.getServerThread(r)
                            .getSocket()
                            .getOutputStream()
            );
            receiverOutputStream.writeObject(message);
        }
    }
}
