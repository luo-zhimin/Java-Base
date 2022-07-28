package com.java.base.day.annotationAndEnum;

/**
 * @author 志敏.罗
 * @Date 2022/6/11 13:11
 * @Version 1.0
 */
public class MetaAnnotationDetail {

    public static void main(String[] args) {
        /*
         * 元注解
         *  1) Retention 指定注解的作用范围，三种 SOURCE,CLASS,RUNTIME
         *  2) Target 指定注解可以在哪些地方使用
         *  3) Documented 指定该注解是否会在 javadoc 体现
         *  4) Inherited 子类会继承父类注解
         *
         * @Retention 注解
         * 只能用于修饰一个 Annotation 定义, 用于指定该 Annotation 可以保留多长时间, @Retention 包含一个 RetentionPolicy 类型的成员变量, 使用 @Retention 时必须为该 value 成员变量指定值:
         * @Retention 的三种值
         * 1) RetentionPolicy.SOURCE: 编译器使用后，直接丢弃这种策略的注释
         * 2) RetentionPolicy.CLASS: 编译器将把注解记录在 class 文件中. 当运行 Java 程序时, JVM 不会保留注解。 这是默认值
         * 3) RetentionPolicy.RUNTIME:编译器将把注解记录在 class 文件中. 当运行 Java 程序时, JVM 会保留注解. 程序可以通过反射获取该注解
         *
         * @Target
         * 用于修饰Annotation定义，用于指定被修饰的Annotation能用于修饰哪些程序元素 @target 也包含一个value的成员变量
         *
         * @Documented
         * 用于指定被该元 Annotation 修饰 Annotation 类将被javadoc 工具提取成文档，即在生成文档时，可以看到该注解
         * 说明∶定义为Documented的注解必须设置Retention值为RUNTIME
         *
         * @Inherited注解
         * 被它修饰的 Annotation 将具有继承性.如果某个类使用了被 @Inherited 修饰的 Annotation， 则其子类将自动具有该注解
         */
    }
}

@SuppressWarnings({"all"})
class Exercise {

    private static Exercise exercise = new Exercise();
    private static Exercise exercise2 = new Exercise();

    static {
        System.out.println("static");
    }

    {
        System.out.println("构");
    }

    public static void main(String[] args) {
        //先进行static 顺序执行  实例化 后static
        new Exercise();//构 构 static 构
        Frock frock = new Frock();
        frock.setSerialNumber(Frock.getNetCurrentNumBer());
        Frock frock1 = new Frock();
        frock1.setSerialNumber(Frock.getNetCurrentNumBer());
        Frock frock2 = new Frock();
        frock2.setSerialNumber(Frock.getNetCurrentNumBer());
        System.out.println(frock.getSerialNumber() + "-" + frock1.getSerialNumber() + "-" + frock2.getSerialNumber());
        System.out.println("3....");
        new Cat().shout();
        System.out.println("4...");
        new CallPhone().testWork(new Computer() {
            @Override
            public double work(double first, double second) {
                return first + second;
            }
        }, 2, 2);
        System.out.println("5...");
        new Animal().new B().show();
        System.out.println("6...");
        ExercisePerson person = new ExercisePerson("唐僧", null);
        person.common();
        person.passRiver();
        person.flame();

    }
}

class Frock {
    //初始化衣服序列化
    private static int currentNum = 100000;

    private int serialNumber;

    public static int getNetCurrentNumBer() {
        return currentNum += 100;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }
}

class Animal {

    private String name = "Outer Animal";

    /**
     * implements shout
     */
    protected void shout() {
    }

    class B {
        private static final String name = "Inner Animal";

        public void show() {
            System.out.println(Animal.this.name + "\t" + name);
        }
    }
}

class Cat extends Animal {
    @Override
    protected void shout() {
        System.out.println("cat cry");
    }
}

interface Computer {
    double work(double first, double second);
}

class CallPhone {
    public void testWork(Computer computer, double first, double second) {
        System.out.println("result " + computer.work(first, second));
    }
}

/**
 * 交通接口
 */
interface Vehicles {
    /**
     * 交通工具工作
     */
    void work();
}

class Factory {

    private Factory() {
    }

    public static class Horse implements Vehicles {

        /**
         * 交通工具工作
         */
        @Override
        public void work() {
            System.out.println("road worker");
        }
    }

    public static class Boat implements Vehicles {
        /**
         * 交通工具工作
         */
        @Override
        public void work() {
            System.out.println("writer worker");
        }
    }

    public static class Fly implements Vehicles {

        /**
         * 交通工具工作
         */
        @Override
        public void work() {
            System.out.println("fly worker");
        }
    }

    /**
     * 饿汉式
     */
    private static final Horse horse = new Horse();

    public static Horse getHorse() {
        return horse;
    }

    public static Boat getBoat() {
        return new Boat();
    }

    public static Fly getFly() {
        return new Fly();
    }
}

class ExercisePerson {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public Vehicles getVehicles() {
        return vehicles;
    }

    public void setVehicles(Vehicles vehicles) {
        this.vehicles = vehicles;
        if (vehicles == null) {
            this.vehicles = Factory.getBoat();
        }
    }

    private Vehicles vehicles;

    /**
     * init Object
     *
     * @param name     name
     * @param vehicles 工具接口
     */
    public ExercisePerson(String name, Vehicles vehicles) {
        this.name = name;
        setVehicles(vehicles);
    }

    /**
     * 过河
     */
    public void passRiver() {
        if (!(this.vehicles instanceof Factory.Boat)) {
            this.vehicles = Factory.getBoat();
        }
        this.vehicles.work();
    }

    public void common() {
        if (!(this.vehicles instanceof Factory.Horse)) {
            this.vehicles = Factory.getHorse();
        }
        this.vehicles.work();
    }

    public void flame() {
        if (!(this.vehicles instanceof Factory.Fly)) {
            this.vehicles = Factory.getFly();
        }
        this.vehicles.work();
    }
}