/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.file.properties;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * Properties类
 * @Author : 志敏.罗
 * @create 2022/7/10 18:02
 */
@SuppressWarnings({"all"})
public class PropertiesBase {

    private String path = "/Users/luozhimin/Desktop/File/daily";

    @Test
    @SneakyThrows
    void normalRead() {
        URL url = PropertiesBase.class.getResource("/test.properties");
//        FileInputStream fileInputStream = new FileInputStream(url.getFile());
//        int read;
//        byte[] bytes = new byte[1024];
//        while ((read = fileInputStream.read(bytes))!=-1){
//            System.out.println(new String(bytes, 0, read));
//        }
        System.out.println(url);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(url.getFile()));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] split = line.split("=");
            System.out.println(split[0] + " " + split[1]);
        }
        bufferedReader.close();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(url.getFile(),true));
        bufferedWriter.write("address=class.Driver.mysql");
        bufferedWriter.close();
    }

    /**
     * 1.专门用于读写配置文件的集合类配置文件的格式∶键=值键-值
     * 2.注意∶键值对不需要有空格，值不需要用引号一起来。默认类型是String
     * 3.Properties的常见方法
     *  load∶加载配置文件的键值对到Properties对象
     *  list:将数据显示到指定设备
     *  getProperty（key）;根据键获取值
     *  SetProperty（key，value）∶设置键值对到Properties对象
     *  store∶将Properties中的键值对存储到配置文件，在idea 中，保存信息到配置文件，如果含有中文，会存储为unicode码
     * <a herf='http//tool.chinaz.com/tools/unicode.aspx' alt='unicode码查询工具'>
     */
    @Test
    @SneakyThrows
    void propertiesTest(){
        //创建Properties对象
        Properties properties = new Properties();
        //加载指定的配置文件
        properties.load(new FileReader(PropertiesBase.class.getResource("/test.properties").getFile()));
        //显示-把毽子对显示到控制台
        properties.list(System.out);
        //指定得到 read
        System.out.println("...get..");
        System.out.println(properties.getProperty("user"));
        System.out.println(properties.get("address"));
        //properties 创建或者修改
        //update
        System.out.println("...set...");
        properties.setProperty("charset","utf8");
        properties.setProperty("user","管理员");
        properties.setProperty("test","汤姆");
        //将k-v存储到文件中
        properties.store(new FileWriter(path+"/test.properties"),"test.properties");
        System.out.println("set successful");


        //properties 父类是hashtable 底层是hashTable
//        public synchronized V put(K key, V value) {
//        // Make sure the value is not null
//        if (value == null) {//value is  not null
//            throw new NullPointerException();
//        }
//
//        // Makes sure the key is not already in the hashtable.
//        Entry<?,?> tab[] = table;
//        int hash = key.hashCode();
//        int index = (hash & 0x7FFFFFFF) % tab.length;
//        @SuppressWarnings("unchecked")
//        Entry<K,V> entry = (Entry<K,V>)tab[index];
//        for(; entry != null ; entry = entry.next) {
//            if ((entry.hash == hash) && entry.key.equals(key)) {
//                V old = entry.value;
//                entry.value = value;//如果key存在 替换
//                return old;
//            }
//        }
//
//        addEntry(hash, key, value, index);//key不存在就创建
//        return null;
//    }
    }
}
