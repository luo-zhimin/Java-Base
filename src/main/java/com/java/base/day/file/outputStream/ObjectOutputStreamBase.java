/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.file.outputStream;

import com.java.base.day.file.Dog;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * Created by IntelliJ IDEA.
 * ObjectOutputStream 提供序列化功能
 *
 * @Author : 志敏.罗
 * @create 2022/7/9 23:15
 */
@SuppressWarnings({"all"})
public class ObjectOutputStreamBase {

    private String path = "/Users/luozhimin/Desktop/File/daily";

    @Test
    @SneakyThrows
    void ObjectOutputStreamTest() {
        //序列化后保存的文本不是存文本 而是他的形式
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream(path + "/data.txt", true)
        );
        //保存数据到data.txt 需要实现Serializable
        objectOutputStream.writeInt(100);//int->Integer
        objectOutputStream.writeBoolean(true);//boolean->Boolean
        objectOutputStream.writeChar('A');//char->Character
        objectOutputStream.writeDouble(1.9d);//double->Double
        objectOutputStream.writeUTF("序列化");//String
        Dog dog = new Dog("大黄", 22);
        objectOutputStream.writeObject(dog);
        objectOutputStream.writeFloat(1.1f);//float->Float
        objectOutputStream.close();
        System.out.println("successful(序列化)");
    }
}