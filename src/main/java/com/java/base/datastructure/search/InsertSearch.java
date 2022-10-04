/*
 * Copyright (c) luoZhiMin 2022.10.3.8.52.1
 */

package com.java.base.datastructure.search;

import org.junit.jupiter.api.Test;

/**
 * Created by IntelliJ IDEA.
 * 查找 - 插值查找
 * @Author : 镜像
 * @create 2022/10/3 17:22
 */
public class InsertSearch {

    /*
        对于数据量较大，关键字分布比较均匀的查找表来说，采用插值查找, 速度较快.
        关键字分布不均匀的情况下，该方法不一定比折半查找要好
     */

    /**
     * <img src="https://cdn.nlark.com/yuque/0/2022/png/27601787/1664796638151-3b91fc49-5b94-4ccf-a688-b9916c1c5fdf.png">
     */
    @Test
    void search(){
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i+1;
        }
//        System.out.println(Arrays.toString(arr));
        int search = insertSearch(arr, 0, arr.length - 1, 55);
        System.out.println("search = "+search);
    }


    /**
     * 插值查找 也必须 是有序的
     * @param arr 源数组
     * @param left 左面索引
     * @param right 右面索引
     * @param value 要查找的值
     * @return 找到的索引 -1没有找到
     */
    private int insertSearch(int[] arr,int left,int right,int value){
        //int mid = low + (high - low) * (key - arr[low]) / (arr[high] - arr[low])
        //左面 大于 右面  要找的值比最小的小 最大的大
        if (left > right || value < arr[0] || value > arr[arr.length - 1]) {
            return -1;
        }

        //中值自适应
        int middle = left + (right - left) * (value - arr[left]) / (arr[right] - arr[left]);
        int middleValue = arr[middle];

        System.out.println("count~"+middle);

        if (value>middleValue){
            //右
            return insertSearch(arr, middle+1, right, value);
        }else if (value<middleValue){
            //左
            return insertSearch(arr, left, middle-1, value);
        }else {
            return middle;
        }
    }
}
