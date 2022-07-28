package com.java.base.day.commonClass;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 志敏.罗
 * @create 2022/6/15 17:05
 */
public class StringBuilderDetail {
    public static void main(String[] args) {
        /*
        * StringBuilder
        *   1）一个可变的字符序列。此类提供一个与 StringBuffer兼容的 API，但不保证同步（StringBuilder 不是线程安全）
        * 该类被设计用作 StringBuffer 的一个简易替换，用在字符串缓冲区被单个线程使用的时候。如果可能，建议优先采用该类因为在大多数实现中，它比 StringBuffer 要快
        *   2）在 StringBuilder上的主要操作是append 和 insert 方法，可重载这些方法，以接受任意类型的数据
        *   用于：StringBuilder 单线程 StringBuffer 多线程
        *
        * String、StringBuffer和StringBuilder的比较：
        *   1）StringBuilder 和 StringBuffer 非常类似，均代表可变的字符序列，而且方法也是一样
        *   2）String∶不可变字符序列，效率低，但是复用率高。
        *   3）StringBuffer∶ 可变字符序列、效率较高（增删）、线程安全， 看源码
        *   4）StringBuilder∶可变字符序列、效率最高、线程不安全
        *   5）String使用注意说明∶
        *       string s="a";//创建了一个字符串
        *       s+="b";//实际上原来的"a"字符串对象已经丢弃了，现在又产生了一个字符串s+"b"（也就是"ab"）。如果多次执行这些改变串内容的操作，会导致大量副本字符串对象存留在内存中，降低效率。
        *       如果这样的操作放到循环中，会极大影响程序的性能=>结论∶如果我们对String做大量修改，不要使用String
        * 效率：
        *   StringBuilder>StringBuffer>String
        *
        * String、StringBuffer和StringBuilder的选择：
        *   1.如果字符串存在大量的修改操作，一般使用 StringBuffer 或StringBuilder
        *   2.如果字符串存在大量的修改操作，并在单线程的情况，使用 StringBuilder
        *   3.如果字符串存在大量的修改操作，并在多线程的情况，使用 StringBuffer
        *   4.如果我们字符串很少修改，被多个对象引用，使用String，比如配置信息等
        */
        StringBuffer buffer = new StringBuffer("buffer");
        StringBuilder builder = new StringBuilder(buffer);
        builder.append(" builder");
        System.out.println(builder);
        long stringStart = System.currentTimeMillis();
        String string = "1";
        for (int i = 0; i < 20000; i++) {
            string += i;
        }
        long stringEnd = System.currentTimeMillis();
        System.out.println("string = "+(stringEnd-stringStart));
        long stringBufferStart = System.currentTimeMillis();
        StringBuffer stringBuffer = new StringBuffer("2");
        for (int i = 0; i < 20000; i++) {
            stringBuffer.append(i);
        }
        long stringBufferEnd = System.currentTimeMillis();
        System.out.println("buffer = "+(stringBufferEnd-stringBufferStart));
        long stringBuilderStart = System.currentTimeMillis();
        StringBuilder stringBuilder = new StringBuilder("3");
        for (int i = 0; i < 20000; i++) {
            stringBuilder.append(i);
        }
        long stringBuilderEnd = System.currentTimeMillis();
        System.out.println("builder = "+(stringBuilderEnd-stringBuilderStart));
    }
}
