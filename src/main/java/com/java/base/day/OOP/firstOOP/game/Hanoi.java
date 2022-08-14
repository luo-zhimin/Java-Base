/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.OOP.firstOOP.game;

/**
 * 汉诺塔 <br/><br/>
 * <img width="450" height="418" src="https://cdn.nlark.com/yuque/0/2022/png/27601787/1653580257293-f953709f-9b3c-4f31-84db-5fff03a3d2bd.png" alt="汉诺塔">
 */
public class Hanoi {

    public static void main(String[] args) {
        /*
         * 汉诺塔
         * 小圆盘上不能放大圆盘，在三根柱子之间一次只能移动一个圆盘
         */
        HH hh = new HH();
        hh.move(5,'a','b','c');
    }
}

class HH {

    //number 多少个盘子 a b c 柱子
    public void move(int number,char a,char b,char c){
        //只有一个盘子
        if (number == 1){
            System.out.println(number+"-"+a+"->"+c);
        }else {
            //多个盘子 可以看成俩个 上面一个和下面所有
            //1，先移动上面所有的盘子到b 借助c
            move(number-1,a,c,b);
            //2.最下面直接到到c
            System.out.println(number+"-"+a+"->"+c);
            //3.在把b上面所有的盘子移动到b 借助a
            move(number-1,b,a,c);
        }
    }
}