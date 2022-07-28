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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

/**
 * Created by IntelliJ IDEA.
 * 输出流
 *
 * @Author : 志敏.罗
 * @create 2022/7/7 21:52
 */
@SuppressWarnings({"all"})
public class OutputStreamBase {

    private String path = "/Users/luozhimin/Desktop/File/daily";

    /**
     * 将数据写入文件中 如果文件不存在 则创建
     */
    @SneakyThrows
    @Test
    void wirteFile() {
        //创建FileOutputStream 默认是flase append 会进行覆盖 true追加
        FileOutputStream fileOutputStream = new FileOutputStream(path + "/file.java", true);
        //单个字节
//        fileOutputStream.write('a');
        //写入字符串
        String name = "Hello Word";
        //写入多个字节 字节数组  现在会进行覆盖
//        fileOutputStream.write(name.getBytes(StandardCharsets.UTF_8));
        //写入指定的大小 off->before len->end
//        write(byte b[], int off, int len);
        fileOutputStream.write(name.getBytes(StandardCharsets.UTF_8), 0, 5);
        //finally close
        fileOutputStream.close();
    }

    /**
     * copy pricture and music
     */
    @SneakyThrows
    @Test
    void wirtePrictureAndMusic() {
        String readPath = "/Users/luozhimin/Desktop/File/package/分享资料";
        //先读取 在写出 如果文件很大 需要读取部分就开始写入指定的文件
        FileInputStream fileInputStream = new FileInputStream(readPath + "/高山流水.mp3");
        FileOutputStream fileOutputStream = new FileOutputStream(path+"/write.mp3");
        //提高读取效率
        byte[] bytes = new byte[1024];
        int read;
        while ((read = fileInputStream.read(bytes)) != -1) {
            //读取之后就写入文件 边读边写
            // write(read); 会出现文件损坏
            fileOutputStream.write(bytes, 0, read);
        }
        System.out.println("copy successful");
        //close
        fileInputStream.close();
        fileOutputStream.close();
    }
}
