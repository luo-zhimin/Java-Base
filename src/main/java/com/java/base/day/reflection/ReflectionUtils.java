/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.reflection;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * 通过反射获取类的结构信息
 *
 * @Author : 志敏.罗
 * @create 2022/7/23 23:37
 */
public class ReflectionUtils {

    /**
     * java.lang.Class类
     *  1.getName∶获取全类名
     *  2.getSimpleName∶获取简单类名
     *  3.getFields∶获取所有public修饰的属性，包含本类以及父类的
     *  4.getDeclaredFields∶获取本类中所有属性
     *  5.getMethods∶获取所有public修饰的方法，包含本类以及父类的
     *  6.getDeclaredMethods∶获取本类中所有方法
     *  7.getConstructors∶获取本类所有public修饰的构造器
     *  8.getDeclaredConstructors∶获取本类中所有构造器
     *  9.getPackage∶以Package形式返回包信息
     *  10.getSuperClass∶以Class形式返回父类信息
     *  11.getInterfaces∶以Class【】形式返回接口信息
     *  12.getAnnotations∶以Annotation【】形式返回注解信息
     */
    @Test
    @SneakyThrows
    void reflectionToClass() {
        Class<?> catClass = getCatClass();
        System.out.println("获取全类名 " + catClass.getName());
        System.out.println();
        System.out.println("获取简单类名 " + catClass.getSimpleName());
        System.out.println();
        //public的属性
        for (Field field : catClass.getFields()) {
            System.out.println("本类以及父类的属性 "+field.getName());
        }
        System.out.println();
        //本类的所有属性 public private
        for (Field declaredField : catClass.getDeclaredFields()) {
            System.out.println("获取本类中所有属性 "+declaredField.getName());
        }
        System.out.println();
        //get public method
        for (Method catClassMethod : catClass.getMethods()) {
            System.out.println("包含本类以及父类的 "+catClassMethod.getName());
        }
        System.out.println();
        //本类所有的方法
        for (Method catClassDeclaredMethod : catClass.getDeclaredMethods()) {
            System.out.println("本类所有的方法 "+catClassDeclaredMethod.getName());
        }
        System.out.println();
        //获取public 构造器 本类
        for (Constructor<?> catClassConstructor : catClass.getConstructors()) {
            System.out.println("本类构造器 "+catClassConstructor);
        }
        System.out.println();
        //本类所有的构造器
        for (Constructor<?> declaredConstructor : catClass.getDeclaredConstructors()) {
            System.out.println("本类所有的构造器 "+declaredConstructor);
        }
        System.out.println();
        //返回包信息
        System.out.println("包信息 "+catClass.getPackage());
        System.out.println();
        //以Class形式返回父类信息
        System.out.println("父类信息 "+catClass.getSuperclass());
        System.out.println();
        //返回接口信息
        for (Class<?> catClassInterface : catClass.getInterfaces()) {
            System.out.println("接口信息 "+catClassInterface);
        }
        System.out.println();
        //返回注解信息
        for (Annotation catClassAnnotation : catClass.getAnnotations()) {
            System.out.println("注解信息 "+catClassAnnotation);
        }
        System.out.println();
        //本类所有的注解信息
        for (Annotation declaredAnnotation : catClass.getDeclaredAnnotations()) {
            System.out.println("本类所有的注解信息 "+declaredAnnotation);
        }
        System.out.println();
        //获取implements接口时的标注的注解和接口 如class  C implements @AnnoA A,@AnnoB B {  }
        for (AnnotatedType annotatedInterface : catClass.getAnnotatedInterfaces()) {
            System.out.println(annotatedInterface.getType());
        }
    }

    /**
     * java.long.reflect.Field
     *  1.getModifiers∶以int形式返回修饰符
     *      【说明默认修饰符是0，public是1，private是2，protected是4，static是8，final是16】，修饰符类型不同相加 public（1）+static（8）=9
     *  2.getType∶以Class形式返回类型
     *  3.getName∶返回属性名
     */
    @Test
    @SneakyThrows
    void reflectionToField(){
        Class<?> catClass = getCatClass();
        //获取本类所有的属性 修饰符类型不同相加 public（1）+static（8）=9
        for (Field declaredField : catClass.getDeclaredFields()) {
            System.out.println("本类所有的属性 "+declaredField+"\t 该属性的修饰符值="+declaredField.getModifiers()
                    +"\t该属性的返回类型 "+declaredField.getType()+" 属性名 "+declaredField.getName());
        }
    }

    /**
     * java.long.reflect.Method
     *  1.getModifiers∶以int形式返回修饰符
     *  【说明∶默认修饰符是0，public是1，private是2，protected是4，static是8，final是16】
     *  2.getReturnType∶以Class形式获取返回类型
     *  3.getName∶返回方法名
     *  4.getParameterTypes∶以Class【】返回参数类型数组
     */
    @Test
    @SneakyThrows
    void reflectionToMethod(){
        Class<?> catClass = getCatClass();
        //获取本类所有的方法
        for (Method declaredMethod : catClass.getDeclaredMethods()) {
            System.out.println("本类所有的方法 "+declaredMethod+"\t该方法修饰符的值 "+declaredMethod.getModifiers()
                    +"\t该方法返回类型 "+declaredMethod.getReturnType()+"\n 方法名 "+declaredMethod.getName()
                    +" 返回该方法的参数类型数组 "+ Arrays.toString(declaredMethod.getParameterTypes()));
        }
    }

    /**
     * java.long.reflect.Constructor
     *  1.getModifiers∶以int形式返回修饰符
     *  2.getName∶返回构造器名（全类名）
     *  3.getParameterTypes∶以Class【】返回参数类型数组
     */
    @Test
    @SneakyThrows
    void reflectionToConstructor(){
        Class<?> catClass = getCatClass();
        //获取本类的所有的构造器
        for (Constructor<?> declaredConstructor : catClass.getDeclaredConstructors()) {
            System.out.println("本类的所有的构造器 "+declaredConstructor+" 构造器的修饰符的值 "
                    +declaredConstructor.getModifiers()+"\n名字 "+declaredConstructor.getName()+" 返回参数类型数组"+Arrays.toString(declaredConstructor.getParameterTypes()));
        }
    }

    @SneakyThrows
    public static Class<?> getCatClass(){
        return Class.forName("com.java.base.day.reflection.Cat");
    }
}
