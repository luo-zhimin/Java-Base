/*
 * Copyright (c) luoZhiMin 2022.10.4.9.11.33
 */

package com.java.base.datastructure.hash;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 * 哈希表
 * @Author : 镜像
 * @create 2022/10/4 21:11
 */
public class HashTableDemo {

    /*
        有一个公司,当有新的员工来报道时,要求将该员工的信息加入(id,性别,年龄,名字,住址..),当输入该员工的 id 时, 要求查找到该员工的 所有信息.

        1) 不使用数据库,,速度越快越好=>哈希表(散列)
        2) 添加时，保证按照 id 从低到高插入 [如果 id 不是从低到高插入，但要求各条链表仍是从低到 高，怎么解决?]
        3) 使用链表来实现哈希表, 该链表不带表头
     */

    Scanner scanner = new Scanner(System.in);

    /**
     * <img src="https://cdn.nlark.com/yuque/0/2022/png/27601787/1664979871907-fd59f3bc-43d0-446a-b93e-baaeb4bad702.png"">
     */
    @Test
    void hash(){
//        Hashtable
        boolean loop = true;
        HashTab hashTab = new HashTab(7);
        String key =" ";
        while (loop){
            System.out.println("add：添加雇员");
            System.out.println("show：展示雇员");
            System.out.println("find：查找雇员");
            System.out.println("delete：删除雇员");
            System.out.println("exit：退出系统");
            System.out.print("请输入你的选择：");
            key = scanner.next();
            switch (key){
                case "add":
                    System.out.print("输入id：");
                    int id = scanner.nextInt();
                    System.out.print("输入name：");
                    String name = scanner.next();
                    Emp emp = new Emp(id, name);
//                    hashTab.add(emp);
                    hashTab.addSort(emp);
                    break;
                case "show":
                    hashTab.show();
                    break;
                case "find":
                    System.out.print("输入您要找到的雇员的id：");
                    id = scanner.nextInt();
                    hashTab.find(id);
                    break;
                case "delete":
                    System.out.print("输入您要删除的雇员的id：");
                    id = scanner.nextInt();
                    hashTab.delete(id);
                    break;
                case "exit":
                    loop = false;
                    break;
            }
        }
    }

    public static void main(String[] args) {
        HashTableDemo tableDemo = new HashTableDemo();
        tableDemo.hash();
    }
}

/**
 * 雇员
 */
class Emp{

    public int id;

    public String name;

    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class EmpLinkedList{
    //头指针 没有 头节点 直接指向 第一个元素
    private Emp head;

    /**
     * 添加雇员
     * 1.当添加雇员时id自增长
     */
    public void add(Emp emp){
        //如果是添加第一个雇员
        if (head==null){
            head = emp;
            return;
        }
        //不是添加第一个雇员
        Emp current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = emp;
    }

    public void addSort(Emp emp){
        //如果是添加第一个雇员
        if (head==null){
            head = emp;
            return;
        }
        //不是添加第一个雇员 需要对比现在这个链表的序号
        Emp current = head;
        boolean flag = false;
        while (current.next != null) {
            if (current.id>emp.id){
                //先进来比后面大
                break;
            }
            if (current.id==emp.id){
                flag = true;
                break;
            }

            current = current.next;
        }

        if (flag){
            System.out.println("该编号已经存在 编号=" + emp.id);
            return;
        }

        if (current.id<emp.id){
            //先进来比后面大
            current.next = emp;
        }else {
            emp.next = current;
            head = emp;
        }
    }

    public void show(int orderNo){
        if (head==null){
            System.out.println("第"+(orderNo+1)+"链表为空");
            return;
        }
        Emp current = head;
        while (true) {
            System.out.printf("雇员->编号[{%d}]-名字[{%s}]\t", current.id, current.name);
            if (current.next == null){
                break;
            }
            current = current.next;
        }
        System.out.println();
    }

    public Emp find(int order){

        //链表为空
        if (head==null){
            return null;
        }
        //查找
        Emp current = head;
        while (true){
            if (current.id == order){
                break;
            }
            if (current.next == null){
                current = null;
                break;
            }
            current = current.next;
        }

        return current;
    }

    public void delete(int order){
        //链表为空
        if (head==null){
            System.out.println("链表为空 ～ 不可以操作 ");
            return;
        }
        //查找
        Emp current = head;
        boolean flag = false;
        while (true){
            //找到
            if (current.id == order){
                flag = true;
                break;
            }
            if (current.next == null){
                break;
            }
            current = current.next;
        }

        if (flag){
            head =  current.next;
            return;
        }
        System.out.printf("没有找到编号是[{%d}]的雇员 无法删除～",order);
    }
}

/*pu * 哈希表 - 散列 -管理多条链表
 */
class HashTab{

    private EmpLinkedList[] empLinkedListArr;

    private int size;

    public HashTab(int size) {
        this.size = size;
        //初始化链表
        this.empLinkedListArr = new EmpLinkedList[size];
        //需要分别初始化所有的链表
        for (int i = 0; i < size; i++) {
            empLinkedListArr[i] = new EmpLinkedList();
        }
    }

    public void add(Emp emp){
        //根据id得到该员工应该添加到哪个位置
        int orderNo = hashFun(emp.id);
        //添加到对应的链表中
        empLinkedListArr[orderNo].add(emp);
    }

    public void addSort(Emp emp){
        //根据id得到该员工应该添加到哪个位置
        int orderNo = hashFun(emp.id);
        //添加到对应的链表中
        empLinkedListArr[orderNo].addSort(emp);
    }

    public void show(){
        //遍历hash表
        for (int i = 0; i < size; i++) {
            empLinkedListArr[i].show(i);
        }
    }

    public void find(int order){
        //使用散列函数 确定 位置
        int orderNo = hashFun(order);
        Emp emp = empLinkedListArr[orderNo].find(order);
        if (emp!=null){
            System.out.printf("在[{%d}]中找到元素[{%s}]",(orderNo+1),emp.id);
        }else {
            System.out.println("在 hashTab中没有找到该雇员");
        }
    }

    public void delete(int order){
        int orderNo = hashFun(order);
        empLinkedListArr[orderNo].delete(order);
    }

    /**
     * 散列函数 使用一个取膜法
     */
    private int hashFun(int id){
        return id % size;
    }
}

