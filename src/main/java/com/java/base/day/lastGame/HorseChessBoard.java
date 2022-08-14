/*
 * Copyright (c) luoZhiMin 2022.8.13.11.7.55
 */

package com.java.base.day.lastGame;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * 马踏棋盘
 * @Author : 志敏.罗
 * @create 2022/8/13 23:07
 */
public class HorseChessBoard {

    /*
        (体验)
        编程中算法很多，比如八大排序算法（冒泡、选择、插入、快排、归并、希尔、基数、堆排序）、
            查找算法、分治算法、动态规划算法、KMP 算法、贪心算法、普里姆算法、克鲁斯卡尔算法、迪杰斯特拉算法、弗洛伊德算法
     */

    /*
        将马随机放在国际象棋的8×8棋盘 某个方格中， 马按走棋规则（马走日字）进行移动。要求每个方格只进入一次，走遍棋盘上全部64个方格
        会使用到图的遍历算法（DFS）+贪心算法优化  algorithm
     */

    //定义棋盘 属性
    private final static int x = 6;//column

    private final static int y = 6;//row

    private final static int[][] chessBoard = new int[y][x];//棋盘  (int[x][y])

    private static boolean[] visited = new boolean[x * y];//记录某个位置是否走过

    private static boolean finished = false;//记录马儿是否结束

    public static void main(String[] args) {
        int row = 5;
        int column = 5;
        long start = System.currentTimeMillis();
        traversalHorseChessBoard(chessBoard,row-1,column-1,1);
        long end = System.currentTimeMillis();
        System.out.println("耗时 "+(end-start));
        //输出
        writeHorse(chessBoard);
    }


    /**
     * 输出当前棋盘情况 <br>
     */
    private static void writeHorse(int[][] chessBoard) {
        for (int[] rows : chessBoard) {
            for (int step : rows) {
                //马儿应该走的第几步
                System.out.print(step + "\t");
            }
            System.out.println();
        }
    }

    //编写方法 获取当前位置 得到下面所有的所有的点

    /**
     * 当前位置图 <br>
     * <img width="450" height="418" src="https://cdn.nlark.com/yuque/0/2022/png/27601787/1660470992961-e5d4f95c-fb60-4fda-b268-9eebb5d20f14.png?x-oss-process=image%2Fresize%2Cw_450%2Climit_0" alt="马踏棋盘-当前位置图">
     *
     * @param currentPoint 当前位置
     * @return 下面所有的所有的点
     */
    private static List<Point> next(Point currentPoint) {
        //创建一个 points
        List<Point> points = new ArrayList<>();
        //创建一个point对象 准备放入points里面
        Point point = new Point();
        //验证 currentPoint 是否可以走如下位置 可以走就放进 point里面
        //马跳日
        //判断是否可以走5的位置
        if ((point.x = currentPoint.x - 2) >= 0 && (point.y = currentPoint.y - 1) >= 0) {
            points.add(new Point(point));
        }
        //判断是否可以走6的位置
        if ((point.x = currentPoint.x - 1) >= 0 && (point.y = currentPoint.y - 2) >= 0) {
            points.add(new Point(point));
        }
        //判断是否可以走7的位置 x 越界 棋盘 边界
        if ((point.x = currentPoint.x + 1) < x && (point.y = currentPoint.y - 2) >= 0) {
            points.add(new Point(point));
        }
        //判断是否可以走0的位置
        if ((point.x = currentPoint.x + 2) < x && (point.y = currentPoint.y - 1) >= 0) {
            points.add(new Point(point));
        }
        //判断是否可以走1的位置
        if ((point.x = currentPoint.x + 2) < x && (point.y = currentPoint.y + 1) < y) {
            points.add(new Point(point));
        }

        //判断是否可以走2的位置
        if ((point.x = currentPoint.x + 1) < x && (point.y = currentPoint.y + 2) < y) {
            points.add(new Point(point));
        }
        //判断是否可以走3的位置
        if ((point.x = currentPoint.x - 1) >= 0 && (point.y = currentPoint.y + 2) < y) {
            points.add(new Point(point));
        }
        //判断是否可以走4的位置
        if ((point.x = currentPoint.x - 2) >= 0 && (point.y = currentPoint.y + 1) < y) {
            points.add(new Point(point));
        }
        return points;
    }

    /**
     * 编写最核心的代码 遍历棋盘 如果遍历成功 就把 finish->true 并且 将马儿的每一步记录到棋盘里面
     * @param chessBoard 棋盘
     * @param row x
     * @param column  y
     * @param step 步数
     */
    public static void traversalHorseChessBoard(int[][] chessBoard, int row, int column, int step) {
        //记录step -> chessBoard
        chessBoard[column][row] = step;
//        writeHorse(chessBoard);
        //把这个位置记录已经访问 第几个点 下标 索引 ->(1,1) 1行1列 第一行的所有的点+第二行开始的点到现在位置的点的数 (6+1)
        visited[column * x + row] = true; //row * x + column
        //获取下一个位置下面所有的点
        List<Point> points = next(new Point(row,column));
        //遍历
        while (CollectionUtils.isNotEmpty(points)) {
            //取出第一个位置 oldValue
            Point point = points.remove(0);
            //判断该位置是否走过 没有走过 递归遍历
            if (!visited[point.y * x + point.x]) {
                traversalHorseChessBoard(chessBoard, point.x, point.y, step + 1);//y,x
            }
        }

        //当退出循环后 校验是否遍历成功 没有成功就重置相应的值 进行回溯
        if (step<x*y && !finished){
            chessBoard[column][row] = 0;
            visited[column * x + row] = false;
        }else {
            finished=true;
        }
    }

    /**
     * 二维数组验证
     */
    @Test
    void test(){
        int [][] arrays = new int[2][1];
        for (int[] ints : arrays) {
            for (int anInt : ints) {
                System.out.print(anInt);
            }
            System.out.println();
        }
    }
}
