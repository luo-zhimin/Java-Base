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

import java.io.BufferedInputStream;
import java.io.FileInputStream;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 志敏.罗
 * @create 2022/7/9 17:55
 */
@SuppressWarnings({"all"})
public class BufferedInputStreamBase {

    private String path ="/Users/luozhimin/Desktop/File/daily";

    /**
     *
     */
    @Test
    @SneakyThrows
    void bufferedInputStreamTest(){
        //创建 BufferedInputStream new BufferedInputStream(InputStream in) InputStream的子类
        BufferedInputStream bufferedInputStream = new BufferedInputStream(
                new FileInputStream(path+"/file.java")
        );
        byte[] bytes = new byte[1024];
        int read;
        while ((read = bufferedInputStream.read(bytes))!=-1){
            System.out.println(new String(bytes,0,read));
        }
        //包装流(处理流)->close->{InputStream.close...}
        bufferedInputStream.close();

        //bufferedInputStream.close()
//        public void close() throws IOException {
//            byte[] buffer;
//            while ( (buffer = buf) != null) {
//                if (bufUpdater.compareAndSet(this, buffer, null)) {
//                    InputStream input = in;
//                    in = null;
//                    if (input != null)
//                        input.close();//->InputStream.close->动态绑定
//                    return;
//                }
//                // Else retry in case a new buf was CASed in fill()
//            }
//        }
    }
}
