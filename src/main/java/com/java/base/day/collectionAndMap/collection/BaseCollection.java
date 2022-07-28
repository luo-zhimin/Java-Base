package com.java.base.day.collectionAndMap.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 志敏.罗
 * @create 2022/6/17 23:36
 */
public class BaseCollection {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        /*
         * 集合
         *  可以动态保存任意多个对象，使用比较方便 并且提供了一系列操作对象的方法
         * 介绍：
         *   集合主要是两组(单列集合,双列集合)
         *   Collection接口有两个重要的子接口ListSet,他们的实现子类都是单列集合
         *   Map接口的实现子类是双列集合，存放的K-V
         *
         * 1）collection实现子类可以存放多个元素，每个元素可以是Object
         * 2）有些Collection的实现类，可以存放重复的元素，有些不可以
         * 3）有些Collection的实现类，有些是有序的（List），有些不是有序（Set）
         * 4）Collection接口没有直接的实现子类，是通过它的子接口Set 和 List 来实现的
         *
         * 常用方法ArrayList为例：
         *  add remove contains size isEmpty clear addAll containsAll removeAll
         *
         * Collection 接口遍历元素方式 1-使用 Iterator(迭代器)：
         *  1.Iterator对象称为迭代器，主要用于遍历 Collection 集合中的元素。
         *  2.所有实现了Collection接口的集合类都有一个iterator（）方法，用以返回一个实现了Iterator接口的对象，即可以返回一个迭代器。
         *  3.Iterator 的结构
         *
         *  4.Iterator 仅用于遍历集合，Iterator 本身并不存放对象
         *
         * Collection接口遍历对象方式2-for循环增强：
         *  增强for循环，可以代替iterator迭代器，特点∶增强for就是简化版的iterator，本质一样。只能用于遍历集合或数组
         */
        //Collection
        ArrayList arrayList = new ArrayList();
        arrayList.add("jack");
        arrayList.add("tom");

//        Map
        HashMap hashMap = new HashMap();
        hashMap.put("1", "Java");
        hashMap.put("2", "Vue");

        /*
         * common methods
         * add remove contains size isEmpty clear addAll containsAll removeAll
         */
        List list = new ArrayList();
        //add
        list.add(true);
        list.add(1);//自动装箱 new Integer(1)
        System.out.println(list);
        //remove
        list.remove(0);//删除第一个元素 下标删除
        list.remove(true);
        System.out.println(list);
        //包含
        System.out.println(list.contains(1));
        //长度
        System.out.println(list.size());
        //清空
//        list.clear();
        //addAll 全部加进去
        list.addAll(arrayList);
        System.out.println("addAll after " + list);
        //containsAll 是否包含全部
        System.out.println(list.containsAll(arrayList));
        //removeAll
        System.out.println(list.removeAll(arrayList));
        //是否为空
        System.out.println(list.isEmpty());

        System.out.println("interator start...");//哎特瑞特
        List books = new ArrayList();
        books.add(new Book("三国志", 22.2));
        books.add(new Book("三国演义", 18.2));
        books.add(new Book("小李飞刀", 9.2));
        System.out.println("books=" + books);
        /*
         * 先获取他本身的迭代器
         * 当退出while循环后,这时iterator迭代器，指向最后的元素
         * 在不使用iterator.hasNext()时  直接调用iterator.next();//NoSuchElementException
         * 如果希望再次遍历，需要重置我们的迭代器 iterator = books.iterator()
         */
        Iterator iterator = books.iterator();
        while (iterator.hasNext()) {//是否还有下一个对象
            Object object = iterator.next();//返回Object
            System.out.println("iterator = " + object);
        }
        System.out.println("iterator end");
        System.out.println("for start..");
        books.forEach(book->{
            System.out.println(book);
        });
        System.out.println("for end..");
    }
}

class Book {
    private String name;
    private double prise;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrise() {
        return prise;
    }

    public void setPrise(double prise) {
        this.prise = prise;
    }

    public Book(String name, double prise) {
        this.name = name;
        this.prise = prise;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", prise=" + prise +
                '}';
    }
}