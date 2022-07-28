/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.file.zip;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 志敏.罗
 * @create 2022/7/17 16:50
 */
@SuppressWarnings({"all"})
public class BaseGZip {

    private String path = "/Users/luozhimin/Desktop/File/daily";

    @Test
    @SneakyThrows
    void baseGZipInputStream() {
        String source = path + "/JDK_API_1.6_zh_中文.CHM";
        String target = path + "/api.zip";
        GZIPInputStream gzipInputStream = new GZIPInputStream(new FileInputStream(target));
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(source));
        byte[] bytes = new byte[1024*1024];
        int read;
        while ((read= gzipInputStream.read(bytes))!=-1){
            bufferedOutputStream.write(bytes,0,read);
        }
        bufferedOutputStream.close();
        gzipInputStream.close();
        System.out.println("减压成功～");
    }

    @Test
    @SneakyThrows
    void baseGZipOutputStream() {
        String source = path + "/javaAPI文档/JDK_API_1.6_zh_中文.CHM";
        String target = path + "/api.zip";
        GZIPOutputStream gzipOutputStream = new GZIPOutputStream(new FileOutputStream(target));
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(source));
        byte[] bytes = new byte[1024*1024];
        int read;
        while ((read = bufferedInputStream.read(bytes)) != -1) {
            gzipOutputStream.write(bytes,0,read);
        }

        gzipOutputStream.close();
        bufferedInputStream.close();
        System.out.println("压缩成功～");
    }
}
