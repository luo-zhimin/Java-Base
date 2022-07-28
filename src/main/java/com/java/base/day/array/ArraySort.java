package com.java.base.day.array;

import java.util.Arrays;

public class ArraySort {
    public static void main(String[] args) {
        /*
         * 排序
         * 排序是将多个数据，依指定的顺序进行排列的过程
         * 内部排序
         *     指将需要处理的所有数据都加载到内部存储器中进行排序。包括(交换式排序法、选择 式排序法和插入式排序法)；
         * 外部排序
         *     数据量过大，无法全部加载到内存中，需要借助外部存储进行排序。包括(合并排序法和直接合并排序法)
         * 冒泡排序法
         *    冒泡排序（Bubble Sorting）的基本思想是：通过对待排序序列从后向前（从下标较大的元素开始），
         * 依次比较相邻元素 的值，若发现逆序则交换，使值较大的元素逐渐从前移向后部，就象水底下的气泡一样逐渐向上冒。
         */

        int[] a = {1,100,55,24, 69, 80, 57, 13};
        int temp;
        for (int i = 0; i < a.length - 1; i++) {
            System.out.println("第" + (i+1) + "轮开始");
            for (int j = 0; j < a.length - 1 - i; j++) {
                System.out.println("第"+j+"个元素和第"+(j+1)+"个元素比较");
                //前一个和后一个进行比较
                if (a[j] > a[j+1]) {
                    temp = a[j];
                    System.out.println(temp);
                    //后面给前面赋值 min 给前面
                    a[j] = a[j+1];
                    //max 给到后面
                    a[j+1] = temp;
                }
            }
        }
        System.out.println("结束"+ Arrays.toString(a));
    }
}
class Receive{
    //receive
    public static void main(String[] args) {
        int[] a = {24, 69, 80, 57, 13};
        int temp;
        for (int i = 0; i < a.length - 1; i++) {
            System.out.println("第" + (i+1) + "轮开始");
            for (int j = 0; j < a.length - 1 - i; j++) {
                System.out.println("第"+j+"个元素和第"+(j+1)+"个元素比较");
                //前一个和后一个进行比较
                if (a[j] < a[j+1]) {
                    temp = a[j];
                    System.out.println(temp);
                    a[j] = a[j+1];
                    a[j+1] = temp;
                }
            }
        }
        System.out.println("结束receive"+ Arrays.toString(a));
    }
}
