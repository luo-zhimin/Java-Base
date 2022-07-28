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
import com.java.base.day.socketProgect.entry.User;
import lombok.SneakyThrows;
import org.apache.commons.collections4.CollectionUtils;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by IntelliJ IDEA.
 * <p/>
 * 服务端 监听端口 等待客户端链接 保持 通讯
 *
 * @Author : 志敏.罗
 * @create 2022/7/17 22:09
 */
public class QQServer {

    private ServerSocket serverSocket = null;

    private final static ConcurrentHashMap<String,User> userMap = new ConcurrentHashMap<>();

    static {
        userMap.put("100",new User("100","123456"));
        userMap.put("200",new User("200","123456"));
        userMap.put("紫霞仙子",new User("紫霞仙子","123456"));
        userMap.put("孙悟空",new User("孙悟空","123456"));
    }

    @SneakyThrows
    public QQServer() {
        System.out.println("服务端端口开始监听");
        serverSocket = new ServerSocket(9999);
        //启动推送新闻的线程
        new Thread(new MessageServerService()).start();
        while (true) {
            //监听 当建立链接后 继续监听
            //接收
            Socket socket = serverSocket.accept();
            //创建对象读取流
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            //创建message对象 准备回复客户端
            Message message = new Message();
            //读取user
            User user = (User) objectInputStream.readObject();
            //....->数据库验证
            //暂时写死 用户
            if (checkUser(user.getUserId(), user.getPassword())) {
                message.setType(CommonConstant.MESSAGE_LOGIN_SUCCESS);
                //将message对象 回复给 客户端
                objectOutputStream.writeObject(message);
                //sso 可以加校验 取线程 找登陆用户 无->login
                //创建一个线程 和 客户端保持通讯
                ServerContentClientThread serverContentClientThread = new ServerContentClientThread(socket, user.getUserId());
                serverContentClientThread.start();
                //放入集合中管理
                ManageServerThread.addThread(user.getUserId(), serverContentClientThread);
                List<Message> messages = ServerContentClientThread.messages;
                if (CollectionUtils.isNotEmpty(messages)){
                    System.out.println(messages);
                    for (int i = 0; i < messages.size(); i++) {
                        Message m = messages.get(i);
                        if (m != null) {
                            switch (m.getType()) {
                                case CommonConstant.MESSAGE_COMMON_MES:
                                    writeMessageToLoginClient(m);
                                    System.out.println(m.getSendTime() + "：" + m.getSender() + " 对 " + m.getReceiver() + "进行私聊（离线用户登陆转发）");
                                    //发送一次后移除
                                    messages.remove(m);
                                    break;
                                case CommonConstant.MESSAGE_FILE:
                                    //文件处理
                                    writeMessageToLoginClient(m);
                                    System.out.println(message.getSendTime() + "：" + message.getSender() + " 对 "+message.getReceiver()+"发送文件（离线用户登陆转发），文件大小 "+message.getFileLength());
                                    //发送一次后移除
                                    messages.remove(m);
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                }
            } else {
                System.out.println("check user fail");
                message.setType(CommonConstant.MESSAGE_LOGIN_FAIL);
                //将message对象 回复给 客户端
                objectOutputStream.writeObject(message);
                //关闭socket
                socket.close();
            }

        }
        //退出之后关闭severSocket
//        serverSocket.close();
    }


    private boolean checkUser(String userId,String password){
        User user = userMap.get(userId);
        if (user != null){
            if (user.getPassword().equals(password)){
                return true;
            }
            System.out.println("密码错误");
            return false;
        }
        System.out.println("用户不存在");
        return false;
    }

    @SneakyThrows
    private void writeMessageToLoginClient(Message message){
        ServerContentClientThread serverThread = ManageServerThread.getServerThread(message.getReceiver());
        ObjectOutputStream speckOnlyOne = new ObjectOutputStream(serverThread.getSocket().getOutputStream());
        //转发 如果用户不在线可以保存 数据库
        speckOnlyOne.writeObject(message);
    }
}
