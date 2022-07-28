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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * Created by IntelliJ IDEA.
 * 字符 不可以操作字节 可能会发生文件损坏(视频/音频/Word/PDF..)
 * @Author : 志敏.罗
 * @create 2022/7/8 22:27
 */
@SuppressWarnings({"all"})
public class BaseBufferedWrite {

    private String path = "/Users/luozhimin/Desktop/File/daily";

    @Test
    @SneakyThrows
    void knowBufferedWrite(){
        BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(path+"/file.java",true)
        );
        String name = "BufferedWriter write1";
        bufferedWriter.newLine();
        bufferedWriter.write(name);
        bufferedWriter.newLine();//插入一个系统的换行
        bufferedWriter.write(name);
        bufferedWriter.newLine();
        System.out.println("bufferedWrite successful");
        //关闭包装流 底层会自动关闭节点流
        bufferedWriter.close();
    }

    /**
     * 文本拷贝
     */
    @Test
    @SneakyThrows
    void knowBufferedCopy(){
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader(path+"/file.java")
        );
        BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(path+"/newFileCopy.java",true)
        );

        String line;
        while ((line = bufferedReader.readLine())!=null){
            bufferedWriter.write(line);
            bufferedWriter.newLine();
        }
        System.out.println("bufferedCopy successful");
        bufferedReader.close();
        bufferedWriter.close();
    }
}
