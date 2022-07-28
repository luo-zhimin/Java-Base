package com.java.base.day.binaryAndOperator;

public class ArithmeticOperatorDetail {

    public static void main(String[] args) {
        /*
         * 算术运算符
         * 在 % 的本质 看一个公式 a % b = a - a / b * b
         * 前++：++i 先自增后赋值 后++：i++先赋值后自增
         */

        // % 取模 ,取余
        System.out.println(10%3);//1
        //++ -- .....
        int i = 10;
        System.out.println(i++);//后++ 先用后加
        System.out.println(++i);//前++ 先加后用
    }
}
