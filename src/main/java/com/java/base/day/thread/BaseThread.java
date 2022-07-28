package com.java.base.day.thread;

/**
 * Created by IntelliJ IDEA.<br/>
 * 初步认识多线程
 *
 * @Author : 志敏.罗
 * @create 2022/6/29 14:14
 */
public class BaseThread {

    /**
     * 多线程<br/>
     * 进程：<br/>
     * &nbsp; 允许的程序<br/>
     * 线程：<br/>
     * &nbsp; 线程由进程创建，是进程的一个实体<br/>
     * &nbsp;  一个进程可以有多个线程<br/>
     * 单线程：<br/>
     * 同一个时刻，只允许执行一个线程<br/>
     * 多线程：<br/>
     * 同一个时刻，可以执行多个线程 比如 下载可以一次下载多个文件<br/>
     * 并发：<br/>
     * 同一个时刻，多个任务交替执行，造成一种"貌似同时"的错觉，简单的说单核cpu实现的多任务就是并发。<br/>
     * 并行：<br/>
     * 同一个时刻，多个任务同时执行。多核cpu可以实现并行。并发和并行<br/>
     * 线程基本使用：<br/>
     * 创建线程的两种方式：<br/>
     * 1.继承Thread类，重写run方法<br/>
     * 2.实现Runnable接口，重写run方法<br/>
     * Thread详情：
     * 当一个类继承了Tread类
     * 该类就可以当作线程使用 Tread run -> extends Runnable (run)写自己的逻辑 终端 JConsole 会看到Jvm
     * 当main线程启动一个子线程Thread-0  主线程不会阻塞 会继续执行 (并发(看你的cpu 单核(并发) 多核(并行))) 交替执行
     * Runnable详情：
     * 1.java是单继承的，在某些情况下一个类可能已经继承了某个父类，这时在用继承Thread类方法来创建线程显然不可能了。
     * 2.java设计者们提供了另外一个方式创建线程，就是通过实现Runnable接口来创建线程
     */
    public static void main(String[] args) {
        //查看自己电脑的cpu
        Runtime runtime = Runtime.getRuntime();
        System.out.println("cpu " + runtime.availableProcessors());
        //启动线程
        //当main线程启动一个子线程Thread-0  主线程不会阻塞 会继续执行
//        new Cat().run(); run是一个普通方法 会发生阻塞 执行完之后继续continue
        new Cat().start();
        new Thread(new Dog()).start();

        //自己模拟的代理
//        new ThreadProxy(new Dog()).start();
//        public synchronized void start() { //1
//            /**
//             * This method is not invoked for the main method thread or "system"
//             * group threads created/set up by the VM. Any new functionality added
//             * to this method in the future may have to also be added to the VM.
//             *
//             * A zero status value corresponds to state "NEW".
//             */
//            if (threadStatus != 0)
//                throw new IllegalThreadStateException();
//
//            /* Notify the group that this thread is about to be started
//             * so that it can be added to the group's list of threads
//             * and the group's unstarted count can be decremented. */
//            group.add(this);
//
//            boolean started = false;
//            try {
//                start0();//最终执行
//                started = true;
//            } finally {
//                try {
//                    if (!started) {
//                        group.threadStartFailed(this);
//                    }
//                } catch (Throwable ignore) {
//                /* do nothing. If start0 threw a Throwable then
//                  it will be passed up the call stack */
//                }
//            }
//        }
//       2 本地方法(native) 是JVM调用 底层是C/C++实现  start0()最终实现多线程
//       private native void start0();


        for (int i = 0; i < 5; i++) {//(并发(看你的cpu 单核(并发) 多核(并行))) 交替执行
            System.out.println("main 线程名字  " + Thread.currentThread().getName() + " " + i);
        }

        new Thread(new A()).start();
        new Thread(new B()).start();
    }
}

/**
 * ThreadProxy 模拟开发线程的机制 模拟了一个极简的thread
 */
class ThreadProxy implements Runnable {

    /**
     * 属性 类型是Runnable
     */
    private Runnable target = null;

    @Override
    public void run() {
        if (target != null) {
            target.run();//动态绑定(运行类型是 params的)
        }
    }

    /**
     * 构造函数
     *
     * @param target runnable
     */
    public ThreadProxy(Runnable target) {
        this.target = target;
    }

    public void start() {
        start0();//真正实现多线程
    }

    public void start0() {
        run();
    }
}

/**
 * 当一个类继承了Tread类 ，该类就可以当作线程使用<br/>
 * Tread run -> extends Runnable 写自己的逻辑<br/>
 * 终端 JConsole 会看到Jvm  <href a="https://docs.oracle.com/javase/8/docs/technotes/guides/management/jconsole.html>
 */
class Cat extends Thread {

    int count;

    @Override
    public void run() {
        //exit 线程也退出 程序结束
        do {
            System.out.println("喵喵，我是小猫咪 " + (++count) + " " + System.currentTimeMillis() + " 线程名字 " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        } while (count != 10);
    }
}

class Dog implements Runnable {

    int count;

    @Override
    public void run() {
        //exit 线程也退出 程序结束
        do {
            System.out.println("小狗，汪汪叫 " + (++count) + " " + System.currentTimeMillis() + " 线程名字 " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        } while (count != 10);
    }
}

class A implements Runnable {

    int count = 0;

    @Override
    public void run() {
        do {
            System.out.println("hello word!! " + (++count) + " " + System.currentTimeMillis() + " 线程名字 " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } while (count != 10);
    }
}
class B implements Runnable {

    int count = 0;

    @Override
    public void run() {
        do {
            System.out.println("hi hi " + (++count) + " " + System.currentTimeMillis() + " 线程名字 " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        } while (count == 5);
    }
}
