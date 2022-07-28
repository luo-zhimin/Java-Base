/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.file.exercise;

import com.java.base.day.file.Dog;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 志敏.罗
 * @create 2022/7/10 21:28
 */
@SuppressWarnings({"all"})
public class Exercise {

    private String path = "/Users/luozhimin/Desktop/File/daily";

    @Test
    @SneakyThrows
    void test1() {
        File file = new File(path + "/java");
        System.out.println(file.getPath());
        if (!file.exists()) {
            //create
            file.mkdirs();
            FileOutputStream fileOutputStream = new FileOutputStream(file.getPath() + "/test.java");
            fileOutputStream.write("Hello,Word!".getBytes(StandardCharsets.UTF_8));
            fileOutputStream.close();
            System.out.println("create");
        } else {
            System.out.println("the file is exists");
        }
    }

    @Test
    @SneakyThrows
    void reader() {
        File file = new File(path + "/file.java");
        //转换流
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file),StandardCharsets.UTF_8);
//        BufferedReader bufferedReader = new BufferedReader(
//                new FileReader(file)
//        );
        BufferedReader bufferedReader = new BufferedReader(
                inputStreamReader
        );
        String line;
        int lineLength=0;
        while ((line = bufferedReader.readLine()) != null){
            System.out.println((++lineLength)+"."+line);
        }
        bufferedReader.close();
    }


    @Test
    @SneakyThrows
    void createPropertise() {
        Properties properties = new Properties();
        properties.setProperty("name","tom");
        properties.setProperty("age","22");
        properties.setProperty("color","red");
        properties.setProperty("six","男");
        properties.store(new FileWriter(path+"/dog.porperties"),"dog");
        System.out.println("create successful");
        Dog dog = new Dog(properties.getProperty("name"), Integer.parseInt(properties.getProperty("age")));
        System.out.println(dog);
        //序列化到。。
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream(path+"/dog.dat")
        );
        objectOutputStream.writeObject(dog);
        objectOutputStream.close();
        System.out.println("序列化成功");
        ObjectInputStream objectInputStream = new ObjectInputStream(
                new FileInputStream(path+"/dog.dat")
        );
        Dog readObject = (Dog) objectInputStream.readObject();
        System.out.println("反序列化成功 "+readObject);
        objectInputStream.close();
        System.out.println("successful");
    }
}
