package com.java.base.day.knowData;

public class ForeCovertDetail {

    public static void main(String[] args) {
        /*
         * 强制类型转换 (大到小)
         * 强转符号只针对于最近的操作数有效，往往会使用小括号提升优先级
         */
        int a = (int) 1.9;
        System.out.println(a);//精度损失

        int b = 10000;
        byte b1 = (byte) b;
        System.out.println(b1);//数据溢出

        //强转符号只针对于最近的操作数有效，往往会使用小括号提升优先级
//        int x = (int)10*3.5+6*1.5; 编译错误
        int x = (int)(10*3.5+6*1.5);
        System.out.println(x);

        char c1 = 100; //ok
        int m = 100; //ok
        // char c2 = m; //错误
        char c3 = (char)m;//ok
        System.out.println(c3);//100 对应的字符, d 字符
    }
}
