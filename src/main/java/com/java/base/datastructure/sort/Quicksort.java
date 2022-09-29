/*
 * Copyright (c) luoZhiMin 2022.9.29.2.31.1
 */

package com.java.base.datastructure.sort;

import org.junit.jupiter.api.Test;

/**
 * Created by IntelliJ IDEA.
 * 快排 - 冒泡的优化
 * @Author : 镜像
 * @create 2022/9/29 14:31
 */
public class Quicksort {

    /*
        快速排序（Quicksort）是对冒泡排序的一种改进。基本思想是：通过一趟排序将要排序的数据分割成独立的两 部分，
        其中一部分的所有数据都比另外一部分的所有数据都要小，然后再按此方法对这两部分数据分别进行快速排 序，整个排序过程可以递归进行，以此达到整个数据变成有序序列
     */


    /**
     * <img src="https://cdn.nlark.com/yuque/0/2022/png/27601787/1664458249044-edd01457-4363-46eb-aa96-24d24939026e.png">
     */
    @Test
    void sort(){
//        int[] arr = {-9,78,0,23,0,1,-567,70};
        int[] arr = SortDemo.getManyArr();
        long start = System.currentTimeMillis();
//        System.out.printf("排序前 %s\n", Arrays.toString(arr));
        quickSort(arr,0,arr.length-1);//
        long end = System.currentTimeMillis();
        System.out.println("消耗时间 "+(end-start)+" ms");
//        System.out.printf("排序后 %s\n", Arrays.toString(arr));
    }

    /**
     * 快排
     * @param arr sourceArray
     */
    private void quickSort(int[] arr,int leftIndex,int rightIndex){
        //左索引
        int left = leftIndex;
        //右索引
        int right = rightIndex;
        //中间
        int pivot = arr[(left+right)/2];

        int temp;

        //目的是 把大约大于 pivot的值 放到右面 否则放到左边
        while (left < right) {
            //在pivot的左边一直找 找到大于等于 pivot的值 退出
            while (arr[left] < pivot) {
                left += 1;
            }
            //找到小于等于pivot值 退出
            while (arr[right] > pivot) {
                right-=1;
            }

            //如果 left >= right 说明pivot 值 已经排序好了
            if (left>=right){
                break;
            }

            temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            //如果交换完后 发现 pivot == arr[left] left--
            if (arr[left] == pivot) {
                right -= 1;
            }
            if (arr[right] == pivot) {
                left += 1;
            }
        }

        //如果left==right 需要left++ right --
        if (left == right) {
            left += 1;
            right -= 1;
        }

        //左递归
        if (leftIndex < right) {
            quickSort(arr, leftIndex, right);
        }

        //右递归
        if (rightIndex > left) {
            quickSort(arr, left, rightIndex);
        }
    }
}
