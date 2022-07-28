package com.java.base.day.commonClass;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 志敏.罗
 * @create 2022/6/16 14:46
 */
public class SystemDetail {
    public static void main(String[] args) {
        /*
        * System
        *  1）exit 退出当前程序 System.exit(0);
        *  2) arraycopy∶复制数组元素，比较适合底层调用，一般使用 System.arraycopy(src,0, dest, 0,3);
        *  3）currentTimeMillis∶返回当前时间距离1970-1-1的毫秒数
        *  4）gc;运行垃圾回收机制 System.gc（）;
        */
        //exit
//        System.out.println("==start==");
//        System.exit(0);
//        System.out.println("==end==");
        long now = System.currentTimeMillis();
        System.out.println(now);
        int[] ints = {1,2,34,6,7};
        int[] newInt = new int[6];
        /*
        *   src  源数组
        *       @param src the source array.
        *   srcPos： 从源数组的哪个索引位置开始拷贝
        *       @param srcPos starting position in the source array.
        *   dest : 目标数组，即把源数组的数据拷贝到哪个数组
        *       @param dest the destination array.
        *   destPos: 把源数组的数据拷贝到 目标数组的哪个索引
        *       @param destPos starting position in the destination data.
        *   length: 从源数组拷贝多少个数据到目标数组
        *       @param length the number of array elements to be copied
        */
        System.arraycopy(ints,0,newInt,0,ints.length);
        System.out.println(Arrays.toString(newInt));
    }
}
