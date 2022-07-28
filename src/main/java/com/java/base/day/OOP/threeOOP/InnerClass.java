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
 * @create 2022/6/8 22:29
 */
public class InnerClass {

    public static void main(String[] args) {
        /*
         * 内部类
         *  如果定义类在局部位置(方法中/代码块) :
         *      (1) 局部内部类
         *      (2) 匿名内部类
         *  定义在成员位置
         *      (1) 成员内部类
         *      (2) 静态内部类
         * 基本介绍
         * 类的内部又完整的嵌套了另一个类结构。被嵌套的类称为内部类（inner class）嵌套其他类的类称为外部类（outer class）。
         * 是我们类的第五大成员【属性、方法、构造器、代码块、内部类】，内部类最大的特点就是可以直接访问私有属性，并且可以体现类与类之间的包含关系，
         * 注意∶内部类是学习的难点， 同时也是重点，后面看底层源码时，有大量的内部类.
         * 语法：
         *      class Outer{//外部类
         *          class Inner{}//内部类
         *      }
         *      class Outer{}//外部其他类
         * 分类：
         *  定义在外部类局部位置上（比如方法内）局部内部类：
         *      1）局部内部类（有类名）
         *      2）匿名内部类（没有类名，重点！！）
         *  定义在外部类的成员位置上∶
         *      1）成员内部类（没用static修饰）
         *      2) 静态内部类（使用static修饰）
         * 局部内部类：
         *  说明∶局部内部类是定义在外部类的局部位置，比如方法中，并且有类名。
         *      1.可以直接访问外部类的所有成员，包含私有的
         *      2.不能添加访问修饰符，因为它的地位就是一个局部变量。局部变量是不能使用修饰符的。但是可以使用final 修饰，因为局部变量也可以使用final
         *      3.作用域∶仅仅在定义它的方法或代码块中。
         *      4.局部内部类--访问---->外部类的成员 【访问方式∶直接访问】
         *      5.外部类---问-->局部内部类的成员 访问方式∶创建对象，再访问（注意∶ 必须在作用域内）
         *      6.外部其他类---不能访问->局部内部类（因为 局部内部类地位是一个局部变量）
         *      7.如果外部类和局部内部类的成员重名时，默认遵循就近原则，如果想访问外部类的成员，则可以使用 （外部类名.this.成员）去访问
         *  记住：
         *      （1）局部内部类定义在方法中/代码块
         *      （2）作用域在方法体或者代码块中
         *      （3）本质仍然是一个类
         * 匿名内部类的使用：
         * （1）本质是类
         * （2）内部类
         * （3）该类没有名字
         * （4）同时还是一个对象
         * 说明∶
         *      匿名内部类是定义在外部类的局部位置，比如方法中，并且没有类名
         *      1.匿名内部类的基本语法 new 类或接口（参数列表）{类体}
         *      2.匿名内部类的语法比较奇特，因为匿名内部类既是一个类的定义，同时它本身也是一个对象，因此从语法上看，
         * 它既有定义类的特征，也有创建对象的特正，分析可以看出这个特点，因此可以调用匿名内部类方法
         *      3.可以直接访问外部类的所有成员，包含私有的
         *      4.不能添加访问修饰符，因为它的地位就是一个局部变量。
         *      5.作用域∶仅仅在定义它的方法或代码块中
         *      6.匿名内部类--问-->外部类成员 【访问方式∶直接访问】
         *      7.外部其他类--不能访问-->匿名内部类（因为 匿名内部类地位是一个局部变量）
         *      8.如果外部类和匿名内部类的成员重名时，匿名内部类访问的话，默认遵循就近原则如果想访问外部类的成员，则可以使用（外部类名.this.成员）去访问
         *  成员内部类的使用：
         *      成员内部类是定义在外部类的成员位置 并且没有static修饰
         *      1.可以直接访问外部类的所有成员，包含私有的
         *      2.可以添加任意访问修饰符（public、protected、默认、private），因为它的地位就是一个成员
         *      3.作用域 和外部类的其他成员一样，为整个类体，在外部类的成员方法中创建成员内部类对象，再调用方法.
         *      4.成员内部类--访问-->外部类成员（比如属性）【访问方式∶直接访问】
         *      5.外部类-访问->成员内部类 访问方式∶ 创建对象，再访问
         *      6.外部其他类--访问-->成员内部类
         *      7.如果外部类和内部类的成员重名时，内部类访问的话，默认遵循就近原则，如果想访问外部类的成员，则可以使用 （外部类名.this.成员）去访问
         *  静态内部类：
         *      说明∶静态内部类是定义在外部类的成员位置，并且有static修饰
         *      1.可以直接访问外部类的所有静态成员，包含私有的，但不能直接访问非静态成员
         *      2.可以添加任意访问修饰符（public、protected、默认、private），因为它的地位就是一个成员。
         *      3.作用域∶同其他的成员，为整个类体
         *      4.静态内部类---访问---->外部类（比如∶静态属性）【访问万式∶直接访问所有静态成员】
         *      5.外部类--访问-->静态内部类 访问方式∶创建对象，再访问
         *      6.外部其他类---访问--->静态内部类
         *      7.如果外部类和静态内部类的成员重名时，静态内部类访问的时，默认遵循就近原则，如果想访问外部类的成员，则可以使用（外部类名.成员）去访问
         */
        new Outer("outer").outer();
        System.out.println("====");
        new Outer("outerInner").show();
        System.out.println("----");
        new Outer().cate();
        System.out.println("static interface ....");
        f(new IA() {
            @Override
            public void cry() {
                System.out.println("实参直接传递 cry");
            }

            @Override
            public void getUserInfo() {
                System.out.println("实参直接传递 getUserInfo");
            }
        });
        System.out.println("成员内部类..");
        Cate cate = new Cate("cate");
        /*
         * 外部其他类 访问 成员内部类
         * 1.外部其他类.new 成员内部类()
         * 2.在外部类中提供一个方法 暴露出去
         * 3.new Cate().new Inner()
         */
        cate.new Inner().innerCate();
        //2.
        System.out.println(cate.cateInner());
        System.out.println("static....static inner class ");
        /*
         * 外部其他类 访问 静态内部类 需要满足访问权限
         * 1.类名.静态内部类
         * 2.在外部类中提供一个方法 暴露出去
         */
        new Cate.StaticInner().staticSay();
    }

    public static void f(IA ia) {
        ia.cry();
        ia.getUserInfo();
    }
}

/**
 * 外部类
 */
class Outer {
    /**
     * 属性
     */
    private String name;

    private int number = 100;

    /**
     * 构造器
     *
     * @param name this.name
     */
    public Outer(String name) {
        this.name = name;
    }

    public Outer() {
    }

    public void show() {
        System.out.println("show");
        /**
         * 基于接口的
         * 匿名内部类 使用一次 就不可以使用了
         */
        IA cate = new IA() {
            @Override
            public void cry() {
                System.out.println("interface cate");
            }

            @Override
            public void getUserInfo() {
                System.out.println("IA getUserInfo");
            }
        };
        System.out.println("基于接口的 匿名内部类 cate 的运行类型 " + cate.getClass());
        cate.cry();
    }

    /**
     * 方法
     */
    public void outer() {
        /**
         * 局部内部类 本质还是类
         * 局部内部类是定义在外部类的局部位置 通常是在方法内
         * 不能添加访问修饰符，但是可以使用final 修饰，因为局部变量也可以使用final
         * 作用域∶仅仅在定义它的方法或代码块中
         */
        final class MethodsInner {
            final int number = 200;

            /**
             * 可以直接访问外部类的所有成员，包含私有的
             */
            public void outerInner() {
                //局部内部类--访问---->外部类的成员 【访问方式∶直接访问】
                //如果外部类和局部内部类的成员重名时，默认遵循就近原则，如果想访问外部类的成员，则可以使用 （外部类名.this.成员）去访问
                System.out.println("methods inner class name number=" + number + ", outer number = " + Outer.this.number);
                show();
            }
        }
        System.out.println("outer....methods");

        {
            class Inner1 {
                {
                    System.out.println("methods { class inner1 {} }");
                }
            }
        }

        //外部类---问-->局部内部类的成员 访问方式∶创建对象，再访问（注意∶ 必须在作用域内）
        new MethodsInner().outerInner();
    }

    //代码块
    {
        System.out.println("代码块");
    }

    public void cate() {
        /**
         * 匿名内部类 基于类的
         */
        new Cate("cate") {
            @Override
            public void eat() {
                System.out.println("outer inner eat  匿名内部类 重写了eat...");
            }

            @Override
            public void cry() {
                super.cry();
            }
        }.money("money");
    }
}

/**
 * interface
 */
interface IA {
    void cry();

    void getUserInfo();
}

/**
 * cate 类-实现-硬编码
 */
class Cate implements IA {

    private static final int NUMBER = 100;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        if (name == null) {
            this.name = "outerName";
        }
    }

    private String name;

    public Cate(String name) {
        this.name = name;
        System.out.println("name " + name);
    }

    @Override
    public void cry() {
        System.out.println("cat ... miao..");
    }

    @Override
    public void getUserInfo() {
        System.out.println("cate getUserInfo");
    }

    public void eat() {
    }

    public void money(String name) {
        System.out.println("cate name " + name);
    }

    /**
     * 成员内部类
     */
    class Inner {
        private String name = "innerCate";

        public void innerCate() {
            System.out.println("inner cate..." + name + " outer class name=" + Cate.this.name);
        }
    }

    protected Inner cateInner() {
        return new Inner();
    }

    /**
     * 静态内部类
     */
    static class StaticInner{
        public void staticSay(){
            System.out.println("static class number "+NUMBER);
        }
    }

    protected StaticInner getStaticInner(){
        return new StaticInner();
    }
}
class T{
    int anInt = 1;
    String name = "T";
}
class ClassExercise {
    public static void main(String[] args) {
        T t = new T();
        t.anInt=22;
        t.name="name";
        System.out.println(new T().anInt+" "+new T().name+"  ***  "+t.anInt+" - "+t.name);
    }
}