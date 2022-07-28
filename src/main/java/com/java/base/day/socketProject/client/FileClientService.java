/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.socketProject.client;

import com.java.base.day.socket.util.StreamUtils;
import com.java.base.day.socketProject.constant.CommonConstant;
import com.java.base.day.socketProject.entry.Message;
import lombok.SneakyThrows;

import java.io.File;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * 文件相关
 *
 * @Author : 志敏.罗
 * @create 2022/7/20 23:33
 */
public class FileClientService {

    /**
     * 文件发送
     *
     * @param source   来源
     * @param target   目标
     * @param sender   发送人
     * @param receiver 接收人
     */
    @SneakyThrows
    public static void sendFileToOnly(String source, String target, String sender, String receiver) {

        //读取 封装对象
        if (!new File(source).exists()) {
            System.out.println("来源文件不存在 请确认后重新发送");
            return;
        }
        Message fileMessage = new Message();
        fileMessage.setType(CommonConstant.MESSAGE_FILE);
        fileMessage.setSendTime(DateFormat.getDateTimeInstance().format(new Date()));
        fileMessage.setSender(sender);
        fileMessage.setReceiver(receiver);

        //file
        fileMessage.setSourcePath(source);
        fileMessage.setTargetPath(target);
        byte[] bytes = StreamUtils.streamToByteArray(Files.newInputStream(Paths.get(source)));
        fileMessage.setFileBytes(bytes);
        fileMessage.setFileLength(bytes.length);
        System.out.println(sender + " 给 " + receiver + " 发送文件 大小 " + bytes.length);
        //写出 数组 -> 对象 send server
        //获取对应 发送者 线程 进行发送
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                ManageClientContentThread
                        .getClientThread(sender)
                        .getSocket()
                        .getOutputStream()
        );
        objectOutputStream.writeObject(fileMessage);
    }
}
