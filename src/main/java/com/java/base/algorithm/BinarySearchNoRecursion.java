/*
 * Copyright (c) luoZhiMin 2022.10.14.10.28.16
 */

package com.java.base.algorithm;

import org.junit.jupiter.api.Test;

/**
 * Created by IntelliJ IDEA.
 * 二分查找 - 非递归 - log2n (对数)-复杂度
 * @Author : 镜像
 * @create 2022/10/14 22:28
 */
public class BinarySearchNoRecursion {

    /*
        二分查找法的运行时间为对数时间 O(㏒₂n) ，即查找到需要的目标位置最多只需要㏒₂n 步，假设从[0,99]的 队列(100 个数，即 n=100)
        中寻到目标数 30，则需要查找步数为㏒₂100 , 即最多需要查找 7 次( 2^6 < 100 < 2^7)
     */


    @Test
    void search(){
        int[] arr = {1,3, 8, 10, 11, 67, 100};
        int search = binarySearch(arr, 11);
        if (search!=-1){
            System.out.println("找到value=>"+search);
            return;
        }
        System.out.println("没找到 请检查传入的值～");
    }

    /**
     * 二分查找 - 非递归
     * @param arr 源数组
     * @param target 目标值
     * @return 目标值所在的下标 没有就是-1
     */
    private int binarySearch(int[] arr,int target){
        int left = 0;
        int right = arr.length-1;

        while (left <= right) {
            int middle = (left + right) / 2;
            if (arr[middle] == target) {
                return middle;
            } else if (arr[middle] > target) {
                //左边查找
                right = middle - 1;
            } else {
                //右
                left = middle + 1;
            }
        }

        return -1;
    }
}
