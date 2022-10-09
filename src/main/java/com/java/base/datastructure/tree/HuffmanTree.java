/*
 * Copyright (c) luoZhiMin 2022.10.9.3.16.0
 */

package com.java.base.datastructure.tree;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * 赫夫曼树
 * @Author : 镜像
 * @create 2022/10/9 15:16
 */
public class HuffmanTree {

    /*
        基本介绍
            给定 n 个权值作为 n 个叶子结点，构造一棵二叉树，若该树的带权路径长度(wpl)达到最小，称这样的二叉树为 最优二叉树，也称为哈夫曼树(Huffman Tree), 还有的书翻译为霍夫曼树。
            赫夫曼树是带权路径长度最短的树，权值较大的结点离根较近

        概念
            路径和路径长度：在一棵树中，从一个结点往下可以达到的孩子或孙子结点之间的通路，称为路径。通路中分支的数目称为路径长度。若规定根结点的层数为 1，则从根结点到第 L 层结点的路径长度为 L-1
            结点的权及带权路径长度：若将树中结点赋给一个有着某种含义的数值，则这个数值称为该结点的权。结 点的带权路径长度为：从根结点到该结点之间的路径长度与该结点的权的乘积
            树的带权路径长度：树的带权路径长度规定为所有叶子结点的带权路径长度之和，记为 WPL(weighted path length) ,权值越大的结点离根结点越近的二叉树才是最优二叉树。
            WPL 最小的就是赫夫曼树

        构建步骤：
            从小到大进行排序, 将每一个数据，每个数据都是一个节点 ，每个节点可以看成是一颗最简单的二叉树
            取出根节点权值最小的两颗二叉树
            组成一颗新的二叉树, 该新的二叉树的根节点的权值是前面两颗二叉树根节点权值的和
            再将这颗新的二叉树，以根节点的权值大小 再次排序， 不断重复 1-2-3-4 的步骤，直到数列中，所有的数 据都被处理，就得到一颗赫夫曼树
     */

    /**
     * <img src="https://cdn.nlark.com/yuque/0/2022/png/27601787/1665307702437-e92e847a-ac0e-4153-8664-3ae23545c5f1.png"><br>
     * <img src="https://cdn.nlark.com/yuque/0/2022/png/27601787/1665308667004-931e8bb3-d775-4804-aa8d-09134273a7dc.png"><br>
     */
    @Test
    void tree(){
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node root = createHuffmanTree(arr);
        beforeShow(root);
        int wpl = wpl(root, 0, 0);
        System.out.printf("带权路径长度 %d",wpl);
    }

    /**
     * 创建huffman tree
     * @param arr 源数组
     */
    private Node createHuffmanTree(int[] arr){
        //取出元素 封装树  遍历arr 封装node 放入list
        List<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }

//        System.out.println("处理前 nodes => " + nodes);
        //循环处理
        while (nodes.size()>1) {
            //排序
            Collections.sort(nodes);
            //取出根节点权值最小的两颗二叉树节点  左 > 右
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            //组成一颗新的二叉树, 该新的二叉树的根节点的权值是前面两颗二叉树根节点权值的和
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;

            //从list里面删除用过的二叉树 重新进行排序
            nodes.removeAll(Arrays.asList(leftNode, rightNode));
            nodes.add(parent);
//            Collections.sort(nodes);
        }
//        System.out.println("处理后 nodes => " + nodes);
        //返回root节点
        return nodes.get(0);
    }

    private void beforeShow(Node root){
        if (root == null) {
            System.out.println("空树");
            return;
        }
        root.beforeShow();
    }

    private int wpl(Node root,int wpl,int deep){
        if (root!=null){
            if (root.right!=null && root.left!=null){
                wpl = root.value*deep;
            }
            wpl+=wpl(root.left,wpl,deep+1)+wpl(root.right,wpl,deep+1);
            return wpl;
        }
        return 0;
    }
}
/**
 * 创建节点类
 */
class Node implements Comparable<Node> {
    //权值
    int value;
    //左自节点
    Node left;
    //右子节点
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }


    @Override
    public int compareTo(Node o) {
        //从小到大
        return this.value - o.value;
    }

    //前序遍历 中 - 左 - 右
    public void beforeShow() {

        System.out.println(this);

        if (this.left != null) {
            this.left.beforeShow();
        }

        if (this.right != null) {
            this.right.beforeShow();
        }
    }
}