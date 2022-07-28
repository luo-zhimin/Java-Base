package com.java.base.day.thread;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.SneakyThrows;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 志敏.罗
 * @create 2022/6/30 14:35
 */
public class TreadDetail {

    /**
     * 继承Thread vs实现Runnable的区别
     * 1.从java的设计来看，通过继承Thread或者实现Runnable接口来创建线程本质上没有区别，从jdk帮助文档我们可以看到Thread类本身就实现了Runnable接口
     * 2.实现Runnable接口方式更加适合多个线程共享一个资源的情况，并且避免了单继承的限制，建议使用Runnable
     * <br>
     * 线程终止
     * 1.当线程完成任务后，会自动退出。
     * 2.还可以通过使用变量来控制run方法退出的方式停止线程，即通知方式
     * 常用方法
     * 1.setName//设线名，使之与参数 name 相同
     * 2.getName //返回该线程的名称
     * 3.start //使该线程开始执行;Java 虚拟机底层调用该线程的 start0方法
     * 4.run //调用线程对象 run 方法;
     * 5.setPriority //更改线程的优先级
     * 6.getPriority //获取线程的优先级
     * 7.sleep //在指定的毫秒数内让当前正在执行的线程休眠（暂停执行）
     * 8.interrupt //中断线程
     * <p>
     * 注意事项和细节
     * 1.start 底层会创建新的线程，调用run，run就是一个简单的方法调用，不会启动新线程
     * 2.Priority 线程优先级的范围 default NORM_PRIORITY 5
     * 3.interrupt，中断线程，但并没有真正的结束线程。所以一般用于中断正在休眠线程
     * 4.sleep∶线程的静态方法，使当前线程休眠
     * <p>
     * 用户线程和守护线程
     * 1.用户线程∶也叫工作线程，当线程的任务执行完或通知方式结束
     * 2.守护线程∶ 一般是为工作线程服务的，当所有的用户线程结束，守护线程自动结束
     * 3.常见的守护线程∶ 垃圾回收机制
     */
    @SneakyThrows
    public static void main(String[] args) {
        //3 window implements Runnable
        SellTicket sellTicket = new SellTicket();
        new Thread(sellTicket).start();
        new Thread(sellTicket).start();
        new Thread(sellTicket).start();

        //extends Thread
//        new SellTicket1().start();
//        new SellTicket1().start();
//        new SellTicket1().start();

//        T t = new T();
//        t.setName("豆芽");
        //p软了台
//        t.setPriority(Thread.MIN_PRIORITY);//default NORM_PRIORITY 5
//        System.out.println(t.getName());
//        t.start();

//        t.join(); //会先执行完t的thread 插队 一定会成功 对方的join 插队之后先执行插入的线程
//        Thread.yield();//礼让 不一定成功 礼让cpu 时间不确认 不一定成功
        //main exit
//        for (int i = 1; i < 15; i++) {
//            Thread.sleep(10);
//            System.out.println("hi " + i);
//            if (i == 5) {
//                t.start();
//                t.interrupt();
//                t.join();
//            }
//            if (i == 10) {
//                System.out.println("~false~");
//                t.setLoop(false);
//            }
//        }

        //中断 结束现在做的事情
//        t.interrupt();
    }
}

/**
 * 售票问题<br/>
 * 3个窗口100张票
 */
class SellTicket implements Runnable {

    int ticket = 100;

    @Override
    public void run() {
        while (true) {
            //是一个同步方法
            if (!sellTicket()) {
                break;
            }
        }
    }

    private synchronized boolean sellTicket() {//同步方法 synchronized  同一个时刻只有一个人来执行这个方法
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

class SellTicket1 extends Thread {

    /**
     * static 多个线程 共享变量 会有超卖的情况
     */
    static int ticket = 100;

    @Override
    public void run() {
        while (true) {
            if (!sellTicket()){
                break;
            }
        }
    }

    private synchronized boolean sellTicket() {//同步方法 synchronized  同一个时刻只有一个人来执行这个方法
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

@Data
@EqualsAndHashCode(callSuper = true)
class T extends Thread {

    private boolean loop = true;

    @Override
    public void run() {
        while (loop) {
//            Thread.yield();
            System.out.println("优先级 " + Thread.currentThread().getPriority());
            for (int i = 0; i < 88; i++) {
                System.out.println(Thread.currentThread().getName() + " 在吃包子 " + i);
            }
            try {
                System.out.println(Thread.currentThread().getName() + "休眠中～～");
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "被 interrupt～～");
            }
        }
    }
}

/**
 * 用户线程和守护线程<br/>
 * 1.用户线程∶也叫工作线程，当线程的任务执行完或通知方式结束<br/>
 * 2.守护线程∶ 一般是为工作线程服务的，当所有的用户线程结束，守护线程自动结束<br/>
 * 3.常见的 守护线程∶ 垃圾回收机制<br/>
 */
class protectedThread {

    @SneakyThrows
    public static void main(String[] args) {
        Thread thread = new Thread(new MyDomainThread());
        //设置守护线程 设置顺序 start最后设置
        thread.setDaemon(true);
        thread.start();
        //如果我们希望main结束之后 子线程自动结束 需要将子线程设置成为守护线程
        //用户线程->一般一创建就是用户线程(工作线程)
        for (int i = 0; i < 10; i++) {
            Thread.yield();//礼让
            System.out.println("辛苦工作 " + i);
            Thread.sleep(1000);
        }
    }

}

class MyDomainThread implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("晓彤在参加王牌～");
        }
    }
}