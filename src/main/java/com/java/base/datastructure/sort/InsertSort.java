/*
 * Copyright (c) luoZhiMin 2022.9.27.7.37.20
 */

package com.java.base.datastructure.sort;

import org.junit.jupiter.api.Test;

/**
 * Created by IntelliJ IDEA <br>
 * 内存排序 - 插入排序 - O(n^2) - 不稳定 - 大部分是有序的比较好
 * @Author : 镜像
 * @create 2022/9/27 19:37
 */
public class InsertSort {

    /*
        插入排序（Insertion Sorting）的基本思想是：把n个待排序的元素看成为 一个有序表 和 一个无序表 ，开始时 有序表中只包含一个元素，无序表中包含有n-1 个元素，
        排序过程中每次从无序表中取出第一个元素，把它的排序码依次与有序表元素的排序码进行比较，将它插入到有序表中的适当位置，使之成为新的有序表。
     */

    @Test
    void sort(){
//        int[] arr = {101, 34, 119, 1,-1,88};
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random()*80000);
        }

        long start = System.currentTimeMillis();
//        System.out.printf("排序前 %s\n", Arrays.toString(arr));
        insertSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("消耗时间 "+(end-start)+" ms");//373ms
//        System.out.printf("排序后 %s", Arrays.toString(arr));
    }

    /**
     * 插入排序
     * @param arr sourceArray
     */
    private void insertSort(int[] arr){
        //todo 逐步推倒 便于理解
        //分为俩个数组 有序 和 无序
        //第一轮
        //定义待插入数
        /*
        int insertValue = arr[1];
        int insertIndex = 1-1;//arr[1] 和 arr[0]
        //为insertValue 找到插入的位置 待插入数还没有找到插入位置
        while (insertIndex>=0 && insertValue < arr[insertIndex]){
            //后移
            arr[insertIndex+1] = arr[insertIndex];//交换位置 arr[1] = arr[0]
            //保持 arr[0] 和 arr[1] 比较
            insertIndex--;
        }
        //找到插入位置 insertIndex + 1  ==> (因为上面insertIndex--)
        arr[insertIndex+1] = insertValue;

        //第二轮
        //定义待插入数
        insertValue = arr[2];
        insertIndex = 2-1;//arr[1] 和 arr[0]
        //为insertValue 找到插入的位置 待插入数还没有找到插入位置
        while (insertIndex>=0 && insertValue < arr[insertIndex]){
            //后移
            arr[insertIndex+1] = arr[insertIndex];//交换位置 arr[1] = arr[0]
            //保持 arr[0] 和 arr[1] 比较
            insertIndex--;
        }
        //找到插入位置 insertIndex + 1  ==> (因为上面insertIndex--)
        arr[insertIndex+1] = insertValue;

         */

        //代码简化
        for (int i = 1; i < arr.length; i++) {
            int insertValue = arr[i];
            int insertIndex = i-1;//arr[1] 和 arr[0]
            //为insertValue 找到插入的位置 待插入数还没有找到插入位置
            while (insertIndex>=0 && insertValue < arr[insertIndex]){
                //后移
                arr[insertIndex+1] = arr[insertIndex];//交换位置 arr[1] = arr[0]
                //保持 arr[0] 和 arr[1] 比较
                insertIndex--;
            }
            //找到插入位置 insertIndex + 1  ==> (因为上面insertIndex--)
            arr[insertIndex+1] = insertValue;
        }
    }
}
