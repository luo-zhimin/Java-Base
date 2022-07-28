/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.OOP.firstOOP;

public class MethodParameter {

    public static void main(String[] args) {
        /*
         * 传参机制
         *  基本数据类型：
         *      基本数据类型，传递的值(值拷贝),形参任何改变不会影响实参
         *  引用数据类型：
         *      引用类型传递的是地址（传递也是值，但是值是地址），可以通过形参影响实参！
         * 递归
         *   递归就是方法自己调用自己,每次调用时传入不同的变量.递归有助于编程者解决复杂问题,同时可以让代码变 得简洁
         *
         */
        T t = new T();
        t.test(4);
        System.out.println(t.factorial(5));//120
        System.out.println("sum=" + t.sum(20));
        System.out.println("peach=" + t.peach(1));
    }
}

class T {
    public void test(int n) {
        if (n > 2) {//4>2 3>2 2>2
            test(n - 1);//3 2
        }
        System.out.println("n=" + n);//2
    }

    public int factorial(int s) {
        if (s == 1) {
            return 1;
        } else {
            return factorial(s - 1) * s;//f(4)*5 f(3)*4 f(2)*3 f(1)*2  1
        }
    }

    public int sum(int n) {
        //1 1 2 3 5 8...
        if (n >= 1) {
            if (n == 1 || n == 2) {
                return 1;
            } else {
                return sum(n - 1) + sum(n - 2);
            }
        } else {
            return -1;
        }
    }

    public int peach(int day) {
        //before = (afterDay+1)*2
        if (day == 10) {
            return 1;
        } else if (day >= 1 && day <= 9) {
            return (peach(day + 1) + 1) * 2;
        }
        System.out.println("day在1-10之间");
        return -1;
    }
}