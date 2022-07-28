package com.java.base.day.collectionAndMap.util;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 志敏.罗
 * @create 2022/6/25 23:53
 */
public class CollectionsDetail {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        /*
        *  集合工具类
        *   1）Collections 是一个操作 Set、List 和 Map等集合的工具类
        *   2）Collections 中提供了一系列静态的方法对集合元素进行排序、查询和修改等操作
        * 常用方法(static)：
        *   1）reverse（List）∶反转 List 中元素的顺序
        *   2）shuffle（List）∶对 List 集合元素进行随机排序
        *   3）sort（List）∶ 根据元素的自然顺序对指定 List 集合元素按升序排序B):
        *   4）sort（List，Comparator）∶根据指定的 Comparator 产生的顺序对 List 集合元素进行排序
        *   5）swap（List，int， int）∶将指定 list 集合中的i处元素和j处元素进行交换
        *   6）Object max（Collection）∶根据元素的自然顺序，返回给定集合中的最大元素
        *   7）Object max（Collection，Comparator）∶根据Comparator指定的顺序，返回给定集合中的最大元素
        *   8) Object min(Collection)
        *   9) Object min(Collection, Comparator)
        *   10）int frequency（Collection， Object）∶ 返回指定集合中指定元素的出现次数
        *   11）void copy（List dest，List src）∶将src中的内容复制到dest中
        *   12）boolean replaceAll（List list， Object oldVal， Object newVal）∶使用新值替换List对象的所有旧值
        */
        List list = new ArrayList();
        list.add("1");
        list.add("22");
        list.add("0.4");
        list.add("3333");
        Collections.sort(list);
        System.out.println("sort "+list);
        Collections.reverse(list);
        System.out.println("reverse "+list);
        Collections.shuffle(list);
        System.out.println("shuffle random "+list);
        //指定
        Collections.sort(list, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((String) o1).length() - ((String) o2).length();
            }
        });
        //small -> big
        System.out.println("sort compare "+list);
        //repalce
        Collections.swap(list,0,1);
        System.out.println("swap replace "+list);
        //max 1
        System.out.println("max "+Collections.max(list));
        //2
        System.out.println("max compare "+Collections.max(list, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((String) o1).length() - ((String) o2).length();
            }
        }));
        //min 1
        System.out.println("min "+Collections.min(list));
        //2
        System.out.println("min compare "+Collections.min(list, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((String) o1).length() - ((String) o2).length();
            }
        }));
        //frequency  返回指定集合中指定元素的出现次数  ==>count
        System.out.println("query "+Collections.frequency(list, 1));
        List newList = Arrays.asList(new String[list.size()]);
        //copy (dest source)  Source does not fit in dest
        Collections.copy(newList,list);
        System.out.println("old copy new "+newList);
        Collections.replaceAll(list, "1", "tom");
        System.out.println("repalceAll "+list);
    }
}

/**
 * chapter exercise
 */
class CollectionAndMapExercise{
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        //1.
        ArrayList news = new ArrayList();
        news.add(new News("新冠确诊病例超千万，数百万印度教信徒赴恒河\"圣浴\"引民众担忧"));
        news.add(new News("男子突然想起俩月前钓的鱼还在网兜里，捞起一看赶紧放生"));
        Collections.reverse(news);
        news.forEach(n->{
            if (((News) n).getTitle()!=null && ((News) n).getTitle().length()>15){
                ((News) n).setTitle(((News) n).getTitle().substring(0,15)+"......");
                System.out.println(n);
            }
        });
        //2.
        System.out.println("2.....");
        news = new ArrayList();//again init
        Car baoma = new Car("宝马", 10000);
        Car aodi = new Car("奥迪", 20000);
        news.add(baoma);
        news.add(aodi);

        System.out.println("change before "+baoma.hashCode());
        baoma.setName("ceshi");
        System.out.println("change after "+baoma.hashCode());
        System.out.println(news);
        System.out.println(new Car("ceshi", 10000).equals(baoma));

        System.out.println("car size "+news.size());
        System.out.println("car contains "+news.contains(baoma));
        System.out.println("car isEmpty "+news.isEmpty());
        System.out.println("car remove "+news.remove(baoma));
        ArrayList cars = new ArrayList(Arrays.asList(news.size()));
        Collections.copy(cars,news);
        System.out.println("car addAll "+news.addAll(cars));
        //for iterator
        System.out.println("..for...");
        news.forEach(n-> System.out.println(n));
        Iterator iterator = news.iterator();
        System.out.println("..iterator...");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("car containsAll "+news.containsAll(cars));
        System.out.println("car removeAll "+news.removeAll(cars));
        news.clear();
        System.out.println("car clear after "+news.size());
        System.out.println("3...");
        Map map = new HashMap();
        map.put("jack",650);
        map.put("tom",1200);
        map.put("java",2900);
        //1.repalce jack
        map.put("jack",2300);
        //加薪100
        map.entrySet().forEach(entry->{
            Map.Entry mapEnrty = (Map.Entry) entry;
            map.put(mapEnrty.getKey(),(int)mapEnrty.getValue()+100);
            System.out.println(entry);
        });
        //
        System.out.println("==");
        //Comparable 对象的话需要实现 Comparable 接口 treeMap compare 需要多态转换
        TreeSet treeSet = new TreeSet();
//        treeSet.add(new Car());
        System.out.println(treeSet);//error  Car cannot be cast to java.lang.Comparable
    }
}
class News{
    private String title;
    private String context;

    public News(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                '}';
    }
}
class Car{
    private String name;
    private double price;

    public Car() {}

    public Car(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Double.compare(car.price, price) == 0 && Objects.equals(name, car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}