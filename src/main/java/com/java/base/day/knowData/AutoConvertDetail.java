package com.java.base.day.knowData;

public class AutoConvertDetail {

    public static void main(String[] args) {
        /*
         * 基础数据类型转换
         * 精度小到精度大 自动类型转换
         * 有多种类型的数据混合运算时 系统首先自动将所有数据转换成容量最大的那种数据类型，然后再进行计算
         * 当我们把精度(容量)大 的数据类型赋值给精度(容量)小 的数据类型时，就会报错，反之就会进行自动类型转换。
         * (byte, short) 和 char 之间不会相互自动转换 当把一个数据赋值给byte时 先判断这个值是否在byte范围内true 如果是变量赋值 判断类型
         * byte，short，char 他们三者可以计算，在计算时首先转换为 int 类型 只要计算就需要转换 int 同级别相加 赋值也是
         * 自动提升原则： 表达式结果的类型自动提升为 操作数中最大的类型
         */
        int a = 'c';//99
        double d = 90;
        System.out.println(a + " " + d);

        System.out.println(a + d);//double
        //(byte, short) 和 char 之间不会相互自动转换
        //当把一个数据赋值给byte时 先判断这个值是否在byte范围内 true
        byte b = 10;//-128~127
        //byte，short，char 他们三者可以计算，在计算时首先转换为 int 类型 只要计算就需要转换 int 同级别相加 赋值也是
        short s = 111;
//        short sum = b + s + 'a'; int (int)
        System.out.println(b+s+'a');//int
        //自动提升原则： 表达式结果的类型自动提升为 操作数中最大的类型
        float f = 1.1f;
        System.out.println(b+s+a+d);//double
    }
}
