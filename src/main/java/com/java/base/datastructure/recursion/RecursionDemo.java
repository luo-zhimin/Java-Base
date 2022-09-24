/*
 * Copyright (c) luoZhiMin 2022.9.23.10.6.13
 */

package com.java.base.datastructure.recursion;

import org.junit.jupiter.api.Test;

/**
 * Created by IntelliJ IDEA.
 * 递归 测试
 * @Author : 镜像
 * @create 2022/9/23 22:06
 */
@SuppressWarnings({"all"})
public class RecursionDemo {

    /*
        递归要遵守的重要原则
            执行一个方法时，就创建一个新的受保护的独立空间(栈空间)
            方法的局部变量是独立的，不会相互影响, 比如 n 变量
            如果方法中使用的是引用类型变量(比如数组)，就会共享该引用类型的数据.
            递归必须向退出递归的条件逼近，否则就是无限递归,出现 StackOverflowError，死龟了
            当一个方法执行完毕，或者遇到 return，就会返回，遵守谁调用，就将结果返回给谁，同时当方法执行完毕或 者返回时，该方法也就执行完毕
     */

    @Test
    void recursion(){
        //打印问题
        show(4);
        System.out.println();
        //阶层问题
        int result = strautm(5);
        System.out.println(result);
    }


    /**
     * <img src="https://cdn.nlark.com/yuque/0/2022/png/27601787/1663943803787-753b45ab-901c-4aeb-aace-8a6ccbc0beb9.png">
     * @param value value
     */
    private void show(int value){
        if (value>2){
            show(value-1);
        }
        System.out.println("value = "+value);
    }


    /**
     * 阶层问题
     * @param value value
     */
    private int strautm(int value){
        if (value==1){
            return 1;
        }else {
            //value => 2 strautm(1)*2
            //value => 3 strautm(2)*3 =>  (strautm(1)*2)*3
            //.... 2*3*4*5 .....
            return value * strautm(value-1);
        }
    }
}
