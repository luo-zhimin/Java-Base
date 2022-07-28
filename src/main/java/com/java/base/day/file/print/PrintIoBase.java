/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.file.print;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * Created by IntelliJ IDEA.
 * 打印流
 * @Author : 志敏.罗
 * @create 2022/7/10 17:30
 */
@SuppressWarnings({"all"})
public class PrintIoBase {

    private String path = "/Users/luozhimin/Desktop/File/daily";

    @Test
    @SneakyThrows
    void printStreamTest() {
        //字节流
        PrintStream out = new PrintStream(System.out, true);
        //默认情况下 PrintStream 输出位置 标准输出 显示器
//        out.println("PrintStream System.out");
        System.setOut(new PrintStream(path + "/file.java"));
//        out.write("PrintStream System.out".getBytes(StandardCharsets.UTF_8));
        System.out.println("PrintStream System.out");
        out.close();

        //修改输出打印流的设备
//        public static void setOut(PrintStream out) {
//            checkIO();//security!=null ->setIo
//            setOut0(out);//修改打印位置
//        }
//        public void println(String x) {
//            synchronized (this) {
//                print(x);
//                newLine();
//            }
//        }
//        public void print(String s) {
//            if (s == null) {
//                s = "null";
//            }
//            write(s);//本质是使用的write
//        }
    }

    @Test
    @SneakyThrows
    void printWriteTest() {
        //字符流
        PrintWriter printWriter = new PrintWriter(new FileWriter(path+"/file.java",true));
        printWriter.write("北京你好");
        printWriter.close();//StreamEncoder->implClose 写入数据
        System.out.println("printWriter successful");
    }
}
