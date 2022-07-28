/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.OOP.secondOOP.modifer;

public class A {

    public void sayM(){
        M m = new M();
        //同包 私有不可以访问 私有 本身
        System.out.println("n1="+m.n1+"\tn2="+m.n2+"\tn3="+m.n3);
    }
}
