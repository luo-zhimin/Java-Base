/*
 * Copyright (c) luoZhiMin 2022.10.7.9.2.50
 */

package com.java.base.datastructure.tree;

import lombok.Data;
import org.junit.jupiter.api.Test;

/**
 * Created by IntelliJ IDEA.
 * 线索二叉树
 * @Author : 镜像
 * @create 2022/10/7 21:02
 */
public class ThreadedTreeDemo {

    /*
        n 个结点的二叉链表中含有 n+1 【公式 2n-(n-1)=n+1】 个空指针域。利用二叉链表中的空指针域，存放指向 该结点在某种遍历次序下的前驱和后继结点的指针（这种附加的指针称为"线索"）
        这种加上了线索的二叉链表称为线索链表，相应的二叉树称为线索二叉树(Threaded BinaryTree)。根据线索性质 的不同，线索二叉树可分为前序线索二叉树、中序线索二叉树和后序线索二叉树三种
        一个结点的前一个结点，称为前驱结点
        一个结点的后一个结点，称为后继结点


        说明: 当线索化二叉树后，Node 节点的 属性 left 和 right ，有如下情况:
           left 指向的是左子树，也可能是指向的前驱节点. 比如 ① 节点 left 指向的左子树, 而 ⑩ 节点的 left 指向的 就是前驱节点.
           right 指向的是右子树，也可能是指向后继节点，比如 ① 节点 right 指向的是右子树，而 ⑩ 节点的 right 指向 的是后继节点
     */

    /**
     * <img src="">
     */
    @Test
    void tree(){
        //创建树
        ThreadedTree tree = new ThreadedTree();
        //创建需要的节点
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "jerry");
        HeroNode node4 = new HeroNode(8, "smith");
        HeroNode node5 = new HeroNode(10, "john");
        HeroNode node6 = new HeroNode(14, "relax");

        //分配 - 手动创建 ---->递归(后面)
        root.setLeft(node2);
        root.setRight(node3);

        node2.setLeft(node4);
        node2.setRight(node5);

        node3.setLeft(node6);

        System.out.println("线索化测试～～");
        tree.setRoot(root);

        tree.threadNodes();//8 3 10 1 14 6

        //10号节点测试
        System.out.println(node5.getNo()+"前驱节点是=>"+node5.getLeft());//3
        System.out.println(node5.getNo()+"后继节点是=>"+node5.getRight());//1

        //原来的进行处理 会出现死循环
        tree.threadMiddleShow();
    }
}
@Data
class ThreadedTree{
    private HeroNode root;

    //为了实现线索化需要借助指针 pre 总是保留前一个节点
    private HeroNode pre;

    public void threadNodes(){
        this.threadNodes(root);
    }

    /**
     * 进行线索化 中序 <br><br>
     * <img src="https://cdn.nlark.com/yuque/0/2022/png/27601787/1665155482778-d51c6e18-fd18-43a6-8494-c19635dce6c9.png">
     */
    private void threadNodes(HeroNode node){
        //对节点进行线索化
        if (node==null){
            System.out.println("树为空~~无法线索化");
            return;
        }

        //开始线索化
        //左 - 中 - 右
        //左子树处理
        if (node.getLeft()!=null){
            threadNodes(node.getLeft());
        }

        //中
        //先处理当前节点的前驱节点
        if (node.getLeft()==null){
            //没有左子节点 需要进行线索化 指向他的前驱节点
            node.setLeft(pre);
            //修改当前节点的左指针的类型
            node.setLeftType(1);
        }

        //后继节点处理
        if (pre != null && pre.getRight() == null) {
            //前驱节点的右指针类型指向当前节点
            pre.setRight(node);
            pre.setRightType(1);
        }

        //每处理一个节点后 让当前节点是一个节点的前驱节点!!!
        pre=node;

        //右
        if (node.getRight()!=null){
            threadNodes(node.getRight());
        }
    }

    /*
        因为线索化后，各个结点指向有变化，因此原来的遍历方式不能使用，这时需要使用新的方式遍历 线索化二叉树，
        各个节点可以通过线型方式遍历，因此无需使用递归方式，这样也提高了遍历的效率。 遍历的次 序应当和中序遍历保持一致
     */
    public void threadMiddleShow(){
        //定义一个变量 存储 当前 变量 从 root 开始
        HeroNode node = root;
        while (node!=null){
            //循环找到leftType=1的节点 第一个找到的是8 pre==>null 后面随着循环 而变化
            while (node.getLeftType()==0){
                node = node.getLeft();
            }
            //打印当前节点
            System.out.println(node);
            //如果当前节点的指针指向是后继节点 就一直输出
            while (node.getRightType()==1){
                //获取当前节点的后继节点
                node = node.getRight();
                System.out.println(node);
            }
            //替换遍历得节点
            node = node.getRight();
        }
    }

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
    public void middleShow(){
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
}
