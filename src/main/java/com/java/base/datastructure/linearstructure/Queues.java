/*
 * Copyright (c) luoZhiMin 2022.8.28.9.59.37
 */

package com.java.base.datastructure.linearstructure;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * 队列
 * @Author : 镜像
 * @create 2022/8/28 21:59 <br/><br/>
 * <img src="https://cdn.nlark.com/yuque/0/2022/png/27601787/1661867507255-76a522d4-2a41-4888-a6c3-0e47ca04364c.png">
 */
public class Queues {

    /*
        队列是一个有序列表，可以用数组或是链表来实现。
        遵循先入先出的原则。即：先存入队列的数据，要先取出。后存入的要后取出队列是一个有序列表，可以用数组或是链表来实现

            队列本身是有序列表，若使用数组的结构来存储队列的数据 其中 maxSize 是该队 列的最大容量
            因为队列的输出、输入是分别从前后端来处理，因此需要两个变量 front 及 rear 分别记录队列前后端的下标， front 会随着数据输出而改变，而 rear 则是随着数据输入而改变

        当我们将数据存入队列时称为”addQueue”，addQueue 的处理需要有两个步骤：
            思路分析
                1) 将尾指针往后移：rear+1 , 当 front == rear 【空】
                2) 若尾指针 rear 小于队列的最大下标 maxSize-1，则将数据存入 rear 所指的数组元素中，否则无法存入数据。 rear == maxSize - 1[队列满]

                rear 是队列的最后(含)
                front 是队列最前的元素(不含)
     */


    /**
     * 数组模拟队列
     */
    @Test
    void arrayQueues(){

    }
}

/**
 * 使用数组模拟队列
 */
class ArrayQueue{

    private int maxSize;//表示最大容量

    private int front;//头 --> 会随着数据输出而改变(不包含)

    private int rear;//尾巴 --> 随着数据输入而改变(包含)

    private int[] array;//数组

    /**
     * 创建队列的构造器
     * @param maxSize 队列最大个数
     */
    public ArrayQueue(int maxSize){
        this.maxSize = maxSize;
        this.array = new int[maxSize];
        this.front = -1;
        this.rear = -1;
    }

    /**
     * 判断队列是否满
     * @return true/false
     */
    public Boolean isFull(){
        return rear == maxSize-1;
    }

    /**
     * 判断队列是否为空
     * @return true/false
     */
    public Boolean isEmpty(){
        return rear == front;
    }

    /**
     * 添加数据到队列
     * @param number 被添加的数据
     */
    public void addQueue(int number) {
        //判断队列是否满
        if (isFull()) {
            System.out.println("队列已满～，不可添加数据");
            return;
        }
        rear++;//real 后移
        array[rear] = number;
    }

    /**
     * 获取队列中的值
     * @return 队列中的数据
     */
    public int getQueueNumber(){
        //判断队列是否为空
        if (isEmpty()){
            throw new RuntimeException("队列空，不可以取数据");
        }
        front++;//头部后移动
        return array[front];
    }

    /**
     * 查询所有的队列
     */
    public void showQueue(){
        //判断队列是否为空
        if (isEmpty()){
            System.out.println("队列空，无数据");
            return;
        }
        System.out.println(Arrays.toString(array));
    }

    /**
     * 显示队列的头部消息
     * @return 头部数据
     */
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列空，不可以取数据");
        }
        //front 不包含 所以加+1
        return array[front+1];
    }

    /**
     * 显示队列的尾部消息
     * @return 尾部数据
     */
    public int tailQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列空，不可以取数据");
        }
        //rear 包含
        return array[rear];
    }
}