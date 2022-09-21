/*
 * Copyright (c) luoZhiMin 2022.9.18.6.53.18
 */

package com.java.base.datastructure.linearstructure.linkedList;

import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * 双向链表
 * @Author : 镜像
 * @create 2022/9/18 18:53 <br><br>
 * <img src="https://cdn.nlark.com/yuque/0/2022/png/27601787/1663511315739-29e1caa7-1274-46c3-ae71-951d3ae0e40e.png">
 */
public class DoubleLinkedList {

    /*
        单向链表的缺点分析:
            1) 单向链表，查找的方向只能是一个方向，而双向链表可以向前或者向后查找。
            2) 单向链表不能自我删除，需要靠辅助节点 ，而双向链表，则可以自我删除，所以前面我们单链表删除 时节点，总是找到 temp,temp 是待删除节点的前一个节点（不然就会断裂）

        理解一定要画图 !!!
        双向链表
            有俩个域(next[after] pre[before])
     */

    private HeroNode head = new HeroNode(0,"","");

    /**
     * 返回头节点
     */
    public HeroNode getHead() {
        return head;
    }

    /**
     * 添加
     * @param node 新的节点
     */
    public void add(HeroNode node){
        HeroNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        //need add 形成双向链表
        temp.next = node;
        node.pre = temp;
    }


    public void sortNoAddNode(HeroNode node){
        HeroNode temp = head;
        boolean flag = false;//英雄添加的编号是否存在
        while (temp.next!=null) {
            //在列表的最后
            //下一个节点比当前添加的 编号 要大 正常添加
            if (temp.next.no > node.no) {
                break;
            }
            if (temp.next.no == node.no) {
                flag = true;
                break;
            }
            //向下移动
            temp = temp.next;
        }

        if (flag) {
            System.out.println("该编号已经存在 编号=" + node.no);
            return;
        }

        //上一个节点
        node.pre = temp.pre;
        //插入列表 插入下一个节点中
        node.next = temp.next;
        temp.next = node;
    }

    /**
     * (双向链表)删除节点
     * 对于双向列表来说 不需要找到要删除的前一个节点 找到后可以自我删除
     * @param no 编号
     */
    public void delete(int no) {
        if (head.next==null){
            System.out.println("列表为空 不可以删除～");
            return;
        }

        //被删除的节点 无引用 会被gc回收掉
        HeroNode temp = head.next;//真正要删除的节点
        boolean flag = false;//表示是否找到
        while (temp!=null) {
            if (temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }

        //找到
        if (flag) {
            //改next的指向 (他(待删除)的下一个节点) 把改删除节点的引用改掉
            //修改他的指向 他的前一个节点的下一个节点 和 他后一个节点的前一个节点
//            temp.next = temp.next.next;
            temp.pre.next = temp.next;
            //todo 假如要删除的节点是最后一个 temp是最后一个 temp.next 是空 最后一个节点不需要指向
            if (temp.next!=null) {
                temp.next.pre = temp.pre;
            }
            return;
        }

        System.out.println("无法删除，没有找到编号为" + no + "的节点\n");
    }

    /**
     * 修改节点 编号不可以修改
     * 单向链表和双向链表一样
     * @param node 节点
     */
    public void update(HeroNode node) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //根据编号 找到修改的节点
        HeroNode temp = head.next;
        boolean flag = false;//表示是否找到
        while (temp!=null) {
            if (temp.no == node.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }

        //找到
        if (flag) {
            temp.name = node.name;
            temp.nickedName = node.nickedName;
            return;
        }

        System.out.println("没有找到编号为" + node.no + "的节点\n");
    }

    /**
     * 链表输出
     */
    public void showDoubleNodes(){
        if (head.next==null){
            System.out.println("链表为空");
            return;
        }
        //创建临时变量
        HeroNode temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    /**
     * 定义节点 每一个HeroNode对象 就是一个节点
     */
    @Data
    static class HeroNode {

        public int no;

        public String name;

        public String nickedName;

        public HeroNode next;//指向下一个节点

        public HeroNode pre;//指向前一个节点

        /**
         * 构造器
         * @param no         编号
         * @param name       英雄名字
         * @param nickedName 英雄名称
         */
        public HeroNode(int no, String name, String nickedName) {
            this.no = no;
            this.name = name;
            this.nickedName = nickedName;
        }

        @Override
        public String toString() {
            return "HeroNode{" +
                    "no=" + no +
                    ", name='" + name + '\'' +
                    ", nickedName='" + nickedName + '\'' +
                    '}';
        }
    }
}
