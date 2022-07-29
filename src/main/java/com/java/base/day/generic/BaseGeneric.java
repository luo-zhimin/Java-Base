/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.generic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by IntelliJ IDEA.
 * 通用的 泛型
 *
 * @Author : 志敏.罗
 * @create 2022/6/26 17:29
 */
public class BaseGeneric {
    public static void main(String[] args) {
        /*
         * 泛型
         *   问题：
         *   1）不能对加入到集合ArrayList中的数据类型进行约束（不安全）
         *   2）遍历的时候，需要进行类型转换，如果集合中的数据量较大，对效率有影响
         * 泛（广泛）型（类型）=>Integer， String，Dog
         *   1）泛型又称参数化类型，是Jdk5.0出现的新特性，解决数据类型的安全性问题
         *   2）在类声明或实例化时只要指定好需要的具体的类型即可。
         *   3）Java泛型可以保证如果程序在编译时没有发出警告，运行时就不会产生ClassCastException异常。同时，代码更加简洁、健壮
         *   4）泛型的作用是∶ 可以在类声明时通过一个标识表示类中某个属性的类型，或者是某个方法的返回值的类型，或者是参数类型。
         * 声明：
         *   interface接口<T>{和 class类<K，V>{
         *   //如∶ List，ArrayList 说明∶
         *   1）其中，TK，V不代表值，而是表示类型。2）任意字母都可以。常用T表示，是Type的缩写
         * 实例化：
         *   要在类名后面指定类型参数的值（类型）。如∶
         *       List<String>strList=new ArrayList<String>（）;
         *       Iterator<Customer> iterator = customers.iterator();
         * 注意事项和细节:
         *   1.interface List<T>{},public class HashSet<E>{} .等等说明∶T， E只能是引用类型
         *   2.在给泛型指定具体类型后，可以传入该类型或者其子类类型
         */

        //init 相当于加入了约束
        ArrayList<Dog> dogs = new ArrayList<Dog>();
        dogs.add(new Dog("蛋黄", 20));
//        dogs.add("");//error
        System.out.println(dogs);
        Person<Dog> dogPerson = new Person<>(new Dog("空间", 22));
        System.out.println(dogPerson.getT());
        dogPerson.show();
    }
}

class Dog {
    private String name;
    private int age;

    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

/**
 * 泛型的作用是：可以在类声明时通过一个标识表示类中某个属性的类型，或者是某个方法的返回值的类型，或者是参数类型
 */
class Person<T> {
    //T表示 t的数据类型 定义对象的时候指定的 编译时候就可以知道T的类型
    T t;

    public Person(T t) {
        this.t = t;
    }

    public T getT() {
        return t;
    }

    public void show() {
        //运行类型打印
        System.out.println(t.getClass());
    }

    @Override
    public String toString() {
        return "Person{" +
                "t=" + t +
                '}';
    }
}

class Exercise {
    public static void main(String[] args) {
        Dog first = new Dog("大黄", 1);
        Dog two = new Dog("小黑", 12);
        Dog three = new Dog("小白", 21);
        HashSet<Dog> dogHashSet = new HashSet<>();
        dogHashSet.add(first);
        dogHashSet.add(two);
        dogHashSet.add(three);
        dogHashSet.forEach(System.out::println);
        System.out.println("===");
        HashMap<String, Dog> dogHashMap = new HashMap<>();
        dogHashMap.put(first.getName(), first);
        dogHashMap.put(two.getName(), two);
        dogHashMap.put(three.getName(), three);
        dogHashMap.forEach((k, v) -> System.out.println(v));
        System.out.println("==============");
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee("天下无贼", 222.2, new Employee().new MyDate(2022, 3, 26)));
        employees.add(new Employee("天下无贼", 122.2, new Employee().new MyDate(2021, 4, 26)));
        employees.add(new Employee("天下无贼", 2.2, new Employee().new MyDate(2020, 1, 26)));

        employees.sort(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                //name length->same name -> birthday
                if (o1.getName().length() == o2.getName().length()) {
                    //birthday small->big
                    return o1.getBirthday().compareTo(o2.getBirthday());
                } else return o1.getName().compareTo(o2.getName());
            }
        });
        employees.forEach(System.out::println);
    }
}

class Employee {
    private String name;
    private Double salary;
    private MyDate birthday;

    class MyDate implements Comparable<MyDate> {
        private Integer year;
        private Integer month;
        private Integer day;

        public MyDate(Integer year, Integer month, Integer day) {
            this.year = year;
            this.month = month;
            this.day = day;
        }

        public Integer getYear() {
            return year;
        }

        public void setYear(Integer year) {
            this.year = year;
        }

        public Integer getMonth() {
            return month;
        }

        public void setMonth(Integer month) {
            this.month = month;
        }

        public Integer getDay() {
            return day;
        }

        public void setDay(Integer day) {
            this.day = day;
        }

        @Override
        public String toString() {
            return "MyDate{" +
                    "year='" + year + '\'' +
                    ", month='" + month + '\'' +
                    ", day='" + day + '\'' +
                    '}';
        }

        @Override
        public int compareTo(MyDate myDate) {
            //year month birthday  this
            if (this.year.equals(myDate.getYear()) && !this.month.equals(this.getMonth())) {
                return this.month - myDate.getMonth();
            } else if (this.year.equals(myDate.getYear()) &&
                    this.month.equals(this.getMonth()) &&
                    this.day.equals(this.getDay()))
                return this.day - myDate.getDay();
            return this.year - myDate.getYear();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public MyDate getBirthday() {
        return birthday;
    }

    public void setBirthday(MyDate birthday) {
        this.birthday = birthday;
    }

    public Employee(String name, Double salary, MyDate birthday) {
        this.name = name;
        this.salary = salary;
        this.birthday = birthday;
    }

    public Employee() {
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", birthday=" + birthday +
                '}';
    }
}
