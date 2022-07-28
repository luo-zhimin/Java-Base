package com.java.base.day.collectionAndMap.map.source;

import java.util.Comparator;
import java.util.TreeMap;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 志敏.罗
 * @create 2022/6/24 23:59
 */
public class TreeMapSourceAnalysis {

    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        /*
         * TreeMap
         *  [key need only same type object] key需要同一个类型的对象
         *  key need not null
         *  if (key == null)
                throw new NullPointerException();
            默认构造器 无排序 需要排序 传入匿名内部类 Comparator
            //把传入实现了Compartor接口的匿名内部类(对象) 传给了TreeMap的comparator
            public TreeMap(Comparator<? super K> comparator) {
                this.comparator = comparator;
            }
            第一次进来 直接添加 把key-value 封装到Entry里面[new Entry<>(key, value, null)] 放入root
            Entry<K,V> t = root;
            if (t == null) {
                compare(key, key); // type (and possibly null) check

                final int compare(Object k1, Object k2) {//第一次添加check null
                    return comparator==null ? ((Comparable<? super K>)k1).compareTo((K)k2)
                        : comparator.compare((K)k1, (K)k2);
                }

                root = new Entry<>(key, value, null);
                size = 1;
                modCount++;
                return null;
            }
            //以后添加
            Comparator<? super K> cpr = comparator;
            if (cpr != null) {
                do { //遍历所有的key 给当前的key找适当的位置
                    parent = t;
                    cmp = cpr.compare(key, t.key);//动态绑定 我们传入的compartor对象
                    if (cmp < 0)
                        t = t.left;
                    else if (cmp > 0)
                        t = t.right;
                    else
                        return t.setValue(value);//key相等直接 value happen repalce
                } while (t != null);
            }
         */
        TreeMap treeMap = new TreeMap(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
//                return ((String)o2).compareTo((String) o1);
                return ((String) o2).length() - ((String) o1).length();
            }
        });
        treeMap.put("1", 1);
        treeMap.put("2", null);
        treeMap.put("tom", "汤姆猫");
        treeMap.put("jerry", "杰瑞鼠");
        System.out.println(treeMap);
    }
}
