package com.java.base.day.generic;

/**
 * Created by IntelliJ IDEA.
 * 自定义泛型
 *
 * @Author : 志敏.罗
 * @create 2022/6/26 23:14
 */
public class CustomGeneric {
    public static void main(String[] args) {
        /*
         * 自定义泛型类
         *  基本语法
         *      class 类名<T，R.…>{
         *         //…表示可以有多个泛型成员
         *         成员
         *      }
         *  注意细节方法:
         *  1）普通成员可以使用泛型（属性、方法）
         *  2）使用泛型的数组，不能初始化
         *  3）静态方法中不能使用类的泛型
         *  4）泛型类的类型，是在创建对象时确定的（因为创建对象时，需要指定确定类型）
         *  5）如果在创建对象时，没有指定类型，默认为Object
         *
         * 自定义泛型接口
         *  基本语法
         *      interface 接口名<T，R.>{}
         *  注意细节
         *      1）接口中，静态成员也不能使用泛型（这个和泛型类规定一样）
         *      2）泛型接口的类型，在继承接口或者实现接口时确定
         *      3）没有指定类型，默认为Object
         *
         * 自定义泛型方法
         *  基本语法
         *      修饰符<T，R.>返回类型 方法名（参数列表）{}
         *  注意细节
         *      1.泛型方法，可以定义在普通类中，也可以定义在泛型类中
         *      2.当泛型方法被调用时，类型会确定
         *      3.public void eat（E e）{ }，修饰符后没有<T，R…> eat 方法不是泛型方法，而是使用了泛型
         */

        System.out.println(UserService.n);
        System.out.println(new UserService<String,Double>() {
            /**
             * @param s
             * @return
             */
            @Override
            public String get(String s) {
                return "2";
            }

            /**
             * @param aDouble
             */
            @Override
            public void hello(Double aDouble) {

            }

            /**
             * @param s
             * @param aDouble
             */
            @Override
            public void run(String s, Double aDouble) {

            }
        }.get("1"));

        Tiger<String, Double, Character> tiger = new Tiger<>("tiger", "1", 1.1, 'a');
        tiger.fly("U11",'m');
    }
}

/**
 * Tiger 后面是泛型 其是自定义泛型类
 * T R E 泛型的标识符 一般是大写字母
 * 泛型标识符可以有多个
 * 普通成员可以使用泛型(方法 属性)
 * 使用泛型的数组，不能初始化
 */
class Tiger<T,R,E>{
    String name;
    T t;
    R r;
    E e;

    //因为数组在new  不能确认T类型，就无法在内存中开辟空间  没办法进行初始化操作
    T[] ts;
    //因为静态是和类相关的 在类加载的时候 对象还没有创建 如果静态对象(方法)使用了泛型 JVM 无法完成初始化
//    public static void showT(T t){}

    public Tiger(String name, T t, R r, E e) {
        this.name = name;
        this.t = t;
        this.r = r;
        this.e = e;
    }

    public void run(){}//普通方法

    /**
     * 泛型方法 一般指定的<...>不会和你定义的类的泛型一样 泛型方法可以使用类声明的泛型 也可以使用自己声明泛型
     * @param <U> 泛型
     * @param <M> 泛型
     */
    public <U,M> void fly(U u,M m){
        //运行类型打印
        System.out.println("u = "+u.getClass().getSimpleName());
        System.out.println("m = "+m.getClass().getSimpleName());
    }

    /**
     * 不是泛型方法 而是方法使用了泛型
     */
    public void eat(T t){
        System.out.println(t.getClass());
    }
}

/**
 * 接口中 静态成员不可以使用泛型
 * 泛型接口的类型，在继承接口或者实现接口时确定
 */
interface UserService<T,R>{

    int n = 0;
    //不可以
//    T t = null;

    T get(T t);

    void hello(R r);

    void run(T t,R r);

    //jdk8 接口可以有默认方法 也可以使用泛型
    default R interfaceDefaultMethod(T t){return null;}
}
/**
 * 泛型接口的类型，在继承接口或者实现接口时确定 UserService<String ,Double> 指定类型T，R
 */
interface DeptService extends UserService<String ,Double>{}