/*
 * Copyright (c) luoZhiMin 2022.9.18.6.51.10
 */

package com.java.base.datastructure.linearstructure.linkedList;

import org.junit.jupiter.api.Test;

/**
 * Created by IntelliJ IDEA.
 * 线性结构 - 链表-[单/双]
 * @Author : 镜像
 * @create 2022/9/6 22:09 <br/><br/>
 *
 */
public class LearnLinkedList {

    @Test
    void testSingLinkedList(){
        //创建节点
        SingleLinkedList singleLinkedList = singleLinkedList();

        //创建列表 -> 加入节点
//        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        singleLinkedList.add(node1);
//        singleLinkedList.add(node2);
//        singleLinkedList.add(node3);

        //按照编号像加
//        singleLinkedList.sortNoAddNode(node3);
//        singleLinkedList.sortNoAddNode(node2);
//        singleLinkedList.sortNoAddNode(node1);

        singleLinkedList.showNode();

        System.out.println();

        //修改节点
        singleLinkedList.update(new SingleLinkedList.HeroNode(4,"关羽","关二爷"));

        //显示
        singleLinkedList.showNode();

        System.out.println();

        //删除节点
        singleLinkedList.delete(1);

        singleLinkedList.showNode();
    }

    @Test
    void groundTest(){
        //创建节点
        SingleLinkedList.HeroNode node1 = new SingleLinkedList.HeroNode(4,"孙悟空","齐天大圣");
        SingleLinkedList.HeroNode node2 = new SingleLinkedList.HeroNode(5,"猪八戒","天蓬元帅");
        //加入
        SingleLinkedList firstNode = new SingleLinkedList();
        firstNode.add(node1);
        firstNode.add(node2);

        int liveLength = InterviewQuestions.daily.getLiveLength();
        System.out.println("有效节点 "+ liveLength+" 个");

        SingleLinkedList secondNode = singleLinkedList();

        firstNode.node();
        System.out.println();
        secondNode.node();
        //最好不要用头节点 自己 返回一个节点 不要使用 静态 共享数据
        InterviewQuestions.daily.margeNode(firstNode.head, secondNode.head);
    }

    @Test
    void xinLang(){
        singleLinkedList();
        System.out.println(InterviewQuestions.XinLang.findLastNodeByIndex(SingleLinkedList.getHead(),3));
    }

    @Test
    void tencent(){
        SingleLinkedList singleLinkedList = singleLinkedList();
        System.out.println("source~~~");
        singleLinkedList.showNode();
        System.out.println("reserve~~~");
        InterviewQuestions.Tencent.reversal(SingleLinkedList.getHead());
        singleLinkedList.showNode();
    }

    @Test
    void baidu(){
        singleLinkedList();
        //stack 特点 先进后出
        InterviewQuestions.BaiDu.showReverseNodes(SingleLinkedList.getHead());
    }

    public static SingleLinkedList singleLinkedList (){
        SingleLinkedList.HeroNode node1 = new SingleLinkedList.HeroNode(1,"宋江","及时雨");
        SingleLinkedList.HeroNode node2 = new SingleLinkedList.HeroNode(2,"吴用","智多星");
        SingleLinkedList.HeroNode node3 = new SingleLinkedList.HeroNode(3,"林冲","豹子头");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.sortNoAddNode(node3);
        singleLinkedList.sortNoAddNode(node2);
        singleLinkedList.sortNoAddNode(node1);
        return singleLinkedList;
    }


    //------- 双链表

    @Test
    void doubleLinkedListTest(){
        System.out.println("双向链表测试～～");
        DoubleLinkedList.HeroNode node1 = new DoubleLinkedList.HeroNode(1,"宋江","及时雨");
        DoubleLinkedList.HeroNode node2 = new DoubleLinkedList.HeroNode(2,"吴用","智多星");
        DoubleLinkedList.HeroNode node3 = new DoubleLinkedList.HeroNode(3,"林冲","豹子头");
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        //add
        doubleLinkedList.add(node1);
        doubleLinkedList.add(node2);
        doubleLinkedList.add(node3);

        System.out.println("原始～～");
        //select
        doubleLinkedList.showDoubleNodes();
        System.out.println();

        DoubleLinkedList.HeroNode node4 = new DoubleLinkedList.HeroNode(3,"鲁班七号","射手");
        //update
        doubleLinkedList.update(node4);
        System.out.println("update~~");
        doubleLinkedList.showDoubleNodes();

        System.out.println();

        doubleLinkedList.delete(1);
        System.out.println("delete~");
        doubleLinkedList.showDoubleNodes();

        System.out.println();

        //sort no
        System.out.println("sort~");
        doubleLinkedList.sortNoAddNode(node1);
        doubleLinkedList.showDoubleNodes();
    }
}

