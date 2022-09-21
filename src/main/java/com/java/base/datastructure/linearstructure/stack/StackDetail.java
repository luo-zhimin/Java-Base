/*
 * Copyright (c) luoZhiMin 2022.9.21.3.29.1
 */

package com.java.base.datastructure.linearstructure.stack;

import lombok.Data;

import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 * 栈(Stack)
 * @Author : 镜像
 * @create 2022/9/21 15:29
 */
public class StackDetail {

    /*
        介绍
            栈的英文为(stack)
            栈是一个 先入后出 (FILO-First In Last Out)的有序列表。
            栈(stack)是限制线性表中元素的插入和删除只能在线性表的同一端进行的一种特殊线性表。允许插入和删除的 一端，为变化的一端，称为栈顶(Top)，另一端为固定的一端，称为栈底(Bottom)。
            根据栈的定义可知，最先放入栈中元素在栈底，最后放入的元素在栈顶，而删除元素刚好相反，最后放入的元 素最先删除，最先放入的元素最后删除
            图解方式说明出栈(pop)和入栈(push)的概念
        栈的应用场景
            子程序的调用：在跳往子程序前，会先将下个指令的地址存到堆栈中，直到子程序执行完后再将地址取出，以回到原来的程序中。
            处理递归调用：和子程序的调用类似，只是除了储存下一个指令的地址外，也将参数、区域变量等数据存入堆 栈中。
            表达式的转换[中缀表达式转后缀表达式]与求值(实际解决)。
            二叉树的遍历。
            图形的深度优先(depth 一 first)搜索法
     */

    /**
     * 出栈(pop)和入栈(push) <br><br>
     * <img src="https://cdn.nlark.com/yuque/0/2022/png/27601787/1663745969991-89a4781c-8ef9-448c-a861-024bf0083d88.png" width="752.6925430718" height="155.6977231078754">
     */
    public void stackPopOrPush(){}

    private static Scanner scanner = new Scanner(System.in);

    public static void testArrayStack(){
        System.out.println("数组模拟栈测试～");
        //创建一个栈
        ArraySimulationStack stack = new ArraySimulationStack(4);
        String key ="";
        boolean loop = true;
        while (loop){
            System.out.println("show：显示栈");
            System.out.println("exit：退出栈");
            System.out.println("push：入栈");
            System.out.println("pop：出栈");
            System.out.print("请输入你的选择：");
            key = scanner.next();
            switch (key){
                case "show":
                    try {
                        stack.showStack();
                    } catch (Exception e) {
                        System.out.printf("message[%s]\n",e.getMessage());
                    }
                    break;
                case "push":
                    System.out.print("请输入一个数：");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int pop = stack.pop();
                        System.out.printf("出栈的数据%d\n", pop);
                    } catch (Exception e) {
                        System.out.printf("message[%s]\n",e.getMessage());
                    }
                    break;
                case "exit":
                    loop = false;
                    System.out.println("程序退出～");
                    break;
                default:
                    System.out.println("类型无处理");
                    break;
            }
        }
    }


    public static void testLinkedListStack(){
        System.out.println("链表模拟栈测试～");
        //创建一个栈
        LinkedSimulationStack stack = new LinkedSimulationStack();
        String key ="";
        boolean loop = true;
        while (loop){
            System.out.println("show：显示栈");
            System.out.println("exit：退出栈");
            System.out.println("push：入栈");
            System.out.println("pop：出栈");
            System.out.print("请输入你的选择：");
            key = scanner.next();
            switch (key){
                case "show":
                    try {
                        stack.showLinkedListStack();
                    } catch (Exception e) {
                        System.out.printf("message[%s]\n",e.getMessage());
                    }
                    break;
                case "push":
                    System.out.print("请输入一个数：");
                    int value = scanner.nextInt();
                    stack.push(new LinkedSimulationStack.SNode(value));
                    break;
                case "pop":
                    stack.pop();
                    break;
                case "exit":
                    loop = false;
                    System.out.println("程序退出～");
                    break;
                default:
                    System.out.println("类型无处理");
                    break;
            }
        }
    }

    public static void main(String[] args) {
//        testArrayStack();
        testLinkedListStack();
    }
}

/**
 * 数组模拟栈
 * 栈底 固定不变
 * 栈顶 变化
 */
@Data
class ArraySimulationStack{
    private int maxSize;//栈的大小
    private int[] stack;//数据 数组模拟栈
    private int top = -1; //栈顶 init -1

    public ArraySimulationStack(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new int[maxSize];
    }

    /**
     * 栈满
     */
    public Boolean isFull(){
        //index 0
        return top == maxSize-1;
    }

    /**
     * 栈空
     */
    public Boolean isEmpty(){
        return top == -1;
    }

    /**
     * 入栈
     * @param value 数据
     */
    public void push(int value){
        if (isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    /**
     * @return 出栈 将栈顶数据返回
     */
    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("该栈为空 没有数据");
        }
        int value = stack[top];
        top--;
        return value;
    }

    /**
     * 显示栈数据
     */
    public void showStack(){
        //从栈顶开始显示数据
        if (isEmpty()){
            throw new RuntimeException("该栈为空 没有数据");
        }
        for (int i = top; i >=0; i--) {
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }
}

/**
 * 链表模拟栈
 * 先入后出
 */
@Data
class LinkedSimulationStack{

    private SNode head = new SNode(-1);

    public Boolean isEmpty(){
        return head.next==null;
    }

    public void push(SNode node){
        SNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = node;
    }

    public void pop(){
        if (isEmpty()){
            System.out.println("栈空～");
            return;
        }
        //需要反转 先入后出
        SNode current = head;
        while (current.next != null) {
            current = current.next;
        }

        System.out.printf("出栈[%s]\n",current.getNum());
        //需要移除当前节点
        deleteNode(current.num);
    }

    private void deleteNode(int key){
        if (isEmpty()){
            System.out.println("栈空～");
            return;
        }
        //找到要删除节点的前一个
        SNode current = head;
        boolean flag = false;
        while (true){
            if (current.next==null){
                break;
            }
            if (current.next.num == key){
                flag = true;
                break;
            }
            current = current.next;
        }

        if (flag){
            current.next = current.next.next;
            return;
        }

        System.out.printf("没有找到节点[%s]无法删除",key);
    }

    public void showLinkedListStack(){
        if (isEmpty()){
            System.out.println("栈空～");
            return;
        }
        SNode current = head.next;
        while (current != null) {
            System.out.printf("栈中[%s]\n", current.getNum());
            current = current.next;
        }
    }


    @Data
    static class SNode{
        private int num;

        private SNode next;

        public SNode(int num) {
            this.num = num;
        }
    }
}