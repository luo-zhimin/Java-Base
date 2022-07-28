/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.tank.draw;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 定义一个面板 panel 需要继承JPanel
 */
public class Panel extends JPanel {
//盘闹

    /**
     * 绘图的方法
     *
     * @param g the <code>Graphics</code> context in which to paint 可以理解为画笔
     *          提供了好多绘图的方法
     * @serialData
     * Graphics类你可以理解就是画笔，为我们提供了各种图形的方法∶
     * 1.画直线 drawLine（int x1，int y1，int x2，int y2）
     * 2.画矩形边框 drawRect（int x， int y， int width，int height）
     * 3.画椭圆边框 drawOval（int x， int y， int width，int height）
     * 4.填充矩形 filIRect（int x， int y， int width， int height）
     * 5.填充椭圆 fillOval（int x， int y， int width， int height）
     * 6.画图片 drawImage（Image img， int x，int y…））
     * 7.画字符串 drawString（String str， int x， int y）
     * 8.设置画笔的字体 setFont（Font font）
     * 9.设置画笔的颜色 setColor（Color c）
     */
    @Override
    public void paint(Graphics g) {//隔 rua 费ks
        //调用父类的方法 初始化
        super.paint(g);
        //xy 圆心的左上角的xy坐标 weight/height 宽和高 绘图的 圆的位置
//        g.drawOval(10, 10, 100, 100);
        //画直线 drawLine（int x1，int y1，int x2，int y2）
//        g.drawLine(10,10,100,100);
        //画矩形边框 drawRect（int x， int y， int width，int height）
//        g.drawRect(10,10,100,100);
        //填充矩形 filIRect（int x， int y， int width， int height）
        //设置画笔的颜色
        g.setColor(Color.PINK);
//        g.fillRect(10,10,100,100);
        //填充椭圆 fillOval（int x， int y， int width， int height）
//        g.fillOval(10,10,150,100);

        //画图片 drawImage（Image img， int x，int y…））
        //加载图片资源 获取图片
//        Toolkit.getDefaultToolkit().getImage(Panel.class.getResource());
        Image image = null;
        try {
            image = Toolkit.getDefaultToolkit().getImage(new URL("https://p8.itc.cn/images01/20220604/2b88d6fff945406187d70906f9e3f69f.jpeg"));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        g.drawImage(image,10,10,320,505,this);//this 当前图片

        //画字符串 drawString（String str， int x， int y）
        //设置画笔的颜色 设置字体(name，字体属性，size)
        g.setFont(new Font("宋体",Font.BOLD,50));
        //x 左下脚 y 左上脚
        g.drawString("刘亦菲",10,50);
        System.out.println("画图～～");
    }
}
