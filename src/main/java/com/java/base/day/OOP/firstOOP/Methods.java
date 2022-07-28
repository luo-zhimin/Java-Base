/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.OOP.firstOOP;

import java.util.Arrays;

public class Methods {

    public static void main(String[] args) {
        /*
         * 成员方法的定义
         * 访问修饰符 返回数据类型 方法名（形参列表..） {//方法体 语句； return 返回值; }
         * 1) 形参列表：表示成员方法输入 cal(int n) ， getSum(int num1, int num2)
         * 2) 返回数据类型：表示成员方法输出, void 表示没有返回值
         * 3) 方法主体：表示为了实现某一功能代码块
         * 4) return 语句不是必须的。
         * 好处
         *  1) 提高代码的复用性
         *  2) 可以将实现的细节封装起来，然后供其他用户来调用即可
         * 注意细节
         *  访问修饰符 (作用是控制 方法使用的范围) 如果不写默认访问，[有四种: public, protected, 默认, private], 具体在后面说
         *  返回数据类型
         * 1) 一个方法最多有一个返回值 [如何返回多个结果 返回数组 ]
         * 2) 返回类型可以为任意类型，包含基本类型或引用类型(数组，对象)
         * 3) 如果方法要求有返回数据类型，则方法体中最后的执行语句必须为 return 值; 而且要求返回值类型必须和 return 的 值类型一致或兼容
         * 4) 如果方法是 void，则方法体中可以没有 return 语句，或者 只写 return ;
         *  方法名 遵循驼峰命名法，最好见名知义，表达出该功能的意思即可, 比如 得到两个数的和 getSum, 开发中按照规范
         */
        MethodPerson methodPerson = new MethodPerson();
        methodPerson.speak();
        System.out.println(methodPerson.computedCount());
        System.out.println(methodPerson.count(100));
        System.out.println(methodPerson.getSum(1, 2));
        System.out.println("====");
        MyTools myTools = new MyTools();
        int[][] array = {{1, 2}, {3, 4, 5, 6}, {9, 1}};
        myTools.writeTwoArray(array);
        System.out.println(Arrays.toString(myTools.computed(1, 2)));
    }
}

class MethodPerson {
    String name;
    int age;

    public void speak() {
        System.out.println("我是person-method..");
    }

    //add
    public int computedCount() {
        int sum = 0;
        for (int i = 0; i <= 1000; i++) {
            sum += i;
        }
        return sum;
    }

    public int count(int number) {
        int sum = 0;
        for (int i = 0; i <= number; i++) {
            sum += i;
        }
        return sum;
    }

    public int getSum(int a, int b) {
        return a + b;
    }
}

class MyTools {
    public void writeTwoArray(int[][] array) {
        for (int[] ints : array) {
            for (int anInt : ints) {
                System.out.print(anInt + "\t");
            }
            System.out.println();
        }
    }

    public double sum() {
        return 1;
    }

    public int[] computed(int a, int b) {
        return new int[]{a - b, a + b};
    }
}

//方法练习
class MethodsExercise {
    public static void main(String[] args) {
        A a = new A();
        //true 基数 false 偶数
        System.out.println(a.odd(2));
        //行 列 打印对应的字符
        a.sayPrint(2,3,"~~");
    }
}

class A {
    public boolean odd(int n) {
        return n % 2 > 0;
    }
    public void sayPrint(int h,int l,String x){
        String[][] strings = new String[h][l];
        for (int i = 0; i < strings.length; i++) {
            for (int j = 0; j < strings[i].length; j++) {
                strings[i][j]=x;
                System.out.print(strings[i][j]+"\t");
            }
            System.out.println();
        }
    }
}