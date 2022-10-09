/*
 * Copyright (c) luoZhiMin 2022.10.8.1.16.43
 */

package com.java.base.datastructure.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * 堆排序 - 树 - O(nlogn) - 不稳定
 * @Author : 镜像
 * @create 2022/10/8 13:16
 */
public class HeapSort {

    /*
        堆排序是利用堆这种数据结构而设计的一种排序算法，堆排序是一种选择排序，它的最坏，最好，平均时间复 杂度均为 O(nlogn)，它也是不稳定排序。
        堆是具有以下性质的完全二叉树：每个结点的值都大于或等于其左右孩子结点的值，称为大顶堆, 注意 : 没有 要求结点的左孩子的值和右孩子的值的大小关系。
        每个结点的值都小于或等于其左右孩子结点的值，称为小顶堆

        一般升序采用大顶堆，降序采用小顶堆

        堆排序的基本思想是：
            将待排序序列构造成一个大顶堆
            此时，整个序列的最大值就是堆顶的根节点。
            将其与末尾元素进行交换，此时末尾就为最大值。
            然后将剩余 n-1 个元素重新构造成一个堆，这样会得到 n 个元素的次小值。如此反复执行，便能得到一个有序 序列了

        将无序序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆;
        将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;
        重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤， 直到整个序列有序。
     */


    /**
     * <img src="https://cdn.nlark.com/yuque/0/2022/png/27601787/1665239021738-bf2fca20-e54d-494b-a71f-64a40503d4a3.png"><br>
     * <img src="https://cdn.nlark.com/yuque/0/2022/png/27601787/1665239030648-523a4ce9-808b-4e71-9e5b-cd88f0e58717.png"><br>
     */
    @Test
    void sort(){
        int[] arr = {4, 6, 8, 5, 9};
//        int[] arr = SortDemo.getManyArr();
        long start = System.currentTimeMillis();
        headSort(arr);
        long end = System.currentTimeMillis();
        System.out.printf("时间消耗[%d]\n",(end-start));
        System.out.printf("排序后 %s", Arrays.toString(arr));
    }

    private void headSort(int[] arr){
        //升序 大顶堆
        //降序 小顶堆

        //分步完成
        //transformBigHead(arr,arr.length,1);//4, 9, 8, 5, 6
        //transformBigHead(arr,arr.length,0);//9, 6, 8, 5, 4

        //arr.length/2-1 第一个非叶子节点
        for (int i =  arr.length/2-1; i>=0 ; i--) {
            transformBigHead(arr,arr.length,i);
        }

        /*
            将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;
            重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤， 直到整个序列有序
         */

        int temp;
        for (int j = arr.length-1; j >0 ; j--) {
            //交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            transformBigHead(arr,j,0);
        }
    }

    /**
     * 一个节点拥有的子树的数目称为节点的度，度为0的节点称为叶子节点。把叶子节点找出来之后其他的都是非叶子节点
     * 将一个数组(二叉树) 调整成一个大顶堆 ==> 将无序序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆
     * @param arr 源数组(待调整)
     * @param index 下标(非叶子节点在数组中的索引)
     * @param length 长度(表示对多少个元素进行调整 逐渐减少)
     */
    private void transformBigHead(int[] arr,int length,int index){
        //临时存储 当前值
        int temp = arr[index];
        //开始调整 找左子节点 2n+1
        //i = index*2+1 => i是index节点的左子节点 i+1是右子节点
        for (int i = index*2+1; i < length; i=i*2+1) {
            if (i+1<length && arr[i]<arr[i+1]){
                //左子节点<右子节点
                i++;//i 指向 右子节点
            }
            if (arr[i]>temp){
                //子节点 > 父节点
                arr[index] = arr[i];//较大的值赋值给当前节点
                index = i;//!!
            }else {
                break;
            }
        }
        //for结束后 最大值就是堆顶
        arr[index] = temp;//将temp值放到了调整后的位置
    }
}
