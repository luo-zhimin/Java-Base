/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.OOP.threeOOP;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : luozhimin
 * @create 2022/6/6 17:24
 */
public class FinalDetail {
    public static void main(String[] args) {
        /*
         * final关键字
         *  final 中文意思∶最后的，最终的.
         *  final可以修饰类、属性、方法和局部变量.
         *  在某些情况下，程序员可能有以下需求，就会使用到final∶
         *      1）当不希望类被继承时，可以用final修饰.
         *      2）当不希望父类的某个方法被子类覆盖/重写（override）时，可以用final关键字修饰
         *      3）当不希望类的的某个属性的值被修改，可以用final修饰
         *      4) 当不希望某个局部变量被修改，可以使用final修饰
         * 细节：
         *   1）final修饰的属性又叫常量，一般用XX_XX_XX来命名
         *   2）final修饰的属性在定义时，必须赋初值，并且以后不能再修改，赋值可以在如下位置之一【选择一个位置赋初值即可】∶
         *           ①定义时∶如 public final double TAX_RATE =0.08；
         *           ②在构造器中
         *           ③在代码块中。
         *   3）如果final修饰的属性是静态的，则初始化的位置只能是 final static
         *      ①定义时
         *      ②在静态代码块
         *      ③不能在构造器中赋值。
         *   4）final类不能继承，但是可以实例化对象。
         *   5）如果类不是final类，但是含有final方法，则该方法虽然不能重写，但是可以被继承
         *   6）一般来说，如果一个类已经是final类了，就没有必要再将方法修饰成final方法。
         *   7）final不能修饰构造方法（即构造器）
         *   8）final和static往往搭配使用，效率更高，不会导致类加载.底层编译器做了优化处理（final static 搭配使用加载不会使类加载 效率更高）
         *   9）包装类（Integer，Double，Float，Boolean,String 等都是final）
         */
//        A.name="aaa";
        System.out.println(A.w);
    }
}
class A{
    protected final static int w;

    public final  String FIRST_NAME;
    public final String FIRST_NAME1;

    protected  void show(){
        System.out.println("show");
    }
    public A(){
        System.out.println("A{}");
        FIRST_NAME = "罗";
    }

    {
        FIRST_NAME1 = "收到";
        System.out.println("{}");
    }

    static {
        System.out.println("static {}");
        w = 1;
    }

    protected void say(){
        System.out.println(FIRST_NAME+"-"+FIRST_NAME1+"-"+w);
    }
}
class B extends A{
    @Override
    protected void show() {
        super.show();
    }
}
class Circle{
    public double getRadios() {
        return radios;
    }

    public void setRadios(double radios) {
        this.radios = radios;
    }

    private double radios;
    //1
    private final double PI;

    public Circle(double radios) {
        this.radios = radios;
        //2
//        PI = 3.14;
    }

    //3
    {
        PI = 3.14;
    }
}