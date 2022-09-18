/*
 * Copyright (c) luoZhiMin 2022.9.6.10.9.0
 */

package com.java.base.datastructure.linearstructure;

import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * Created by IntelliJ IDEA.
 * 线性结构 - 链表
 * @Author : 镜像
 * @create 2022/9/6 22:09 <br/><br/>
 *
 * <img src="https://cdn.nlark.com/yuque/0/2022/png/27601787/1662967597505-c4c11b95-6485-492c-86c3-dd8836fb8e82.png">
 */
public class LearnLinkedList {

    /*
        链表是有序的列表
        链表是以节点的方式来存储,是链式存储
        每个节点包含 data 域， next 域：指向下一个节点.
        如图(单链表示意图)：发现链表的各个节点不一定是连续存储.
        链表分带头节点的链表和没有头节点的链表，根据实际的需求来确定

        单链表(带头结点) 逻辑结构示意图
     */

    /**
     * <img src="https://cdn.nlark.com/yuque/0/2022/png/27601787/1662968091158-6791d785-4129-4ed2-8a71-182e5b3a8f91.png">
     */
    private void show(){}

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
}

/**
 * 模拟单链表
 */
class SingleLinkedList{

    /*
        使用带 head 头的单向链表实现 –水浒英雄排行榜管理完成对英雄人物的增删改查操作
        俩种方式
            第一种方法在添加英雄时，直接添加到链表的尾部
            第二种方式在添加英雄时，根据排名将英雄插入到指定位置(如果有这个排名，则添加失败，并给出提示)
     */

    //初始化一个头节点 不存放具体数据
    public static HeroNode head = new HeroNode(0,"","");

    public static HeroNode getHead() {
        return head;
    }

    /**
     * 添加节点到单向列表--第一种方法在添加英雄时，直接添加到链表的尾部
     * 不考虑编号顺序情况下
     * 找到当前列表的最后节点 将这个节点的next 指向新的节点
     * @param heroNode 节点
     */
    public void add(HeroNode heroNode){
        //需要一个辅助变量
        HeroNode temp = head;
        //遍历列表找到最后一个节点
        while (true){
            //下一个元素咩有
            if (temp.next==null) {
                break;
            }
            //如果没有找到最后 就将temp后移动
            temp = temp.next;
        }
        //退出循环时候就是最后
        temp.next = heroNode;
    }

    /**
     * 第二种方式在添加英雄时，根据排名将英雄插入到指定位置(如果有这个排名，则添加失败，并给出提示)
     * @param node 新的节点
     */
    public void sortNoAddNode(HeroNode node){
        HeroNode temp = head;
        //因为是单链表 temp 是位于插入位置的前一个节点
        boolean flag = false;//英雄添加的编号是否存在
        while (true){
            //在列表的最后
            if (temp.next==null){
                break;
            }
            //下一个节点比当前添加的 编号 要大 正常添加
            if (temp.next.no > node.no){
                break;
            }
            if (temp.next.no == node.no){
                flag = true;
                break;
            }
            //向下移动
            temp = temp.next;
        }

        if (flag){
            System.out.println("该编号已经存在 编号="+node.no);
            return;
        }

        //插入列表 插入下一个节点中
        node.next = temp.next;
        temp.next=node;
    }

    /**
     * 修改节点 编号不可以修改
     * @param node 节点
     */
    public void update(HeroNode node){
        if (head.next==null){
            System.out.println("链表为空");
            return;
        }
        //根据编号 找到修改的节点
        HeroNode temp = head.next;
        boolean flag = false;//表示是否找到
        while (true){
            //链表遍历完
            if (temp==null){
                break;
            }
            if (temp.no==node.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }

        //找到
        if (flag){
            temp.name = node.name;
            temp.nickedName = node.nickedName;
            return;
        }

        System.out.println("没有找到编号为"+node.no+"的节点\n");
    }

    /**
     * 删除节点
     * 找到需要删除的前一个节点
     * 改next的指向 (他(待删除)的下一个节点)
     * @param no 编号
     */
    public void delete(int no){
        //被删除的节点 无引用 会被gc回收掉
        HeroNode temp = head;
        boolean flag = false;//表示是否找到
        while (true) {
            //链表遍历完
            if (temp.next == null) {
                break;
            }
            if (temp.next.no==no){
                flag = true;
                break;
            }
            temp = temp.next;
        }

        //找到
        if (flag){
            //改next的指向 (他(待删除)的下一个节点) 把改删除节点的引用改掉
            temp.next = temp.next.next;
            return;
        }

        System.out.println("无法删除，没有找到编号为"+no+"的节点\n");
    }


    /**
     * 显示所有的元素
     */
    public void showNode(){
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        while (true){
            if (temp==null){
                break;
            }
            System.out.println(temp);
            //后移动
            temp = temp.next;
        }
    }

    public HeroNode node(){
        return getHead();
    }

    /**
     * 定义节点 每一个HeroNode对象 就是一个节点
     */
    static class HeroNode{

        public int no;

        public String name;

        public String nickedName;

        public HeroNode next;//指向下一个节点

        /**
         * 构造器
         * @param no 编号
         * @param name 英雄名字
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

/**
 * 面试题
 */
class InterviewQuestions{

    /**
     * 平常 <br>
     * 求单链表中有效节点的个数 (如果是带头结点的链表，需求不统计头节点)
     */
    static class daily {

        /**
         * 带头节点链表统计有效节点个数
         * @return 有效个数
         */
        public static int getLiveLength() {

            int length = 0;
            SingleLinkedList.HeroNode temp = SingleLinkedList.head;
            while (true) {
                //空链表
                if (temp.next == null) {
                    break;
                }
                if (temp.next != null) {
                    length++;
                }
                temp = temp.next;
            }
            return length;
        }

        public static void margeNode(SingleLinkedList.HeroNode firstNode, SingleLinkedList.HeroNode secondNode){
            //合并两个有序的单链表，合并之后的链表依然有序
            //先合并 在排序
            SingleLinkedList.HeroNode tempNode = firstNode.next;
            while (true){
                if (tempNode.next==null) {
                    break;
                }
                tempNode = tempNode.next;
            }
            tempNode.next = secondNode.next;
            System.out.println();
            //sort
            while (true) {
                if (tempNode.next ==null){break;}
                System.out.println(tempNode);
                tempNode = tempNode.next;
            }
        }
    }

    /**
     * 百度 <br>
     * 从尾到头打印单链表【百度，要求方式1：反向遍历(反转+遍历[反转会破坏原来的数据结构])。方式2：Stack栈(先进后出)】
     */
    static class BaiDu{
        //方式一：(反向遍历)反转 + 遍历 ==> reverse + show
        //二 栈
        public static void showReverseNodes(SingleLinkedList.HeroNode node){
            //空链表
            if (node.next==null){
                return;
            }
            //创建一个栈 将各个节点加入栈中
            Stack<SingleLinkedList.HeroNode> heroNodeStack = new Stack<>();
            SingleLinkedList.HeroNode currentNode = node.next;
            //将链表的所有节点压入栈中
            while (currentNode!=null){
                heroNodeStack.push(currentNode);
                currentNode = currentNode.next;
            }
            //出栈
            while (heroNodeStack.size()>0){
                System.out.println(heroNodeStack.pop());
            }
        }

        /**
         * Stack 测试
         */
        static class StackDemo{

            public static void main(String[] args) {
                Stack<String> stack = new Stack<>();
                //入栈
                stack.add("jack");
                stack.add("john");
                stack.add("switch");

                //出栈
                while (stack.size()>0){
                    //pop 就是将栈顶取出
                    System.out.println(stack.pop());
                }
            }
        }
    }

    /**
     * 新浪<br>
     * 查找单链表中的倒数第 k 个结点
     */
    static class XinLang{
        /*
            接收head节点 和 index(倒数第n个节点)
            先把链表重头到尾 遍历
            先得到有效节点个数
            遍历 查找[size(有效数组长度)-index]
         */

        /**
         * 查找倒数的第n个节点
         * @param node 头节点
         * @param index 找第几个
         * @return 找到的节点
         */
        public static SingleLinkedList.HeroNode findLastNodeByIndex(SingleLinkedList.HeroNode node, int index){
            //空链表
            if (node.next==null){
                return null;
            }
            //第一个遍历得到列表的长度(节点的个数)
            int size = daily.getLiveLength();
            //第二次遍历到 size-index 就是倒数第n个节点
            //check index
            if (index<=0 || index>size){
                System.out.println("index="+index+" size="+size);
                return null;
            }
            //辅助变量
            SingleLinkedList.HeroNode temp = node.next;
            for (int i = 0; i < size-index; i++) {
                temp = temp.next;
            }
            return temp;
        }
    }

    /**
     * 腾讯 <br>
     * 单链表反转
     */
    static class Tencent{

        /**
         * 链表反转
         * @param headNode 头节点 <br><br>
         * <img src="https://cdn.nlark.com/yuque/0/2022/png/27601787/1663411809969-bd84850d-2fd4-4dad-a236-3d1bd3c4fcd8.png">
         */
        public static void reversal(SingleLinkedList.HeroNode headNode){
            //如果是空链表 或者 链表只有一个节点不需要反转
            if (headNode.next==null || headNode.next.next==null){
                return;
            }
            //定义一个辅助变量 来反转 原来的链表
            SingleLinkedList.HeroNode currentNode = headNode.next;
            //指向当前节点的下个节点 (单链表) 不然节点就会断链 currentNode
            SingleLinkedList.HeroNode next = null;
            //定义一个反转节点
            SingleLinkedList.HeroNode reverseNode = new SingleLinkedList.HeroNode(0, "", "");
            //遍历原始节点 从头到尾 把节点从currentNode里面摘出 放到 reverseNode的最前端 需要节点关联
            while (currentNode!=null){
                //临时保留currentNode的当前节点的下一个节点
                next = currentNode.next;
                //将currentNode的下个节点指向 新的链表的最前端
                currentNode.next = reverseNode.next; // 反转
                //将current连接到新的链表上 !!!!成环
                reverseNode.next = currentNode;
                //后移动
                currentNode = next;
            }
            //连接 将 head.next 指向 reverse.next
            headNode.next = reverseNode.next;
        }
    }
}