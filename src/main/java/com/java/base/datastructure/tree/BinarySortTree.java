/*
 * Copyright (c) luoZhiMin 2022.10.10.5.35.35
 */

package com.java.base.datastructure.tree;

import org.junit.jupiter.api.Test;

/**
 * Created by IntelliJ IDEA.
 * BST 二叉排序树
 * @Author : 镜像
 * @create 2022/10/10 17:35
 */
public class BinarySortTree {

    /*
        二叉排序树：BST: (Binary Sort(Search) Tree), 对于二叉排序树的任何一个非叶子节点，要求左子节点的值比当 前节点的值小，右子节点的值比当前节点的值大。
        特别说明：如果有相同的值，可以将该节点放在左子节点或右子节点
     */

    private BinarySortTreeNode root;

    private void add(BinarySortTreeNode node){
        if (root==null){
            root = node;
            return;
        }
        root.add(node);
    }

    private void middleShow(){
        if (root!=null){
            root.middleShow();
            return;
        }
        System.out.println("树为空 不可以操作～～");
    }

    private BinarySortTreeNode search(int value){
        if (root==null){
            return null;
        }
        return root.search(value);
    }

    private BinarySortTreeNode searchParent(int value){
        if (root==null){
            return null;
        }
        return root.searchParent(value);
    }

    private void delete(int value){
        //3种
        if (root==null){
            return;
        }
        //找到targetNode
        BinarySortTreeNode targetNode = search(value);
        if (targetNode == null){
            System.out.println("该节点没有找到～");
            return;
        }
        //发现当前这个二叉排序树只有一个节点
        if (root.left==null && root.right == null){
            root = null;
            return;
        }

        //找parentNode
        BinarySortTreeNode parentNode = searchParent(value);
        //叶子节点
        if (targetNode.left == null && targetNode.right==null) {
            //需要判断targetNode是parentNode的 哪个节点
            if (parentNode != null) {
                if (parentNode.left != null && parentNode.left.value == targetNode.value) {
                    //targetNode是parentNode的左子节点
                    parentNode.left = null;
                } else if (parentNode.right != null && parentNode.right.value == targetNode.value){
                    ////targetNode是parentNode的右子节点
                    parentNode.right = null;
                }
            }
        }else if (targetNode.right!=null && targetNode.left!=null){
            //左右子树 replace
            targetNode.value = delRightTreeMin(targetNode.right);
        }else {
            //一颗 子树
            //如果要删除的节点 有左 父节点的左面
            if(parentNode!=null) {
                if (targetNode.left != null) {
                    if (value == parentNode.left.value) {
                        parentNode.left = targetNode.left;
                    } else {
                        parentNode.right = targetNode.left;
                    }
                } else {
                    //右
                    if (value == parentNode.left.value) {
                        parentNode.left = targetNode.right;
                    } else {
                        parentNode.right = targetNode.right;
                    }
                }
            }
        }
    }

    /**
     * @param node 传入的节点(当前二叉树的节点)
     * @return  返回以 node 为 根节点的二叉排序树 的最小值
     */
    private int delRightTreeMin(BinarySortTreeNode node){
        BinarySortTreeNode target = node;
        //循环查找左节点 找最小的值
        while (target.left!=null){
            target = target.left;
        }
        //删除最小节点
        delete(target.value);
        return target.value;
    }

    @Test
    void tree(){
        int[] arr = {7, 3, 10, 12, 5, 1, 9,4};
        BinarySortTree tree = new BinarySortTree();
        for (int j : arr) {
            tree.add(new BinarySortTreeNode(j));
        }

        tree.middleShow();//1,3,5,7,10,12
        //叶子节点
        tree.delete(2);
        //有一个节点
        tree.delete(4);
        //俩个节点
        tree.delete(7);
        System.out.println();

        tree.middleShow();
    }
}
/**
 * 节点
 */
class BinarySortTreeNode{
    int value;

    BinarySortTreeNode left;

    BinarySortTreeNode right;

    public BinarySortTreeNode(int value) {
        this.value = value;
    }

    /**
     * @param value 值
     * @return 找到的节点 没找到就是 null
     */
    public BinarySortTreeNode search(int value){
        if (value==this.value){
            return this;
        }else if (value<this.value){
            //左
            return this.left==null ? null :  this.left.search(value);
        }else {
            return this.right==null ? null :  this.right.search(value);
        }
    }

    /**
     * @param value 值
     * @return 父节点
     */
    public BinarySortTreeNode searchParent(int value){
        //当前节点就是要删除节点的父节点
        if ((this.left != null && this.left.value == value)
                || (this.right != null && this.right.value == value)){
            return this;
        }else {
            if (this.value > value && this.left!=null){
                //左子树递归查找
                return this.left.searchParent(value);
            }else if (this.value <= value && this.right!=null){
                return this.right.searchParent(value);
            }
        }
        //没有找到父节点
        return null;
    }

    /**
     * 添加节点
     * 递归添加 - 需要满足 二叉排序树
     */
    public void add(BinarySortTreeNode node){
        if (node==null){
            return;
        }
        //判断传入的值和当前根节点进行比较
        if (node.value < this.value) {
            //左
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            //右
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }


    public void middleShow(){
        if (this.left!=null){
            this.left.middleShow();
        }
        System.out.println(this);
        if (this.right!=null){
            this.right.middleShow();
        }
    }


    @Override
    public String toString() {
        return "BinarySortTreeNode{" +
                "value=" + value +
                '}';
    }
}