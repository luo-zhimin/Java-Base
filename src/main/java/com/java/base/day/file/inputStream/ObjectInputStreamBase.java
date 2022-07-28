/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.file.inputStream;

import com.java.base.day.file.Dog;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * Created by IntelliJ IDEA.
 * ObjectInputStream 提供反序列化功能
 * @Author : 志敏.罗
 * @create 2022/7/9 23:15
 */
@SuppressWarnings({"all"})
public class ObjectInputStreamBase {

    private String path ="/Users/luozhimin/Desktop/File/daily";

    /**
     * 对象流(序列化/反序列化)
     *  功能：提供了对基本类型或对象类型的序列化和反序列化的方法
     *  序列化和反序列化
     * 1.序列化就是在保存数据时，保存数据的值和数据类型
     * 2.反序列化就是在恢复数据时，恢复数据的值和数据类型
     * 3.需要让某个对象支持序列化机制，则必须让其类是可序列化的，为了让某个类是可序列化的，该类必须实现如下两个接口之一∶
     *     Serializable // 这是一个标记接口，没有方法
     *     Externalizable //该接口有方法需要实现，因此我们一般实现上面的 Serializable接口
     * 注意细节
     *  1）读写顺序要一致
     *  2）要求序列化或反序列化对象，需要 实现 Serializable
     *  3）序列化的类中建议添加SerialVersionUID，为了提高版本的兼容性
     *  4）序列化对象时，默认将里面所有属性都进行序列化，但除了static或transient修饰的成员
     *  5）序列化对象时，要求里面属性的类型也需要实现序列化接口
     *  6）序列化具备可继承性，也就是如果某类已经实现了序列化，则它的所有子类也已经默认实现了序列化
     */
    @Test
    @SneakyThrows
    void ObjectInputStreamTest(){
        ObjectInputStream objectInputStream = new ObjectInputStream(
                new FileInputStream(path+"/data.txt")
        );

        //读取
        System.out.println(objectInputStream.readInt());
        System.out.println(objectInputStream.readBoolean());
        System.out.println(objectInputStream.readChar());
        System.out.println(objectInputStream.readDouble());
        System.out.println(objectInputStream.readUTF());
        System.out.println((Dog)objectInputStream.readObject());
        System.out.println(objectInputStream.readFloat());

        //close
        objectInputStream.close();
    }
}
