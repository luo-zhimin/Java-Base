package com.java.base.day.collectionAndMap.collection.source.set;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 志敏.罗
 * @create 2022/6/25 00:00
 */
public class TreeSetSourceAnalysis {

    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        /*
         * TreeSet
         *  当我们使用无参构造创建的时候 依然是无序的 需要有序需要去重写compare方法 匿名内部类 比较器实现
         *  TreeSet中存储的类型必须是一致的
         *      public TreeSet(Comparator<? super E> comparator) {
                    this(new TreeMap<>(comparator));
                }
                //treeMap
                public TreeMap(Comparator<? super K> comparator) {
                    this.comparator = comparator;
                }
               //cpr 就是我们的匿名内部类(对象) 比较器
               Comparator<? super K> cpr = comparator;
               if (cpr != null) {
                    do {
                        parent = t;
                        //动态绑定到我们的匿名内部类(对象)compare
                        cmp = cpr.compare(key, t.key);
                        if (cmp < 0)
                            t = t.left;
                        else if (cmp > 0)
                            t = t.right;
                        else //如果相等 cmp=0 replace key就不可以加入了
                            return t.setValue(value);
                    } while (t != null);
                }
               add 根据规则走 比如 string length same only has one happen replace setValue(value)
         */
        TreeSet treeSet = new TreeSet(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                System.out.println("comparator "+o2+"--"+o2);
//                return ((String)o2).compareTo((String) o1);
                //big->small
                return ((String)o2).length()-((String) o1).length();
            }
        });
        treeSet.add("1");
        treeSet.add("2");
        treeSet.add("3");
        treeSet.add("jack");
        treeSet.add("true");
        treeSet.add("java");
        // [jack/1] because other value happen repale compare string length
        System.out.println(treeSet);
    }
}
