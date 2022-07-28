/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.tank;

import com.java.base.day.tank.entry.Recorder;
import com.java.base.day.tank.entry.TankPanel;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 志敏.罗
 * @create 2022/6/27 23:50
 */
@SuppressWarnings({"all"})
public class TankGame extends JFrame {

    /**
     * init panel
     */
    TankPanel tankPanel = null;

    public TankGame(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入选择 1：新游戏 2：继续上场");
        String key = scanner.next();
        tankPanel = new TankPanel(key);
        //启动myPannel
        new Thread(tankPanel).start();
        //add
        this.add(tankPanel);
        //close
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //see
        this.setVisible(true);
        //jFrame size
        this.setSize(1200,750);
        //listener
        this.addKeyListener(tankPanel);

        //监听是否退出 exit 关闭窗口
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("监听到关闭程序");
                Recorder.keepRecord();
                System.exit(0);
            }

//            @Override
//            public void windowOpened(WindowEvent e) {
//                System.out.println("监听到window opened");
//                try {
//                    Recorder.readRecord();
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                }
//            }
        });
    }

    public static void main(String[] args) {
        new TankGame();
    }
}
