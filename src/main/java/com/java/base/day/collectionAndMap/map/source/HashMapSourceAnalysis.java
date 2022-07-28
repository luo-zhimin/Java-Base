package com.java.base.day.collectionAndMap.map.source;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 志敏.罗
 * @create 2022/6/22 00:03
 */
@SuppressWarnings({"all"})
public class HashMapSourceAnalysis {
    public static void main(String[] args) {
        /*
         * HashMap小节：
         *  1）Map接口的常用实现类∶HashMap、Hashtable和Properties。
         *  2）HashMap是 Map 接口使用频率最高的实现类。
         *  3）HashMap 是以 key-val 对的方式来存储数据（HashMap$Node类型）
         *  4）key 不能重复，但是值可以重复，允许使用nul键和null值。
         *  5）如果添加相同的key，则会覆盖原来的key-val，等同于修改（key不会替换，val会替换）
         *    //key的hash相等 && key==p.key || key.equals(p.key)
             if (e != null) { // existing mapping for key
                V oldValue = e.value;
                //发生替换 e.value = value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
              }
         *  6）与HashSet一样，不保证映射的顺序，因为底层是以hash表的方式来存储的.（jdk8的hashMap 底层 数组+链表+红黑树）
         *  7）HashMap没有实现同步，因此是线程不安全的，方法没有做同步互斥的操作，没有synchronized
         *
         *  结论：
         *  1）HlashMap底层维护了Node类型的数组table，默认为null
         *  2）当创建对象时，将加载因子（loadfactor）初始化为0.75.
         *  3）当添加key-val时，通过key的哈希值得到在table的索引。然后判断该索引处是否有元素，如果没有元素直接添加。如果该索引处有元素，继续判断该元素的key和准备加入的key相是否等，
         * 如果相等，则直接替换val;如果不相等需要判断是树结构还是链表结构，做出相应处理。如果添加时发现容量不够，则需要扩容。
         *  4）第1次添加，则需要扩容table容量为16，临界值（threshold）为12（16*0.75）
         *  5）以后再扩容，则需要扩容table容量为原来的2倍（32），临界值为原来的2倍，即24，依次类推
         *  6）在Java8中，如果一条链表的元素个数超过 TREEIFYTHRESHOLD（默认是8），并且 table的大小>= MIN TREEIFY CAPACITY（默认64），就会进行树化（红黑树）
         */

        HashMap hashMap = new HashMap();
        hashMap.put(1,"java");
        hashMap.put(2,"php");
        hashMap.put(1,"spring");//key same value raplace
        System.out.println(hashMap);

        /*
        * hashMap 源码解析 接着HashSet继续分析
         1.构造器 new hashMap() init loadFactor 0.75 hashMap$Node[] table = null
         2.putVal(hash(key), key, value, false, true) hash(key) 计算新的hash值  传入key的hashCode 按位异或 无符号又移16位
            return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16)
         3.putVal 执行过程
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
                //在判断p是不是一颗红黑树 如果是一颗红黑树 调用 putTreeVal 判断当前table 节点的Node是不是TreeNode
                else if (p instanceof TreeNode)
                    e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
                else {
                //如果当前table对应的索引位置，已经是一个链表 使用for循环比较
                //1.依次和该链表的每一个元素比较后 都不相同 则加入到该链表的最后
                //2.比较的过程中 如果有形同的情况 就直接break
                //3.在添加到链表后 ，立即判断 ，该链表是否已经达到8个节点 如果达到就调用 treeifyBin()
                //对当前这个链表进行树化(转成红黑树)  树化需要满足 该链表是否已经 >=8 个节点(在第9个节点加入的时候) 这个table表的大小 >= 64
                // 如果其中一个条件不满足则进行扩容(table表)
                //treeifyBin => if (tab == null || (n = tab.length) < MIN_TREEIFY_CAPACITY (64) ) resize();
                //转成红黑树的条件 该链表是否已经 >=8 个节点 这个table表的大小 >= 64
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
                //key 相同 如果添加相同的key，则会覆盖原来的key-val，等同于修改（key不会替换，val会替换）
                if (e != null) { // existing mapping for key
                    V oldValue = e.value;
                    if (!onlyIfAbsent || oldValue == null)
                    //发生替换 replace
                        e.value = value;
                    afterNodeAccess(e);
                    return oldValue;
                }
            }
            //变化几次 每增加一个Node 就size++
            ++modCount;
            //当前大小 是否 比 threshold[丝软丝hold](临界值 12 first) 大 是的话进行扩容
            if (++size > threshold)
                resize();
            //afterNodeInsertion 是给linkedHashMap使用的 hashMap可以忽略
            afterNodeInsertion(evict);
            return null;
        }
        */

        hashMap = new HashMap();
        //扩容+树化
        System.out.println("==扩容+树化==");
        //在加入第9个元素的时候进行考虑树化 但是table表的大小没到达64 所以进行扩容 table 2倍扩容 第二次扩容（init 之后的第一次扩容）32 临界值 threshold 32*0.75..
        // 11次时候条件满足 进行树化该链表变成HashMap$TreeNode
        for (int i = 1; i <= 12; i++) {
            hashMap.put(new A(i),i);
        }
        System.out.println(hashMap);//12->K-V
    }
}
class A{

    private int num;

    public A(int num) {
        this.num = num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        A a = (A) o;
        return num == a.num;
    }

    //为了放在同一个列表
    @Override
    public int hashCode() {
//        return Objects.hash(num);
        return 100;
    }

    @Override
    public String toString() {
        return "A{" +
                "num=" + num +
                '}';
    }
}
