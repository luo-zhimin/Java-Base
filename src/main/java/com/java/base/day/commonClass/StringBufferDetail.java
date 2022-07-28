package com.java.base.day.commonClass;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 志敏.罗
 * @create 2022/6/13 23:31
 */
public class StringBufferDetail {

    public static void main(String[] args) {
        /*
         * StringBuffer：
         *       无参构造 初始容量是16  public StringBuffer() {super(16);}
         *   1. StringBuffer 的直接父类 是 AbstractStringBuilder
         *   2. StringBuffer 实现了 Serializable, 即 StringBuffer 的对象可以串行化
         *   3. 在父类中 AbstractStringBuilder 有属性 char[] value,不是 final 该 value 数组存放 字符串内容，引出存放在堆中的
         *   4. StringBuffer 是一个 final 类，不能被继承
         *   5. 因为 StringBuffer 字符内容是存在 char[] value, 所有在变化(增加/删除),不用每次都更换地址(即不是每次创建新对象)， 所以效率高于 String
         *
         * String VS StringBuffer：
         *   1）String保存的是字符串常量，里面的值不能更改，每次String类的更新实际上就是更改地址，效率较低 //private final char value;
         *   2）StringBuffer保存的是字符串变量，里面的值可以更改，每次StringBuffer的更新实际上可以更新内容，不用每次更新地址，效率较高//char[] value; 这个放在堆
         *
         * String to StringBuffer
         *   1.直接 new StringBuffer(name);
         *   2.append new StringBuffer().append();
         *
         * StringBuffer to String
         *   1.buffer.toString()
         *   2.new String(StringBuffer)
         *
         * 常见方法:
         *   append add
         *   delete [0,4) 根据索引删除
         *   replace 使用name replace [0,4)索引 下标 update
         *  insert 在索引为4的位置上插入 对应的值 原来的向后移动
         */

        //通过构造器直接指定buffer的 大小 capacity 容量
        new StringBuffer(100);

        //创建一个容量为 参数.length+16的char数组
        StringBuffer buffer = new StringBuffer("hello");
        //add
        buffer.append("jack");
        buffer.append(1).append(true).append(10.5);
        //delete [0,4) 根据索引删除
        buffer.delete(0, 5);
        //update 使用name replace [0,4)索引 下标
        buffer.replace(0, 4, "name");
        //insert 在索引为4的位置上插入 对应的值 原来的向后移动
        buffer.insert(4, "buffer");
        System.out.println(buffer);
        System.out.println(buffer.length());
        //String to StringBuffer 1.直接 new StringBuffer(name); 2.append new StringBuffer().append();
        //1 返回的是buffer对象 对其本身没有影响
        String name = "jack";
        new StringBuffer(name);
        //StringBuffer to String  1.buffer.toString() 2.new String(StringBuffer)
        System.out.println(buffer.toString());
        new String(buffer);
        String number = "1111111111.11111";
        StringBuffer stringBuffer = new StringBuffer(number);
        int lastPoint = number.lastIndexOf(".");
        for (int i = lastPoint - 3; i > 0; i -= 3) {
            stringBuffer.insert(i,",");
        }
        System.out.println(stringBuffer);
    }
}
