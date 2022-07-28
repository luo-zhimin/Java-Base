package com.java.base.day.collectionAndMap.collection;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 志敏.罗
 * @create 2022/6/19 19:10
 */
public class SetDetail {

    public static void main(String[] args) {
        /*
         * Set
         *  无序（添加和取出的顺序不一致），没有索引
         *  不允许重复元素，最多包含一个null
         *  JDK Api中的Set接口实现类有
         *      HashSet/TreeSet
         *
         * 同Collection的遍历方式一样，因为Set接口是Collection接口的子接口:
         *  1.可以使用迭代器
         *  2.增强for
         *  3.不能使用索引的方式来获取.
         */

        /*
         *  set 接口的实现类的对象(Set 接口对象), 不能存放重复的元素, 可以添加一个 null
         *  set 接口对象存放数据是无序(即添加的顺序和取出的顺序不一致)
         *  注意：取出的顺序的顺序虽然不是添加的顺序，但是他是固定的
         */
        Set set = new HashSet<>();
        set.add(1);
        set.add(2);//Value.of()
        set.add(1);//重复
        set.add(null);
        set.add("tom");
        System.out.println(set.size());
        System.out.println("set "+set);

        //遍历方式
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println("Set iterator "+iterator.next());
        }
        set.forEach(s-> System.out.println("set for "+s));
    }
}
