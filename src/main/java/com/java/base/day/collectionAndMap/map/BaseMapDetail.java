package com.java.base.day.collectionAndMap.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 志敏.罗
 * @create 2022/6/22 00:03
 */
public class BaseMapDetail {

    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        /*
         * Map Jdk8 接口特点：
         *   1）Map与Collection并列存在。用于保存具有映射关系的数据∶Key-Value
         *   2）Map 中的 key 和 value 可以是任何引用类型的数据，会封装到HashMap$Node 对象中
         *   3）Map 中的 key 不允许重复，原因和HashSet 一样
         *   4）Map中的 value 可以重复
         *   5）Map 的key 可以为 null value 也可以为null，注意 key 为null，只能有一个value 为null，可以多个.
         *   6）常用String类作为Map的 key
         *   7）key 和 value 之间存在单向一对一关系，即通过指定的 key 总能找到对应的 value
         *   8）Map存放数据的key-value示意图，一对 k-v是放在一个HashMap$Node中的，有因为Node 实现了 Entry 接口，有些书上也说一对k-v就是一个Entry（如图）
         *
         */

        Map map = new HashMap();
        map.put("1", 1);//t
        map.put("2", 2);
        map.put("1", 2);//形同key的value会被替换掉  源码分析
        map.put(null, null);
        map.put(null, 1);
        map.put(new Object(), "越香肉丝");
        System.out.println(map);

        /*
            1.k-v 最后是 HashMap$Node node = new Node<>(hash, key, value, next)
            2.k-v 为了方便遍历 还创建了一个EntrySet 集合 ，该集合存放的元素 类型Entry 而一个Entry
                对象就有k，v, EntrySet<Enety<K,V>> => Set<Map.Entry<K,V>>
            3.entrySet 中 定义的类型是Map.Entry ,但是实际上存放的还是 HashMap$Node
                HashMap的内部类 static class Node<K,V> implements Map.Entry<K,V>
            4.当把 HashMap$Node 存放到 entrySet 方便遍历 getKey()/getValue()
        */

        Set entrySet = map.entrySet();
        System.out.println(entrySet.getClass());//class java.util.HashMap$EntrySet
        entrySet.forEach(entry->{
            //HashMap$Node  运行类型
            System.out.println(entry);
        });
    }
}
@SuppressWarnings({"all"})
class MapExercise{
    static class Staff{
        private Long id;
        private String name;

        private double salary;

        public Staff(Long id, String name,double salary) {
            this.id = id;
            this.name = name;
            this.salary = salary;
        }

        @Override
        public String toString() {
            return "Staff{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", salary='" + salary + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        Map map = new HashMap();
        map.put(1L,new Staff(1L,"fisrt",100000));
        map.put(2L,new Staff(2L,"second",180000));
        map.put(3L,new Staff(3L,"third",300000));
        Set entrySet = map.entrySet();
        //first
        entrySet.forEach(entry->{
            Map.Entry mapEntry = (Map.Entry) entry;
            Staff staff = (Staff) mapEntry.getValue();
            if (staff.salary>=180000){
                System.out.println(staff);
            }
        });
        //second
        System.out.println("===");
        map.values().forEach(c->{
            Staff staff = (Staff) c;
            if (staff.salary>=180000){
                System.out.println(staff);
            }
        });
    }
}