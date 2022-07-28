/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.thread;

/**
 * Created by IntelliJ IDEA.
 * 同步+Synchronized 分析
 * @Author : 志敏.罗
 * @create 2022/6/30 22:39
 */
public class SynchronizedAnalysis {

    /**
     * 线程的同步机制
     *      1.在多线程编程，一些敏感数据不允许被多个线程同时访问，此时就使用同步访问技术，保证数据在任何同一时刻，最多有一个线程访问，以保证数据的完整性。
     *      2.也可以这里理解∶线程同步，即当有一个线程在对内存进行操作时，其他线程都不可以对这个内存地址进行操作，直到该线程完成操作，其他线程才能对该内存地址进行操作.
     *  Synchronized
     *      同步代码块
     *      synchronized（对象）{ //得到对象的锁，才能操作同步代码
     *          // 需要被同步代码;
     *      }
     *      同步方法
     *          synchronized还可以放在方法声明中，表示整个方法-为同步方法
     *          public synchronized void m (String name){
     *              //需要被同步的代码
     *          }
     */
    public static void main(String[] args) {
        //implements runnable
        SellTicket sellTicket = new SellTicket();
        new Thread(sellTicket).start();
        new Thread(sellTicket).start();
        new Thread(sellTicket).start();
    }
}
