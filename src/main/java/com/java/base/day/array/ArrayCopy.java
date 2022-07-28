package com.java.base.day.array;

import java.util.Arrays;

public class ArrayCopy {
    public static void main(String[] args) {
        /*
         * 数组拷贝 (值拷贝)
         */
        int[] a = {1, 2, 3};
        //动态赋值 开辟一个新的数据空间
        int[] b = new int[a.length];
        System.arraycopy(a,0,b,0,a.length);
        b[0] = 22;
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
    }
}
