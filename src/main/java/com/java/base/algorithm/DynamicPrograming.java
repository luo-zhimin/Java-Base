/*
 * Copyright (c) luoZhiMin 2022.10.15.7.58.50
 */

package com.java.base.algorithm;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * 动态规划-DP
 * @Author : 镜像
 * @create 2022/10/15 19:58
 */
public class DynamicPrograming {

    /*
        动态规划-耦合 分治-独立 相同：将大问题划分为小问题进行解决

        动态规划(Dynamic Programming)算法的核心思想是：将大问题划分为小问题进行解决，从而一步步获取最优解 的处理算法
        动态规划算法与分治算法类似，其基本思想也是将待求解问题分解成若干个子问题，先求解子问题，然后从这 些子问题的解得到原问题的解。
        与分治法不同的是，适合于用动态规划求解的问题，经分解得到子问题往往不是互相独立的。 ( 即下一个子 阶段的求解是建立在上一个子阶段的解的基础上，进行进一步的求解 )
        动态规划可以通过填表的方式来逐步推进，得到最优解

        01 背包 和完全背包(完全背包指的是：每种物品都有无限件可用)

        完全背包 max计算需要修改 商品无限放

        01背包 v[i][j]=Math.max(v[i-1][j], value[i]+v[i-1][j-weight[i]])
        无限背包 v[i][j]=Math.max(v[i-1][j], value[i]+v[i][j-weight[i]])

        完全背包和0/1背包最大区别就是在 v[i][j-w[i]]，意味着来到本层，我们还是可以取当前层的物品

     */

    @Test
    void bagProgram(){
        //01 背包
        //物品 价值 对应 关系表
        int[] weight = {1,4,3};
        int[] value = {1500,3000,2000};
        //背包容量
        int m = 4;
        int n = value.length;

        //创建二维数组
        //v[i][j] 表示在前 i 个物品中能够装入容量为 j 的背包中的最大价值 => v[n][m]
        int[][] v = new int[n+1][m+1];
        //记录商品存放记录
        int[][] path = new int[n+1][m+1];

        //初始化二维数组
//        v[n][0]=v[0][m] = 1; //默认0
        for (int i = 0; i < v.length; i++) {
            v[i][0]=0;//列
        }
        Arrays.fill(v[0], 0);//行

        System.out.println("init～～～～");
        show(v);

        //j 背包容量 - >m / i 商品个数 - > n 代入法 表格法
        //当 j>=w[i]时： v[i][j]=max{v[i-1][j], v[i]+v[i-1][j-w[i]]}  准备加入的新增的商品的容量小于等于当前背包的容量
        //当 w[i]> j 时：v[i][j]=v[i-1][j] // 当准备加入新增的商品的容量大于 当前背包的容量时，就直接使用上一个 单元格的装入策略
        //动态规划  公式 ->进行 不处理第一行 第一列
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) {

                //公式
                if (weight[i-1]>j){//because index begin 1
                    v[i][j]=v[i-1][j];
                }else{
                    //从1开始 所以下标需要-1
                    //v[i][j]=Math.max(v[i-1][j], value[i-1]+v[i-1][j-weight[i-1]]);
                    //需要查看记录 修改 max
                    //j-weight[i-1] 当前剩余背包大小 v[i-1][j] 当前商品最大价值
                    if (v[i-1][j]<value[i-1]+v[i-1][j-weight[i-1]]){
                        v[i][j] = value[i-1]+v[i-1][j-weight[i-1]];
                        //record
                        path[i][j] = 1;
                    }else {
                        v[i][j] = v[i-1][j];
                    }
                }
            }
        }

        System.out.println("add～～～～");
        show(v);
        //System.out.println("path record~");
        //show(path);
        int i = path.length-1;//行的最大下标
        int j = path[0].length-1;//列的最大下标
        while (i>0 && j>0){
            if (path[i][j]==1) {
                System.out.printf("第%d个商品放入背包\n", i);
                j -= weight[i - 1];//容量
            }
            i--;
        }
    }

    private void show(int[][] arr){
        for (int[] ints : arr) {
            System.out.print(Arrays.toString(ints)+"\n");
        }
        System.out.println();
    }
}
