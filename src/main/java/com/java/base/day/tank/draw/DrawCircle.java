package com.java.base.day.tank.draw;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * 绘图训练
 * @Author : 志敏.罗
 * @create 2022/6/27 22:37
 */
public class DrawCircle extends JFrame{

    /**
     * 绘图原理
     *
     * Component类提供了两个和绘图相关最重要的方法∶<br/>
     * 1.paint（Graphics g）绘制组件的外观<br/>
     * 2.repaint（）刷新组件的外观<br/>
     *<br/>
     * 当组件第一次在屏幕显示的时候，程序会自动的调用paint（）方法来绘制组件<br/>
     * 在以下情况paint（）将会被调用∶<br/>
     * 1.窗口最小化，再最大化<br/>
     * 2.窗口的大小发生变化<br/>
     * 3.repaint方法被调用<br/>
     */

    //定一个面板
    private Panel panel = null;

    //JFrame 对应画框
    public static void main(String[] args) {
        new DrawCircle();
        System.out.println("draw exit");
    }

    public DrawCircle(){
        //初始化面板
        panel = new Panel();
        //把面板放入到窗口(画框)
        this.add(panel);
        //设置窗口的大小
        this.setSize(400,300);
        //可视
        this.setVisible(true);
        //当点击窗口x时候 完全退出
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
