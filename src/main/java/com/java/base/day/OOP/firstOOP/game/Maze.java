/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.OOP.firstOOP.game;

/**
 * 老鼠找迷宫 <br/><br/>
 * <img width="450" height="418" src="https://cdn.nlark.com/yuque/0/2022/png/27601787/1653577460841-016afb50-5aba-4e5f-a3d6-a53d6bfc8a87.png" alt="老鼠找迷宫">
 */
public class Maze {
    public static void main(String[] args) {
        /*
         * 迷宫
         */
        TT tt = new TT();
        int[][] maze = new int[8][7];
        // 0 normal 1 error
        System.out.println("==当前地图情况==");
        tt.marge(maze);
        //老鼠找路
        tt.findRoad(maze, 1, 1);
        System.out.println("====找路情况==");
        tt.marge(maze);
    }
}

class TT {
    //找迷宫路径 map 地图 x j 老鼠的位置 初始before(1,1)  (6,5)over
    //(0 default) (1 error) (2 success) (3 run error)  (over == 2 success)
    //老鼠找路的策略 下 右 上 左
    int sum = 0 ;
    public boolean findRoad(int[][] map, int x, int j) {
        sum ++;
        if (map[6][5] == 2) {
            System.out.println("find road successful use sum= "+sum);
            return true;
        } else {
            if (map[x][j] == 0) {
                //表示可以走 但是没走
                //假设 2
                map[x][j] = 2;
                //老鼠找路的策略 下 右 上 左
                if (findRoad(map, x + 1, j)) {//下
                    return true;
                } else if (findRoad(map, x, j + 1)) {//右
                    return true;
                } else if (findRoad(map, x - 1, j)) {//上
                    return true;
                } else if (findRoad(map, x, j - 1)) {//左
                    return true;
                } else {
                    map[x][j] = 3;
                    System.out.println("找不到路...不玩了..");
                    return false;
                }
            } else {//1 2 3
                return false;
            }
        }
    }

    int count = 0;

    //找路策略修改 上 右 下 左
    public boolean findRoad2(int[][] map, int x, int j) {
        this.count ++;
        if (map[6][5] == 2) {
            System.out.println("count"+count);
            return true;
        } else {
            if (map[x][j] == 0) {
                //表示可以走 但是没走
                //假设 2
                map[x][j] = 2;
                //老鼠找路的策略 下 右 上 左
                if (findRoad2(map, x - 1, j)) {//上
                    return true;
                } else if (findRoad2(map, x, j + 1)) {//右
                    return true;
                } else if (findRoad2(map, x, j + 1)) {//下
                    return true;
                } else if (findRoad2(map, x, j - 1)) {//左
                    return true;
                } else {
                    map[x][j] = 3;
                    System.out.println("findRoad2 找不到路...不玩了..");
                    return false;
                }
            } else {//1 2 3
                return false;
            }
        }
    }

    /**
     * 初始化障碍物(一般+特殊处理)
     * @param maze map
     */
    public void marge(int[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                //init 1 error 障碍物
                if (j == 0 || i == 0 || j == (maze[i].length - 1) || i == maze.length - 1) {
                    maze[i][j] = 1;
                }
                //特殊处理
                if (i == 3 && j <= 2) {
                    maze[i][j] = 1;
                }
                System.out.print(maze[i][j] + "\t");
            }
            System.out.println();
        }
    }
}