package com.java.base.day.collectionAndMap.collection.source.list;

import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 志敏.罗
 * @create 2022/6/19 00:38
 */
public class LinkedListSourceAnalysis {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        /*
         * LinkedList扩容机制源码分析
         *  1）LinkedList底层实现了双向链表和双端队列特点
         *  2）可以添加任意元素（元素可以重复），包括null
         *  3）线程不安全，没有实现同步
         * 底层操作机制：
         *  1）LinkedList底层维护了一个双向链表。
         *  2）LinkedList中维护了两个属性first和last分别指向 首节点和尾节点
         *  3）每个节点（Node对象），里面又维护了prev、next、item三个属性，其中通过prev指向前一个，通过next指向后一个节点。最终实现双向链表.
         *  4）所以LinkedList的元素的添加和删除，不是通过数组完成的，相对来说效率较高。
         *  5 模拟一个简单的双向链表
         */

        //node 分析
        Node firstNode = new Node("jack");
        Node secondNode = new Node("tom");
        Node thirteenNode = new Node("jerry");

        //连接三个节点 形成双向链表
        //jack->tom->jerry
        firstNode.next = secondNode;
        secondNode.next = thirteenNode;
        //jerry->tom->jack
        thirteenNode.prev = secondNode;
        secondNode.prev = firstNode;
        //双向链表的头
        Node first = firstNode;
        Node last = thirteenNode;

        show(first,last);

        //双向链表 添加元素 tom -- jerry之间 插入 元素
        Node item = new Node("item");
        //.....tom->item->jerry....
        item.next = thirteenNode;
        item.prev = secondNode;
        //.....jerry->item->tom....
        secondNode.next = item;
        thirteenNode.prev = item;
        show(first,last);
    }

    private static void beforeLastToFirst(Node last){
        System.out.println("==从尾到头遍历==");
        while (last != null) {
            //输出last
            System.out.println(last);
            //找他的上一个元素
            last = last.prev;
        }
    }

    private static void beforeFirstToLast(Node first){
        System.out.println("==从头到尾遍历==");
        while (first != null) {
            //输出first
            System.out.println(first);
            //找他的下一个元素
            first = first.next;
        }
    }

    private static void show(Node first,Node last){
        System.out.println("===show before===");
        //从头到尾遍历
        beforeFirstToLast(first);
        //从尾到头遍历
        beforeLastToFirst(last);
        System.out.println("===show end===");
    }
}

/**
 * 定一个node类 表示双向链表的一个节点
 */
class Node {
    protected Object item;//存放数据的地方
    protected Node next;//指向下个一个节点
    protected Node prev;//指向上一个节点

    public Node(Object item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "Node{" +
                "item=" + item +
                '}';
    }
}
class LinkedListDetail{
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
//        for (int i = 0; i < 10; i++) {
//            linkedList.add(i);
//        }
        linkedList.add("LinkedList");
        linkedList.add(2);
        //默认删除第一个
        linkedList.remove();
        System.out.println(linkedList);
    }
}