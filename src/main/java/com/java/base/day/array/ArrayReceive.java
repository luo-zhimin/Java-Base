package com.java.base.day.array;

import java.util.Arrays;

public class ArrayReceive {

    public static void main(String[] args) {
        /*
         * 数组反转
         */
//        int[] a = {11,22,33,44,55,66};
        int[] a = {33, 22, 11, 66, 88};
        int length = a.length;
        int temp = 0;
        for (int i = 0; i < length / 2; i++) {
            temp = a[length - 1 - i];//{0 5}-{1,4}... 中间对折
            System.out.println(".." + temp);
            //赋值
            a[length - 1 - i] = a[i];
            a[i] = temp;
        }
        System.out.println(Arrays.toString(a));

        //新的Array 逆序赋值
        int[] b = new int[length];
        for (int i = length - 1, j = 0; i >= 0; i--, j++) {
            b[j] = a[i];
//            a[i] = a[length-1-i];
        }
        //让 a 指向 b 数据空间, 此时 a 原来的数据空间就没有变量引用
        //会被当做垃圾，销毁
        a = b;//同一个地址了 同一个堆和栈
        System.out.println(Arrays.toString(b) + "\t" + Arrays.toString(a));
    }
}
