package com.java.base.day.annotationAndEnum;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 *  [ɪˌnjuːməˈreɪʃn] 哎奈么瑞生
 * @Author : luozhimin
 * @create 2022/6/10 17:36
 */
public class Enumeration {
    public static void main(String[] args) {
        /*
         * 枚举
         * enum实现接口
         *  使用 enum 关键字后，就不能再继承其它类了，因为 enum 会隐式继承 Enum，而 Java 是单继承机制
         *  枚举类和普通类一样，可以实现接口，如下形式。 enum 类名 implements 接口 1，接口 2{}
         */
        System.out.println(Season.SPRING);
        Person person = Person.STUDENT;
        //name：返回当前对象名（常量名
        System.out.println(person.name());
        //ordinal 输出的我们枚举对象的顺序 从0开始
        System.out.println(person.ordinal());
        //返回当前枚举类中所有的常量 该枚举对象的数组
        System.out.println(Arrays.toString(Person.values()));
        //valueOf 将字符串转换成枚举对象，要求字符串必须 为已有的常量名，否则报异常！
        System.out.println(Person.valueOf("STUDENT"));
        //compareTo：比较两个枚举常量，比较的就是编号！[before ordinal - after ordinal]-[self.ordinal - other.ordinal]
        System.out.println(Person.STUDENT.compareTo(Person.TEACHER));
        System.out.println("===");
        for (Person value : Person.values()) {
            System.out.println(value.getName());
        }
        System.out.println("enum implements interface");
        person.getPerson();
    }
}

class Season {

    private String name;
    private String desc;

    public final static Season SPRING = new Season("春天", "温暖");

    /**
     * 自定义枚举--单例-饿汉式
     * 构造器私有化 防止修改
     * 去掉set 防止属性被修改
     * 在类内部直接创建固定的对象
     * 可以 加入 final 修饰符 final为了避免加载类
     *
     * @param name 名字
     * @param desc 描述
     */
    private Season(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "Season{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}

interface PersonService {
    /**
     * 获取个人信息
     */
    void getPerson();
}

/**
 * enum person
 */
enum Person implements PersonService{
    TEACHER("小明", "老师"),
    STUDENT("小白", "学生");

    /**
     * 当我们使用 enum 关键字开发一个枚举类时，默认会继承 Enum 类, 而且是一个 final 类
     * 使用关键字 enum 代替class
     * 常量名(实参列表) 多个逗号间隔
     * 如果使用无参构造器 创建 枚举对象，则实参列表和小括号都可以省略
     * 枚举对象必须放在枚举类的行首
     * 1) toString:Enum 类已经重写过了，返回的是当前对象名,子类可以重写该方法，用于返回对象的属性信息
     * 2) name：返回当前对象名（常量名），子类中不能重写
     * 3) ordinal：返回当前对象的位置号，默认从 0 开始
     * 4) values：返回当前枚举类中所有的常量
     * 5) valueOf：将字符串转换成枚举对象，要求字符串必须 为已有的常量名，否则报异常！
     * 6) compareTo：比较两个枚举常量，比较的就是编号！
     *
     * @param name name
     * @param desc desc
     */
    Person(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    private String name;
    private String desc;

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }

    /**
     * 获取个人信息
     */
    @Override
    public void getPerson() {
        System.out.println("personEnum getPersonInfo");
    }
}