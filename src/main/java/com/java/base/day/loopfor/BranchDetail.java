package com.java.base.day.loopfor;

import java.util.Locale;
import java.util.Scanner;

public class BranchDetail {

    public static void main(String[] args) {
        /*
         * 分支控制
         * 1) 单分支 if
         * 2) 双分支 if-else
         * 3) 多分支 if-else if -....-else
         *
         *  switch表达式数据类型，应和case后的常量一样，或者是可以 自动转换可以比较的类型 如输入的字符 而常量是int
         *  switch中的表达式必须是(byte/int/short/long/enum/String)
         *  case子句必须是常量而不是变量
         * 1) 如果判断的具体数值不多，而且符合 byte、 short 、int、 char, enum[枚举], String 这 6 种类型。虽然两个语句都可 以使用，建议使用 switch 语句。
         * 2) 其他情况：对区间判断，对结果为 boolean 类型判断，使用 if，if 的使用范围更广
         */

        //1) 编写程序，声明 2 个 double 型变量并赋值。判断第一个数大于 10.0，且第 2 个数小于 20.0，打印两数之和。
        //2) 定义两个变量 int，判断二者的和，是否能被 3 又能被 5 整除，打印提示信息
        //3) 判断一个年份是否是闰年，闰年的条件是符合下面二者之一：(1)年份能被 4 整除，但不能被 100 整除；(2)能被 400 整除
        double a = 11;
        double b = 12;
        if (a > 10 && b < 20) {
            System.out.println(a + b);
        }
        int i1 = 10;
        int i2 = 5;
        int sum = i1 + i2;
        if (sum % 3 == 0 & sum % 5 == 0) {
            System.out.println(sum);
        }
        int year = 2020;
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            System.out.println("闰年");
        }
        char c1 = 'a';
        System.out.println(c1);
        //switch
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入(A-z)");
        //char c = scanner.next().charAt(0);
        String next = scanner.next();
        //在java中只要有值返回就是一个表达式 常量是个具体的值
        switch (next) {
            case "a":
                System.out.println("a："+next.toUpperCase(Locale.ROOT));
                break;
            case "b":
                System.out.println("b："+next.toUpperCase());
                break;
            default:
                System.out.println("default："+next.toUpperCase());
        }
    }
}
