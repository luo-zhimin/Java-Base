/*
 * Copyright (c) luoZhiMin 2022.10.2.5.20.59
 */

package com.java.base.datastructure.search;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    /**
     * <img src="https://cdn.nlark.com/yuque/0/2022/png/27601787/1664715631138-17954ec4-46a7-45cc-836a-66a3d6931413.png">
     */
    @Test
    void search(){
        int[] arr = {1,8, 10, 89, 1000,1000,1000,1234};
        int search = binarySearch(arr, 12,0,arr.length-1);
        System.out.println("search = "+search);
        System.out.println();
        List<Integer> result =  binarySearchList(arr, 1000,0,arr.length-1);
        if (result.size()>0){
            System.out.println("search list index = "+result);
        }
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


    /**
     * 找到middle值之后不要返回 进行左右扫描
     */
    private List<Integer> binarySearchList(int[] arr, int value, int left, int right) {
        int middle = (left + right) / 2;
        int middleValue = arr[middle];
        //没有找到 left>right
        if (left>right){
            return Collections.emptyList();
        }

        //右递归
        if (value > middleValue) {
            return binarySearchList(arr, value, middle + 1, right);
        } else if (value < middleValue) {
            //左递归
            return binarySearchList(arr, value, left, middle - 1);
        } else {//相等
            //进行左右扫描
            List<Integer> resultIndexList = new ArrayList<>();
            //左边扫描
            int temp = middle-1;
            while (temp < 0 || arr[temp] == value) {
                //否则一直放入
                resultIndexList.add(temp);
                temp -= 1;
            }
            //放入中间的
            resultIndexList.add(middle);
            //右扫描
            temp = middle+1;
            while (temp < arr.length - 1 || arr[temp] == value) {
                //否则一直放入
                resultIndexList.add(temp);
                temp += 1;
            }
            return resultIndexList;
        }
    }
}
