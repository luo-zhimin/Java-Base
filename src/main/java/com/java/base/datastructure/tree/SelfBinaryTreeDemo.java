package com.java.base.datastructure.tree;/*
 * Copyright (c) luoZhiMin 2022.10.11.0.55.36
 */


import org.junit.jupiter.api.Test;

/**
 * Created by IntelliJ IDEA.
 * 平衡二叉树 - AVL
 * @Author : 镜像
 * @create 2022/10/11 00:55
 */
public class SelfBinaryTreeDemo {

    /*
        平衡二叉树也叫平衡二叉搜索树（Self-balancing binary search tree）又被称为 AVL 树， 可以保证查询效率较高。
        具有以下特点：它是一 棵空树或它的左右两个子树的高度差的绝对值不超过 1，并且左右两个子树都是一棵 平衡二叉树。平衡二叉树的常用实现方法有红黑树、AVL、替罪羊树、Treap、伸展树等
     */

    @Test
    void tree(){
        int[] arr = {4,3,6,5,7,8};
        AVLTree tree = new AVLTree();
        //创建一个avlTree
        for (int i : arr) {
            tree.add(new SelfNode(i));
        }
        tree.middleShow();
        System.out.println("没有平衡处理前...");
        System.out.println("tree height = > "+tree.getRoot().treeHeight());//4
        System.out.println("tree left height = > "+tree.getRoot().leftHeight());//1
        System.out.println("tree right height = > "+tree.getRoot().rightHeight());//3

        //绝对值 超过 1 => 右>左 左旋转

    }
}

/**
 * AVL 平衡树
 */
class AVLTree extends BinarySortTree{



}
class SelfNode extends BinarySortTreeNode{

    public SelfNode(int value) {
        super(value);
    }

    /**
     * 左旋转
     */
    private void leftRotate(){
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

    public void add(BinarySortTreeNode node){
        super.add(node);
        if (rightHeight()-leftHeight()>1){
            //左旋转
            leftRotate();
        }
    }
}