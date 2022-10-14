/*
 * Copyright (c) luoZhiMin 2022.10.14.5.50.41
 */

package com.java.base.datastructure.nonlinearstructure.tree;

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

    public BinarySortTreeNode getRoot() {
        return root;
    }

    public void add(BinarySortTreeNode node){
        if (root==null){
            root = node;
            return;
        }
        root.add(node);
    }

    public void middleShow(){
        if (root!=null){
            root.middleShow();
            return;
        }
        System.out.println("树为空 不可以操作～～");
    }

    public BinarySortTreeNode search(int value){
        if (root==null){
            return null;
        }
        return root.search(value);
    }

    public BinarySortTreeNode searchParent(int value){
        if (root==null){
            return null;
        }
        return root.searchParent(value);
    }

    public void delete(int value){
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
//            targetNode.value = delRightTreeMin(targetNode.right);
            targetNode.value = delLeftTreeMax(targetNode.left);
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
            }else {
                //需要判断是target的左还是右
                root = targetNode.right != null ? targetNode.right : targetNode.left;
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

    private int delLeftTreeMax(BinarySortTreeNode node){
        BinarySortTreeNode temp = node;
        while (temp.right!=null){
            temp = temp.right;
        }
        delete(temp.value);
        return temp.value;
    }

    /**
     * <img src="https://cdn.nlark.com/yuque/0/2022/png/27601787/1665418841020-2e50e45e-cd7c-44f3-8705-84bec68bef2a.png">
     */
    @Test
    void tree(){
        int[] arr = {7, 3, 10, 12, 5, 1, 9,4,2};
        BinarySortTree tree = new BinarySortTree();
        for (int j : arr) {
            tree.add(new BinarySortTreeNode(j));
        }

        tree.middleShow();//1,3,5,7,10,12
        //叶子节点
        tree.delete(2);
        //有一个节点
        tree.delete(4);
        tree.delete(5);
        //俩个节点
        tree.delete(7);
        tree.delete(3);
        tree.delete(9);
        tree.delete(12);

        tree.delete(1);
        tree.delete(10);
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


    /**
     * 不包含根节点
     * @return 层树
     */
    public int treeHeight(){
        return Math.max(left == null ? 0 : left.treeHeight(), right == null ? 0 : right.treeHeight())+1;
    }

    public int leftHeight(){
        if (left==null){
            return 0;
        }
        return left.treeHeight();
    }

    public int rightHeight(){
        if (right==null){
            return 0;
        }
        return right.treeHeight();
    }

    @Override
    public String toString() {
        return "BinarySortTreeNode{" +
                "value=" + value +
                '}';
    }


    /**
     * 左旋转
     */
    public void leftRotate(){
        //先处理右 - > 左
        //创建新的节点 根节点的值
        SelfNode node = new SelfNode(value);
        //把新的节点的左子树 设置成当前节点的左子树
        node.left = left;
        //右 -> 当前节点的右子树的左子树
        node.right = right.left;
        //当前节点的值 替换 右子节点的值
        value = right.value;
        //把当前节点的右子树 设置成 右子树的右子树
        right = right.right;
        //左 -> 设置成当前节点
        left = node;
    }

    /**
     * 右旋转
     */
    public void rightRotate(){
        //先处理左边 - > 右边
        SelfNode node = new SelfNode(value);
        node.right = right;
        node.left = left.right;
        value = left.value;
        left = left.left;
        right = node;
    }
}