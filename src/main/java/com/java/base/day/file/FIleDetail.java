/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.file;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 志敏.罗
 * @create 2022/7/7 23:20
 */
@SuppressWarnings({"all"})
public class FIleDetail {

    /**
     * 修饰器设计模式演示
     * <p>
     * 1.节点流是底层流/低级流，直接跟数据源相接。
     * 2.处理流（（包装流）包装节点流，既可以消除不同节点流的实现差异，也可以提供更方便的方法来完成输入输出。
     * 3.处理流（也叫包装流）对节点流进行包装，使用了修饰器设计模式，不会直接与数据源相连
     * <p>
     * 处理流的功能主要体现在以下两个方面
     * 1.性能的提高∶主要以增加缓冲的方式来提高输入输出的效率。
     * 2.操作的便捷∶处理流可能提供了一系列便捷的方法来一次输入输出大批量的数据，使用更加灵活方健
     */
    @Test
    void nodeStream() {
        //多态 + abstract => 修饰器设计模式
        BufferedReader_ bufferedReader_2 = new BufferedReader_(new StringReader_());
        bufferedReader_2.readStrings(1);

        BufferedReader_ reader_ = new BufferedReader_(new Reader_() {

            @Override
            public void readFile() {
                System.out.println("abstract interface update");
            }

            @Override
            public void readString() {
                super.readString();
            }
        });
        reader_.readFiles(2);

        BufferedReader_ bufferedReader_ = new BufferedReader_(new FileReader_());
        bufferedReader_.readFiles(2);
    }

    /**
     * 标准输入输出流
     */
    @Test
    void normalInputAndOutput(){
        //标准输入 键盘
        //System类的 public final static InputStream in = null;
        //System.in 编译类型 InputStream 运行类型 BufferedInputStream
        System.out.println("运行类型 "+System.in.getClass());
        //标准输出 显示器
        //System类的 public final static PrintStream out = null;
        //System.in 编译类型 PrintStream 运行类型 PrintStream
        System.out.println("运行类型 "+System.out.getClass());

        Scanner scanner = new Scanner(System.in);
        System.out.println("你好，请输入数字：");
        String next = scanner.next();
        System.out.println(next);
    }
}

//抽象类
abstract class Reader_ {
    public void readFile() {}

    public void readString() {}
}

//节点流 只能对具体的数据源操作
class FileReader_ extends Reader_ {

    public void readFile() {
        System.out.println("对文件进行读取..");
    }
}

class StringReader_ extends Reader_ {

    public void readString() {
        System.out.println("对字符串进行读取..");
    }
}

//处理流 包装流
@Data
@EqualsAndHashCode(callSuper = true)
class BufferedReader_ extends Reader_ {
    //属性是Reader_类型
    private Reader_ reader_;

    public BufferedReader_(Reader_ reader_) {
        this.reader_ = reader_;
    }

    //封装 多次读取
    public void readFiles(int number) {
        for (int i = 0; i < number; i++) {
            reader_.readFile();
        }
    }

    public void readStrings(int number){
        for (int i = 0; i < number; i++) {
            reader_.readString();
        }
    }
}