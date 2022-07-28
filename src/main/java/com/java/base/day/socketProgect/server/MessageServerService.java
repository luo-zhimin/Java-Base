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
import com.java.base.day.socketProgect.util.Utility;

import java.text.DateFormat;
import java.util.Date;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * 服务端 消息处理
 *
 * @Author : 志敏.罗
 * @create 2022/7/21 17:13
 */
public class MessageServerService implements Runnable {


    @Override
    public void run() {
        //推送多次 新闻
        while (true) {
            System.out.print("请输入服务器要推送的新闻(出入exit退出推送服务线程)：");
            String news = Utility.readString(100);
            if (news.equals("exit")){
                break;
            }
            //构建message
            Message message = new Message();
            message.setType(CommonConstant.MESSAGE_SERVER_NEWS);
            message.setSender("服务器");
            message.setContent(news);
            message.setSendTime(DateFormat.getTimeInstance().format(new Date()));
            System.out.println("服务器 推送消息给 所有人 说：" + message.getContent());
            Set<String> keys = ManageServerThread.getKeys();
            //send
            ServerContentClientThread.sendMessage(keys, message);
        }
    }
}
