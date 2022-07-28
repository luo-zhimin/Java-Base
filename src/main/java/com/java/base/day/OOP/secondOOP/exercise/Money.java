/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.OOP.secondOOP.exercise;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : luozhimin
 * @create 2022/6/3 20:30
 */
public class Money {
    public static void main(String[] args) {

        List<SmallChange> smallChanges = new ArrayList<>();
        double money = 0;
        double balance = 0;
        boolean loop = true;
        do {
            System.out.println("================零钱通=====================");
            System.out.println("1.零钱通明细");
            System.out.println("2.收益入账");
            System.out.println("3.消费");
            System.out.println("4.退出");
            Scanner scanner = new Scanner(System.in);
            System.out.println("请选择（1-4）:");
            //exit y/n
            char charAt = scanner.next().charAt(0);
            switch (charAt) {
                case '1':
                    System.out.println("================零钱通明细=====================");
                    if (smallChanges.size()>0){
                        smallChanges.forEach(smallChange -> {
                            System.out.println(smallChange.getName() + "\t" + smallChange.getMon() + "\t" + smallChange.getTime() + "\t" + "余额：" + smallChange.getBalance());
                        });
                    }
                    break;
                case '2':
                    System.out.println("================收益入账=====================");
                    System.out.println("收益入账金额：");
                    money = scanner.nextDouble();
                    balance += money;
                    SmallChange s = new SmallChange("收益入账", money, new Date(), balance,true);
                    smallChanges.add(s);
                    break;
                case '3':
                    System.out.println("================消费=====================");
                    System.out.println("消费金额：");
                    money = scanner.nextDouble();
                    if (balance - money < 0) {
                        System.out.println("消费失败金额不足，您需要在"+"0-"+balance+"之内消费");
                        break;
                    }
                    balance -= money;
                    SmallChange m = new SmallChange("消费", money, new Date(), balance,false);
                    smallChanges.add(m);
                    break;
                case '4':
                    System.out.println("你确定要退出吗（y/n）");
                    while (true) {
                        char charAt2 = scanner.next().charAt(0);
                        if ('y' == charAt2) {
                            System.out.println("success exit");
                            loop = false;
                            break;
                        }else if ('n'==charAt2){
                            break;
                        }
                    }
                    break;
                default:
                    System.out.println("please print success number");
            }
        } while (loop);
        System.out.println("..exit...");
    }
}

class SmallChange {
    private String name;
    private double money;
    private Date date;
    private double balance;

    private boolean expand = true;
    private String time;

    public String getMon() {
        return mon;
    }

    public void setMon(String mon) {
        this.mon = mon;
    }

    private String mon;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
        if (this.expand){
            setMon("+"+this.money);
        }else {
            setMon("-"+this.money);
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        setTime(simpleDateFormat.format(date));
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public SmallChange(String name, double money, Date date, double balance,boolean expand) {
        this.name = name;
        this.expand = expand;
        setMoney(money);
        setDate(date);
        setBalance(balance);
        System.out.println(this.name + "\t" + this.mon + "\t" + this.time + "\t" + "余额：" + this.balance);
    }
}