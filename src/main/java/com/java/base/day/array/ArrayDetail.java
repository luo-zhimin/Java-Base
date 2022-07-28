package com.java.base.day.array;

import java.util.Arrays;

public class ArrayDetail {

    public static void main(String[] args) {
        /*
         * 数组
         * 数组可以存放多个同一类型的数据。数组也是一种数据类型，是引用类型
         *
         * 动态初始化
         * 声明数组 语法:数据类型 数组名[]; 也可以 数据类型[] 数组名; int a[]; 或者 int[] a;
         * 创建数组 语法: 数组名=new 数据类型[大小]; a=new int[10];
         *
         * 静态初始化
         * int arr[] = {1,2...}
         *
         * 注意事件
         * 1) 数组是多个相同类型数据的组合，实现对这些数据的统一管理
         * 2) 数组中的元素可以是任何数据类型，包括基本类型和引用类型，但是不能混用。
         * 3) 数组创建后，如果没有赋值，有默认值 int 0，short 0, byte 0, long 0, float 0.0,double 0.0，char \u0000，boolean false，String null
         * 4) 使用数组的步骤 1. 声明数组并开辟空间 2 给数组各个元素赋值 3 使用数组
         * 5) 数组的下标是从 0 开始的
         * 6) 数组下标必须在指定范围内使用，否则报：下标越界异常，比如 int [] arr=new int[5]; 则有效下标为 0-4
         * 7) 数组属引用类型，数组型数据是对象(object)
         */
        double[] hens = {3, 5, 6, 45, 33};
        double sum = Arrays.stream(hens).sum();
        System.out.println("allHeight=" + sum + "，avg=" + sum / hens.length);
        float[] floats = new float[1];
        System.out.println(floats[0]);
        //创建一个 char 类型的 26 个元素的数组，分别 放置'A'-'Z'
        char[] chars = new char[26];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) ('A' + i);
            System.out.println(chars[i] + "\t" + (int) chars[i]);
        }
        //请求出一个数组 int[]的最大值 {4,-1,9, 10,23}，并得到对应的下标
        //请求出一个数组的和和平均值
        int[] ints = {4, -1, 9, 10, 23};
        int maxIndex = 0;
        int intSum = 0;
        int avgInt = 0;
        int max = ints[0];
        for (int i = 0; i < ints.length; i++) {
            //如果一个数比max大直接给max value index
            if (max<ints[i]){
                maxIndex = i;
                max = ints[i];
            }
            intSum+=ints[i];
        }
        avgInt = intSum/ints.length;
        System.out.println("最大元素是"+max+"，该元素的下标是"+maxIndex);
        System.out.println("和是"+intSum+"\tavg="+avgInt);
    }
}
