package com.java.base.day.collectionAndMap.map.source;

import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 志敏.罗
 * @create 2022/6/24 17:38
 */
public class PropertiesSourceAnalysis {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        /*
         * Properties
         *  1.Properties类 继承 自Hashtable类 并且实现了Map接口，也是使用一种键值对的形式来保存数据。
         *  2.他的使用特点和Hashtable类似
         *  3.Properties 还可以用于从xx.properties 文件中，加载数据到Properties类对象并进行读取和修改
         */
        Properties properties = new Properties();
        properties.put("1","java");
        properties.put("2","spring");
        properties.put("3","cloud");
        properties.put("4","vue");
        System.out.println(properties);
        //get
        System.out.println(properties.get("1"));
        //remove
        System.out.println(properties.remove("2"));
        //replace
        System.out.println(properties.put("3", "alibaba"));
        //end
        System.out.println("end = "+properties);
    }
}
