package com.java.base.day.thread;

import lombok.SneakyThrows;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 志敏.罗
 * @create 2022/6/30 22:09
 */
public class ThreadLiveBean {

    /**
     * 线程的声明周期<br/>
     *  NEW(new) 尚未启动的线程处于此状态。<br/>
     *  RUNNABLE(runnable) 在Java虚拟机中执行的线程处于此状态。<br/>
     *  BLOCKED(blocked) 被阻塞等待监视器锁定的线程处于此状态。<br/>
     *  WAITING(waiting) 正在等待另一个线程执行特定动作的线程处于此状态。<br/>
     *  TIMED_WAITING(time waiting) 正在等待另一个线程执行动作达到指定等待时间的线程处于此状态。 超时等待<br/>
     *  TERMINATED(terminated) 已退出的线程处于此状态。<br/>
     */
    @SneakyThrows
    public static void main(String[] args) {
        LiveThread liveThread = new LiveThread();
        System.out.println("1 "+liveThread.getName()+" 状态 "+liveThread.getState());
        liveThread.start();

        while (Thread.State.TERMINATED != liveThread.getState()){
            System.out.println("2 "+liveThread.getName()+" 状态 "+liveThread.getState());
            Thread.sleep(200);
        }

        System.out.println("3 "+liveThread.getName()+" 状态 "+liveThread.getState());

    }
}
class LiveThread extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println("hello "+i);
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}