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

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 志敏.罗
 * @create 2022/7/10 16:20
 */
@SuppressWarnings({"all"})
public class OutputStreamWriterBase {

    private String path ="/Users/luozhimin/Desktop/File/daily";

    @Test
    @SneakyThrows
    void write(){
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                new FileOutputStream(path+"/file.java",true), StandardCharsets.UTF_8
        );
        outputStreamWriter.write("\n转换流");
        outputStreamWriter.close();
        System.out.println("OutputStreamWriter successful");
    }
}
