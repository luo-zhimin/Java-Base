package com.java.base.day.commonClass;

import java.util.Arrays;
import java.util.Locale;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 志敏.罗
 * @create 2022/6/12 22:44
 */
public class StringDetail {
    public static void main(String[] args) {
        /*
         * String
         *  1）String 对象用于保存字符串，也就是一组字符序列
         *  2）字符串常量对象是用双引号括起的字符序列。例如∶"你好"、"12.97"、"boy"等
         *  3）字符串的字符使用Unicode字符编码，一个字符（不区分字母还是汉字）占两个字节
         *  4）String类较常用构造器（其它看手册）
         *      String s1 = new String();
         *      String s2 = new String(String original);
         *      String s3 = new String(char[] a);
         *      String s4 = new String(char[] a,int startIndex,int count)
         *      String s5 = newString(byte[]b)
         *  5) String 类实现了接口 Serializable【String 可以串行化:可以在网络传输】 接口 Comparable [String 对象可以比较大小]
         *  6) String 是 final 类，不能被其他的类继承
         *  7) String 有属性 private final char value[]; 用于存放字符串内容 底层是char
         *  8) 一定要注意：value 是一个 final 类型， 不可以修改：即 value 不能指向新的地址，但是单个字符内容是可以变化
         * 创建String对象的两种方式:
         *  1）方式一∶直接赋值 String s ="xx";
         *  2）方式二∶调用构造器 String s=new String（"xx"）;
         * 两种创建String对象的区别:
         *  1.方式一∶先从常量池查看是否有"xx"数据空间，如果有，直接指向;如果没有则重新创建，然后指向。s最终指向的是常量池的空间地址
         *  2.方式二∶先在堆中创建空间，里面维护了value属性，指向常量池的xx空间 如果常量池没有"xx"，，重新创建，如果有，直接通过value指向。最终指向的是堆中的空间地址
         *
         * 当调用 intern 方法时，如果池已经包含一个等于此 String 对象的字符串（用方法确定），则返回池中的字符串。否则，将此 String 对象添加到池中，并返回此 String 对象的引用
         * b.intern（）方法最终返回的是常量池的地址（对象）
         *
         * 字符串的特性：
         *  底层是 StringBuilder sb = new StringBuilder();sb.append（a）;sb.append（b）;sb是在堆中，并且append是在原来字符串的基础上追加的.
         * 重要规则，String c1= "ab"+"cd";常量相加，看的是池。String c1=a +b;变量相加，是在堆中
         *
         * String类的常见方法
         *  equals // 区分大小写，判断内容是否相等
         *  equalsIgnoreCase //忽略大小写的判断内容是否相等
         *  length // 获取字符的个数，字符串的长度
         *  indexOf //获取字符在字符串中第1次出现的索引素引从0开始，如果找不到，返回-1
         *  lastIndexOf //获取字符在字符串中最后1次出现的索引，索引从0开始，如找不到，返回-1
         *  substring //截取指定范围的子串
         *  trim //去前后空格
         *  charAt;获取某索引处的字符，注意不能使用Str[index]这种方式。
         *  toUpperCase //转大写
         *  tolLowerCase //转小写
         *  concat //连接 比较像append
         *  replace 替换字符串中的字符
         *  split 分割字符串， 对于某些分割字符，我们需要 转义比如|\等
         *  compareTo //比较两个字符串的大小 前者大 正数 小 负数 不想等返回的是char对应得值 相减
         *  toCharArray //转换成字符数组
         *  format //格式字符串，%s 字符串 %c 字符 %d 整型 %.2f 浮点型
         */

        //字符串常量对象是用双引号括起的字符序列
        String name = "jack";//最终常量池地址
        String address = new String("上海");//堆中的地址
        final char[] value = {'a', 'b', 'v'};
        char[] value2 = {'t', 'o', 'm'};
        value[0] = 'A';
//        value = value2;  final 不可以修改value的地址 单个char可以修改
        System.out.println("name intern " + name.intern().hashCode());
        System.out.println("address intern " + address.intern().hashCode());
        System.out.println("address " + address.hashCode());
        System.out.println(address == address.intern());//false new 是堆中的 intern 是常量池的
        System.out.println(name + address);//string 变量想加 底层是StringBuilder 是多个append相加 最后再去toString 3个对象 2或者3 【string stringBuilder xxx】
        System.out.println("11" + "22");//1个对象 常量相加
        System.out.println("1" + "2" + "3" + "4" + "5");//1
        System.out.println(1 + name);//3
        System.out.println(name.equalsIgnoreCase("JACK"));
        System.out.println(name.lastIndexOf("c"));//底层是final char array
        System.out.println(name.indexOf('c'));
        System.out.println(name.charAt(1));//xxx[index]==>array
        System.out.println(name.toUpperCase(Locale.ROOT));//big
        System.out.println(name.toLowerCase(Locale.ROOT));//small
        System.out.println(name.concat(" address"));
        String s = "宝玉and林黛玉林黛玉林黛玉";
        System.out.println(s.replace("and", "貂蝉"));
        System.out.println(Arrays.toString(s.split("玉")));
        /*
         * compareTo比较两个字符串的大小，如果前者大，则返回正数，后者大，则返回负数，如果相等，返回0
         * (1)如果长度相同，并且每个字符也相同，就返回0
         * (2)如果长度相同或者不相同，但是在进行比较时，可以区分大小
         * (3)如果前面的部分都相同，就返回str1.len-str2.len
         */
        System.out.println(name.compareTo("java"));
        //charArray
        System.out.println(name.toCharArray());
        //format 格式字符串，%s 字符串 %c 字符 %d 整型 %.2f 浮点型 占位符
        int age = 22;
        double score = 99/22;
        char c = 'A';
        String info = String.format("我的名字是%s,年龄是%d,成绩是%.2f,表现是%c",name,age,score,c);
        System.out.println(info);
    }
}
