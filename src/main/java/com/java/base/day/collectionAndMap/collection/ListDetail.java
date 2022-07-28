package com.java.base.day.collectionAndMap.collection;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 志敏.罗
 * @create 2022/6/18 17:34
 */
public class ListDetail {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        /*
         * List
         *   基本介绍:
         *     List 接口是 Collection 接口的子接口
         *       1）List集合类中元素有序（即添加顺序和取出顺序一致）、且可重复
         *       2）List集合中的每个元素都有其对应的顺序索引，即支持索引
         *       3) List容器中的元素都对应一个整数型的序号记载其在容器中的位置，可以根据序号存取容器中的元素。
         *       4）JDKAPI中List接口的实现类有∶
         *       常用的有∶ArrayList、LinkedList和Vector
         *  常用方法:
         *      void add(int index,Object ele):在index位置插入ele元素
         *      boolean addAll(int index,Collection eles):从index位置开始将eles中的所有元素添加进来
         *      Object get(int index):获取指定index位置的元素
         *      int indexOf(Object obj):返回obj在集合中首次出现的位置
         *      int lastIndexOf(Object obj):返回obj在当前集合中末次出现的位置
         *      Object remove(int index):移除指定index位置的元素，并返回此元素
         *      Object set(int index,Object ele):设置指定index位置的元素为ele,相当于是替换
         *      List subList(intfrom Index,int toIndex):返回从fromIndex到toIndex位置的子集合
         *
         * List(ArrayList、LinkedList、Vector)的三种遍历方式:
         *
         */

        //List集合类中元素有序（即添加顺序和取出顺序一致）、且可重复
        List firstList = new ArrayList();
        firstList.add("enterprise");
        List list = new ArrayList();
        list.add("tom");
        list.add("jerry");
        list.add("tom");
        System.out.println(list);
        //List集合中的每个元素都有其对应的顺序索引，即支持索引
        System.out.println(list.get(1));
        list.add(1, "police");
        System.out.println("插入police " + list);
        list.addAll(1, firstList);
        System.out.println("插入 firstList " + list);
        System.out.println(list.indexOf("enterprise"));
        System.out.println(list.lastIndexOf("jerry"));
        System.out.println(list.remove(0));
        list.set(3, "rabbit");
        System.out.println("replace " + list);
        //返回的子集合fromIndex<=subList<toIndex
        System.out.println(list.subList(0, 2));
        System.out.println(list.size());//4
        //2 add remove 6 update 7
        list.add(1, "education");
        System.out.println("exercise " + list);
        System.out.println(list.remove(3));
        list.set(2, "java");
        System.out.println("exercise set " + list);
        //iterator + for
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println("exercise iterator " + iterator.next());
        }
        System.out.println("===");
        list.forEach(l -> System.out.println("exercise for " + l));
        //List(ArrayList、LinkedList、Vector)的三种遍历方式
        //LinkedList
        List linkedList = new LinkedList();
        linkedList.add("红烧肉");
        linkedList.add("浴血奋战");
        linkedList.add("长生不老");
        //iterator
        Iterator linkedIterator = linkedList.iterator();
        System.out.println("====iterator====");
        while (linkedIterator.hasNext()) {
            System.out.println(linkedIterator.next());
        }
        //加强for
        System.out.println("==加强for==");
        linkedList.forEach(linked -> System.out.println(linked));
        //普通for
        System.out.println("==普通for==");
        for (int i = 0; i < linkedList.size(); i++) {
            System.out.println(linkedList.get(i));
        }
    }
}

class ListExercise {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        //ArrayList
//        List list = new ArrayList();
        //linkedList
//        List list = new LinkedList();
        //vector
        List list = new Vector();
        list.add(new Book("鱼香肉丝", 22.2));
        list.add(new Book("三国演义", 18.2));
        list.add(new Book("小李飞刀", 9.2));
        //1.prise down to up
        sort(list);
        list.forEach(listBook -> {
            Book book = (Book) listBook;
            System.out.println("名称：" + book.getName() + "\t价格：" + book.getPrise());
        });
    }

    private static void sort(List list) {
        int firstForSize = list.size() - 1;
        for (int i = 0; i < firstForSize; i++) {
            for (int j = 0; j < firstForSize - i; j++) {
                //对象转化
                Book book = (Book) list.get(j);
                Book book1 = (Book) list.get(j+1);
                if (book.getPrise()>book1.getPrise()){
                    //replace set sort
                    list.set(j,book1);
                    list.set(j+1,book);
                }
            }
        }
    }
}