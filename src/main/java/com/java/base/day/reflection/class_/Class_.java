/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.reflection.class_;

import com.java.base.day.reflection.Car;
import com.java.base.day.reflection.Cat;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 志敏.罗
 * @create 2022/7/22 22:30
 */
public class Class_ {

    /**
     * 梳理Class类的特点
     * 1.Class也是类，因此也继承Object类
     * 2.Class类对象不是new出来的，而是系统创建的
     * 3.对于某个类的Class类对象，在内存中只有一份，因为类只加载一次
     * 4.每个类的实例都会记得自己是由哪个Class实例所生成
     * 5.通过Class对象可以完整地得到一个类的完整结构，通过一系列API
     * 6.Class对象是存放在堆的
     * 7.类的字节码二进制数据，是放在方法区的，有的地方称为类的元数据（包括方法代码，变量名，方法名，访问权限等等）https∶//www.zhihu.com/question/38496907
     * <p>
     * 类的常用方法
     */
    @Test
    @SneakyThrows
    void class01() {
        //1.Class也是类，因此也继承Object类
        // Class
        // 2.Class类对象不是new出来的，而是系统创建的
        //(1) 传统的new对象
//        Cat cat = new Cat();
        Cat cat = new Cat();
        Cat cat1 = new Cat();
        System.out.println(cat.hashCode());
        System.out.println(cat1.hashCode());
        System.out.println("======");
        //ClassLoader
//        public Class<?> loadClass(String name) throws ClassNotFoundException {
//            return loadClass(name, false);
//        }
        //(2)反射形式  类只会加载一次 上面new Cat()已经加载了
        //最后仍然是通过加载 classLoader类的loadClass加载 Cat类的Class对象
//        public Class<?> loadClass(String name) throws ClassNotFoundException {
//            return loadClass(name, false);
//        }
        Class<?> aClass = Class.forName("com.java.base.day.reflection.Cat");
        Class<?> aClass1 = Class.forName("com.java.base.day.reflection.Cat");
        System.out.println(aClass.hashCode());//hashCode 相同是同一个对象
        System.out.println(aClass1.hashCode());
        //4.每个类的实例都会记得自己是由哪个Class实例所生成
//        通过Class对象可以完整地得到一个类的完整结构，通过一系列API
    }


    /**
     * 常用方法
     */
    @Test
    @SneakyThrows
    void commonReflectionMethods() {
        String path = "com.java.base.day.reflection.Car";
        //拿到对应的class对象
        //<?> 表示不确定的java类型
        Class<?> aClass = Class.forName(path);
        //输出class
        System.out.println(aClass);//显示aClass对象 是哪个类的class的对象
        System.out.println("运行类型 = " + aClass.getClass());//java.lang.Class
        //得到包名
        System.out.println("package = " + aClass.getPackage().getName());
        //得到全类名
        System.out.println("全类名 = " + aClass.getName());
        //创建对象实例
        Object o = aClass.newInstance();
        System.out.println("对象实例 = " + o);
        //通过反射获取属性 公有属性
        Field field = aClass.getField("brand");
        System.out.println("get public field = " + field);
        //通过反射 给属性设置值
        field.set(o, "奔驰");
        System.out.println("field set = " + field.get(o));
        //获取全部属性
        System.out.println("get fields ==== ");
        for (Field classField : aClass.getFields()) {
            System.out.println(classField.getName());
        }
    }

    /**
     * 获取 Class 类对象
     * 编译阶段 Class.forName() 类加载器得到Class对象  加载阶段 类.class 运行阶段 对象.getClass()
     * 1. 前提已知一个类的全类名，且该类在类路径下，可通过Class类的静态方法forName（）获取，可能抛出ClassNotFoundException，实例∶Class cls1=Class.forName("java.lang.Cat");
     * 应用场景∶多用于配置文件，读取类全路径，加载类.
     * 2. 前提若已知具体的类，通过类的class 获取， 该方式 最为安全可靠， 程序性能最高 实例∶Class cls2=Cat.class；
     * 应用场景多用于参数传递，比如通过反射得到对应构造器对象.
     * 3. 前提已知某个类的实例，调用该实例的getClass（）方法获取Class对象，实例∶Class clazz=对象.getClass（）∶//运行类型
     * 应用场景∶通过创建好的对象，获取Class对象.
     * 4. 类加载器[4种]
     * ClassLoader cl=对象.getClass（）.getClassLoader（）；
     * Class clazz4=cl.loadClass（“类的全类名”）
     * 5. 基本数据（int，char，boolean，float，double，byte，long，short）按如下方式得到Class类对象
     * Class cls=基本数据类型.class
     * 6.基本数据类型对应的包装类，可以通过.TYPE得到Class类对象
     * Class cls=包装类.TYPE
     * <p>
     * 有Class对象的类型
     * 1.外部类，成员内部类，静态内部类，局部内部类，匿名内部类
     * 2. interface∶接口
     * 3. 数组
     * 4. enum∶枚举
     * 5. annotation注解
     * 6.基本数据类型
     * 7. void
     */
    @Test
    @SneakyThrows
    void getClassObject() {
        //1 class.forName()
        //通过配置文件获取 编译阶段
        Class<?> aClass1 = Class.forName("com.java.base.day.reflection.Car");
        System.out.println("aClass1 = " + aClass1);
        //2 类名.class 应用场景多用于参数传递 加载
        Class<?> aClass2 = Car.class;
        System.out.println("aClass2 = " + aClass2);
        //3.对象.getClass() 运行对象 运行  应用场景∶通过创建好的对象，获取Class对象
        Car car = new Car();
        Class<? extends Car> aClass3 = car.getClass();//运行类型
        System.out.println("aClass3 = " + aClass3);
        //4.通过类加载器[4种]加载对象 classLoader
        //得到类加载器
        ClassLoader classLoader = car.getClass().getClassLoader();
        //通过类加载器得到class对象
        Class<?> aClass4 = classLoader.loadClass("com.java.base.day.reflection.Car");
        System.out.println("aClass4 = " + aClass4);
        //aClass1 ~ aClass4 是同一个对象
        //5 基本数据（int，char，boolean，float，double，byte，long，short)
        Class<Integer> integerClass = int.class;
        Class<Character> characterClass = char.class;
        Class<Short> shortClass = short.class;
        System.out.println(integerClass + "\n" + characterClass + "\n" + shortClass);//int..
        //6 基本数据类型对应的包装类，可以通过.TYPE得到Class类对象
        Class<Integer> integerClass1 = Integer.TYPE;
        Class<Character> characterClass1 = Character.TYPE;
        System.out.println("\n" + integerClass1 + "\n" + characterClass1);

        System.out.println("\n" + integerClass.hashCode());
        System.out.println(integerClass1.hashCode()); //同一个对象
    }


    /**
     * 哪些类有class对象
     */
    @Test
    void getHasClassObject(){
        //外部类
        Class<String> stringClass = String.class;
        //接口
        Class<Serializable> serializableClass = Serializable.class;
        //数组
        Class<Integer[]> aClass = Integer[].class;
        //二维数组
        Class<Integer[][]> aClass1 = Integer[][].class;
        //注解
        Class<Deprecated> deprecatedClass = Deprecated.class;
        //枚举
        Class<Thread.State> stateClass = Thread.State.class;
        //基本数据类型
        Class<Integer> integerClass = int.class;
        //void
        Class<Void> voidClass = void.class;
        //class
        Class<?> classClass = Class.class;

        System.out.println(stringClass+"\n"+serializableClass+"\n"+aClass+"\n"+aClass1+"\n"+deprecatedClass+"\n"+stateClass+"\n"+integerClass+"\n"+voidClass+"\n"+classClass);
    }
}
