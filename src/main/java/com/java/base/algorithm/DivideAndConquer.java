/*
 * Copyright (c) luoZhiMin 2022.10.14.11.16.10
 */

package com.java.base.algorithm;

import org.junit.jupiter.api.Test;

/**
 * Created by IntelliJ IDEA.
 * 分治
 * @Author : 镜像
 * @create 2022/10/14 23:16
 */
public class DivideAndConquer {

    /*
        分治法是一种很重要的算法。字面上的解释是“分而治之”，就是把一个复杂的问题分成两个或更多的相同或 相似的子问题，
        再把子问题分成更小的子问题……直到最后子问题可以简单的直接求解，原问题的解即子问题 的解的合并。这个技巧是很多高效算法的基础，如排序算法(快速排序，归并排序)，傅立叶变换(快速傅立叶变 换)……

        分治法在每一层递归上都有三个步骤：
          分解：将原问题分解为若干个规模较小，相互独立，与原问题形式相同的子问题
          解决：若子问题规模较小而容易被解决则直接解，否则递归地解各个子问题
          合并：将各个子问题的解合并为原问题的解
     */

    @Test
    void dac(){
        hanoiTower(5,'A','B','C');
    }

    /**
     * 汉诺塔 - 分治 n>=2 ....
     * @param number 多少个盘子
     * @param a 柱子
     * @param b 柱子
     * @param c 柱子
     */
    private void hanoiTower(int number,char a,char b,char c){
        if (number==1){
            System.out.println("第1个盘从 "+a+" - > "+ c);
        }else {
            //如果我们有 n >= 2 情况，我们总是可以看做是两个盘 1.最下边的盘  2. 上面的盘
            // 先把 最上面的盘 A->B
            //移动过程借助 c 从 a 到 b
            hanoiTower(number-1,a,c,b);
            // 把最下边的盘 A->C
            System.out.println("第"+number+"个盘从 "+a+" - > "+c);
            // 把 B 塔的所有盘 从 B->C b->c 借助a
            hanoiTower(number-1,b,a,c);
        }
    }
}

