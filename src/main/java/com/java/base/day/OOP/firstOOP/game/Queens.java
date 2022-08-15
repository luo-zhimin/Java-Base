/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.OOP.firstOOP.game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 八皇后 <br/><br/>
 * <img width="520" height="418" src="https://cdn.nlark.com/yuque/0/2022/png/27601787/1660478665456-b2dac582-0aff-4d2b-89d6-7924b835a9ce.png" alt="八皇后">
 */
public class Queens {

    /*
        八皇后问题，是一个古老而著名的问题，是回溯算法的典型案例。该问题是国际西洋棋棋手马克斯·贝瑟尔
        于 1848 年 提出：在 8×8 格的国际象棋上摆放八个皇后，使其不能互相攻击，
        即：任意两个皇后都不能处于同一行、同一列或同 一 斜线上，问有多少种摆法
     */

    List<List<String>> result = new ArrayList<>();

    private int chessBoard[][]; //棋盘

    private static int n=8;//几皇后

    //todo 可以采用数组 存储
    HashSet<Integer> column,pie,na;//列，斜线(俩种)

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Queens queens = new Queens();
        List<List<String>> solveQueens = queens.solveQueens();
        long end = System.currentTimeMillis();
        System.out.println("耗时 = "+(end-start)+"ms");
        System.out.println(n+"皇后，一共有"+solveQueens.size()+"种解法");
        solveQueens.forEach(System.out::println);
    }

    private List<List<String>> solveQueens(){
        this.chessBoard = new int[n][n];
        this.column = new HashSet<>();
        this.pie = new HashSet<>();
        this.na = new HashSet<>();
        //从第几行开始
        dfs(0);
        return result;
    }


    private void dfs(int row) {

        if (row >= n) {
            //转换
            transformResult();
            return;
        }

        for (int j = 0; j < chessBoard.length; j++) {
            //这一列如果有 就去下一行执行
            if (column.contains(j) || pie.contains(row + j) || na.contains(row - j)) continue;

            chessBoard[row][j] = 1;//放下棋子
            column.add(j);
            //左边斜线
            pie.add(row + j);
            //右边斜线
            na.add(row - j);

            dfs(row + 1);//下一行

            //回溯
            column.remove(j);
            pie.remove(row + j);
            na.remove(row - j);
            chessBoard[row][j] = 0;
        }
    }

    private void transformResult() {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < n; j++) {
                stringBuilder.append(".");
                if (chessBoard[i][j] == 1) {
                    stringBuilder.append("Q");
                }
            }
            res.add(stringBuilder.toString());
        }
        result.add(res);
    }
}