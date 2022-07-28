package com.java.base.day.knowData;

public class CharDetail {

    public static void main(String[] args) {
        /*
        * char 2 byte 可以存放单个汉字 多个String
        * 字符常量是用单引号括起来的单个字符
        * char的本质是一个整数 在输出时 是unicode对应的编码
        * (int)char 可以将字符转换成整数
        * char 是数字的话会找对应的 字符 罗=>32599 32599=>罗
        * char 可以相加 本质是整数
        * ASCII 一个字节表示 一个128字符 实际上一个字节可以表示256个字符
        * unicode 汉字和字母统一2个字节  有点浪费存储空间
        * utf-8 字母1个字节 汉字3个字节
        * 0-127 unicode和ASCII是兼容的
        */

        char c = 'a';
        char c1 = '\t';
        char c2 = '理';
        char c3 = 98;//unicode 码对应的字符
        System.out.println("c "+c+" c1 "+c1+" c2 "+c2+" c3 "+c3);

        char c4 = '罗';
        char c5 = 32599;
        System.out.println((int) c4);
        System.out.println(c5);

        //本质是整数可以相加
        System.out.println(c3 + 100);

        //a 97 b 98 c 99 ......
        char c6 = 'b'+1;
        System.out.println((int)c6);//int 整数 99
        System.out.println(c6);//c
        System.out.println(c6+10);//加数字 int
        System.out.println(c6+'d');
    }
}
