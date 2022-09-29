/*
 * Copyright (c) luoZhiMin 2022.9.25.1.2.28
 */

package com.java.base.datastructure.sort;

/**
 * Created by IntelliJ IDEA.
 * 排序介绍
 * @Author : 镜像
 * @create 2022/9/25 13:02
 */
public class SortDemo {

    /*
        内部排序: 指将需要处理的所有数据都加载到内部存储器(内存)中进行排序。
        外部排序法：数据量过大，无法全部加载到内存中，需要借助外部存储(文件等)进行排序
     */

    /**
     * <img src="https://cdn.nlark.com/yuque/0/2022/png/27601787/1664082219766-a636084f-dc8e-4e50-8ffd-a42b42fb15cb.png">
     */
    private void sortGroup(){}

    /*
        8w条数据测试消耗时间
        冒泡 8.8s
        选择 1.5s
        插入 0.4s
        希尔 0.02s(12ms) -> 移位

        希尔 < 插入 < 选择 < 冒泡
     */



    public static int[] getManyArr(){
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random()*80000);
        }
        return arr;
    }
}
