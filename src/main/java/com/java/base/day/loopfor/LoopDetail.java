package com.java.base.day.loopfor;

import java.util.Scanner;
public class LoopDetail {
    public static void main(String[] args) {
        /*
         * int 无论如何数 == true /integer 在int常量范围内== true ~128-127
         *  循环控制 for while
         * 1. for 关键字，表示循环控制
         * 2. for 有四要素:
         *  (1)循环变量初始化
         *  (2)循环条件
         *  (3)循环操作
         *  (4)循环变量迭代
         * 3. 循环操作 , 这里可以有多条语句，也就是我们要循环执行的代码
         * 4. 如果 循环操作(语句) 只有一条语句，可以省略 {}, 建议不要省略
         * 1) 循环条件是返回一个布尔值的表达式
         * 2) for(;循环判断条件;) 中的初始化和变量迭代可以写到其它地方，但是两边的分号不能省略。
         * 3) 循环初始值可以有多条初始化语句，但要求类型一样，并且中间用逗号隔开，循环变量迭代也可以有多条变量迭代 语句，中间用逗号隔开
         *
         * while 循环控制
         * 1) 循环条件是返回一个布尔值的表达式
         * 2) while 循环是先判断再执行语句
         *  循环变量初始化
         *  while(循环条件){循环体(语句) 迭代}
         *
         * do..while..
         * 1. 也有循环四要素, 只是位置不一样
         * 2. 先执行，再判断，也就是说，一定会至少执行一次
         * 3. 最后 有一个 分号 ;
         * 4. while 和 do..while 区别举例: 要账
         *
         * 多重循环控制(难点! 重点!)
         * 1) 将一个循环放在另一个循环体内，就形成了嵌套循环。其中，for ,while ,do…while 均可以作为外层循环和内层循环。 【建议一般使用两层，最多不要超过 3 层, 否则，代码的可读性很差】
         * 2) 实质上，嵌套循环就是把内层循环当成外层循环的循环体。当只有内层循环的循环条件为 false 时，才会完全跳出内 层循环，才可结束外层的当次循环，开始下一次的循环[听不懂，走案例]。
         * 3) 设外层循环次数为 m 次，内层为 n 次，则内层循环体实际上需要执行 m*n 次。
         *
         * break
         * break 语句用于终止某个语句块的执行，一般使用在 switch 或者循环[for , while , do-while]中
         *
         * continue
         * 1) continue 语句用于结束本次循环，继续执行下一次循环。
         * 2) continue 语句出现在多层嵌套的循环语句体中时，可以通过标签指明要跳过的是哪一层循环 , 这个和前面的标签的 使用的规则一样.
         *
         * return
         * return 使用在方法，表示跳出所在的方法
         */

        //for
        //打印 1~100 之间所有是 9 的倍数的整数，统计个数 及 总和
        int sum = 0;
        int count = 0;
        for (int i = 0; i < 100; i++) {
            if (i % 9 == 0 && i != 0) {
                count++;
                sum += i;
            }
        }
        System.out.println("个数：" + count + "和：" + sum);
        //0000 0001 => 1111 1110/ 1000 0010
        System.out.println(~1);

        for (int j = 0; j < 6; j++) {
            System.out.println(j + "+" + (5 - j) + "=" + (j + (5 - j)));
        }
        System.out.println("break for");

        //while
        int x = 6;
        while (x < 7 && x > 0) {
            System.out.println("while--" + x);
            x--;
        }
        System.out.println("break while...");
        //40-200 %2
        int d = 40;
        while ((d >= 40 && d <= 200)) {
            if (d % 2 == 0) {
                System.out.println(d);
            }
            d++;
        }
        System.out.println("..");

        //do..while..
        int y = 6;
        do {
            if (y % 2 == 0) System.out.println("do..while.." + y);
            y++;
        } while (y < 50);
        System.out.println("do..while..break.." + y);

        //1) 统计 3 个班成绩情况，每个班有 5 名同学，求出各个班的平均分和所有班级的平均分[学生的成绩从键盘输入]。
        //2) 统计三个班及格人数，每个班有 5 名同学。

        Scanner scanner = new Scanner(System.in);
        double totalScore = 0;
        byte passStudentCount = 0;
        byte classRoomCount = 3;
        byte studentCount = 5;
        for (int i = 1; i < classRoomCount; i++) {
            for (int j = 1; j < studentCount; j++) {
                System.out.println("请数第"+i+"个班的第"+j+"个学生的成绩");
                double score = scanner.nextDouble();
                if (score>=60){
                    passStudentCount ++;
                }
                totalScore += score;
            }
        }
        System.out.println("三个班总分="+ totalScore + " 平均分=" + totalScore / (classRoomCount*studentCount));
        System.out.println("及格人数=" + passStudentCount);

        //99乘法表
        for (int z = 1; z < 10; z++) {
            for (int p = 1; p <= z; p++) {
                System.out.print(z + "*" + p + "=" + (z * p) + "\t");
            }
            System.out.println();
        }
        System.out.println("9*9 break ...");

        //经典的打印金字塔 --- 空心金字塔
        //up
        int leve = 5;
        for (int i = 1; i <= leve; i++) {
            for (int w = 1; w <= leve - i; w++) {
                System.out.print(" ");
            }
            //经典金字塔 (2n-1) *个数
            for (int j = 1; j <= (2 * i - 1); j++) {
                //第一行和最后一行是完整的 其他是俩个* 镂空菱形金字塔
                if (j == 1 || j == (2 * i - 1) || i == leve) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        //down
        for (int i = leve; i <= leve && i > 0; i--) {
            for (int w = 1; w <= leve - i; w++) {
                System.out.print(" ");
            }
            //经典金字塔 (2n-1) *个数
            for (int j = 1; j <= (2 * i - 1); j++) {
                //第一行和最后一行是完整的 其他是俩个* 镂空菱形金字塔
                if (j == 1 || j == (2 * i - 1)) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        System.out.println("菱形金字塔break....");

        //break continue
        int breakCount = 0;
        while (true) {
            int i = (int) (Math.random() * 100);
            breakCount++;
            if (i == 97) {
                System.out.println("while..break..");
                break;
            }
        }
        do {
            int i = (int) (Math.random() * 100);
            breakCount++;
            if (i == 97) {
                System.out.println("do..while..break");
                break;
            }
        } while (true);
        System.out.println(breakCount);

        //continue
        while (true) {
            int i = (int) (Math.random() * 100);
            System.out.println("continue...." + i);
            if (i < 98) {
                System.out.println("continue");
                continue;
            }
            System.out.println("before while break....continue..");
            if (i == 98) {
                break;
            }
        }
    }
}
