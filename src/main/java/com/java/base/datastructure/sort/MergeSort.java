/*
 * Copyright (c) luoZhiMin 2022.9.29.11.57.13
 */

package com.java.base.datastructure.sort;

import org.junit.jupiter.api.Test;

/**
 * Created by IntelliJ IDEA.
 * 归并排序
 * @Author : 镜像
 * @create 2022/9/29 23:57
 */
public class MergeSort {

    /*
        归并排序（MERGE-SORT）是利用归并的思想实现的排序方法，该算法采用经典的分治（divide-and-conquer）
        策略（分治法将问题分(divide)成一些小的问题然后递归求解，而治(conquer)的阶段则将分的阶段得到的各答案"修 补"在一起，即分而治之)
     */

    /**
     * <img src="https://cdn.nlark.com/yuque/0/2022/png/27601787/1664471199954-99529611-4b25-42ca-8aa7-f8d7b6d315aa.png">
     */
    @Test
    void sort(){
        //int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};//8个数据 merge 7次 8w数据 merge 8w-1次
        int[] arr = SortDemo.getManyArr();
        //归并排序需要额外的空间
        long start = System.currentTimeMillis();
        mergeSort(arr,0,arr.length-1,new int[arr.length]);
//        System.out.printf("排序后 %s\n", Arrays.toString(arr));
        long end = System.currentTimeMillis();
        System.out.println("消耗时间 "+(end-start)+" ms");
    }


    private void mergeSort(int[] array,int left,int right,int[] temp){
       if (left<right){
           int middle = (left+right)/2;
           //左递归进行分解
           mergeSort(array, left, middle, temp);
           //右递归
           mergeSort(array, middle+1, right, temp);
           //每分解一次 递归一次
           merge(array,left,middle,right,temp);
       }
    }


    /**
     * <img src="https://cdn.nlark.com/yuque/0/2022/png/27601787/1664510307926-6cf4e1f2-cf8d-4847-b747-8a4a2ada0193.png">
     * 合并
     * @param array sourceArray
     * @param left 左边的index
     * @param middle 中间的index
     * @param right 右边的index
     * @param temp 临时数组-中转
     */
    private void merge(int[] array,int left,int middle,int right,int[] temp){
//        System.out.println("合并");
        //左边有序的初始化索引
        int l = left;
        //右边有序的初始化索引
        int r = middle + 1;
        int tempIndex = 0;//临时数组的当前索引
        //1.先把左右俩边的数据按照规则 填充到临时数组里面
        //直到左右俩边 有一边 处理完毕
        while (l<=middle && r<=right){
            //如果左面元素 小于等于 当前 序列的值 将左边的当前元素 拷贝到temp中 然后 tempIndex left 后移
            if (array[l]<=array[r]){
                temp[tempIndex] = array[l];
                tempIndex+=1;
                l+=1;
            }else {
                //右边处理
                temp[tempIndex] = array[r];
                tempIndex+=1;
                r+=1;
            }
        }

        //2.把剩余数据的一边的数据 依次全部填充到临时数组
        //左边
        while (l<=middle){
            //有值 要clone 到 temp中
            temp[tempIndex] = array[l];
            tempIndex+=1;
            l+=1;
        }

        while (r<=right){
            //有值 要clone 到 temp中
            temp[tempIndex] = array[r];
            tempIndex+=1;
            r+=1;
        }

        //3.将temp clone 到 array里面
        //并不是每一次是所有的元素
        tempIndex = 0;
        int tempLeft = left;
        //打印合并顺序
//        System.out.println("tempLeft: "+tempLeft+" - right: "+right);
        while (tempLeft<= right){
            //第一次合并时 tempLeft=0 right =1 看第一张图
            array[tempLeft] = temp[tempIndex];
            tempIndex+=1;
            tempLeft+=1;
        }
    }
}
