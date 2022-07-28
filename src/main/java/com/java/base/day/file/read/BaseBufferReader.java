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
import java.io.FileReader;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 志敏.罗
 * @create 2022/7/7 23:20
 */
@SuppressWarnings({"all"})
public class BaseBufferReader {

    private String path = "/Users/luozhimin/Desktop/File/daily";

    @Test
    @SneakyThrows
    void knowBufferReader() {
        //高效读取
        //bufferedReader 包装流->缓冲流 ->new BufferedReader(Reader in) 所有的reader的子类 节点流
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader(path + "/file.java")
        );
        //readLine 按行读取
        String line;
        while ((line = bufferedReader.readLine()) != null){
            System.out.println(line);
        }
        //关闭流 只需要关闭包装流 因为底层会自动关闭节点流
        bufferedReader.close();

        //bufferedReader.close(); 源码分析
        //public void close() throws IOException {
        //        synchronized (lock) {
        //            if (in == null)
        //                return;
        //            try {
        //                in.close();//in就是我们传入的对象 new xxReader("xxx")
        //多态动态绑定 却找对应的的实现  finally->buf = null;
        //            } finally {
        //                in = null;
        //                cb = null;
        //            }
        //        }
        //    }
    }
}
