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
import lombok.SneakyThrows;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * 客户端消息处理
 * @Author : 志敏.罗
 * @create 2022/7/20 21:39
 * @throws java.io.StreamCorruptedException invalid type code: AC
 * 俩种解决方法
 * 1.判断里面是否就一个对象 available input write need while
 * 2.重写 ObjectOutputStream的writeStreamHeader
 */
public class MessageClientService {


    /**
     * 私聊
     *
     * @param content  要说的内容
     * @param receiver 接收人
     * @param sender   发送人
     */
    @SneakyThrows
    public static void sendMessageToOnlyOne(String content, String receiver, String sender) {
        if (receiver.equals(sender)) {
            System.out.println("自己不可以对自己私聊哦");
            return;
        }
        //构建对象
        Message message = new Message();
        message.setType(CommonConstant.MESSAGE_COMMON_MES);
        message.setSender(sender);
        message.setContent(content);
        message.setReceiver(receiver);
        message.setSendTime(DateFormat.getDateTimeInstance().format(new Date()));
        System.out.println(message.getSendTime() + "：" + sender + " 对 " + receiver + " 说 " + content);
        //发送给服务端
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                ManageClientContentThread
                        .getClientThread(sender)
                        .getSocket()
                        .getOutputStream()
        );
        objectOutputStream.writeObject(message);
    }


    /**
     * 群聊
     *
     * @param content 内容
     * @param sender  发送人
     */
    @SneakyThrows
    public static void sendMessages(String content, String sender) {
        Message message = new Message();
        message.setType(CommonConstant.MESSAGE_COMMON_EVERYBODY);
        message.setSender(sender);
        message.setContent(content);
        message.setSendTime(DateFormat.getDateTimeInstance().format(new Date()));
        System.out.println(message.getSendTime() + "：" + sender + " 对大家说 " + content);
        //发送给服务端
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                ManageClientContentThread
                        .getClientThread(sender)
                        .getSocket()
                        .getOutputStream()
        );
        objectOutputStream.writeObject(message);
    }

    /**
     *
     * @throws IOException
     */
    public void t() throws IOException {
        new ObjectOutputStream(new FileOutputStream("1"));
    }
}
