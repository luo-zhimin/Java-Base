package com.java.base.day.loopfor;

public class LoopExercise {

    public static void main(String[] args) {
        //exercise 1
        double money = 100000;
        int pass = 0;
        //>50000 5% <=50000 1000
        System.out.println("1...");
        while (true) {
            if (money > 50000) {
                money = money - (money * 0.05);
            } else {
                money -= 1000;
                if (money <= 0) {
                    break;
                }
            }
            pass++;
//            System.out.println(pass+"现在患有money=" + money);
        }
        System.out.println("pass .." + pass);

        //2...
        System.out.println("2...");
        int n = 22;
        if (n>0){
            System.out.println(">0");
        }else if (n==0){
            System.out.println("==0");
        }else {
            System.out.println("<0");
        }

        //3..闰年

        //4.。水仙花
        int h = 153;
        int b = h/100;
        int s = h%100/10;
        int g = h%10;
        System.out.println("...4");
        if ((Math.pow(b,3)+Math.pow(s,3)+Math.pow(g,3)) == h){
            System.out.println("水仙花");
        }

        //6..1-100 5！=0
        int numberCount =0;
        System.out.println("6...");
        for (int i = 1; i <= 100; i++) {
            if (i%5==0){
                continue;
            }
            numberCount++;
            System.out.print(i+"\t");
            if (numberCount%5==0){
                System.out.println("....."+numberCount);
            }
        }
        //7.a-z/A-Z char
        char a = 'a';
        char A ='A';
        System.out.println("7...");
        for (int i = 1; i <=26 ; i++) {
            System.out.println(a+"--"+A);
            a++;
            A++;
        }

        System.out.println("8...");
        //8. 1-1/2+1/3-1/4...1/100 ... (1/1)-(1/2)+...(1/100) double(/)
        double w = 0;
        for (int i = 1; i <= 100; i++) {
            //基数+ 偶数-
            if (i%2==0){
                w-=(1.0/i);
            }else {
                w+=(1.0/i);
            }
        }
        System.out.println("-/-double.."+w);

        //9 1+(1+2)+(1+2+3)......(1+...+100) (n-1+n)
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= i; j++) {
                sum+=j;
                System.out.println("i-"+i+"-j-"+(j));
//                System.out.println(sum);
            }
        }
        System.out.println("9..sum="+sum);
    }
}
