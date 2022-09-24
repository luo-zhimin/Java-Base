/*
 * Copyright (c) luoZhiMin 2022.9.24.7.1.53
 */

package com.java.base.datastructure.recursion;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * 递归 - 小老鼠迷宫 找路
 * @Author : 镜像
 * @create 2022/9/24 19:01
 */
public class MouseMaze {

    /**
     * 迷宫
     */
    private static int[][] map = new int[8][7];

    /**
     * 放置Map<String,Integer>
     */
    private static Map<String,Integer> countMap = new HashMap<>();

    /*
        小球得到的路径，和程序员设置的找路策略有关即：找路的上下左右的顺序相关
        再得到小球路径时，可以先使用(下右上左)，再改成(上右下左)，看看路径是不是有变化
     */

    @Test
    void road(){
        initMaze();
        showCurrentMaze("init");
        findRoad(1,1);//first group
//        findRoad2(1,1);//second group
        showCurrentMaze("first group");
        System.out.printf("小球最短路径[%d]",countMap.get("first group"));
    }

    /**
     * 初始化一个迷宫 添加障碍物<br><br>
     * <img width="450" height="418" src="https://cdn.nlark.com/yuque/0/2022/png/27601787/1653577460841-016afb50-5aba-4e5f-a3d6-a53d6bfc8a87.png" alt="老鼠找迷宫">
     */
    private void initMaze(){
        System.out.println("init ....");
        //上下
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //左右
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //特殊处理
        map[3][1]=1;
        map[3][2]=1;
        //回溯 挡死
//        map[1][2]=1;
//        map[2][2]=1;
    }

    /**
     * @param name 策略名字
     */
    private void showCurrentMaze(String name){
        System.out.println("show current maze ....");
        int count = 0;
        for (int[] ints : map) {
            for (int anInt : ints) {
                if (anInt==2) {
                    count++;
                }
                System.out.print(anInt+"\t");
            }
            System.out.println();
        }
        countMap.put(name,count);
        System.out.println("show end ....");
    }

    /**
     * 找路策略 - 下-右-上-左
     * @param i 从哪个地方开始找路 - 行
     * @param j 从哪个地方开始找路 - 列
     * @return 是否找到
     */
    private boolean findRoad(int i,int j){
        //需要遇到找路的方向
        //约定 (1,1)开始  (6,5)结束
        // 0 是默认-没走 1是墙 2是通路 3是表示走过但是有问题走不通
        // 找路的方向 -> 下-右-上-左 走不通就回溯
        if (map[6][5]==2){
            //over
            return true;
        }else {
            //no go
            if (map[i][j]==0){
                //下-右-上-左
                map[i][j]=2;//假设可以走通
                if (findRoad(i+1,j)){
                    return true;//下
                }else if (findRoad(i,j+1)){
                    return true;//右
                }else if (findRoad(i-1,j)){
                    return true;//上
                }else if(findRoad(i,j-1)){
                    return true;//左
                }else {
                    //走不通 死路 回溯
                    map[i][j] = 3;
                    return false;
                }
            }else {
                // value => 1,2,3
                return false;
            }
        }
    }

    /**
     * 找路策略 - 上 右 下 左
     * @param i 从哪个地方开始找路 - 行
     * @param j 从哪个地方开始找路 - 列
     * @return 是否找到
     */
    private boolean findRoad2(int i,int j){
        //需要遇到找路的方向
        //约定 (1,1)开始  (6,5)结束
        // 0 是默认-没走 1是墙 2是通路 3是表示走过但是有问题走不通
        // 找路的方向 -> 上右下左 走不通就回溯
        if (map[6][5]==2){
            //over
            return true;
        }else {
            //no go
            if (map[i][j]==0){
                //上右下左
                map[i][j]=2;//假设可以走通
                if (findRoad2(i-1,j)){
                    return true;//上
                }else if (findRoad2(i,j+1)){
                    return true;//右
                }else if (findRoad2(i+1,j)){
                    return true;//下
                }else if(findRoad2(i,j-1)){
                    return true;//左
                }else {
                    //走不通 死路 回溯
                    map[i][j] = 3;
                    return false;
                }
            }else {
                // value => 1,2,3
                return false;
            }
        }
    }
}
