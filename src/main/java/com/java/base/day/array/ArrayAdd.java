package com.java.base.day.array;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayAdd {
    public static void main(String[] args) {
        /*
         * 数组添加与扩容
         */
        System.out.println("add.....");
        int[] initArr = {1, 2, 3};
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("请输入添加的元素数字");
            int nextInt = scanner.nextInt();
            int[] newArr = new int[initArr.length+1];
            System.arraycopy(initArr,0,newArr,0,initArr.length);
            newArr[newArr.length-1] = nextInt;
            initArr = newArr;
            System.out.println("是否继续?y/n");
            char c = scanner.next().charAt(0);
            if (c=='n'){
                break;
            }
        }while (true);
        System.out.println("现在数组-"+ Arrays.toString(initArr));

        System.out.println("reduce......");
        do {
            int length = initArr.length;
            System.out.println("reduce-1..y/n");
            char b = scanner.next().charAt(0);
            if (b == 'n') {
                break;
            }
            System.out.println(length);
            int[] reduceArr = new int[length-1];
            initArr = reduceArr;
            System.out.println("现在患有-" + reduceArr.length + "个元素");
            if (reduceArr.length <= 1) {
                break;
            }
        } while (true);
        System.out.println("reduce .. break..");
    }
}
