/*
 * Copyright (c) luoZhiMin 2022.10.6.3.17.53
 */

package com.java.base.datastructure.tree;

import lombok.Data;
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

    /**
     * 前序查找
     * @param orderNo 编号
     * @return 返回找到的节点 不然 返回空
     */
    public HeroNode beforeSearch(int orderNo){
        System.out.println("~~~");
        //比较当前节点是不是
        if (this.no==orderNo){
            return this;
        }
        //左递归 要找到返回
        HeroNode node = null;
        if (this.left!=null){
            node = this.left.beforeSearch(orderNo);
        }

        if (node!=null){
            return node;
        }

        if (this.right!=null){
            node = this.right.beforeSearch(orderNo);
        }

        return node;
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

    public HeroNode middleSearch(int orderNo){
        HeroNode node = null;
        if (this.left!=null){
            node = this.left.middleSearch(orderNo);
        }
        if(node!=null){
            return node;
        }
        if (this.no == orderNo){
            return this;
        }
        if (this.right!=null){
            node = this.right.middleSearch(orderNo);
        }
        return node;
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

    public HeroNode afterSearch(int orderNo){
        HeroNode node = null;
        if (this.left!=null){
            node = this.left.afterSearch(orderNo);
        }

        if(node!=null){
            return node;
        }

        //右子树递归
        if (this.right!=null){
            node = this.right.afterSearch(orderNo);
        }

        if(node!=null){
            return node;
        }

        if (this.no == orderNo){
            return this;
        }
        return node;
    }

    public void deleteNode(int orderNo){
        //如果删除的节点是叶子节点，则删除该节点
        //如果删除的节点是非叶子节点，则删除该子树
        //前置放到 tree 里面执行 里面进行递归逻辑

        //左子节点
        if (this.left!=null && this.left.no == orderNo){
            this.left = null;
            return;
        }

        //右子节点
        if (this.right!=null && this.right.no == orderNo){
            this.right = null;
            return;
        }

        //进行递归
        if (this.left!=null){
            this.left.deleteNode(orderNo);
        }

        if (this.right!=null){
            this.right.deleteNode(orderNo);
        }
    }

    public void deleteReplaceNode(int orderNo){
        //如果要删除的节点是非叶子节点，现在我们不希望将该非叶子节点为根节点的子树删除，需要指定规则
        //如果该非叶子节点 A 只有一个子节点 B，则子节点 B 替代节点 A
        //如果该非叶子节点 A 有左子节点 B 和右子节点 C，则让左子节点 B 替代节点 A

        //左子节点
        if (this.left!=null && this.left.no == orderNo){
            //检查该节点下面是否有其他子节点 如果有则需要替换 (规则)左>右
            this.left = this.left.left!=null ? this.left.left : this.left.right;
            //todo 如果原来右面有值 需要放到该节点左面 二叉排序树detail
            return;
        }

        //右子节点
        if (this.right!=null && this.right.no == orderNo){
            this.right = this.right.left!=null ? this.right.left : this.right.right;
//            this.right.left = this.right.right!=null ? this.right.right : this.right.left;
            return;
        }

        //进行递归
        if (this.left!=null){
            this.left.deleteReplaceNode(orderNo);
        }

        if (this.right!=null){
            this.right.deleteReplaceNode(orderNo);
        }
    }
}