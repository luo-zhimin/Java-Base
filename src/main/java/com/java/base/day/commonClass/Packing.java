package com.java.base.day.commonClass;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 志敏.罗
 * @create 2022/6/12 21:41
 */
public class Packing {
    public static void main(String[] args) {
        /*
         * 包装类
         *   针对八种基本数据类型相应的引用类型—包装类
         *   1）jdk5 前的手动装箱和拆箱方式，装箱∶基本类型-→>包装类型，反之，拆箱
         *   2）jdk5以后（含jdk5）的自动装箱和拆箱方式
         *   3）自动装箱底层调用的是valueOf方法，比如Integer.valueOf
         */
//        Boolean. boolean
//        Character char
        //装箱 int->Integer 1.5之前手动
        int n = 100;
        Integer integer = Integer.valueOf(n);
        System.out.println(integer);
        //拆箱 Integer->int
        System.out.println(integer.intValue());
        Integer integer1 = n;//1.5之后自动

        //integer cache -128～127
        Integer i = new Integer(1);
        Integer j = new Integer(1);
        System.out.println(i == j);//false new object need override equals
        Integer m = 1;
        Integer mn = 1;
        System.out.println(m == mn);//true integer cache => integer array -128～127 else new Integer()
        //只要有基本数据类型，判断的是值是否相同
        Integer i11 = 127;
        int i12 = 127;
        System.out.println(i11==i12);
    }
}
