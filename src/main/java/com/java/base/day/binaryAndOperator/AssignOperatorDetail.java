package com.java.base.day.binaryAndOperator;

public class AssignOperatorDetail {

    public static void main(String[] args) {
        /*
        * 赋值运算符
        * 复合赋值运算符 += ，-= ，*= ， /= ，%= 等 ,
        * 重点讲解一个 += ，其它的使用是一个道理
        * a += b; [等价 a = a + b; ]
        * a -= b; [等价 a = a - b; ]
        * % a % b => a-a/b*b
        */

        //赋值运算符的左边 只能是变量,右边 可以是变量、表达式、常量值
        int sum = 11*11+1;
        System.out.println(sum);
        //复合赋值运算符会进行类型转换
        byte b = 127;
        b+=3;
        b++;
        System.out.println(b);//normal 131 byte -128~127 131>127 溢出 -125
    }
}
