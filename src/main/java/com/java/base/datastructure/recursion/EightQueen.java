/*
 * Copyright (c) luoZhiMin 2022.9.24.11.13.31
 */

package com.java.base.datastructure.recursion;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * 递归 - 八皇后 - 暴力解法(后面dfs优化) -- 最好画图
 * @Author : 镜像
 * @create 2022/9/24 23:13
 */
public class EightQueen {

    /*
        在 8×8 格的国际象棋上摆放八个皇后，使其不能互相攻击
        任意两个皇后都不能处于同一行、 同一列或同一斜线上，问有多少种摆法
     */

    /**
     * 多少个皇后
     */
    private final static int max = 8;

    private static int[] queen = new int[max];

    private static int count = 0;

    private List<int[]> queens = new ArrayList<>();

    private static int judgeCount = 0;

    /**
     * <img src="https://cdn.nlark.com/yuque/0/2022/png/27601787/1664033559936-c0f3df15-6626-4b8f-9f04-209fab368571.png"">
     */
    @Test
    void queen8(){
        //开始放置
        //时间复杂度数高
        long start = System.currentTimeMillis();
        addQueen(0);//14ms
        long end = System.currentTimeMillis();
        System.out.println("耗时 = "+(end-start)+"ms 解法 "+queens.size());
        showQueen();
        System.out.printf("%d皇后一共有%d种解法\n",max,count);//92x
        System.out.printf("判断冲突的个数是%d",judgeCount);//15720
    }


    /**
     * 输出皇后位置
     */
    private void showQueen(){
        queens.forEach(q->{
            count++;
            //因为是下标 所以是(输出)+1就是列 行就是当前数+1
            for (int j : queen) {
                System.out.print(j+1 + "\t");
            }
            System.out.println();
        });
    }

    /**
     * 判断是否冲突(放置皇后时候 检查是否和前面放置的皇后冲突)
     * @param n 第n个皇后
     * @return 是否
     */
    private boolean judge(int n){
        judgeCount++;
        for (int i = 0; i < n; i++) {
            //queen[i] == queen[n] 同一列
            //Math.abs(n-i)==Math.abs(queen[n]-queen[i]) 斜线
            //y=kx 斜率
            //n是行 所以没有必要判断同一行
            if (queen[i] == queen[n] || Math.abs(n-i)==Math.abs(queen[n]-queen[i])){
                return true;
            }
        }
        return false;
    }

    /**
     * 放置第n个皇后
     * @param n 第n个
     */
    private void addQueen(int n){
        if (n == max){
            //放置完毕 n是从0开始
            queens.add(queen);
            return;
        }
        //开始放置 依次放入 并判断是否冲突 时间复杂度 O(n8)n的8次幂
        for (int i = 0; i < max; i++) {
            //放置当前皇后 放到该行的第一列
            queen[n] = i;
            //判断是否冲突
            if (!judge(n)){
                //放置n+1个皇后 开始递归
                addQueen(n+1);//每一次进入递归时候 都有一个循环
            }
            //如果冲突继续执行 queen[n]=i i++
        }
    }
}
