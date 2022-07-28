package com.java.base.day.knowData;

public class IntDetail {

    public static void main(String[] args) {
        /*
        * byte(字节) 1字节 -127 ~ 128 计算机基本存储单位
        * short(短整数) 2字节 -32768 ~ 32767
        * int(整形) 4字节 .. -2147483648 ~ 2147483648
        * long(长整形) 8字节 ..
        *
        * 整数常量(具体量) 默认int 转换long 需要加 l 或者 L
        * 保大不保小 知道范围 就选用小值 ...int<long
        *
        * bit 最小存储单位 1byte = 8bit =>0000 0011
        */
        int n1 = 1;//4byte
        long n2 = 1L;//8byte
        //8字节 1字节 8 bit
        long longByte = 8*8;
        System.out.println(longByte+"bit");
    }
}
