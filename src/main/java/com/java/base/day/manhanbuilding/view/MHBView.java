/*
 * Copyright (c) luoZhiMin 2022.8.8.5.11.58
 */

package com.java.base.day.manhanbuilding.view;

import com.java.base.day.manhanbuilding.service.BillService;
import com.java.base.day.manhanbuilding.service.DiningTableService;
import com.java.base.day.manhanbuilding.service.MenuService;
import com.java.base.day.manhanbuilding.service.UserService;
import com.java.base.day.socketProject.util.Utility;
import org.junit.platform.commons.util.StringUtils;

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

    /*
        todo 事务考虑 threadLocal 或者 框架 transactional
        可以进行拓展 菜单 多表考虑 视图.... 多表查询 多表名字可以不一样
        但是需要注意起别名
     */

    private static final UserService userService;

    private static final DiningTableService diningTableService;

    private static final MenuService menuService;

    private static final BillService billService;

    static {
        userService = new UserService();
        diningTableService = new DiningTableService();
        menuService = new MenuService();
        billService = new BillService();
    }


    /**
     * 控制是否结束
     */
    private boolean loop = true;

    /**
     * 接收用户的输入
     */
    private String key = "";

    /**
     * 文本菜单
     */
    public void menuView() {
        int tableId;
        char save;
        //一级菜单
        while (loop) {
            System.out.println("=======欢迎来到满汉楼=======");
            System.out.println("\t\t 1 登陆系统");
            System.out.println("\t\t 2 退出系统");
            System.out.print("请输入你的选择：");

            key = Utility.readString(1);

            switch (key) {
                case "1":
                    System.out.print("请输入员工号：");
                    String userName = Utility.readString(8);
                    System.out.print("请输入密 码：");
                    String password = Utility.readString(10);
                    //check login ..... userService
                    //successful login display second menu or fail exit
                    if (userService.checkUser(userName, password)) {
                        while (loop) {
                            System.out.println("=======欢迎(用户" + userName + ")登陆系统=======");

                            System.out.println("\t\t 1 显示餐桌状态");
                            System.out.println("\t\t 2 预定餐桌");
                            System.out.println("\t\t 3 显示所有菜品");
                            System.out.println("\t\t 4 点餐服务");
                            System.out.println("\t\t 5 查看账单");
                            System.out.println("\t\t 6 结账");
                            System.out.println("\t\t 9 退出系统");

                            System.out.print("请输入你的选择：");
                            key = Utility.readString(1);
                            switch (key) {
                                case "1":
                                    diningTableService.getTableStates();
                                    System.out.println("=====显示完毕=====");
                                    break;
                                case "2":
                                    System.out.println("=====预定餐桌=====");
                                    System.out.print("请选择要预定的餐桌编号(-1退出)");
                                    //check table
                                    tableId = Utility.readInt(3);
                                    if (tableId==-1){
                                        System.out.println("====取消预定====");
                                        break;
                                    }
                                    if (!diningTableService.checkTableIsUse(tableId)){
                                        break;
                                    }
                                    save = Utility.readConfirmSelection();
                                    if (save=='N'){
                                        System.out.println("====取消预定====");
                                        break;
                                    }
                                    System.out.print("预定人名字：");
                                    String name = Utility.readString(5);
                                    System.out.print("预定人电话：");
                                    String tel = Utility.readString(13);
                                    System.out.println(diningTableService.updateState(tableId,name,tel)?
                                            "======预定成功======":"======预定失败======");

                                    break;
                                case "3":
                                    menuService.showMenus();
                                    System.out.println("=====显示完毕=====");
                                    break;
                                case "4":
                                    System.out.println("=====点餐服务=====");
                                    //check table menu
                                    System.out.print("请选择点餐的桌号(-1退出)");
                                    tableId = Utility.readInt(3);
                                    if (tableId==-1){
                                        System.out.println("====取消点餐====");
                                        break;
                                    }
                                    //check table
                                    if (!diningTableService.checkTable(tableId,1)) {
                                        break;
                                    }

                                    System.out.print("请选择菜品的编号(-1退出)");
                                    int menuId = Utility.readInt(3);
                                    if (menuId==-1){
                                        System.out.println("====取消点餐====");
                                        break;
                                    }
                                    if (menuService.checkMenu(menuId)) {
                                        System.out.println("该菜单不存在~");
                                        break;
                                    }
                                    System.out.print("请选择菜名的数量(-1退出)");
                                    int nums = Utility.readInt(3);
                                    if (nums==-1){
                                        System.out.println("====取消点餐====");
                                        break;
                                    }
                                    System.out.print("确认是否要点这个菜(Y/N) ");
                                    save = Utility.readChar();
                                    if (save=='N'){
                                        System.out.println("====取消点餐====");
                                        break;
                                    }
                                    //更新 table 插入 bill
                                    if (billService.saveBill(menuId,nums,tableId)){
                                        System.out.println("=====点餐成功=====");
                                    }
                                    break;
                                case "5":
                                    System.out.println("查看账单");
                                    //billService.showBills();
                                    billService.showBillResponse(); // 拓展 多表
                                    System.out.println("=====显示完毕=====");
                                    break;
                                case "6":
                                    System.out.println("=====结账服务======");
                                    //update bill table
                                    System.out.print("请选择要结账的餐桌编号(-1退出)：");
                                    tableId = Utility.readInt(3);
                                    if (tableId==-1){
                                        System.out.println("====取消结账====");
                                        break;
                                    }
                                    if (!diningTableService.checkTable(tableId,2)){
                                        break;
                                    }
                                    System.out.print("结账方式(现金/支付宝/微信)回车表示退出：");
                                    String state = Utility.readString(3,"");
                                    if (StringUtils.isBlank(state)){
                                        System.out.println("====取消结账====");
                                        break;
                                    }
                                    System.out.print("确认是否结账(Y/N)：");
                                    save = Utility.readChar();
                                    if (save=='N'){
                                        System.out.println("====取消结账====");
                                        break;
                                    }
                                    if (billService.checkOut(tableId,state)) {
                                        System.out.println("===结账完成===");
                                    }
                                    break;
                                case "9":
                                    System.out.println("===退出系统===");
                                    loop = false;
                                    break;
                                default:
                                    System.out.println("输入错误 请查询输入");
                            }
                        }
                    } else {
                        System.out.println("====登录失败====");
                    }
                    break;
                case "2":
                    System.out.println("===退出系统===");
                    loop = false;
                    break;
                default:
                    System.out.println("输入错误 请查询输入");
            }
        }
    }
}
