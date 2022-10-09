/*
 * Copyright (c) luoZhiMin 2022.9.25.1.2.28
 */

package com.java.base.datastructure.sort;

/**
 * Created by IntelliJ IDEA.
 * 排序介绍
 * @Author : 镜像
 * @create 2022/9/25 13:02
 */
public class SortDemo {

    /*
        内部排序: 指将需要处理的所有数据都加载到内部存储器(内存)中进行排序。
        外部排序法：数据量过大，无法全部加载到内存中，需要借助外部存储(文件等)进行排序
     */

    /**
     * <img src="https://cdn.nlark.com/yuque/0/2022/png/27601787/1664082219766-a636084f-dc8e-4e50-8ffd-a42b42fb15cb.png">
     */
    private void sortGroup(){}

    /*
        8w条数据测试消耗时间

        冒泡 8.8s
        选择 1.5s
        插入 0.4s

        数量越大越好
            希尔 0.02s(12ms) -> 移位
            快速 0.01s(11ms)
            归并 0.01s(9ms)
            基数(桶子法) 0.01(6ms)
            堆排序 0.01(8ms)

        基数 < 归并 < 快速 < 希尔 < 插入 < 选择 < 冒泡
     */


    public static int[] getManyArr(){
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random()*80000);
        }
        return arr;
    }


    /*
        常用排序算法总结和对比

        不稳定
          选择 快排 希尔 堆
        稳定
          冒泡 插入 基数 归并  (计数 桶)

          稳定：如果 a 原本在 b 前面，而 a=b，排序之后 a 仍然在 b 的前面；
          不稳定：如果 a 原本在 b 的前面，而 a=b，排序之后 a 可能会出现在 b 的后面；
          内排序：所有排序操作都在内存中完成；
          外排序：由于数据太大，因此把数据放在磁盘中，而排序通过磁盘和内存的数据传输才能进行；
          时间复杂度： 一个算法执行所耗费的时间。
          空间复杂度：运行完一个程序所需内存的大小。
          n: 数据规模
          k: “桶”的个数
          In-place: 不占用额外内存
          Out-place: 占用额外内存
     */

    /**
     * <img src="https://cdn.nlark.com/yuque/0/2022/png/27601787/1664112222641-57952b08-4dcf-41c5-aeae-e5176a3d4e3a.png"> <br>
     * <img src="https://cdn.nlark.com/yuque/0/2022/png/27601787/1664535678002-1a805fa0-f164-4fc9-a9d2-9c125b53d57e.png">
     */
    private void show(){}

}
