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
 * @create 2022/6/7 23:03
 */
public class InterFace {

    public static void main(String[] args) {
        /*
         * 接口
         *  接口就是给出一些没有实现的方法，封装到一起，到某个类要使用的时候，在根据具体情况把这些方法写出来。
         * 语法:
         *  interface 接口名{
         *      //属性
         *      //方法(抽象方法,默认方法，静态方法)
         * }
         *  class类名implements接口{
         *  自己属性；
         *  自己方法；
         *  必须实现的接口的抽象方法
         * 小结:
         *      接口是更加抽象的抽象的类，抽象类里的方法可以有方法体，接口里的所有方法都没有方法体【jdk7.0】接口体现了程序设计的多态和高内聚低偶合的设计思想。
         *   特别说明 Jdk8.0 后接口类可以有静态方法，默认方法，也就是说接口中可以有方法的具体实现
         * 注意：
         *  1.接口不能被实例化
         *  2.接口中所有的方法是 public 方法, 接口中抽象方法，可以不用 abstract 修饰
         *  3.一个普通类实现接口,就必须将该接口的所有方法都实现
         *  4.抽象类去实现接口时，可以不实现接口的抽象方法
         *  5.一个类同时可以实现多个接口
         *  6.接口中的属性,只能是 final 的，而且是 public static final 修饰符 int n1 = 10; //等价 public static final int n1 = 10
         *  7.接口中属性的访问形式∶ 接口名.属性名
         *  8.接口不能继承其它的类，但是可以继承多个别的接口 interface A extends B,C{}
         *  9.接口的修饰符 只能是 public 和默认，这点和类的修饰符是一样的
         *
         * 实现接口 vs 继承类
         *  接口和继承解决的问题不同
         *       继承的价值主要在于∶解决代码的复用性和可维护性。
         *       接口的价值主要在于∶设计，设计好各种规范（方法），让其它类去实现这些方法。即更加灵活.
         *   接口比继承更加灵活
         *      接口比继承更加灵活，继承是满足 is-a 的关系，而接口只需满足 like-a 的关系。
         *   接口在一定程度上实现代码解耦 【即∶接口规范性+动态绑定机制】
         *
         * 接口的多态特性
         *  多态参数 phone.show(phone) show function need extends interface params
         *  多态数组
         *  接口存在多态传递现象--多实现
         */
        Phone phone = new Phone("interface");
        phone.start();
        phone.end();
        //electron parent
//        phone.show(phone);
        InterFaceService.hi();
        //多态数组
        Electron[] electrons = new Electron[2];
        //向下转型 多态
        electrons[0] = new Phone("apple");
        electrons[1] = new mac("mac");
        for (Electron electron : electrons) {
            if (electron instanceof Phone){
                //向上转型
                ((Phone)electron).showName();
            }
        }

    }
}
interface InterFaceService{

    /**
     * 接口属性 相等于抽象类的属性 但是接口的属性(我)个人比较少用
     */
    int NUMBER = 1;

    /**
     * start 1.8之后可以有实现的方法 default 关键字 1.7没有
     */
    void start();

    /**
     * end
     */
    void end();

    /**
     * interface methods need default keyword
     */
    default public void hello(){
        System.out.println("interFaceService hello");
    }

    /**
     * interface static methods
     */
    public static void hi(){
        System.out.println("interFaceService static hi");
    }
}
/**
 * 接口不能继承其它的类，但是可以继承多个别的接口
 */
interface UsbService extends InterFaceService,W{

    /**
     * usb interface start
     */
    void usbStart();

    /**
     * usb interface end
     */
    void usbEnd();
}

/**
 * parent
 */
class Electron{

    int age;

    public Electron(String name) {
        this.name = name;
    }

    public Electron() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    /**
     * 赋值 继承了faceService的子类
     * @param faceService interface
     */
    public void show(InterFaceService faceService){
        System.out.println(this.name + "我是 parent show interface electron");
        faceService.start();
        faceService.end();
    }
}
class mac extends Electron{
    public mac(String name) {
        super(name);
    }
}
/**
 * child
 */
class Phone extends Electron implements InterFaceService{

    static {
        System.out.println("static phone execute");
    }

    {
        System.out.println("phone {} ");
    }

    public Phone(String name) {
        super(name);
    }

    /**
     * start
     */
    @Override
    public void start() {
        System.out.println("phone start");
        //接口中属性的访问形式∶ 接口名.属性名
        System.out.println(InterFaceService.NUMBER+1);
    }

    /**
     * end
     */
    @Override
    public void end() {
        System.out.println("phone end");
    }

    public void showName(){
        System.out.println(super.getName());
    }
}
/**
 * 一个类同时可以实现多个接口
 * 一个普通类实现接口,就必须将该接口的所有方法都实现
 */
class FaceImpl implements UsbService,InterFaceService{
    /**
     * start
     */
    @Override
    public void start() {

    }

    /**
     * end
     */
    @Override
    public void end() {

    }

    /**
     * usb interface start
     */
    @Override
    public void usbStart() {

    }

    /**
     * usb interface end
     */
    @Override
    public void usbEnd() {

    }
}
/**
 * 抽象类去实现接口时，可以不实现接口的抽象方法
 */
abstract class F implements UsbService{}
interface W{}