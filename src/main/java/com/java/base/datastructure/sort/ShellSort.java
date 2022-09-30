/*
 * Copyright (c) luoZhiMin 2022.9.28.0.17.1
 */

package com.java.base.datastructure.sort;

import org.junit.jupiter.api.Test;

/**
 * Created by IntelliJ IDEA.
 * Donald Shell O=(nlog n) 希尔排序 - 插入的一种
 * @Author : 镜像
 * @create 2022/9/28 00:17
 */
public class ShellSort {

    /*
        希尔排序也是一种插入排序，它是简单插入排序经过改进之后的一个更高效的版本，也称为缩小增量排序

        希尔排序是把记录按下标的一定增量分组，对每组使用直接插入排序算法排序；随着增量逐渐减少，每组包含 的关键词越来越多，当增量减至 1 时，整个文件恰被分成一组，算法便终止

            1) 希尔排序时， 对有序序列在插入时采用交换法, 并测试排序速度.
            2) 希尔排序时， 对有序序列在插入时采用移动法, 并测试排序速度
     */

    /**
     * <img src="https://cdn.nlark.com/yuque/0/2022/png/27601787/1664359510862-ee373954-43f3-47e0-81ef-96c2235b352a.png">
     */
    @Test
    void sort(){
        int[] arr = {8,9,1,7,2,3,5,4,6,0};
//        int[] arr = SortDemo.getManyArr();

        long start = System.currentTimeMillis();
//        System.out.printf("排序前 %s\n", Arrays.toString(arr));
//        shellExchangeSort(arr);//5056 希尔-交换--》冒泡
        shellInsertSort(arr);//12 希尔-移位--》插入
        long end = System.currentTimeMillis();
        System.out.println("消耗时间 "+(end-start)+" ms");
//        System.out.printf("排序后 %s", Arrays.toString(arr));
    }

    /**
     * 希尔排序 - 交换
     * @param arr sourceArray
     */
    private void shellExchangeSort(int[] arr){
        //todo 逐步
        //第一轮 分为5组 arr.length/2=>i
        int temp;
        /*
        for (int i = 5; i < arr.length; i++) {
            //遍历各组中所有的元素 步长是5
            for (int j = i-5; j >=0 ; j-=5) {//j= j-5
                //如果当前元素大于加上步长的数 说明需要交换
                if (arr[j]>arr[j+5]){
                    temp = arr[j];
                    arr[j] = arr[j+5];
                    arr[j+5] = temp;
                }
            }
        }

        for (int i = 2; i < arr.length; i++) {
            //遍历各组中所有的元素 步长是5
            for (int j = i-2; j >=0 ; j-=2) {//j= j-5
                //如果当前元素大于加上步长的数 说明需要交换
                if (arr[j]>arr[j+2]){
                    temp = arr[j];
                    arr[j] = arr[j+2];
                    arr[j+2] = temp;
                }
            }
        }

         */

        //每次 /2 分组 向下取整
        for (int m = arr.length / 2; m > 0; m /= 2) {
            for (int i = m; i < arr.length; i++) {
                //遍历各组中所有的元素 步长是 m
                for (int j = i - m; j >= 0; j -= m) {
                    //如果当前元素大于加上步长的数 说明需要交换
                    if (arr[j] > arr[j + m]) {
                        //冒泡
                        temp = arr[j];
                        arr[j] = arr[j + m];
                        arr[j + m] = temp;
                    }
                }
            }
        }
    }

    /**
     * 移位
     * @param arr sourceArray
     */
    private void shellInsertSort(int[] arr) {
        int temp;
        int index;
        //逐步缩小范围
        for (int m = arr.length / 2; m > 0; m /= 2) {
            //从m个元素 逐个对其所在的组进行插入排序
            for (int i = m; i < arr.length; i++) {
                //临时存储 当前索引值
                index = i;
                //值
                temp = arr[index];
                //例如 length 10 m第一次 5 index 5 就是 arr[0] arr[5] 比较 .....  m 是步长 也是 多少组
                if (arr[index] < arr[index - m]) {
                    while (index - m >= 0 && temp < arr[index - m]) {
                        arr[index] = arr[index-m];
                        index-=m;
                    }
                    //退出之后说明找到
                    arr[index] = temp;
                }
            }
        }
    }
}
