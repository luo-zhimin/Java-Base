package com.java.base.day.commonClass;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 志敏.罗
 * @create 2022/6/15 22:51
 */
public class ArraysDetail {
    public static void main(String[] args) {
        /*
         * Arrays:
         *   Arrays里面包含了一系列静态方法，用于管理或操作数组（比如排序和搜索）
         * 常用方法：
         *   1）toString 返回数组的字符串形式 Arrays.toString(arr)
         *   2）sort 排序（自然排序和定制排序）sort重载的，也可以通过传入一个接口Comparator实现定制排序
         *       调用定制排序时，传入两个参数
         *           (1)排序的数组arr
         *           (2)实现了Comparator接口的匿名内部类,要求实现compare方法
         *           底层是TimSort的binarySort方法 主要是compare
         *   3）binarySearch 通过二分搜索法进行查找，要求必须先排好序
         *         如果不存在 return -(low + 1) low=>formIndex 应该存在的位置+1 取反
         *   4）copyOf 数组元素的复制
         *   5）fill 数组元素的填充 后面指定的元素去替换所有的元素
         *   6）equals 比较两个数组元素内容是否完全一致
         *   7）asList 将一组值，转换成list
         */

        Integer[] ints = {99, 22, 1, 3, 4, 5, 6, 8, 9};
//        Arrays.sort(ints);//sort
        System.out.println(Arrays.toString(ints)); //new StringBuilder toString
        //定制排序 Arrays.sort(array,new Comparator)
        //冒泡+多态+动态绑定机制
        Arrays.sort(ints, new Comparator() {
            //冒泡+多态+动态绑定机制
            @Override
            @SuppressWarnings({"all"})
            public int compare(Object o1, Object o2) {
                int temp = 0;
                for (int i = 0; i < ints.length - 1; i++) {
                    for (int j = 0; j < ints.length - 1 - i; j++) {
                        if (ints[j] > ints[j + 1]) {
                            temp = ints[j];
                            ints[j] = ints[j + 1];
                            ints[j + 1] = temp;
                        }
                    }
                }
                return 0;
            }
        });

//        Arrays.sort(ints, (Comparator) ArraysDetail::compare);
        System.out.println(Arrays.toString(ints));
        int index = Arrays.binarySearch(ints, 99);//如果不存在 return -(low + 1) low=>formIndex 应该存在的位置+1 取反
        System.out.println("binary 出现的索引是" + index);
        Integer[] newInts = Arrays.copyOf(ints, ints.length);
        System.out.println(Arrays.toString(newInts));
        //后面指定的元素去替换所有的元素
//        Arrays.fill(newInts,88);
        System.out.println(Arrays.equals(Arrays.copyOf(ints, ints.length + 1), newInts));
        //编译类型 List 运行类型 class java.util.Arrays$ArrayList
        List integers = Arrays.asList(ints);
        System.out.println(integers);
        System.out.println(integers.getClass());
        Book[] books = new Book[3];
        books[0] = new Book(12,"Java");
        books[1] = new Book(8,"地球文秀");
        books[2] = new Book(17,"J");
        Arrays.sort(books, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getPrise()-o2.getPrise();
            }
        });
        System.out.println("sort="+Arrays.toString(books));
        Arrays.sort(books, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o2.getPrise()-o1.getPrise();
            }
        });
        System.out.println("reverse="+Arrays.toString(books));
        Arrays.sort(books, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getName().length()-o2.getName().length();
            }
        });
        System.out.println("length="+Arrays.toString(books));
    }

    /**
     * 冒泡加定制排序
     *
     * @param t          数组泛型
     * @param comparator comparator to used for the sort
     */
    @SuppressWarnings({"all"})
    public static <T> void sort(int[] t, Comparator comparator) {
        int temp = 0;
        for (int i = 0; i < t.length - 1; i++) {
            for (int j = 0; j < t.length - 1 - i; j++) {
                if (comparator.compare(t[j], t[j + 1]) > 0) {
                    temp = t[j];
                    t[j] = t[j + 1];
                    t[j + 1] = temp;
                }
            }
        }
    }

    private static int compare(Object o1, Object o2) {
        Integer first = (Integer) o1;
        Integer second = (Integer) o2;
        //first-second sort
        //second-second reverse
        return second - first;
    }
}

class Book {

    public Book(int prise, String name) {
        this.prise = prise;
        this.name = name;
    }

    private int prise;
    private String name;

    public int getPrise() {
        return prise;
    }

    public void setPrise(int prise) {
        this.prise = prise;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Book{" +
                "prise=" + prise +
                ", name='" + name + '\'' +
                '}';
    }
}