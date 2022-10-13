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
@SuppressWarnings({"all"})
public class SelfBinaryTreeDemo {

    /*
        问题BST：
            左子树全部为空，从形式上看，更像一个单链表.
            插入速度没有影响
            查询速度明显降低(因为需要依次比较), 不能发挥 BST的优势，因为每次还需要比较左子树，其查询速度比 单链表还慢
        解决：
            平衡二叉树也叫平衡二叉搜索树（Self-balancing binary search tree）又被称为 AVL 树， 可以保证查询效率较高。
            具有以下特点：它是一 棵空树或它的左右两个子树的高度差的绝对值不超过 1，并且左右两个子树都是一棵 平衡二叉树。平衡二叉树的常用实现方法有红黑树、AVL、替罪羊树、Treap、伸展树等
     */


    /**
     * <img src="https://cdn.nlark.com/yuque/0/2022/png/27601787/1665502215246-614eaab7-0f05-4ff1-a531-26be0f1d424a.png">
     */
    @Test
    void leftTree(){
        int[] arr = {4,3,6,5,7,8};
        haddleArray(arr);
        //绝对值 超过 1 => 右>左 左旋转
    }

    /**
     * <img src="https://cdn.nlark.com/yuque/0/2022/png/27601787/1665502370436-e2c3c6b4-870c-4c67-85a2-8800e18be3fe.png">
     */
    @Test
    void rightTree(){
        int[] arr = {10,12, 8, 9, 7, 6};
        haddleArray(arr);
    }

    /**
     * <img src="https://cdn.nlark.com/yuque/0/2022/png/27601787/1665558970078-96646c16-5e05-4ff2-bd8c-ce6b9308196e.png">
     */
    @Test
    void rotateTree(){
        //没有进行左右旋转前 left 3 right 1 ->add 后 会出现 left 1 right 3 ❌
        //需要进行左右旋转
//        int[] arr = {10, 11, 7, 6, 8, 9};
        int[] arr = {2,1,6,5,7,3};

        //当符号右旋转的条件时
        //如果它的左子树的右子树高度大于它的左子树的高度
        //先对当前这个结点的左节点进行左旋转
        //在对当前结点进行右旋转的操作即可

        haddleArray(arr);
    }

    private void haddleArray(int[] arr){
        AVLTree tree = new AVLTree();
        //创建一个avlTree
        for (int i : arr) {
            tree.add(new SelfNode(i));
        }
        tree.middleShow();
//        System.out.println("没有平衡处理前...");
        System.out.println();
        System.out.println("tree height = > "+tree.getRoot().treeHeight());//4
        System.out.println("tree left height = > "+tree.getRoot().leftHeight());//1
        System.out.println("tree right height = > "+tree.getRoot().rightHeight());//3
        System.out.println("root = > "+tree.getRoot().value);
    }
}

/**
 * AVL 平衡树
 */
class AVLTree extends BinarySortTree{ }
class SelfNode extends BinarySortTreeNode{

    public SelfNode(int value) {
        super(value);
    }

    public void add(BinarySortTreeNode node) {
        super.add(node);
        if (rightHeight() - leftHeight() > 1) {
            //如果它的右子树的左子树高度大于它的右子树的高度
            if (right != null && right.leftHeight() > right.rightHeight()) {
                right.rightRotate();
                //左旋转
                leftRotate();
            } else {
                leftRotate();
            }
            return;
        }
        if (leftHeight() - rightHeight() > 1) {
            //右旋转
            //如果它的左子树的右子树高度大于它的左子树的高度 - 左->右
            if (left != null && left.rightHeight() > left.leftHeight()) {
                //左节点 左旋转
                left.leftRotate();
                //当前节点右旋转
                rightRotate();
            } else {
                rightRotate();
            }
        }
    }
}