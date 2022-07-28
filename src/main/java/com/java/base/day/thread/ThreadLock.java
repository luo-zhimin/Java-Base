/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.thread;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.SneakyThrows;

import java.util.Locale;
import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.<br/>
 * 锁 互斥锁 死锁...
 *
 * @Author : 志敏.罗
 * @create 2022/7/1 14:34
 */
public class ThreadLock {
    /**
     * 互斥锁
     * 1.Java语言中，引入了对象互斥锁的概念，来保证共享数据操作的完整性。
     * 2.每个对象都对应于一个可称为"互斥锁"的标记，这个标记用来保证在任一时刻，只能有一个线程访问该对象
     * 3.关键字 synchronized 来与对象的互斥锁联系。当某个对象用synchronized修饰时表明该对象在任一时刻只能由一个线程访问
     * 4.同步的局限性∶导致程序的执行效率要降低
     * 5.同步方法（非静态的）的锁可以是this，也可以是其他对象（要求是同一个对象）
     * 6.同步方法（静态的）的锁为当前类本身。类.class
     * <p>
     * 注意事项和细节
     * 1.同步方法如果没有使用 static 修饰∶默认锁对象为this
     * 2.如果方法使用 static 修饰，默认锁对象∶当前类.class
     * 3.实现的落地步骤∶
     * 需要先分析上锁的代码
     * 选择同步代码块或同步方法
     * 要求多个线程的锁对象为同一个即可
     * <p>
     * 线程的死锁
     * 多个线程都占用了对方的锁资源，但不肯相让，导致了死锁， 在编程是一定要避免死锁的发生.
     */
    public static void main(String[] args) {
        System.out.println("... sell ticket ...");
        LockSellTicket lockSellTicket = new LockSellTicket();
//        new Thread(lockSellTicket).start();
//        new Thread(lockSellTicket).start();
//        new Thread(lockSellTicket).start();
//        new LockSellTicket1().start();
//        new LockSellTicket1().start();
//        new LockSellTicket1().start();
        System.out.println("...die..");
        DieThreadLock mom = new DieThreadLock(true);
        mom.setName("mom");
        System.out.println("mom " + mom.getState());
        DieThreadLock son = new DieThreadLock(false);
        son.setName("son");
        System.out.println("son " + son.getState());
        mom.start();
        son.start();
//        son.suspend();
        System.out.println("mom " + mom.getState());
        System.out.println("son " + mom.getState());

    }
}

class LockSellTicket implements Runnable {

    int ticket = 100;

    /**
     * 同一个对象
     */
    Object object = new Object();

    @Override
    public void run() {
        while (true) {
            //是一个同步方法
            if (!sellTicket()) {
                break;
            }
        }
    }

    /**
     * private synchronized boolean sellTicket() 同步方法 锁加在this对象
     * private synchronized static boolean sellTicket() 锁是加在LockSellTicket.class上
     * 也可以在代码块上加锁->同步代码块
     */
    private /*synchronized*/ boolean sellTicket() {//同步方法 synchronized  同一个时刻只有一个人来执行这个方法

        // 同步代码块
        synchronized (this) {
            if (ticket <= 0) {
                System.out.println("Runnable 售票结束～～");
                return false;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("窗口 " + Thread.currentThread().getName() + " 卖出一张票 " + "剩余" + (--ticket) + "张票");
            return true;
        }
    }
}

class LockSellTicket1 extends Thread {

    /**
     * static 多个线程 共享变量 会有超卖的情况
     */
    static int ticket = 100;

    @Override
    public void run() {
        while (true) {
            if (!sellTicket()) {
                break;
            }
        }
    }

    private /*synchronized*/ boolean sellTicket() {//同步方法 synchronized  同一个时刻只有一个人来执行这个方法
        synchronized (LockSellTicket1.class) {
            if (ticket <= 0) {
                System.out.println("Thread 售票结束～～");
                return false;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("窗口 " + Thread.currentThread().getName() + " 卖出一张票 " + "剩余" + (--ticket) + "张票");
            return true;
        }
    }
}

@Data
@EqualsAndHashCode(callSuper = true)
class DieThreadLock extends Thread {

    //mom + son static 共享
    static final Object mom = new Object();
    static final Object son = new Object();

    private boolean flag;

    public DieThreadLock(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        if (flag) {
            //对象互斥锁，下面是同步代码
            //flag true 就会先得到mom 对象的锁 尝试获取son的锁 如果mom线程得不到son的锁 就会blocked 阻塞
            synchronized (mom) {
                System.out.println(Thread.currentThread().getName() + " 进入1");
                synchronized (son) {
                    System.out.println(Thread.currentThread().getName() + " 进入2");
                }
            }
        } else {
            synchronized (son) {
                System.out.println(Thread.currentThread().getName() + " 进入3");
                synchronized (mom) {
                    System.out.println(Thread.currentThread().getName() + " 进入4");
                }
            }
        }
    }
}

class ReleaseLock {
    /**
     * 会释放锁
     *      当前线程的同步方法、同步代码块执行结束
     *      当前线程在同步代码块、同步方法中遇到break、return (没有正常的完事，经理叫他修改bug，不得已出来)
     *      当前线程在同步代码块、同步方法中出现了未处理的Error或Exception，导致异常结束()
     *      当前线程在同步代码块、同步方法中执行了线程对象的wait（）方法，当前线程暂停，并释放锁。
     * 不会释放锁
     *      线程执行同步代码块或同步方法时，程序调用Thread.sleep（）、Thread.yield（）方法暂停当前线程的执行，不会释放锁
     *      线程执行同步代码块时，其他线程调用了该线程的suspend（）方法将该线程挂起，该线程不会释放锁。
     *      提示∶应尽量避免使用 suspend（）和 resume（）来控制线程，方法不再推荐使用
     */
    public static void main(String[] args) {
//        A a = new A(true);
//        a.start();
//        B b = new B(a);
//        b.start();
        new Card().start();
        new Card().start();
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    static class A extends Thread {

        public A(boolean flag) {
            this.flag = flag;
        }

        private boolean flag;

        @SneakyThrows
        @Override
        public void run() {
            while (flag) {
                Thread.sleep(1000);
                System.out.println((int) (Math.random() * 100 + 1));
            }
            System.out.println("a exit");
        }
    }

    static class B extends Thread {
        private final Scanner scanner = new Scanner(System.in);
        private A a;

        public B(A a) {
            this.a = a;
        }

        @SneakyThrows
        @Override
        public void run() {
            while (true) {
                System.out.println("请输入你的指令(Q exit)");
                char key = scanner.next().toUpperCase(Locale.ROOT).charAt(0);
                Thread.sleep(1000);
                if (key == 'Q') {
                    System.out.println("b exit...");
                    a.setFlag(false);
                    break;
                }
            }
        }
    }

    static class Card extends Thread {
        static int money = 10000;

        @Override
        public void run() {
            while (true){
                if (!getMoney()) {
                    break;
                }
            }
        }

        //取钱
        @SneakyThrows
        private static boolean getMoney() {
            synchronized (Card.class){
                if (money < 1000) {
                    System.out.println("额度不足～～");
                    return false;
                }
                System.out.println(Thread.currentThread().getName() + " 取钱，余额还有 " + (money -= 1000));
                Thread.sleep(1000);
                return true;
            }
        }
    }
}