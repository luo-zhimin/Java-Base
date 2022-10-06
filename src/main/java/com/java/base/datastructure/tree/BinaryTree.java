/*
 * Copyright (c) luoZhiMin 2022.10.6.3.17.53
 */

package com.java.base.datastructure.tree;

import lombok.Data;
import org.junit.jupiter.api.Test;

/**
 * Created by IntelliJ IDEA.
 * 二叉树
 * @Author : 镜像
 * @create 2022/10/6 15:17
 */
public class BinaryTree {

    /*
        前序遍历: 先输出父节点，再遍历左子树和右子树
        中序遍历: 先遍历左子树，再输出父节点，再遍历右子树
        后序遍历: 先遍历左子树，再遍历右子树，最后输出父节点

        看输出父节点的顺序，就确定是前序，中序还是后序
     */


    /**
     * 定义一个树
     */
    private HeroNode root;

    //前序遍历
    private void beforeShow(){
        if (this.root!=null){
            this.root.beforeShow();
        }else {
            System.out.println("为空 无法遍历");
        }
    }

    //中序遍历
    private void middleShow(){
        if (this.root!=null){
            this.root.middleShow();
        }else {
            System.out.println("为空 无法遍历");
        }
    }

    //后序遍历
    private void afterShow(){
        if (this.root!=null){
            this.root.afterShow();
        }else {
            System.out.println("为空 无法遍历");
        }
    }

    @Test
    void treeTest(){
        //创建树
        BinaryTree binaryTree = new BinaryTree();
        //创建需要的节点
        HeroNode root = new HeroNode(1, "松江");
        HeroNode node2 = new HeroNode(2, "萨瑟");
        HeroNode node3 = new HeroNode(3, "大师");
        HeroNode node4 = new HeroNode(4, "大赛");
        HeroNode node5 = new HeroNode(5, "放松");

        //分配 - 手动创建 ---->递归
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);

        //遍历
        System.out.println("前序遍历");
        binaryTree.root = root;
        binaryTree.beforeShow(); // 1 2 3 4 (1 2 3 5 4)

        System.out.println();
        System.out.println("中序遍历");
        binaryTree.middleShow();//2 1 3 4  (2 1 5 3 4)

        System.out.println();
        System.out.println("后序遍历");
        binaryTree.afterShow();//2 1 3 4 (2,5,4,3,1)
    }
}

@Data
class HeroNode{
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }


    //前序遍历 父 - 左 -右
    public void beforeShow(){
        //输出当前节点
        System.out.println(this);
        //左子树递归
        if (this.left!=null){
            this.left.beforeShow();
        }
        //右子树递归
        if (this.right!=null){
            this.right.beforeShow();
        }
    }

    //中序遍历 左 - 父  -右
    public void middleShow(){
        //左子树递归
        if (this.left!=null){
            this.left.middleShow();
        }
        //输出当前节点
        System.out.println(this);
        //右子树递归
        if (this.right!=null){
            this.right.middleShow();
        }
    }

    //后序遍历  左 - 右 - 父
    public void afterShow(){
        //左子树递归
        if (this.left!=null){
            this.left.afterShow();
        }

        //右子树递归
        if (this.right!=null){
            this.right.afterShow();
        }

        //输出当前节点
        System.out.println(this);
    }
}