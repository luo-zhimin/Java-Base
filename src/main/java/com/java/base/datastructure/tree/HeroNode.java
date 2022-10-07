/*
 * Copyright (c) luoZhiMin 2022.10.7.10.34.59
 */

package com.java.base.datastructure.tree;

import lombok.Data;

@Data
public class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    /**
     * 0 左自树 1前驱节点
     */
    private int leftType;

    /**
     * 0 右自树 1后继节点
     */
    private int rightType;

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
    public void beforeShow() {
        //输出当前节点
        System.out.println(this);
        //左子树递归
        if (this.left != null) {
            this.left.beforeShow();
        }
        //右子树递归
        if (this.right != null) {
            this.right.beforeShow();
        }
    }

    /**
     * 前序查找
     *
     * @param orderNo 编号
     * @return 返回找到的节点 不然 返回空
     */
    public HeroNode beforeSearch(int orderNo) {
        System.out.println("~~~");
        //比较当前节点是不是
        if (this.no == orderNo) {
            return this;
        }
        //左递归 要找到返回
        HeroNode node = null;
        if (this.left != null) {
            node = this.left.beforeSearch(orderNo);
        }

        if (node != null) {
            return node;
        }

        if (this.right != null) {
            node = this.right.beforeSearch(orderNo);
        }

        return node;
    }


    //中序遍历 左 - 父  -右
    public void middleShow() {
        //左子树递归
        if (this.left != null) {
            this.left.middleShow();
        }
        //输出当前节点
        System.out.println(this);
        //右子树递归
        if (this.right != null) {
            this.right.middleShow();
        }
    }

    public HeroNode middleSearch(int orderNo) {
        HeroNode node = null;
        if (this.left != null) {
            node = this.left.middleSearch(orderNo);
        }
        if (node != null) {
            return node;
        }
        if (this.no == orderNo) {
            return this;
        }
        if (this.right != null) {
            node = this.right.middleSearch(orderNo);
        }
        return node;
    }

    //后序遍历  左 - 右 - 父
    public void afterShow() {
        //左子树递归
        if (this.left != null) {
            this.left.afterShow();
        }

        //右子树递归
        if (this.right != null) {
            this.right.afterShow();
        }

        //输出当前节点
        System.out.println(this);
    }

    public HeroNode afterSearch(int orderNo) {
        HeroNode node = null;
        if (this.left != null) {
            node = this.left.afterSearch(orderNo);
        }

        if (node != null) {
            return node;
        }

        //右子树递归
        if (this.right != null) {
            node = this.right.afterSearch(orderNo);
        }

        if (node != null) {
            return node;
        }

        if (this.no == orderNo) {
            return this;
        }
        return node;
    }

    public void deleteNode(int orderNo) {
        //如果删除的节点是叶子节点，则删除该节点
        //如果删除的节点是非叶子节点，则删除该子树
        //前置放到 tree 里面执行 里面进行递归逻辑

        //左子节点
        if (this.left != null && this.left.no == orderNo) {
            this.left = null;
            return;
        }

        //右子节点
        if (this.right != null && this.right.no == orderNo) {
            this.right = null;
            return;
        }

        //进行递归
        if (this.left != null) {
            this.left.deleteNode(orderNo);
        }

        if (this.right != null) {
            this.right.deleteNode(orderNo);
        }
    }

    public void deleteReplaceNode(int orderNo) {
        //如果要删除的节点是非叶子节点，现在我们不希望将该非叶子节点为根节点的子树删除，需要指定规则
        //如果该非叶子节点 A 只有一个子节点 B，则子节点 B 替代节点 A
        //如果该非叶子节点 A 有左子节点 B 和右子节点 C，则让左子节点 B 替代节点 A

        //左子节点
        if (this.left != null && this.left.no == orderNo) {
            //检查该节点下面是否有其他子节点 如果有则需要替换 (规则)左>右
            this.left = this.left.left != null ? this.left.left : this.left.right;
            //todo 如果原来右面有值 需要放到该节点左面 二叉排序树detail
            return;
        }

        //右子节点
        if (this.right != null && this.right.no == orderNo) {
            this.right = this.right.left != null ? this.right.left : this.right.right;
//            this.right.left = this.right.right!=null ? this.right.right : this.right.left;
            return;
        }

        //进行递归
        if (this.left != null) {
            this.left.deleteReplaceNode(orderNo);
        }

        if (this.right != null) {
            this.right.deleteReplaceNode(orderNo);
        }
    }
}
