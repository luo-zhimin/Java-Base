/*
 * Copyright (c) luoZhiMin 2022.8.8.5.11.58
 */

package com.java.base.day.manhanbuilding.view;

import com.java.base.day.manhanbuilding.service.UserService;
import com.java.base.day.socketProject.util.Utility;

/**
 * Created by IntelliJ IDEA.
 * 满汉楼视图 展示页面
 * @Author : 志敏.罗
 * @create 2022/8/8 13:39
 */
public class MHBView {

    public static void main(String[] args) {
        new MHBView().menuView();
    }

    private static final UserService userService;

    static {
        userService = new UserService();
    }


    /**
     * 控制是否结束
     */
    private boolean loop = true;

    /**
     * 接收用户的输入
     */
    private String key ="";

    /**
     * 文本菜单
     */
    public void menuView(){
        //一级菜单
        while (loop){
            System.out.println("=======欢迎来到满汉楼=======");
            System.out.println("\t\t 1 登陆系统");
            System.out.println("\t\t 2 退出系统");
            System.out.print("请输入你的选择：");

            key = Utility.readString(1);

            switch (key){
                case "1":
                    System.out.print("请输入员工号：");
                    String userName = Utility.readString(8);
                    System.out.print("请输入密 码：");
                    String password = Utility.readString(10);
                    //check login ..... userService
                    //successful login display second menu or fail exit
                    System.out.println("登录满汉楼");
                    if (userService.checkUser(userName,password)){
                        while (loop){
                            System.out.println("=======满汉楼二级菜单(用户" + userName + ")=======");

                            System.out.println("\t\t 1 显示餐桌状态");
                            System.out.println("\t\t 2 预定餐桌");
                            System.out.println("\t\t 3 显示所有菜品");
                            System.out.println("\t\t 4 点餐服务");
                            System.out.println("\t\t 5 产看账单");
                            System.out.println("\t\t 6 结账");
                            System.out.println("\t\t 9 退出系统");

                            System.out.print("请输入你的选择：");
                            key = Utility.readString(1);
                            switch (key){
                                case "1":
                                    System.out.println("显示餐桌状态");
                                    break;
                                case "2":
                                    System.out.println("预定餐桌");
                                    break;
                                case "3":
                                    System.out.println("显示所有菜品");
                                    break;
                                case "4":
                                    System.out.println("点餐服务");
                                    break;
                                case "5":
                                    System.out.println("产看账单");
                                    break;
                                case "6":
                                    System.out.println("结账");
                                    break;
                                case "9":
                                    System.out.println("===退出系统===");
                                    loop=false;
                                    break;
                                default:
                                    System.out.println("输入错误 请查询输入");
                            }
                        }
                    }else {
                        System.out.println("====登录失败====");
                    }
                    break;
                case "2":
                    System.out.println("===退出系统===");
                    loop=false;
                    break;
                default:
                    System.out.println("输入错误 请查询输入");
            }
        }
    }
}
