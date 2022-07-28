package com.java.base.day.collectionAndMap.collection.source.list;

import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 志敏.罗
 * @create 2022/6/18 23:54
 */
public class VectorSourceAnalysis {

    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        /*
        * 扩容机制源码分析
        * Vector定义说明
        *   1.Vector底层也是一个对象数组，protected Object【】 elementData;
        *   2.Vector是线程同步的，即线程安全，Vector类的操作方法带有synchronized
        *       public synchronized E get(int index) {
        *           if (index>= elementCount)
        *               throw new ArrayIndexOutOfBoundsException(index);
        *           return elementData(index);
        *       }
        * 3.在开发中，需要线程同步安全时，考虑使用Vector
        *  int newCapacity = oldCapacity + ((capacityIncrement > 0) ?
                                         capacityIncrement : oldCapacity);
            俩倍扩容 grow
        */

        //无参构造
        Vector vector = new Vector();
        for (int i = 0; i < 10; i++) {
            vector.add(i);
        }
        vector.add(10);
        System.out.println(vector);
    }
}
