/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.file.read;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Created by IntelliJ IDEA.
 * 转换流
 * @Author : 志敏.罗
 * @create 2022/7/10 16:19
 */
@SuppressWarnings({"all"})
public class InputStreamReaderBase {

    private String path ="/Users/luozhimin/Desktop/File/daily";

    /**
     * 介绍：
     *  1.InputStreamReader∶Reader的子类，可以将InputStream（字节流）包装成（转换）Reader（字符流）
     *  2.OutputStreamWriter∶Writer的子类，实现将OutputStream（字节流）包装成Writer（字符流）
     *  3.当处理纯文本数据时，如果使用字符流效率更高，并且可以有效解决中文问题，所以建议将字节流转换成字符流
     *  4.可以在使用时指定编码格式（比如 utf-8，gbk，gb2312，ISO8859-1等）
     */
    @Test
    @SneakyThrows
    void reader(){
        //字符流->字节流(乱码) 读取默认UTF8
//        BufferedReader bufferedReader = new BufferedReader(
//                new FileReader(path+"/file.java")
//        );
        //创建InputStreamReader new InputStreamReader(InputStream in,CharSet charset)
        InputStreamReader inputStreamReader = new InputStreamReader(
                new FileInputStream(path+"/file.java"), StandardCharsets.UTF_8
        );
        //转换 inputStreamReader->bufferedReader 包装俩次
//        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                new FileInputStream(path+"/file.java"), StandardCharsets.UTF_8
        ));
        String line;
        while ((line = bufferedReader.readLine())!=null){
            System.out.println(line);
        }
        bufferedReader.close();
    }
}
