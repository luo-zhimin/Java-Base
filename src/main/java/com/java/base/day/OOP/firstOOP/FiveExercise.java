/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.OOP.firstOOP;

import java.util.Arrays;

/**
 * @author luozhimin
 */
public class FiveExercise {
    public static void main(String[] args) {
        Five five = new Five();
        System.out.println("max="+five.max(new double[]{11, 1, 3, 4, 7, 8}));
        System.out.println("find="+five.find("宿舍"));
        System.out.println("update="+five.updatePrice(1));
        System.out.println("copy="+Arrays.toString(five.copyArray(new int[]{1, 2, 2, 4, 5})));
        System.out.println("area=" + five.computed(2.1, true));
        System.out.println("perimeter="+five.computed(2.1, false));
        System.out.println("sum="+five.caleComputed(2, 1, 1));
        System.out.println("reduce="+five.caleComputed(2, 1, 2));
        System.out.println("take="+five.caleComputed(2, 1, 3));
        System.out.println("remove="+five.caleComputed(2, 2, 4));
        five.show("大黄","紫色",11);
        System.out.println(five.guessingFist(2));
    }
}

class Five {
    //1 求最大的max
    public double max(double[] doubles) {
        //冒泡
        double temp = 0;
        if (doubles != null && doubles.length > 0) {
            for (int i = 0; i < doubles.length - 1; i++) {
                for (int j = 0; j < doubles.length - 1 - i; j++) {
                    if (doubles[j] > doubles[j + 1]) {
                        temp = doubles[j];
                        doubles[j] = doubles[j + 1];
                        doubles[j + 1] = temp;
                    }
                }
            }
            System.out.println("max=" + Arrays.toString(doubles));
            return doubles[doubles.length - 1];
        }
        return -1;
    }

    public int find(String s) {
        String[] strings = {"稀有性", "时尚", "宿舍"};
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].equals(s)) {
                return i;
            }
        }
        return -1;
    }

    public int updatePrice(int price) {
        if (price > 150) {
            return 150;
        }
        if (price > 100){ return 100;}
        return price;
    }

    public int[] copyArray(int[] array) {
        int[] ints = new int[array.length];
        if (array.length > 0) {
            System.arraycopy(array, 0, ints, 0, array.length);
        }
        return ints;
    }

    //圆的周长 面积
    public double computed(double r, boolean area) {
        if (area) {
            return Math.pow(r, 2) * Math.PI;
        }
        return 2 * r * Math.PI;
    }

    /**
     *
     * @param first
     * @param second
     * @param cale
     * @return
     */
    public double caleComputed(double first, double second, int cale) {
        switch (cale) {
            //和
            case 1:
                return first + second;
            case 2:
                return first - second;
            //乘
            case 3:
                return first * second;
            case 4:
                if (second == 0) {
                    System.out.println("除数请大于0");
                    break;
                }
                return first / second;
            default:
                return -1;
        }
        return 0;
    }

    String name;
    String color;
    int age;

    public void show(String name,String color,int age) {
        this.name = name;
        this.color = color;
        this.age = age;
        System.out.println(this.name+"-"+this.age+"-"+this.color);
    }

    int sum =0;
    int count = 3;
    //0 石头 1 剪刀 2 布
    //scanner number 模拟
    public int guessingFist(int number){
        System.out.println("===");
        for (int i = 0; i < count; i++) {
            int c = (int) (Math.random()*3);
            if (number == 0 && c==1){
                sum ++;
            }else if(number == 1 && c==2){
                sum ++;
            }else if (number == 2 && c==0){
                sum++;
            }else if (number == c){
                System.out.println("平手");
            }
        }
        return sum;
    }
}