/*
 * Copyright (c) luoZhiMin 2022.9.30.2.40.27
 */

package com.java.base.datastructure.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * 基数排序 - 稳定
 * @Author : 镜像
 * @create 2022/9/30 14:40
 */
public class RadixSort {

    /*
        基数排序（radix sort）属于“分配式排序”（distribution sort），又称“桶子法”（bucket sort）或 bin sort，
     顾名思义，它是通过键值的各个位的值，将要排序的元素分配至某些“桶”中，达到排序的作用
        基数排序法是属于稳定性的排序，基数排序法的是效率高的稳定性排序法
        基数排序(Radix Sort)是桶排序的扩展
        基数排序是 1887 年赫尔曼·何乐礼发明的。它是这样实现的：将整数按位数切割成不同的数字，然后按每个位数分别比较

        将所有待比较数值统一为同样的数位长度，数位较短的数前面补零。然后，从最低位开始，依次进行一次排序。 这样从最低位排序一直到最高位排序完成以后, 数列就变成一个有序序列

        基数排序是对传统桶排序的扩展，速度很快.
        基数排序是经典的空间换时间的方式，占用内存很大, 当对海量数据排序时，容易造成 OutOfMemoryError 。
        基数排序时稳定的。[注:假定在待排序的记录序列中，存在多个具有相同的关键字的记录，若经过排序，这些 记录的相对次序保持不变，即在原序列中，r[i]=r[j]，且 r[i]在 r[j]之前，而在排序后的序列中，r[i]仍在 r[j]之前， 则称这种排序算法是稳定的；否则称为不稳定的]
        有负数的数组，我们不用基数排序来进行排序, 如果要支持负数 取绝对值 反转
     */

    /**
     * <img src="https://cdn.nlark.com/yuque/0/2022/png/27601787/1664525130425-c9b9bb93-8071-4a1c-a3d3-3b168e69f3ed.png">
     */
    @Test
    void sort(){
        //空间换时间的经典算法
        int[] arr = {53, 3, 542, 748, 14, 214};
//        int[] arr = SortDemo.getManyArr();
        long start = System.currentTimeMillis();
        radixSort(arr);
        System.out.printf("排序后 %s\n", Arrays.toString(arr));
        long end = System.currentTimeMillis();
        System.out.println("消耗时间 "+(end-start)+" ms");
    }

    private void radixSort(int[] arr){
        //第一轮排序 (对每个元素的个位进行处理)
        //定义一个二维数组 表示10个桶
        //桶的大小不确定 为了防止放入数字的时候溢出 所以每个桶的大小是 数组的大小
        int[][] bucket = new int[10][arr.length];

        //为了记录每个桶当中的实际存放的数据 需要一个数组来记录各个桶每次放入数据的个数
        int[] bucketElementCounts = new int[10];

        //得到数组中最大的位数
        //假设
        int max = arr[0];
        for (int j : arr) {
            if (j > max) {
                max = j;
            }
        }

        //得到最大的位数
        int maxLength = (max + "").length();

        for (int j = 0, n = 1; j < maxLength; j++, n *= 10) {
            for (int value : arr) {
                //取出 每一轮对应的位 的值 第一次是个位 十位 百位...
                int digitElement = value /n % 10;
                //放入到对应的桶中
                bucket[digitElement][bucketElementCounts[digitElement]] = value;
                bucketElementCounts[digitElement]++;
            }

            //按照桶的顺序取值
            int index =0;
            //遍历每一个桶
            for (int k = 0; k < bucketElementCounts.length; k++) {
                //如果桶中有数据 才放入到源数组
                if (bucketElementCounts[k]!=0){
                    for (int i = 0; i < bucketElementCounts[k]; i++) {
                        //取出元素放入 arr
                        arr[index++] = bucket[k][i];
                    }
                }
                //每一轮处理后 需要将 bucketElementCounts[k] == 0
                bucketElementCounts[k] = 0;
            }
        }

        //第一轮 取出每个元素的个位
        /*
        for (int i = 0; i < arr.length; i++) {
            //取出 个位的值
            int digitElement = arr[i]%10;
            //放入到对应的桶中
            bucket[digitElement][bucketElementCounts[digitElement]] = arr[i];
            bucketElementCounts[digitElement]++;
        }

        //按照桶的顺序取值
        int index =0;
        //遍历每一个桶
        for (int k = 0; k < bucketElementCounts.length; k++) {
            //如果桶中有数据 才放入到源数组
            if (bucketElementCounts[k]!=0){
                for (int i = 0; i < bucketElementCounts[k]; i++) {
                    //取出元素放入 arr
                    arr[index++] = bucket[k][i];
                }
            }
            //第一轮处理后 需要将 bucketElementCounts[k] == 0
            bucketElementCounts[k] = 0;
        }

        //第二轮 十位
        for (int i = 0; i < arr.length; i++) {
            //取出 十位的值
            int digitElement = arr[i] / 10 % 10;
            //放入到对应的桶中
            bucket[digitElement][bucketElementCounts[digitElement]] = arr[i];
            bucketElementCounts[digitElement]++;
        }

        //按照桶的顺序取值
        index =0;
        //遍历每一个桶
        for (int k = 0; k < bucketElementCounts.length; k++) {
            //如果桶中有数据 才放入到源数组
            if (bucketElementCounts[k]!=0){
                for (int i = 0; i < bucketElementCounts[k]; i++) {
                    //取出元素放入 arr
                    arr[index++] = bucket[k][i];
                }
            }
        }
         */
    }
}
