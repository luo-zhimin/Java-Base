package com.java.base.day.array;

import java.util.Arrays;
import java.util.Scanner;

public class SeqSearch {
    public static void main(String[] args) {
        /*
         * 在 java 中，我们常用的查找有两种:
         * 1) 顺序查找
         * 2) 二分查找
         */
        String[] names = {"测试", "递归", "数组", "学习"};
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入你要查询的名字：");
        int count = 0;
        String next = scanner.next();
        if (Arrays.asList(names).contains(next)){
            System.out.println("...找到了..."+next);
        }
        for (int i = 0; i < names.length; i++) {
            if (names[i].equals(next)) {
                System.out.println("在第" + i + "个下标找到了"+next);
                count ++;
                break;
            }
        }
        if (count<=0) System.out.println("没找到.."+next);
    }
}
