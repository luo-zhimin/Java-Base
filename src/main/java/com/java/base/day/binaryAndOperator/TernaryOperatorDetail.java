package com.java.base.day.binaryAndOperator;

public class TernaryOperatorDetail {

    public static void main(String[] args) {
        /*
         * 三元运算符
         * 条件表达式 ? 表达式 1: 表达式 2; 运算规则：
         * 如果条件表达式为 true，运算后的结果是表达式 1；
         * 如果条件表达式为 false，运算后的结果是表达式 2；
         * 口诀: [一灯大师：一真大师]
         *
         * 表达式 1 和表达式 2 要为可以赋给接收变量的类型(或可以自动转换) int c = a>b ? a : b
         * 三元运算符可以转成 if--else 语句
         */

        int a = 12;
        int b = 20;
        int d = 19;
        int c = a > b ? a : b;//Math.max(a,b);
        int max = (a > b) ? (a > d ? a : d) : (b > d ? b : d);
        System.out.println(c);
        System.out.println(max);
    }
}
