package com.java.base.day.array;

import java.util.Arrays;

public class ArrayAssign {

    public static void main(String[] args) {
        /*
         * 数组赋值
         *
         * 1) 基本数据类型赋值，这个值就是具体的数据(值拷贝)，而且相互不影响。 int n1 = 2; int n2 = n1;
         * 2) 数组在默认情况下是引用传递，赋的值是地址。
         */

        int n = 10;
        int n1 = n;
        n1 = 20;
        //基本类型值拷贝
        System.out.println(n+"--"+n1);//10--20
        //数组 是地址 赋值类型是引用类型
        //arr/arr1 在栈里面指向同一个地址值 -> 同一个地址在堆里面只有一个内存空间
        int[] arr ={1,2,3};
        int[] arr1 = arr;
        arr1[0] =10;
        System.out.println(Arrays.toString(arr));
    }
}
