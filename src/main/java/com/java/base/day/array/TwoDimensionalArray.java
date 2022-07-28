package com.java.base.day.array;

import java.util.Arrays;
import java.util.Scanner;

public class TwoDimensionalArray {

    public static void main(String[] args) {
        /*
         * 多维数组-二维数组
         * 从定义上来看就是 int[][] 可以理解为一维数组上面的每个元素都是一个数组
         * 动态加载
         *   类型[][] 数组名 = new 类型[大小][大小]
         *   比如: int a[][]=new int[2][3]
         * 静态加载
         *   定义 类型 数组名[][] = {{值 1,值 2..},{值 1,值 2..},{值 1,值 2..}}
         *   使用即可 [ 固定方式访问 ]
         * 注意事项
         *   1) 一维数组的声明方式有: int[] x 或者 int x[]
         *   2) 二维数组的声明方式有: int[][] y 或者 int[] y[] 或者 int y[][]
         *   3) 二维数组实际上是由多个一维数组组成的，它的各个一维数组的长度可以相同，也可以不相同。
         *      比如： map[][] 是 一个二维数组 int map [][] = {{1,2},{3,4,5}} 由 map[0] 是一个含有两个元素的一维数组 ，
         *      map[1] 是一个含有三个元素的一维数组构成，我们也称为列数不等 的二维数组
         */
//        int[][] arr = {{1,2,3},{1,2,3},{0,0,0}};
        int[][] arr = new int[2][3];
        arr[1][1] = 8;
        //一维
        for (int[] ints : arr) {
            //二维
            for (int anInt : ints) {
                System.out.print(anInt + "\t");
            }
            System.out.println();
        }
        System.out.println("======");

        int[][] d = new int[3][];
        for (int i = 0; i < d.length; i++) {
            //给每一个二维数组开辟空间
            d[i] = new int[i + 1];
            for (int j = 0; j < d[i].length; j++) {
                d[i][j] = (i + 1);
                System.out.print(d[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("==");

        int[][] as = {{4, 6}, {1, 4, 5, 7}, {-2}};
        int sum = 0;
        for (int[] a : as) {
            for (int i : a) {
                System.out.print(i + "\t");
                sum += i;
            }
            System.out.println();
        }
        System.out.println("合是->" + sum);
        System.out.println("杨辉三角....start");
        /*
         * 1
         * 1 1
         * 1 2 1 ..3
         * 1 3 3 1 ..4
         * 1 4 6 4 1 ..5
         */
        int[][] y = new int[10][];
        for (int i = 0; i < y.length; i++) {
            //行
            y[i] = new int[i + 1];
            for (int j = 0; j < y[i].length; j++) {
                //第一个合最后一个是1
                if (j == 0 || j == y[i].length - 1) {
                    y[i][j] = 1;
                } else {
                    //j>3 最后俩个数相加是行数 3=>2+1 4=>3+1 ......
                    y[i][j] = (y[i - 1][j] + y[i - 1][j - 1]);
                }
                System.out.print(y[i][j] + "\t");
            }
            System.out.println();
        }
    }
}

class ArrayExercise {
    public static void main(String[] args) {
        String[] a = new String[]{"x"};
        System.out.println(Arrays.toString(a));
        //插入一个值也是升序的
        int [] arr = {10,22,33,44,55};
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("请输入值：");
            int value = scanner.nextInt();
            int[] newArr = new int[arr.length+1];
            System.arraycopy(arr, 0, newArr, 0, arr.length);
            newArr[arr.length] = value;
            System.out.println("newArray"+Arrays.toString(newArr));
            //排序
            int temp;
            for (int i = 0; i < newArr.length-1; i++) {
                System.out.println("第"+(i+1)+"轮开始");
                for (int j = 0; j < newArr.length-1-i; j++) {
                    if (newArr[j]>newArr[j+1]){
                        //大的给临时
                        temp = newArr[j];
                        System.out.println("temp="+temp);
                        //大的小的互换 j小的 j+1 大的
                        newArr[j] = newArr[j+1];
                        newArr[j+1] = temp;
                    }
                }
            }
            arr = newArr;
            System.out.println("请问是否继续输入(y/n)");
            char c = scanner.next().charAt(0);
            if (c == 'n'){
                break;
            }
        }while (true);
        System.out.println("4.."+Arrays.toString(arr));
        int count = 0;
        int sum = 0;
        int[] math = new int[10];
        int max = math[0];
        int index=0;
        for (int i = 0; i < math.length; i++) {
            int v = (int) (Math.random() * 100);
            math[i] = v;
            sum += math[i];
            count++;
            if (count > 10) {
                break;
            }
            if (max < math[i]) {
                max = math[i];
                index = i;
            }
        }
        System.out.println("normal"+"\t"+Arrays.toString(math));
        //receive
        int receiveTemp;
        int findCount =0;
        for (int i = 0; i < math.length-1; i++) {
            for (int j = 0; j < math.length-1-i; j++) {
                if (math[j]<math[j+1]){
                    //min
                    receiveTemp = math[j];
                    math[j] = math[j+1];
                    math[j+1]=receiveTemp;
                }
                if (math[j]==66){
                    findCount++;
                }
            }
        }
        System.out.println("receive"+"\t"+Arrays.toString(math) + "\t" + (sum / math.length) + "\t" + max+"\t"+index);
        if (findCount>9) {
            System.out.println("找到了");
        }
        char[] char1 = {'a','b','v','d'};
        char[] char2 = char1;
        char1[1] = '罗';
        System.out.println("char"+"\t"+Arrays.toString(char1)+"\t"+Arrays.toString(char2));
    }
}
