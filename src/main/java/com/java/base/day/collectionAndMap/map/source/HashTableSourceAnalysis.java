package com.java.base.day.collectionAndMap.map.source;

import java.util.Hashtable;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 志敏.罗
 * @create 2022/6/24 17:37
 */
public class HashTableSourceAnalysis {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        /*
         * HashTable
         *  1）存放的元素是键值对∶ 即K-V
         *  2）hashtable的键和值都不能为null，否则会抛出NullPointerException
         *  3）hashTable 使用方法基本上和HashMap一样
         *  4）hashTable 是线程安全的（synchronized），hashMap是线程不安全
         */
        Hashtable hashtable = new Hashtable();
        hashtable.put("1","java");//HableTable$Entry
        hashtable.put("1","cloud");
        hashtable.put("2","spring");
        for (int i = 0; i <= 9; i++) {
            hashtable.put(i,i);
        }
//        hashtable.put(null,"spring");// null
//        hashtable.put(1,null);//

        System.out.println(hashtable);

        /*
        * hashTableSource
        * 1.底层有数组 HableTable$Entry[] 初始化大小为 11 threshold 8
        * 2.扩容机制
        *   value 不可以为null
        *   if (value == null) {
            throw new NullPointerException();
           }
         第一次扩容  this(11, 0.75f); 无参构造->含参构造
         put methods 添加K-V 封装到Entry中
         put => modCount++ -> addEntry(hash, key, value, index)
           扩容机制 if(count >= threshold) => rehash() -> (oldCapacity << 1) + 1 [向左位移1位 *2] (2n+1)
           -> modCount++ ->count++ (扩容的时候modCount++ 相等于扩容一次 modCount就比count大1)
        *
        */
    }
}
