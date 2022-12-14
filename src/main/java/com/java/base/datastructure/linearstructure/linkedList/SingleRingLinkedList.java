/*
 * Copyright (c) luoZhiMin 2022.9.19.11.49.6
 */

package com.java.base.datastructure.linearstructure.linkedList;

import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * 单向环形链表 + 约瑟夫环(Joseph)
 * @Author : 镜像
 * @create 2022/9/19 23:49 <br><br>
 * <img src="https://cdn.nlark.com/yuque/0/2022/png/27601787/1663682790399-196adf85-7e74-499b-9970-027a000ecc22.png">
 */
public class SingleRingLinkedList {

    /*
        尾巴指向头(简单单向环形链表)
     */

    /**
     * 创建一个单向环形链表 当前没有编号
     */
    private Joseph.Child first = null;

    /**
     * 添加节点 构建成环形链表
     * @param nums 要添加多少个节点
     */
    public void add(int nums){
        if (nums<0){
            System.out.println("添加数据错误 当前 number = "+nums);
            return;
        }
        //辅助变量
        Joseph.Child currentChild = null;
        //构建
        for (int i = 1; i <= nums; i++) {
            //根据编号创建节点
            Joseph.Child child = new Joseph.Child(i);
            //如果是第一个的话 自我成环 自我指向
            if (i==1){
                first = child;
                first.setNext(first);//ring
                currentChild = first;//让currentChild指向第一个节点
            }else {
                currentChild.setNext(child);//指向下个节点
                child.setNext(first);//第一个节点 头
                currentChild = child;//当前节点后移
            }
        }
    }

    /**
     * 根据用户输入 计算出小孩出圈的顺序
     * @param start 表示从第几个开始
     * @param countNumber 表示数几下
     * @param nums 表示有多少个节点 <br><br>
     * <img src="https://cdn.nlark.com/yuque/0/2022/png/27601787/1663730653110-35adb28b-70f2-443d-9216-24af379d6543.png">
     */
    public void outOfLoop(int start,int countNumber,int nums){
        //对数据进行校验
        if (first == null || start < 1 || start > nums){
            System.out.println("参数输入有误～");
            return;
        }
        //创建辅助变量  helper是链表的最后一个节点
        Joseph.Child helper = first;
        while (helper.getNext() != first) {
            helper = helper.getNext();
        }
        //报数前移动 进行定位 先移动到start位置 开始移动(helper first) 移动k-1次(间隔)
        for (int i = 0; i < start-1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //开始报数 让 first 和 helper 同时开始移动 m-1次 然后出圈 (本身也算一次)
        while (helper != first) {
            //只有一个节点
            //开始移动k-1次
            for (int i = 0; i < countNumber - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //first 指向就是出圈的节点
            System.out.printf("小孩%d出圈\n", first.getNo());
            //后移
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩%d",first.getNo());
    }


    public void showChildren(){
        //判断链表是否为空 judge
        if (first==null){
            System.out.println("当前链表为空～");
            return;
        }

        Joseph.Child temp = first;
        while (true){
            System.out.printf("小孩的编号%d\n",temp.getNo());
            if (temp.getNext()==first){
                break;
            }
            //后移
            temp = temp.getNext();
        }
    }
}

/**
 * 约瑟夫 <br><br>
 * 单向环形链表创建<br><br>
 * <img src="https://cdn.nlark.com/yuque/0/2022/png/27601787/1663683110064-01709e73-f36b-427a-a885-03861ef6d465.png"> <br><br>
 */
class Joseph{

    /**
     * 创建节点
     */
    @Data
    static class Child {
        //编号
        private int no;
        //下一个节点
        private Child next;

        public Child(int no) {
            this.no = no;
        }
    }
}
