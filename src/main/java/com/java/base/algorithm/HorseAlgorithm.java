/*
 * Copyright (c) luoZhiMin 2022.10.19.11.11.40
 */

package com.java.base.algorithm;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * 骑士周游问题
 * @Author : 镜像
 * @create 2022/10/19 23:11
 */
//@SuppressWarnings({"all"})
public class HorseAlgorithm {

    //列数
    private static int x;

    //行数
    private static int y;

    //各个位置是否被访问
    private static boolean visited[];

    //是否所有的位置都被访问 true finished false fail
    private static boolean flag;

    @Test
    void horse(){
        //优化 贪心 从小到大 每次 最优选择
        x = 8;
        y = 8;
        flag = false;
        int row = 1;//第一行
        int column = 1;
        int[][] chessBoard = new int[x][y];
        visited = new boolean[x*y];
        long start = System.currentTimeMillis();
        transformChessBoard(chessBoard,row-1,column-1,1);
        long end = System.currentTimeMillis();
        System.out.println("消耗时间 "+(end-start)+"ms");//15415ms

        System.out.println("chessBoard～～～");
        for (int[] ints : chessBoard) {
            System.out.println(Arrays.toString(ints));
        }
    }

    /**
     * 骑士周游问题算法
     * @param chessBoard 棋盘
     * @param row 马儿当前位置的行
     * @param column 马儿当前位置的列
     * @param step 步数 init 1
     */
    private void transformChessBoard(int[][] chessBoard,int row,int column,int step){
        chessBoard[row][column] = step;
        //-> photo  4 * 8 + 4
        visited[row*x+column] = true;
        //获取当前 位置可以 走的下一个位置的集合
        List<Point> points = next(new Point(column, row));
        //贪心 从小到大sort
        sort(points);
        //遍历
        while (!points.isEmpty()){
            //取出一个走的位置
            Point point = points.remove(0);
            //判断是否走过了
            if (!visited[point.y*x+ point.x]){
                //没有访问过
                transformChessBoard(chessBoard, point.y, point.x,step+1);
            }
        }
        if (step<x*y && !flag){
            chessBoard[row][column] = 0;
            visited[row*x+column] = false;
        }else {
            flag = true;
        }
    }


    /**
     * 根据当前的位置 计算马儿可以走那些路
     * @param currentPoint 当前点
     */
    private List<Point> next(Point currentPoint){
        List<Point> p = new ArrayList<>();
        Point p1 = new Point();
        //图解 位置 -> 对应 index (马儿跳的位置)
        //验证 currentPoint 是否可以走如下位置 可以走就放进 point里面
        //马跳日
        //5
        if ((p1.x = currentPoint.x - 2) >= 0 && (p1.y = currentPoint.y - 1) >= 0) {
            p.add(new Point(p1));
        }
        //判断马儿可以走 6 这个位置
        if ((p1.x = currentPoint.x - 1) >= 0 && (p1.y = currentPoint.y - 2) >= 0) {
            p.add(new Point(p1));
        }
        //判断马儿可以走 7 这个位置
        if ((p1.x = currentPoint.x + 1) < x && (p1.y = currentPoint.y - 2) >= 0) {
            p.add(new Point(p1));
        }
        //判断马儿可以走 0 这个位置
        if ((p1.x = currentPoint.x + 2) < x && (p1.y = currentPoint.y - 1) >= 0) {
            p.add(new Point(p1));
        }
        //判断马儿可以走 1 这个位置
        if ((p1.x = currentPoint.x + 2) < x && (p1.y = currentPoint.y + 1) < y) {
            p.add(new Point(p1));
        }
        //判断马儿可以走 2 这个位置
        if ((p1.x = currentPoint.x + 1) < x && (p1.y = currentPoint.y + 2) < y) {
            p.add(new Point(p1));
        }
        //判断马儿可以走 3 这个位置
        if ((p1.x = currentPoint.x - 1) >= 0 && (p1.y = currentPoint.y + 2) < y) {
            p.add(new Point(p1));
        }
        //判断马儿可以走 4 这个位置
        if ((p1.x = currentPoint.x - 2) >= 0 && (p1.y = currentPoint.y + 1) < y) {
            p.add(new Point(p1));
        }
        return p;
    }

    private void sort(List<Point> points){
        points.sort((o1, o2) -> next(o1).size()-next(o2).size());
    }
}
