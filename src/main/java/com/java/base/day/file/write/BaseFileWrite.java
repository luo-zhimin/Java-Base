/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.file.write;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;

/**
 * Created by IntelliJ IDEA.
 * 字符(输出)流
 * @Author : 志敏.罗
 * @create 2022/7/7 22:31
 */
@SuppressWarnings({"all"})
public class BaseFileWrite {

    private String path = "/Users/luozhimin/Desktop/File/daily";

    /**
     * FileWriter常用方法
     * 1.new FileWriter（File/String）∶ 覆盖模式，相当于流的指针在首端
     * 2.new FileWriter（File/String，true）∶追加模式，相当于流的指针在尾端
     * 3.write（int）写入单个字符
     * 4.write（char[]）;写入指定数组
     * 5.write（char[]，offlen）∶写入指定数组的指定部分
     * 6.write（string）∶写入整个字符串
     * 7.write（string，off，len）∶写入字符串的指定部分
     * 相关API∶String类∶ toCharArray∶将String转换成char[]
     * 注意∶
     *  FileWriter使用后，必须要关闭（close）或刷新（flush），否则写入不到指定的文件!
     */
    @SneakyThrows
    @Test
    void fileWriteTest(){
        //创建fileWriter
        FileWriter fileWriter = new FileWriter(path+"/file.java",true);
        String name = "Java中有三种主要的循环结构： while 循环 do…while 循环 for 循环在Java5 中引入了一种主要用于数组的 ... 对于while 语句而言，如果不满足条件，则不能进入循环";
//        write（int）写入单个字符
//        fileWriter.write('罗');
        //write（char[]）;写入指定数组
//        fileWriter.write(name.toCharArray());
        //write（char[]，offlen）∶写入指定数组的指定部分
//        fileWriter.write(name.toCharArray(),0,4);
        //write（string）∶写入整个字符串
//        fileWriter.write("测试写入Writer字符");
        //write（string，off，len）∶写入字符串的指定部分
        fileWriter.write("上海北京",0,2);
        System.out.println("write successful");

        //close 源码分析
//        private void writeBytes() throws IOException {
//            bb.flip();
//            int lim = bb.limit();
//            int pos = bb.position();
//            assert (pos <= lim);
//            int rem = (pos <= lim ? lim - pos : 0);
//
//            if (rem > 0) {
//                if (ch != null) {
//                    if (ch.write(bb) != rem)
//                        assert false : rem;
//                } else {
//                    out.write(bb.array(), bb.arrayOffset() + pos, rem);
//                }
//            }
//            bb.clear();
//        }
        fileWriter.close();//close()->implClose()->writeBytes()
//        fileWriter.flush(); flsuh()+close()
    }
}


