package com.java.base.day.generic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * 泛型的继承和通配符
 * @Author : 志敏.罗
 * @create 2022/6/27 16:55
 */
public class GenericExtends {

    public static void main(String[] args) {
        /*
        * 泛型的继承和通配符说明
        *   1.泛型不具备继承性
        *   2.<?>: 支持任意泛型类型
        *   3.<? extends A>:支持A类以及A类的子类，规定了泛型的上限
        *   4.<?super A>∶支持A类以及A类的父类，不限于直接父类，规定了泛型的下限
        * ArrayList.addALl->sourceCode 使用了 <? extends E>
        *   public boolean addAll(Collection<? extends E> c) {}
        */

//        List<Object> list = new ArrayList<String>();  error 没有直接继承关系 需要指定
        ArrayList<A> as = new ArrayList<>();
        ArrayList<B> bs = new ArrayList<>();
        ArrayList<C> cs = new ArrayList<>();
        ArrayList<Object> os = new ArrayList<>();
        //extends 找其加其子
        printExtendsA(as);
        printExtendsA(bs);
        printExtendsA(cs);
        //supper 找其加其父
        printSupperA(as);
        printSupperA(os);
//        printSupperA(bs); error B extends A A(parent) B(child)
    }

    /**
     * <? extends A>:支持A类以及A类的子类
     * @param a <? extends A>
     */
    public static void printExtendsA(List <? extends A> a){
        a.forEach(System.out::println);
    }

    /**
     * <?super A>∶支持A类以及A类的父类，不限于直接父类
     * @param a 支持A类以及A类的父类
     */
    public static void printSupperA(List <? super A> a){
        a.forEach(System.out::println);
    }
}
class A {}
class B extends A{}
class C extends B{}

/**
 * chapter exercise
 */
class GenericExerciseTest{

    @Test
    public void testUser() {
        DAO<User> stringDAO = new DAO<>();
        stringDAO.save("1",new User(1,20,"测试"));
        stringDAO.update("1",new User(1,20,"更新"));
        System.out.println("get "+stringDAO.get("1"));
        stringDAO.list().forEach(System.out::println);
        stringDAO.delete("1");
    }
}
class DAO<T>{

    Map<String,T> map = new HashMap<>();

    public void save(String id,T entry){
        map.put(id,entry);
    }

    public T get(String id){
        return map!=null ? map.get(id) : null;
    }

    public void update(String id,T entry){
        T t = get(id);
        if (t!=null){
            //replace
            map.put(id,entry);
            return;
        }
        System.out.println("need to insert before");
    }

    public List<T> list(){
        if (map!=null){
            return new ArrayList<>(map.values());
        }
        return null;
    }

    public void delete(String id){
        T t = get(id);
        if (t!=null){
            //delete
            T remove = map.remove(id);
            System.out.println("remove "+remove);
        }
    }
}
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
class User{
    private int id;
    private int age;
    private String name;
}