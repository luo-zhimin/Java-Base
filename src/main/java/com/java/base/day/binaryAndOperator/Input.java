package com.java.base.day.binaryAndOperator;

import java.util.Scanner;

public class Input {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入名字：");
        System.out.println("name："+scanner.next());
        System.out.println("请输入性别：");
        System.out.println("sex："+scanner.next());
        System.out.println("请输入薪水：");
        System.out.println("薪水："+scanner.nextDouble());
    }
}
