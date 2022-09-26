/*
 * Copyright (c) luoZhiMin 2022.9.26.3.19.57
 */

package com.java.base.datastructure.sort;

import org.junit.jupiter.api.Test;

/**
 * Created by IntelliJ IDEA.
 * 选择排序 不稳定 O(n^2)
 * @Author : 镜像
 * @create 2022/9/26 15:19
 */
public class SelectSort {

    /*
        选择排序（select sorting）也是一种简单的排序方法。它的基本思想是：第一次从 arr[0]~arr[n-1]中选取最小值， 与 arr[0]交换，第二次从 arr[1]~arr[n-1]中选取最小值，
        与 arr[1]交换，第三次从 arr[2]~arr[n-1]中选取最小值，与 arr[2] 交换，…，第 i 次从 arr[i-1]~arr[n-1]中选取最小值，与 arr[i-1]交换，…, 第 n-1 次从 arr[n-2]~arr[n-1]中选取最小值，
        与 arr[n-2]交换，总共通过 n-1 次，得到一个按排序码从小到大排列的有序序列。
     */

    @Test
    void sort(){
//        int[] arr = {101, 34, 119, 1};
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random()*80000);
        }
//        System.out.println("排序前="+Arrays.toString(arr));
        long start = System.currentTimeMillis();
        selectSort(arr);
        long end = System.currentTimeMillis();
//        System.out.println("排序后="+Arrays.toString(arr));
        System.out.println("消耗时间 "+(end-start)+" ms");//1559
    }


    /**
     * 选择排序
     * @param arr sourceArray
     */
    private void selectSort(int[] arr){
        //todo 逐步分析
        //第一轮 101, 34, 119, 1
        //先简单->复杂
//        int minIndex = 0;
//        int min = arr[0];
//        for (int i = 1; i < arr.length; i++) {
//            if (min>arr[i]){//说明假定值不是最小
//                //重置最小值
//                min = arr[i];
//                minIndex = i;
//            }
//        }
//
//        //进行交换 最小值放到 arr[0]
//        if (minIndex!=0) {
//            arr[minIndex] = arr[0];
//            arr[0] = min;
//        }
//
//        System.out.println("1轮后="+Arrays.toString(arr));
//
//
//        minIndex = 1;
//        min = arr[1];
//        for (int i = 2; i < arr.length; i++) {
//            if (min > arr[i]) {//说明假定值不是最小
//                //重置最小值
//                min = arr[i];
//                minIndex = i;
//            }
//        }
//
//        if (minIndex!=1) {
//            //进行交换 最小值放到 arr[1]
//            arr[minIndex] = arr[1];
//            arr[1] = min;
//        }
//
//        System.out.println("2轮后=" + Arrays.toString(arr));
        // ..... O(n^2)
        for (int j = 0; j < arr.length - 1; j++) {
            int minIndex = j;
            int min = arr[j];
            for (int i = j + 1; i < arr.length; i++) {
                if (min > arr[i]) {//说明假定值不是最小
                    //重置最小值
                    min = arr[i];
                    minIndex = i;
                }
            }
            if (minIndex != j) {
                //进行交换 最小值放到 arr[1]
                arr[minIndex] = arr[j];
                arr[j] = min;
            }
//            System.out.println(j + 1 + "轮后=" + Arrays.toString(arr));
        }
    }
}
