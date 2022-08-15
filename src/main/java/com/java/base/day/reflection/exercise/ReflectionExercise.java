/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.reflection.exercise;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 志敏.罗
 * @create 2022/7/24 18:53
 */
public class ReflectionExercise {

    private String path="/Users/luozhimin/Desktop/File/daily";

    @Test
    @SneakyThrows
    void exercise01(){
        Class<?> reflectionClass = Class.forName("com.java.base.day.reflection.exercise.reflectionTest");
        //获取实例化对象
        Object reflection = reflectionClass.newInstance();
        Field field = reflectionClass.getDeclaredField("name");
        field.setAccessible(true);
        field.set(reflection,"reflection private update");
        //getName()
        Method method = reflectionClass.getMethod("getName");
        Object invoke = method.invoke(reflection);
        System.out.println(invoke);
    }

    /**
     * IO+reflection
     * 要求
     * 1.利用Class类的forName方法得到File类的class对象
     * 2.在控制台打印File类的所有构造器
     * 3.通过newInstance的方法创建File对象，并创建
     */
    @Test
    @SneakyThrows
    void reflectionExercise02(){
        Class<?> fileClass = Class.forName("java.io.File");
        for (Constructor<?> fileClassDeclaredConstructor : fileClass.getDeclaredConstructors()) {
            System.out.println("file类的构造器 "+fileClassDeclaredConstructor);
        }
        Constructor<?> fileConstructor = fileClass.getConstructor(String.class);
        //创建对象
        Object file = fileConstructor.newInstance(path+"/reflection.java");
        System.out.println("运行类型 "+file.getClass());
        Method method = fileClass.getMethod("exists");
        Object returnValue = method.invoke(file);
        if (returnValue.equals(true)){
            System.out.println("this file is existence");
            return;
        }
        //调用方法
        Method createNewFile = fileClass.getMethod("createNewFile");
        createNewFile.invoke(file);
        System.out.println("successful create file by reflection");
    }


    /**
     * 获取指定的结构 方法 构造器
     */
    @Test
    @SneakyThrows
    void exercise02(){
        Class<reflectionTest> reflectionTestClass = reflectionTest.class;
        reflectionTest instance = reflectionTestClass.newInstance();
        //获取指定的属性
        Field name = reflectionTestClass.getDeclaredField("name");
        //private need accessible true
        name.setAccessible(true);
        //clazz update name
        name.set(instance,"clazz update name");
        //获取对象的值
        System.out.println("filed -> "+name.get(instance));
        //获取指定的方法
        Method declaredMethod = reflectionTestClass.getDeclaredMethod("getName");
        Object invoke = declaredMethod.invoke(instance);
        System.out.println("methods->  "+invoke);
        //获取指定的构造器
        Constructor<reflectionTest> declaredConstructor = reflectionTestClass.getDeclaredConstructor(String.class, Integer.class);
        System.out.println("Constructor-> "+declaredConstructor);
        //static or final static field or methods
        Field staticFinalHobby = reflectionTestClass.getDeclaredField("address");
        staticFinalHobby.setAccessible(true);
        staticFinalHobby.set(instance,"clazz static final update address");
        System.out.println(staticFinalHobby.get(instance));
        //method
        Method getStaticAge = reflectionTestClass.getDeclaredMethod("getStaticAge",int.class);
        System.out.println(getStaticAge.invoke(instance,10));
    }
}
@AllArgsConstructor
@NoArgsConstructor
class reflectionTest{
    private String name="hello kitty";
    public Integer age = 10;

    private static String address = "reflectionTest shanghai";

    private final static String hobby="computer reflectionTest";

    public String getName() {
        return name+" reflectionTest";
    }

    private String getAge(){
        return age+" reflectionTest ";
    }

    static Integer getStaticAge(int age){
        return age;
    }
}
