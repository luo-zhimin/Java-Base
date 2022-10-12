/*
 * Copyright (c) luoZhiMin 2022.10.7.5.8.57
 */

package com.java.base.datastructure.tree;

import org.junit.jupiter.api.Test;

/**
 * Created by IntelliJ IDEA.
 * 顺序存储二叉树(完全二叉树)
 * @Author : 镜像
 * @create 2022/10/7 17:08
 */
public class OrderBinaryTreeDemo {

    /*

        从数据存储来看，数组存储方式和树的存储方式可以相互转换，即数组可以转换成树，树也可以转换成数组

        顺序二叉树通常只考虑完全二叉树
        第 n 个元素的左子节点为 2 * n + 1
        第 n 个元素的右子节点为 2 * n + 2
        第 n 个元素的父节点为 (n-1) / 2
        n : 表示二叉树中的第几个元素(按 0 开始)
     */

    /**
     * <img src="https://cdn.nlark.com/yuque/0/2022/png/27601787/1665133930068-ea1ce644-f870-4302-97dd-8977bf778a4d.png">
     */
    @Test
    void order(){
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        //创建tree
        OrderBinaryTree orderBinaryTree = new OrderBinaryTree(arr);
        orderBinaryTree.beforeOrder();//1,2,4,5,3,6,7

        System.out.println();
        orderBinaryTree.middleOrder();//2 4 5 1 3 6 7

        System.out.println();
        orderBinaryTree.afterOrder();//2 4 5 3 6 7 1
    }

}
class OrderBinaryTree{
    //数据存储
    private int arr[];

    public OrderBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void beforeOrder(){
        this.beforeOrder(0);
    }

    public void middleOrder(){
        this.middleOrder(0);
    }

    public void afterOrder(){
        this.afterOrder(0);
    }

    /**
     * 前序遍历 - 顺序二叉树
     * @param index 数组的下标
     */
    public void beforeOrder(int index){
       if (arr==null || arr.length==0){
           System.out.println("数组为空 不能按照二叉树进行前序遍历");
           return;
       }
        //当前元素输出
        System.out.print(arr[index]+"\t");
       //左
        if ((2*index+1)<arr.length){
            beforeOrder(2*index+1);
        }
        //右
        if ((2*index+2)<arr.length){
            beforeOrder(2*index+2);
        }
    }

    public void middleOrder(int index){
        //左 - 中 - 右
        if (arr==null || arr.length==0){
            System.out.println("数组为空 不能按照二叉树进行前序遍历");
            return;
        }
        //左
        if ((2*index+1)<arr.length){
            beforeOrder(2*index+1);
        }
        //当前元素输出
        System.out.print(arr[index]+"\t");
        //右
        if ((2*index+2)<arr.length){
            beforeOrder(2*index+2);
        }
    }

    public void afterOrder(int index){
        if (arr==null || arr.length==0){
            System.out.println("数组为空 不能按照二叉树进行前序遍历");
            return;
        }
        //左
        if ((2*index+1)<arr.length){
            beforeOrder(2*index+1);
        }

        //右
        if ((2*index+2)<arr.length){
            beforeOrder(2*index+2);
        }

        //当前元素输出
        System.out.print(arr[index]+"\t");
    }
}
