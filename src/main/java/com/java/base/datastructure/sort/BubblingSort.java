/*
 * Copyright (c) luoZhiMin 2022.9.25.11.6.54
 */

package com.java.base.datastructure.sort;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by IntelliJ IDEA.
 * 冒泡排序 O(n^2) 稳定
 * @Author : 镜像
 * @create 2022/9/25 23:06
 */
public class BubblingSort {

    /*
        冒泡排序（Bubble Sorting）的基本思想是：通过对待排序序列从前向后（从下标较小的元素开始）,依次比较 相邻元素的值，
        若发现逆序则交换，使值较大的元素逐渐从前移向后部，就象水底下的气泡一样逐渐向上冒

        优化： 因为排序的过程中，各元素不断接近自己的位置，如果一趟比较下来没有进行过交换，就说明序列有序，
        因此要在 排序过程中设置一个标志 flag 判断元素是否进行过交换。从而减少不必要的比较。(这里说的优化，可以在冒泡排 序写好后，在进行)
     */

    @Test
    void bubbling(){
//        int[] ints = {3, 9, -1, 10, -2};

        //todo 演变过程
        //第一趟 排序 找到最大的
//        int temp = 0;
//        for (int i = 0; i < ints.length-1; i++) {
//            for (int j = 0; j < ints.length-1-i; j++) {
//                if (ints[j]>ints[j+1]){
//                    temp = ints[j];
//                    ints[j] = ints[j+1];
//                    ints[j+1]=temp;
//                }
//            }
//            System.out.printf("第%d趟排序过后 %s\n", i+1,Arrays.toString(ints));
//        }


        /*
        //第二趟 排序 找到第二大的
        for (int i = 0; i < ints.length-1-1; i++) {
            if (ints[i]>ints[i+1]){
                temp = ints[i];
                ints[i] = ints[i+1];
                ints[i+1]=temp;
            }
        }

        System.out.printf("第二趟排序过后 %s\n", Arrays.toString(ints));

        //第三趟 排序 找到第三大的
        for (int i = 0; i < ints.length-1-2; i++) {
            if (ints[i]>ints[i+1]){
                temp = ints[i];
                ints[i] = ints[i+1];
                ints[i+1]=temp;
            }
        }

        System.out.printf("第三趟排序过后 %s\n", Arrays.toString(ints));

        //第四趟 排序 找到第四大的 从小到大 --> 排序结束
        for (int i = 0; i < ints.length-1-3; i++) {
            if (ints[i]>ints[i+1]){
                temp = ints[i];
                ints[i] = ints[i+1];
                ints[i+1]=temp;
            }
        }

        System.out.printf("第四趟排序过后 %s\n", Arrays.toString(ints));
        */

        //批量生成数据
        int[] ints = SortDemo.getManyArr();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:ss:mm");
        String beforeSortTime = formatter.format(LocalDateTime.now());
        System.out.println(beforeSortTime);


        //排序
        long start = System.currentTimeMillis();
        sort(ints);//没有优化前 8224ms 优化后 8059ms  O(n^2)
        long end = System.currentTimeMillis();
        System.out.printf("耗时[%d]\n",(end-start));
        String afterSortTime = formatter.format(LocalDateTime.now());
        System.out.println(afterSortTime);
    }


    /**
     * 冒泡排序 - 封装 - 优化(提前退出)
     * @param ints 源数组
     */
    @SuppressWarnings({"all"})
    private void sort(int[] ints){
        int temp;
        int count = 0;
        boolean flag = false;
        for (int i = 0; i < ints.length-1; i++) {
            for (int j = 0; j < ints.length-1-i; j++) {
                if (ints[j]>ints[j+1]){
                    flag = true;
                    temp = ints[j];
                    ints[j] = ints[j+1];
                    ints[j+1] = temp;
                }
            }
            if (!flag){
                count++;
                break;
            }
            //重置
            flag = false;
        }
//        System.out.println(Arrays.toString(ints));
        System.out.printf("提前退出[%d]次",count);
    }
}