/*
 * Copyright (c) luoZhiMin 2022.9.18.6.52.11
 */

package com.java.base.datastructure.linearstructure.linkedList;

import java.util.Stack;

/**
 * 面试题
 */
public class InterviewQuestions {

    /**
     * 平常 <br>
     * 求单链表中有效节点的个数 (如果是带头结点的链表，需求不统计头节点)
     */
    static class daily {

        /**
         * 带头节点链表统计有效节点个数
         *
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

        public static void margeNode(SingleLinkedList.HeroNode firstNode, SingleLinkedList.HeroNode secondNode) {
            //合并两个有序的单链表，合并之后的链表依然有序
            //先合并 在排序
            SingleLinkedList.HeroNode tempNode = firstNode.next;
            while (true) {
                if (tempNode.next == null) {
                    break;
                }
                tempNode = tempNode.next;
            }
            tempNode.next = secondNode.next;
            System.out.println();
            //sort
            while (true) {
                if (tempNode.next == null) {
                    break;
                }
                System.out.println(tempNode);
                tempNode = tempNode.next;
            }
        }
    }

    /**
     * 百度 <br>
     * 从尾到头打印单链表【百度，要求方式1：反向遍历(反转+遍历[反转会破坏原来的数据结构])。方式2：Stack栈(先进后出)】
     */
    static class BaiDu {
        //方式一：(反向遍历)反转 + 遍历 ==> reverse + show
        //二 栈
        public static void showReverseNodes(SingleLinkedList.HeroNode node) {
            //空链表
            if (node.next == null) {
                return;
            }
            //创建一个栈 将各个节点加入栈中
            Stack<SingleLinkedList.HeroNode> heroNodeStack = new Stack<>();
            SingleLinkedList.HeroNode currentNode = node.next;
            //将链表的所有节点压入栈中
            while (currentNode != null) {
                heroNodeStack.push(currentNode);
                currentNode = currentNode.next;
            }
            //出栈
            while (heroNodeStack.size() > 0) {
                System.out.println(heroNodeStack.pop());
            }
        }

        /**
         * Stack 测试
         */
        static class StackDemo {

            public static void main(String[] args) {
                Stack<String> stack = new Stack<>();
                //入栈
                stack.add("jack");
                stack.add("john");
                stack.add("switch");

                //出栈
                while (stack.size() > 0) {
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
    static class XinLang {
        /*
            接收head节点 和 index(倒数第n个节点)
            先把链表重头到尾 遍历
            先得到有效节点个数
            遍历 查找[size(有效数组长度)-index]
         */

        /**
         * 查找倒数的第n个节点
         *
         * @param node  头节点
         * @param index 找第几个
         * @return 找到的节点
         */
        public static SingleLinkedList.HeroNode findLastNodeByIndex(SingleLinkedList.HeroNode node, int index) {
            //空链表
            if (node.next == null) {
                return null;
            }
            //第一个遍历得到列表的长度(节点的个数)
            int size = daily.getLiveLength();
            //第二次遍历到 size-index 就是倒数第n个节点
            //check index
            if (index <= 0 || index > size) {
                System.out.println("index=" + index + " size=" + size);
                return null;
            }
            //辅助变量
            SingleLinkedList.HeroNode temp = node.next;
            for (int i = 0; i < size - index; i++) {
                temp = temp.next;
            }
            return temp;
        }
    }

    /**
     * 腾讯 <br>
     * 单链表反转
     */
    static class Tencent {

        /**
         * 链表反转
         *
         * @param headNode 头节点 <br><br>
         *                 <img src="https://cdn.nlark.com/yuque/0/2022/png/27601787/1663411809969-bd84850d-2fd4-4dad-a236-3d1bd3c4fcd8.png">
         */
        public static void reversal(SingleLinkedList.HeroNode headNode) {
            //如果是空链表 或者 链表只有一个节点不需要反转
            if (headNode.next == null || headNode.next.next == null) {
                return;
            }
            //定义一个辅助变量 来反转 原来的链表
            SingleLinkedList.HeroNode currentNode = headNode.next;
            //指向当前节点的下个节点 (单链表) 不然节点就会断链 currentNode
            SingleLinkedList.HeroNode next = null;
            //定义一个反转节点
            SingleLinkedList.HeroNode reverseNode = new SingleLinkedList.HeroNode(0, "", "");
            //遍历原始节点 从头到尾 把节点从currentNode里面摘出 放到 reverseNode的最前端 需要节点关联
            while (currentNode != null) {
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
