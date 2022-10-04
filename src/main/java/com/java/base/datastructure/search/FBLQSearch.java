/*
 * Copyright (c) luoZhiMin 2022.10.3.8.51.49
 */

package com.java.base.datastructure.search;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * 查找 - 斐波那契 - 黄金分割
 * @Author : 镜像
 * @create 2022/10/3 20:09
 */
public class FBLQSearch {

    /*
        对 F(k-1)-1 的理解：
            由斐波那契数列 F[k]=F[k-1]+F[k-2] 的性质，可以得到 （F[k]-1）=（F[k-1]-1）+（F[k-2]-1）+1 。
                该式说明： 只要顺序表的长度为 F[k]-1，则可以将该表分成长度为 F[k-1]-1 和 F[k-2]-1 的两段，中间位置为 mid=low+F(k-1)-1
            类似的，每一子段也可以用相同的方式分割
            但顺序表长度 n 不一定刚好等于 F[k]-1，所以需要将原来的顺序表长度 n 增加至 F[k]-1。这里的 k 值只要能使 得 F[k]-1 恰好大于或等于 n 即可，
                由以下代码得到,顺序表长度增加后，新增的位置（从 n+1 到 F[k]-1 位置）， 都赋为 n 位置的值即可
     */

    private static final int MAXSIZE = 20;

    /**
     * <img src="https://cdn.nlark.com/yuque/0/2022/png/27601787/1664868185137-d8e60669-f772-4427-aeaf-f7cd366d082c.png">
     */
    @Test
    void search(){
        int [] arr ={1,8, 10, 89, 1000};
        System.out.println(fBLQSearch(arr, 10));
    }

    /**
     * @return 得到非递归的斐波那契数列
     */
    private int[] fbl(){
        int[] f = new int[MAXSIZE];
        f[0]=1;
        f[1]=1;
        for (int i = 2; i < MAXSIZE; i++) {
            f[i] = f[i-1]+f[i-2];
        }
        return f;
    }

    /**
     * 斐波那契 也必须 是有序的 非递归
     * @param arr 源数组
     * @param value 要查找的值
     * @return 找到的索引 -1没有找到
     */
    private int fBLQSearch(int[] arr,int value){
        //F(k-1)-1

        //left
        int low = 0;
        //right
        int high = arr.length-1;
        //表示斐波那契分隔数值的下标
        int key = 0;
        int middle = 0;
        //获取斐波那契 数列
        int[] f = fbl();

        while (high>=f[key]-1){
            key++;
        }

        //f[key] 可能大于数组的长度 所以我们需要构造一个新的数组 并执行源数组
        int[] temp = Arrays.copyOf(arr,f[key]);
        //实际上需要使用 arr数组的最后一个值 填充
        //{1,8, 10, 89, 1000, 1234,0,0} => {1,8, 10, 89, 1000, 1234,1234,1234}
        for (int i = high+1; i < temp.length; i++) {
            temp[i] = arr[high];
        }

        //找到我们的key
        while (low<=high){
            middle = low + f[key-1]-1;
            if (value < temp[middle]){
                //左面查找
                high = middle - 1;
                //全部元素 = 前+后 f[k] = f[k-1]+f[k-2]
                //前面有f[k-1]个元素 所以可以继续拆分 f[k-1] = f[k-2]+f[k-3]
                key--;
            }else if (value > temp[middle]){
                //右边
                low = middle+1;
                //后面有f[k-2]个元素 f[k-1] = f[k-3]+f[k-4]
                key-=2;
            }else {
                //找到了
                return Math.min(middle, high);
            }
        }

        return -1;
    }
}
