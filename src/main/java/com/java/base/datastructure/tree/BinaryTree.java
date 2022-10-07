/*
 * Copyright (c) luoZhiMin 2022.10.6.3.17.53
 */

package com.java.base.datastructure.tree;

import org.junit.jupiter.api.Test;

/**
 * Created by IntelliJ IDEA.
 * 二叉树 - 遍历 - 查找 - 删除
 * @Author : 镜像
 * @create 2022/10/6 15:17
 */
public class BinaryTree {

    /*
        前序遍历: 先输出父节点，再遍历左子树和右子树
        中序遍历: 先遍历左子树，再输出父节点，再遍历右子树
        后序遍历: 先遍历左子树，再遍历右子树，最后输出父节点

        看输出父节点的顺序，就确定是前序，中序还是后序

        删除
            如果删除的节点是叶子节点，则删除该节点
            如果删除的节点是非叶子节点，则删除该子树

            如果要删除的节点是非叶子节点，现在我们不希望将该非叶子节点为根节点的子树删除，需要指定规则
            如果该非叶子节点 A 只有一个子节点 B，则子节点 B 替代节点 A
            如果该非叶子节点 A 有左子节点 B 和右子节点 C，则让左子节点 B 替代节点 A
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

    public HeroNode beforeSearch(int orderNo){
        if (this.root!=null){
            return this.root.beforeSearch(orderNo);
        }else {
            return null;
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

    public HeroNode middleSearch(int orderNo){
        if (this.root!=null){
            return this.root.middleSearch(orderNo);
        }else {
            return null;
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

    public HeroNode afterSearch(int orderNo){
        if (this.root!=null){
            return this.root.afterSearch(orderNo);
        }else {
            return null;
        }
    }

    public void deleteNode(int orderNo){
        if (this.root!=null){
            //如果只有一个root 需要判断 root是不是要删除的节点
            if (root.getNo()==orderNo){
                root = null;
                return;
            }
//            this.root.deleteNode(orderNo);
            this.root.deleteReplaceNode(orderNo);
        }else {
            System.out.println("为空 无法删除");
        }
    }



    /**
     * <img src="https://cdn.nlark.com/yuque/0/2022/png/27601787/1665049469115-89a137ca-f7e9-4454-a4dd-caa63e27f8a3.png"><br>
     * <img src="https://cdn.nlark.com/yuque/0/2022/png/27601787/1665066203479-503aa3a7-be9d-4237-a879-d2afda89579f.png">
     */
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
        HeroNode node6 = new HeroNode(6, "大撒");

        //分配 - 手动创建 ---->递归
        root.setLeft(node2);
        root.setRight(node3);
//        node2.setRight(node6);
        node3.setRight(node4);
        node3.setLeft(node5);

        //show
        System.out.println("前序遍历");
        binaryTree.root = root;
        binaryTree.beforeShow(); // 1 2 3 4 (1 2 3 5 4)

        System.out.println();
        System.out.println("中序遍历");
        binaryTree.middleShow();//2 1 3 4  (2 1 5 3 4)

        System.out.println();
        System.out.println("后序遍历");
        binaryTree.afterShow();//2 1 3 4 (2,5,4,3,1)


        //search
        System.out.println();
        System.out.println("前序查找");//查找4次
        HeroNode heroNode = binaryTree.beforeSearch(5);
        if (heroNode!=null){
            System.out.println("找到 " + heroNode);
        }

        System.out.println();
        System.out.println("中序查找");//查找3次
        heroNode = binaryTree.middleSearch(5);
        if (heroNode!=null){
            System.out.println("找到 " + heroNode);
        }

        System.out.println();
        System.out.println("后序查找");//查找2次
        heroNode = binaryTree.afterSearch(5);
        if (heroNode!=null){
            System.out.println("找到 " + heroNode);
        }

        //delete   5号(1,2,3,4) 3号节点(1,2)
        System.out.println("删除前～～～");
        binaryTree.beforeShow();
        binaryTree.deleteNode(3);
        System.out.println("删除后～～～");
        binaryTree.beforeShow();
    }
}