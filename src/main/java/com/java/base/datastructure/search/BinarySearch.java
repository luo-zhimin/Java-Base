/*
 * Copyright (c) luoZhiMin 2022.10.2.5.20.59
 */

package com.java.base.datastructure.search;

import org.junit.jupiter.api.Test;

/**
 * Created by IntelliJ IDEA.
 * 查找 - 二分查找
 * @Author : 镜像
 * @create 2022/10/2 17:20
 */
public class BinarySearch {

    /*
        ！！有序数组
     */

    @Test
    void search(){
        int[] arr = {1,8, 10, 89, 1000,1000, 1000,1234};
        int search = binarySearch(arr, 12,0,arr.length-1);
        System.out.println("search = "+search);
    }


    /**
     * @param arr 源数组
     * @param value 要查找的值
     * @return 返回具体的索引 没有就是-1
     */
    private int binarySearch(int[] arr, int value, int left, int right) {
        int middle = (left + right) / 2;
        int middleValue = arr[middle];
        //没有找到 left>right
        if (left>right){
            return -1;
        }

        //右递归
        if (value > middleValue) {
            return binarySearch(arr, value, middle + 1, right);
        } else if (value < middleValue) {
            //左递归
            return binarySearch(arr, value, left, middle - 1);
        } else {//相等
            return middle;
        }
    }
}
