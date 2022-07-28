package com.java.base.day.collectionAndMap.collection.source.set;

import java.util.LinkedHashSet;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 志敏.罗
 * @create 2022/6/20 23:31
 */
public class LinkedHashSetSourceAnalysis {

    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        /*
         * 1.LinkedHashSet 是 HashSet 的子类
         * 2.LinkedHashSet 底层是一个LinkedHashMap，底层维护了一个数组+双向链表
         * 3.LinkedHashSet 根据元素的 hashCode 值来决定元素的存储位置，同时使用链表维护元素的次序（图），这使得元素看起来是以插入顺序保存的。
         * 4.LinkedHashSet 不允许添重复元素
         */

        //加入和取出是一致的 likedHashSet 底层是linkedHashMap(HashMap的子类)
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add(new String("AA"));
        linkedHashSet.add(555);
        linkedHashSet.add(555);
        linkedHashSet.add("1234");
        linkedHashSet.add(1234);
        System.out.println(linkedHashSet);
//        LinkedHashMap
        /*
         * likedHashSet 底层结构 数组+双向链表 第一次添加 数组table 初始化扩容到16
         * 存放的节点类型不再是Node了 变成了LinkedHashMap$Entry
         * 数组是 HashMap$Node[] 存放的元素/数据是 LinkedHashMap$Entry 类型 多态数组
           //继承关系内部类完成
           static class Entry<K,V> extends HashMap.Node<K,V> {
                Entry<K,V> before, after;
                Entry(int hash, K key, V value, Node<K,V> next) {
                    super(hash, key, value, next);
               }
            }

         */

        Outer outer = new Outer();
        outer.show(new Cate("cate"){
            @Override
            protected void cate() {
                System.out.println("类的匿名内部类");
            }
        });
        outer.interFace(new IA() {
            @Override
            public void say() {
                System.out.println("接口匿名内部类");
            }
        });

        linkedHashSet.add(new Car("奥迪",1000));
        linkedHashSet.add(new Car("法拉利",1000000));
        linkedHashSet.add(new Car("奥迪",1000));
        System.out.println(linkedHashSet);
    }
}
class Outer{
    private String name;

    protected void show(Cate cate){
        cate.cate();
    }

    protected void interFace(IA ia){
        ia.say();
    }
}
class Cate{
    private String name;

    public Cate(String name) {
        this.name = name;
    }

    protected void cate(){
        System.out.println("cate");
    }

    protected Cate showCateName(){
        return new Cate(this.name);
    }
}
interface IA{
    void say();
}
class Car{
    private String name;
    private double price;

    public Car(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Double.compare(car.price, price) == 0 && Objects.equals(name, car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
