package com.java.base.day.collectionAndMap.collection.source.list;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 志敏.罗
 * @create 2022/6/18 21:26
 */
public class ArrayListSourceAnalysis {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        /*
         *  扩容机制源码分析
         *  ArrayList
         *  1）permits all elements， including null，ArrayList 可以加入null，并且多个
         *  2）ArrayList 是由数组来实现数据存储的
         *  3）ArrayList 基本等同于Vector，除了ArrayList是线程不安全（执行效率高）看源码. 在多线程情况下，不建议使用ArrayList
         * 底层操作机制源码分析:
         *  1）ArrayList中维护了一个Object类型的数组elementData.【debug 看源码】 transient Object【】 elementData;//transient 表示瞬间，短暂的，表示该属性不会被序列号
         *  2）当创建ArrayList对象时，如果使用的是无参构造器，则初始elementData容量为0，第1次添加，则扩容elementData为10，如需要再次扩容，则扩容elementData为1.5倍。
         *  3）如果使用的是指定大小的构造器，则初始elementData容量为指定大小，如果需要扩容，则直接扩容elementData为1.5倍。
         *      int newCapacity = oldCapacity + (oldCapacity >> 1); 1.5倍扩容
         */
        ArrayList arrayList = new ArrayList();
        arrayList.add("jack");
        arrayList.add(null);
        System.out.println(arrayList);
        //含参构造
        ArrayList initCapacity = new ArrayList(8);
    }
}
