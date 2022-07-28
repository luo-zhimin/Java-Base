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

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by IntelliJ IDEA.
 * 通过反射创建对象
 * @Author : 志敏.罗
 * @create 2022/7/24 14:29
 */
public class UseReflectionCreateObject {

    /**
     * 通过反射创建对象
     *  1.方式一∶调用类中的public修饰的无参构造器
     *  2.方式二∶调用类中的指定构造器
     *  3.Class类相关方法
     *     newInstance∶调用类中的无参构造器，获取对应类的对象
     *     getConstructor（Class…clazz）∶根据参数列表，获取对应的public构造器对象
     *     getDeclaredConstructor（Class…clazz）∶根据参数列表，获取对应的所有构造器对象
     * 4.Constructor类相关方法
     *  setAccessible∶暴破
     *  newInstance（Object…obj）∶调用构造器
     */
    @SneakyThrows
    public static void main(String[] args) {
        //获取class对象
        Class<?> catClass = ReflectionUtils.getCatClass();
        //public 无参构造创建对象
        Cat cat = (Cat)catClass.newInstance();
        System.out.println(cat);
        //public 含参构造创建对象
        //public Cat(String name, int age) {}
        Constructor<?> constructor = catClass.getConstructor(String.class, int.class);
        //创建含参构造 传入 实参
        Object newO = constructor.newInstance("旺财", 1);
        System.out.println(newO);
        //private 含参构造创建对象
        //private Cat(String name, String job) {}
        Constructor<?> declaredConstructor = catClass.getDeclaredConstructor(String.class, String.class);
        //accessible 哎克塞丝包
        declaredConstructor.setAccessible(true);//暴力破解 使用发射可以访问私有的private 构造器
        Object o = declaredConstructor.newInstance("私有", "猫");
        System.out.println(o);
    }

    /**
     * 通过反射访问类中的成员
     * 访问属性
     *  1.根据属性名获取Field对象
     *      Field f = clazz对象.getDeclaredField（属性名）；
     *  2.暴破∶
     *     f.setAccessible（true）；//f是Field
     * 3.访问
     *  f.set（o，值）；//o表示对象
     *  f.get（o）；//o表示对象
     * 4.注意∶如果是静态属性，则set和get中的参数o，可以写成null
     * 静态属性属于类的
     *  set(null,"private") get(null)
     *  <p/>
     * 访问方法
     *  1.根据方法名和参数列表获取Method方法对象∶
     *      Method m=clazz.getDeclaredMethod（方法名， XX.class）；//得到本类的所有方法 xx是入参的class对象
     * 2.获取对象∶
     *      Object o=clazz.newInstance（）；
     * 3.暴破
     *      m.setAccessible（true）；
     * 4.访问
     *      Object returnValue=m.invoke（o，实参列表）∶ //o就是对象
     * 5.注意∶
     *      如果是静态方法，则invoke的参数o，可以写成null!
     */
    @Test
    @SneakyThrows
    void reflectAccessProperty(){
        //反射访问操作属性
        Class<?> catClass = ReflectionUtils.getCatClass();
        //创建对象
        Object cat = catClass.newInstance();//运行类型 是 Cat
        System.out.println(cat.getClass());//com.java.base.day.reflection.Cat
        //使用反射得到 age public getField
        Field field = catClass.getField("age");
        field.set(cat,88);//反射操作属性 设置
        System.out.println(field.get(cat));
        //反射操作name属性 private static String
        Field declaredField = catClass.getDeclaredField("name");
        //爆破之后可以操作 私有属性
        declaredField.setAccessible(true);
//        declaredField.set(cat,"private");
        declaredField.set(null,"private");//设置属性值 要求属性是静态的 属于类
        System.out.println(declaredField.get(null));
    }

    @Test
    @SneakyThrows
    void reflectAccessMethod(){
        //反射访问操作属性
        Class<?> catClass = ReflectionUtils.getCatClass();
        //创建对象
        Object cat = catClass.newInstance();//运行类型 是 Cat
        //通过反射获取方法
//        catClass.getMethod("hi",String.class) 调用public
        //public void cry(String name){}
        Method method = catClass.getDeclaredMethod("cry",String.class);//所有
        method.invoke(cat,"猫咪");
        //调用private static 方法 private static String hi3(int age, double salary) {}
        Method declaredMethod = catClass.getDeclaredMethod("hi3", int.class, double.class);
        //暴破
        declaredMethod.setAccessible(true);
        System.out.println(declaredMethod.invoke(cat, 111, 11));
        //hi3 是static的 所以 对象可以为null
        System.out.println(declaredMethod.invoke(null, 22, 2));

        //返回值 在反射中 如果方法有返回值 统一返回Object 运行类型还是和你定义的一样
        Object returnValue = declaredMethod.invoke(null, 33, 33);
        System.out.println(returnValue + " 运行类型是 "+returnValue.getClass());
    }
}
