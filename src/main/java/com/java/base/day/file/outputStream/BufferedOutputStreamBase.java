/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.file.outputStream;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

/**
 * Created by IntelliJ IDEA.
 * 字节包装流 操作字节
 * @Author : 志敏.罗
 * @create 2022/7/9 17:56
 */
@SuppressWarnings({"all"})
public class BufferedOutputStreamBase {

    private String path ="/Users/luozhimin/Desktop/File/daily";

    @Test
    @SneakyThrows
    void bufferedOutputStreamTest(){
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                new FileOutputStream(path+"/file.java",true)
        );
        bufferedOutputStream.write("死锁".getBytes(StandardCharsets.UTF_8));
        System.out.println("successful write");
        bufferedOutputStream.close();
    }

    /**
     * 字节copy
     */
    @Test
    @SneakyThrows
    void bufferedCopy(){
        //pricture copy
        //read
        BufferedInputStream bufferedInputStream = new BufferedInputStream(
                new FileInputStream(path+"/write.mp3")
        );
        //write
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                new FileOutputStream(path+"/bufferedOutputStream.mp3",true)
        );
        int read;
        //效率读取
        byte[] bytes = new byte[1024];
        while ((read = bufferedInputStream.read(bytes))!=-1){
            //使用效率读取 需要使用对应的write方法
            bufferedOutputStream.write(bytes,0,read);
        }
        System.out.println("buffered copy successful");
        //successful finally close
        bufferedInputStream.close();
        bufferedOutputStream.close();
    }
}
