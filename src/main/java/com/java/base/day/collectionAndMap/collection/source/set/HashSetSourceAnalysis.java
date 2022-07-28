package com.java.base.day.collectionAndMap.collection.source.set;

import java.util.HashSet;
import java.util.Objects;
import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 志敏.罗
 * @create 2022/6/19 19:46
 */
public class HashSetSourceAnalysis {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        /*
         * hashSet全面分析
         *  1.HashSet实现了Set接口
         *  2.HashSet实际上是HashMap，看下源码
         *  3.可以存放null值，但是只能有一个null
         *  4.HashSet不保证元素是有序的，取决于hash后，再确定索引的结果.（即，不保证存放元素的顺序和取出顺序一致）
         *  5.不能有重复元素/对象
         *
         * 分析HashSet底层是HashMap，HashMap底层是（数组+链表+红黑树）
         *
         */

        //HashSet实际上是HashMap   map = new HashMap<>();
        HashSet hashSet = new HashSet();
        //执行add 会有boolean返回
//        System.out.println(hashSet.add("john"));
//        System.out.println(hashSet.add("jack"));
//        System.out.println(hashSet.add("john"));
//        System.out.println(hashSet.add("java"));
        //remove 对象
//        hashSet.remove("john");
//        System.out.println("hashSet " + hashSet);

        hashSet = new HashSet();
        hashSet.add("tom");//true   第一次add分析完毕
        hashSet.add("jerry");
        hashSet.add("tom");//false
        /*
        * hashSet 源码解析

        final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                       boolean evict) {
            Node<K,V>[] tab; Node<K,V> p; int n, i;//定义了辅助变量
            //table是hashMap的一个数组 类型是Node[] 节点
            //if表示当前table是空的或者大小是0 的话 进行第一次扩容 table 开辟 16个空间
            if ((tab = table) == null || (n = tab.length) == 0)
                n = (tab = resize()).length;
            //根据key得到的hash值 去计算该keu应该存放的table表的哪个索引位置 并且赋值给辅助变量p
            //如果p为null 表示还没有存放元素 就创建一个Node 并且添加元素 tab[i] = newNode(hash, key, value, null);
            if ((p = tab[i = (n - 1) & hash]) == null)
                tab[i] = newNode(hash, key, value, null);
            else {
                //在需要局部变量(辅助变量)时候 在创建
                Node<K,V> e; K k;
                //如果当前索引对应链表的第一个元素和准备添加的key的hash值一样 (hash相等)
                //并且满足下面俩个条件之一：
                //1.加入的key 和p指向的Node 节点的key 是同一个对象 (对象相等)
                //2.p 指向的Node 节点的key的equals() 和加入的key比较后相同 (值相等)
                if (p.hash == hash &&
                    ((k = p.key) == key || (key != null && key.equals(k))))
                    e = p;
                //在判断p是不是一颗红黑树 如果是一颗红黑树 调用 putTreeVal
                else if (p instanceof TreeNode)
                    e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
                else {
                //如果当前table对应的索引位置，已经是一个链表 使用for循环比较
                //1.依次和该链表的每一个元素比较后 都不相同 则加入到该链表的最后
                //2.比较的过程中 如果有形同的情况 就直接break
                //3. 注意在添加到链表后 ，立即判断 ， 该链表是否已经达到8个节点 如果达到就调用 treeifyBin()
                //对当前这个链表进行树化(转成红黑树)  树化还需要满足 这个表的大小大于等于 64  如果其中一个条件不满足则进行扩容(table表)
                //if (tab == null || (n = tab.length) < MIN_TREEIFY_CAPACITY (64) ) resize();
                //
                //转成红黑树的条件  该链表是否已经 >=8 个节点 这个表的大小 >= 64
                    for (int binCount = 0; ; ++binCount) {
                        if ((e = p.next) == null) {
                            p.next = newNode(hash, key, value, null);
                            if (binCount >= TREEIFY_THRESHOLD (8) - 1) // -1 for 1st
                                treeifyBin(tab, hash);
                            break;
                        }
                        if (e.hash == hash &&
                            ((k = e.key) == key || (key != null && key.equals(k))))
                            break;
                        p = e;
                    }
                }
                if (e != null) { // existing mapping for key
                    V oldValue = e.value;
                    if (!onlyIfAbsent || oldValue == null)
                        e.value = value;
                    afterNodeAccess(e);
                    return oldValue;
                }
            }
            //变化几次
            ++modCount;
            //当前大小 是否 比 threshold(临界值 12 first) 大 是的话进行扩容
            if (++size > threshold)
                resize();
            //afterNodeInsertion 是给linkedHashMap使用的 hashMap可以忽略
            afterNodeInsertion(evict);
            return null;
        }

        */

        //不同的对象
//        hashSet.add(new Dog("dog"));
//        hashSet.add(new Dog("dog"));
        System.out.println("hashSet 2  " + hashSet);

        /*
         * 源码分析
         * add 的底层执行原理
         */
//        hashSet.add(new String("class"));//true 俩个对象 堆中
//        hashSet.add(new String("class"));//false 1一个对象 class在池子中
//        System.out.println("hashSet 3  " + hashSet);
    }
}

class Dog {
    private String name;

    public Dog(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                '}';
    }
}

class SourceAnalysis {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        /*
         * 模拟简单数组+链表
         */
        //HashSet -> (底层HashMap)
        //创建一个数组 Node[] 底层叫table
        Node[] table = new Node[16];
        //创建节点 索引2 存储元素
        Node john = new Node("john", null);
        table[2] = john;
        Node jack = new Node("jack", null);
        //挂载形成链表
        john.next = jack;
        Node roles = new Node("roles", null);
        //将roles挂载到jack
        jack.next = roles;
        Node rabbit = new Node("rabbit", null);
        table[3] = rabbit;
        System.out.println(table.length);
    }
}

/**
 * 节点 存储数据 可以指向下个节点 形成链表
 */
class Node {
    Object item;
    Node next;

    public Node(Object item, Node next) {
        this.item = item;
        this.next = next;
    }
}

/**
 * hashSet 扩容 和 转化红黑树机制
 */
class HashSetIncrement {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        /*
         *   扩容:
         *   HashSet 底层是 HashMap, 第一次添加时，table 数组扩容到 16，
         *   临界值(threshold)是 16*加载因子(loadFactor)是 0.75 = 12
         *   如果 table 数组使用到了临界值 12,就会扩容到 16 * 2 = 32,
         *   新的临界值就是 32*0.75 = 24, 依次类推
         */

        HashSet hashSet = new HashSet();
        for (int i = 1; i <= 100; i++) {
            hashSet.add(i);//1...100
        }
        System.out.println(hashSet);
        /*
         * 转化红黑树机制
         *  在 Java8 中, 如果一条链表的元素个数到达 TREEIFY_THRESHOLD(默认是 8 )， 并且 table 的大小 >= MIN_TREEIFY_CAPACITY(默认 64),就会进行树化(红黑树), 否则仍然采用数组扩容机制
         *  当我们向 hashset 增加一个元素，-> Node -> 加入 table , 就算是增加了一个 size++ 当size>loadFactor(临界值)也会触发数组扩容
         */
        hashSet = new HashSet();
        for (int i = 1; i <= 12; i++) {//10 扩容到64 table的大小 重新计算hash    11
            hashSet.add(new Num(i));//hashCode == 形成 列表
        }
        System.out.println(hashSet);
        new Vector().add("");
    }
}

class Num {
    private int n;

    public Num(int n) {
        this.n = n;
    }

    @Override
    public int hashCode() {
//        return Objects.hash(n);
        return 100;
    }

    @Override
    public String toString() {
        return "Num{" +
                "n=" + n +
                '}';
    }
}
class Cat{
    private String name;
    private String age;
    private MyDate birthday;

    public Cat(String name, String age, MyDate birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return Objects.equals(name, cat.name) && Objects.equals(birthday, cat.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthday);
    }

    //    public Cat(String name, String age) {
//        this.name = name;
//        this.age = age;
//    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Cat cat = (Cat) o;
//        return Objects.equals(name, cat.name) && Objects.equals(age, cat.age);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(name, age);
//    }

//    @Override
//    public String toString() {
//        return "Cat{" +
//                "name='" + name + '\'' +
//                ", age='" + age + '\'' +
//                '}';
//    }


    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", birthday=" + birthday +
                '}';
    }

    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        HashSet hashSet = new HashSet();
//        hashSet.add(new Cat("cat","12"));
//        hashSet.add(new Cat("tom","13"));
//        hashSet.add(new Cat("cat","12"));
//        System.out.println(hashSet);
        hashSet.add(new Cat("cat","12",new MyDate(2022,3,26)));
        hashSet.add(new Cat("tom","13",new MyDate(2022,4,27)));
        hashSet.add(new Cat("cat","12",new MyDate(2022,3,26)));
        System.out.println(hashSet);
    }
}
class MyDate{
    private Integer year;
    private Integer month;
    private Integer day;

    public MyDate(Integer year, Integer month, Integer day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyDate myDate = (MyDate) o;
        return Objects.equals(year, myDate.year) && Objects.equals(month, myDate.month) && Objects.equals(day, myDate.day);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month, day);
    }

    @Override
    public String toString() {
        return "MyDate{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }
}