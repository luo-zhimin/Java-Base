/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.socketProgect.client;

import com.java.base.day.socketProgect.util.Utility;

/**
 * Created by IntelliJ IDEA.
 * 客户端 菜单界面
 *
 * @Author : 志敏.罗
 * @create 2022/7/17 22:42
 */
public class QQView {

    UserClientService userClientService = new UserClientService();

    /**
     * 控制菜单
     */
    private boolean loop = true;

    /**
     * 接收用户的输入
     */
    private String key = "";

    /**
     * 显示主菜单
     */
    public void mainView() {
        while (loop) {
            System.out.println("=======欢迎登陆网络通讯系统=======");
            System.out.println("\t\t 1 登陆系统");
            System.out.println("\t\t 9 退出系统");

            System.out.print("请输入你的选择：");

            key = Utility.readString(1);
            String content;
            String receiveId;

            switch (key) {
                case "1":
                    System.out.print("请输入用户名：");
                    String userId = Utility.readString(8);
                    System.out.print("请输入密码：");
                    String password = Utility.readString(10);

                    //组装User对象 链接服务器
                    if (userClientService.checkUser(userId, password)) {
                        //成功二级菜单
                        System.out.println("=======欢迎（用户 " + userId + "）登陆网络通讯系统=======");
                        while (loop) {
                            System.out.println("=======网络通讯系统二级菜单(用户" + userId + ")=======");

                            System.out.println("\t\t 1 显示在线用户列表");
                            System.out.println("\t\t 2 群发消息");
                            System.out.println("\t\t 3 私聊消息");
                            System.out.println("\t\t 4 发送文件");
                            System.out.println("\t\t 9 退出系统");

                            System.out.print("请输入你的选择：");
                            key = Utility.readString(1);
                            switch (key) {
                                case "1":
                                    userClientService.getOnLineUserList();
                                    break;
                                case "2":
                                    System.out.print("请输入想对大家说的话：");
                                    content = Utility.readString(50);
                                    //发给大家 message对象 send user
                                    MessageClientService.sendMessages(content,userId);
                                    break;
                                case "3":
                                    //客户端 当前用户给 this.user 接收人发送 由服务器进行转发给 接收人 message.getter
                                    //去找到服务端对应的 socket线程 组装message对象转发
                                    System.out.print("请输入想聊天的用户号（在线)：");
                                    receiveId = Utility.readString(10);
//                                    if (!userClientService.checkUserIsOnline(receiveId)) {
//                                        System.out.println("当前用户不在线..");//后续离线留言
//                                        break;
//                                    }
                                    System.out.print("请输入想说的话：");
                                    content = Utility.readString(100);
                                    MessageClientService.sendMessageToOnlyOne(content,receiveId,userId);

                                    break;
                                case "4":
                                    System.out.print("请输入想发送文件的用户号（在线）：");
                                    receiveId = Utility.readString(10);
                                    System.out.print("请输入发送的文件完整路径（形式/Users/luozhimin/Desktop/File/daily）：");
                                    String sourcePath = Utility.readString(100);
                                    System.out.print("请输入想发送文件到对方的路径（形式/Users/luozhimin/Desktop/File/daily：");
                                    String targetPath = Utility.readString(100);
                                    FileClientService.sendFileToOnly(sourcePath,targetPath,userId,receiveId);
                                    break;
                                case "9":
                                    //给服务器发送退出指令
                                    userClientService.logout();
                                    loop = false;
                                    break;
                            }
                        }
                    } else {
                        System.out.println("===登录失败===");
                    }
                    break;
                case "9":
                    System.out.println("退出系统");
                    loop = false;
                    break;
            }
        }
    }
}
