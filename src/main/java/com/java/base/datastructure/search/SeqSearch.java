/*
 * Copyright (c) luoZhiMin 2022.9.30.10.29.51
 */

package com.java.base.datastructure.search;

import org.junit.jupiter.api.Test;

/**
 * Created by IntelliJ IDEA.
 * 查找 - 线性查找
 * @Author : 镜像
 * @create 2022/9/30 22:29
 */
public class SeqSearch {


    @Test
    void search(){
        int[] arr = {1, 9, 11, -1, -1 ,34, 89};
//        int search = seqSearch(arr, new int[]{-1});
//        if (search!=-1) {
//            System.out.printf("找到了他的下标是[{%d}]", search);
//        }
        int[] searches = seqSearch(arr, new int[]{-1});
        for (int search : searches) {
            if (search!=0){
                System.out.printf("找到了他的下标 %s \n", search);
            }
        }
    }

    /**
     * 这里实现的线性查找找到一个满足就返回
     * @param arr 源数组
     * @param value 要查找的值
     * @return 返回找到的具体索引
     */
    private int seqSearch(int[] arr,int value){
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]==value){
                return i;
            }
        }
        return -1;
    }

    private int[] seqSearch(int[] arr,int[] values){
        int[] indexArr = new int[arr.length];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int value : values) {
                if (arr[i] == value) {
                    indexArr[index++] = i;
                    break;
                }
            }
        }
        return indexArr;
    }

}
