/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.file.inputStream;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by IntelliJ IDEA.
 * 初识IO 流分类 原理 InputStream -> 字节(输入)流
 * @Author : 志敏.罗
 * @create 2022/7/6 23:09
 */
@SuppressWarnings({"all"})
public class InputStreamBase {

    private String path ="/Users/luozhimin/Desktop/File/daily";


    /**
     * 1.I/O是Input/Output的缩写，I/O技术是非常实用的技术，用于处理数据传输 如读/写文件，网络通讯等。
     * 2.Java程序中，对于数据的输入/输出操作以"流（stream）" 的方式进行。
     * 3.java.io包下提供了各种"流"类和接口，用以获取不同种类的数据，并通过方法输入或输出数据
     * 4.输入input∶读取外部数据（磁盘、光盘等存储设备的数据）到程序（内存）中。
     * 5.输出output∶将程序（内存）数据输出到磁盘、光盘等存储设备中
     */
    //SneakyThrows 丝尼k throws
    @SneakyThrows
    @Test
    void ReadFile(){
        //FileInputStream
        File file = new File(path+"/file.java");
        if (!file.exists()){
            file.createNewFile();
            System.out.println("创建成功");
        }
        //创建FileInputStram对象
        FileInputStream fileInputStream = new FileInputStream(path+"/file.java");
        //单个字节的读取，效率比较低 read()
        int read;
        while ((read = fileInputStream.read()) !=-1){
            //字节流 读取汉字 乱码 因为一个中文占据3个字节 读取汉字->字符流
            System.out.print((char) read);
        }
        //finally close
        fileInputStream.close();
    }


    /**
     * read array
     * read(byte[] b) > read() 单个字节读取
     */
    @SneakyThrows
    @Test
    void ReadFileArray(){
        //创建FileInputStram对象
        FileInputStream fileInputStream = new FileInputStream(path+"/file.java");
        //byet[] 数组读取
        byte[] bytes = new byte[8];
        int read;
        //读取正常 返回实际读取的字节数
        while ((read = fileInputStream.read(bytes)) !=-1){
            //new String(bytes,0,read) byte[],before,length
            System.out.print(new String(bytes,0,read));
        }
        //finally close
        fileInputStream.close();
    }
}
