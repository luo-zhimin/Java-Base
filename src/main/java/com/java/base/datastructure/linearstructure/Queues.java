/*
 * Copyright (c) luoZhiMin 2022.8.28.9.59.37
 */

package com.java.base.datastructure.linearstructure;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 * 队列
 *
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

                初始值 -1 （front/rear）
                front 是队列最前的元素(第一个的前一个)
                rear 是队列的最后的元素(就是最后一个)

       数组模拟队列
         1) 目前数组使用一次就不能用， 没有达到复用的效果
         2) 将这个数组使用算法，改进成一个环形的队列 取模：%

         环形队列
            充分利用数组. 因此将数组看做是一个环形的 取模：%
          定义调整
            初始值 0 (rear/front)
            front 指向队列的第一个元素
            rear 指向队列的最后一个元素的后一个位置 流出一个空间进行约定  rear+1 ==> 最后一个元素
            队列满时 (rear+1) % maxSize = front
            队列为空时 rear == front
            有效个数 (rear+maxSize-front)%maxSize 例如 存进一个元素 变化为 rear=1 front=0

     */


    /**
     * 数组模拟队列
     */
    public static void main(String[] args) {
        //初始化一个模拟队列
//        MyArrayQueue arrayQueue = new MyArrayQueue(3);

        //环形队列
        MyRingTypeArrayQueues arrayQueue = new MyRingTypeArrayQueues(3);//最多可以存2个 (n-1) 预留一个空间 作为约定

        boolean isRing = true;

        char key = ' ';//接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        int value;

        while (loop) {

            if (isRing) {
                System.out.println("\n\t 模拟环形队列 ");
            } else {
                System.out.println("\t 数组模拟队列 ");
            }

            System.out.println("\t\ts(show) 显示队列");
            System.out.println("\t\ta(add) 添加数据到队列");
            System.out.println("\t\tg(get) 队列取出数据");
            System.out.println("\t\th(hand) 查看头部数据");
            System.out.println("\t\te(exit) 退出\n");
            System.out.print("请输入你的选择：");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    System.out.println("~~显示队列~~");
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.print("请输入要添加的值：");
                    value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int number = arrayQueue.getQueueNumber();
                        System.out.println("取出数据 = " + number);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':

                    try {
                        System.out.println("查看头部数据 = " + arrayQueue.headQueue());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    System.out.println("退出程序");
                    break;
                default:
                    break;
            }
        }

    }

}

/**
 * 使用数组模拟队列
 */
class MyArrayQueue {

    private int maxSize;//表示最大容量

    private int front;//头 --> 会随着数据输出而改变(第一个的前一个)

    private int rear;//尾巴 --> 随着数据输入而改变(最后一个)

    private int[] array;//数组

    /**
     * 创建队列的构造器
     *
     * @param maxSize 队列最大个数
     */
    public MyArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        this.array = new int[maxSize];
        this.front = -1;//指向队列头部，分析出 front 是指向队列头的前一个位置.
        this.rear = -1;//指向队列尾，指向队列尾的数据(即就是队列最后一个数据)
    }

    /**
     * 判断队列是否满
     *
     * @return true/false
     */
    public Boolean isFull() {
        return rear == maxSize - 1;
    }

    /**
     * 判断队列是否为空
     *
     * @return true/false
     */
    public Boolean isEmpty() {
        return rear == front;
    }

    /**
     * 添加数据到队列
     *
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
     * 获取队列中的值 == > remove 取出
     *
     * @return 队列中的数据
     */
    public int getQueueNumber() {
        //判断队列是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列空，不可以取数据");
        }
        front++;//头部后移动
        return array[front];
    }

    /**
     * 查询所有的队列
     */
    public void showQueue() {
        //判断队列是否为空
        if (isEmpty()) {
            System.out.println("队列空，无数据");
            return;
        }
        System.out.println(Arrays.toString(array));
    }

    /**
     * 显示队列的头部消息
     *
     * @return 头部数据
     */
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，不可以取数据");
        }
        //front 指向前一个位置 所以加+1
        return array[front + 1];
    }

    /**
     * 显示队列的尾部消息
     *
     * @return 尾部数据
     */
    public int tailQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，不可以取数据");
        }
        //rear 包含
        return array[rear];
    }
}

/**
 * 数组环形队列 模拟
 */
class MyRingTypeArrayQueues {

    private int maxSize;//表示最大容量

    private int front;//头 --> 会随着数据输出而改变(第一个元素)

    private int rear;//尾巴 --> 随着数据输入而改变(最后一个的后一个)

    private int[] array;//数组

    /**
     * 创建队列的构造器
     *
     * @param maxSize 队列最大个数
     */
    public MyRingTypeArrayQueues(int maxSize) {
        this.maxSize = maxSize;
        this.array = new int[maxSize];
    }

    /**
     * 判断队列是否满
     *
     * @return true/false
     */
    public Boolean isFull() {
        //real + 1 最后一个
        return (rear + 1) % maxSize == front;
    }

    /**
     * 判断队列是否为空
     *
     * @return true/false
     */
    public Boolean isEmpty() {
        return rear == front;
    }

    /**
     * 添加数据到队列
     *
     * @param number 被添加的数据
     */
    public void addQueue(int number) {
        //判断队列是否满
        if (isFull()) {
            System.out.println("队列已满～，不可添加数据");
            return;
        }
        //直接将数据加入 因为 real 现在是最后一个的后一个元素
        array[rear] = number;
        //将real后移动 考虑 % 是否满了 利用前面取出数据余留的空间  直接+1 可能会越界
        rear = (rear + 1) % maxSize;
    }

    /**
     * 获取队列中的值 == > remove 取出
     *
     * @return 队列中的数据
     */
    public int getQueueNumber() {
        //判断队列是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列空，不可以取数据");
        }
        //front 现在是队列的第一个元素
        //先把front对应的值保留到临时变量 并且 进行后移(考虑取模) 操作
        int value = array[front];
        front = (front + 1) % maxSize;
        return value;
    }

    /**
     * 查询所有的队列
     */
    public void showQueue() {
        //判断队列是否为空
        if (isEmpty()) {
            System.out.println("队列空，无数据");
            return;
        }

        //todo 从 front 开始 取 遍历多少个有用的元素  (rear+maxSize-front) % maxSize =>|rear-front| % maxSize 防止负数
        for (int i = front; i < front + hasLiveSize(); i++) {
            System.out.printf("array[%d]=%d\n", i % maxSize, array[i % maxSize]);
        }
    }

    /**
     * 获取当前队列的有效个数
     */
    private int hasLiveSize() {
        //例如 rear = 1 front = 0 maxSize =3  ==>(1+3-0)%3  |1-0|%3 => 1
        return (rear + maxSize - front) % maxSize;
    }


    /**
     * 显示队列的头部消息
     *
     * @return 头部数据
     */
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，不可以取数据");
        }
        //front 本身就是 第一个元素
        return array[front];
    }
}