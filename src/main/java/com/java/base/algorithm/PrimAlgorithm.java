/*
 * Copyright (c) luoZhiMin 2022.10.17.10.38.40
 */

package com.java.base.algorithm;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * 普里姆算法 - 修路问题
 * @Author : 镜像
 * @create 2022/10/17 10:38
 */
public class PrimAlgorithm {

    /*
        修路问题本质就是就是最小生成树问题， 先介绍一下最小生成树(Minimum Cost Spanning Tree)，简称 MST。 给定一个带权的无向连通图,如何选取一棵生成树,使树上所有边上权的总和为最小,这叫最小生成树

        步骤：
            1.设 G=(V,E)是连通网，T=(U,D)是最小生成树，V,U 是顶点集合，E,D 是边的集合
            2. 若从顶点 u 开始构造最小生成树，则从集合 V 中取出顶点 u 放入集合 U 中，标记顶点 v 的 visited[u]=1
            3.若集合 U 中顶点 ui 与集合 V-U 中的顶点 vj 之间存在边，则寻找这些边中权值最小的边，但不能构成回路，将 顶点 vj 加入集合 U 中，将边（ui,vj）加入集合 D 中，标记 visited[vj]=1
            4.重复步骤②，直到 U 与 V 相等，即所有顶点都被标记为访问过，此时 D 中有 n-1 条边

     */


    /**
     * <img src="https://cdn.nlark.com/yuque/0/2022/png/27601787/1665974757761-a7bd8c01-a49f-41bb-beac-062820517169.png"><br>
     * <img src="https://cdn.nlark.com/yuque/0/2022/png/27601787/1665978392188-8da24612-0f09-4eef-8424-70fdd003711c.png"><br>
     */
    @Test
    void roadConstruction(){
        char[] data = {'A','B','C','D','E','F','G'};
        int vertx = data.length;
        //邻接矩阵的关系使用二维数组表示,10000 这个大数，表示两个点不联通
        int[][] weight = new int[][]{
                //'A','B','C','D','E','F','G'
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000}};

        MGraph graph = new MGraph(vertx);
        MinTree minTree = new MinTree();
        minTree.createGraph(graph,vertx,data,weight);
        minTree.showGraph(graph);
        System.out.println();
        System.out.println("prim~~");
        minTree.prim(graph,0);
    }
}

/**
 * 创建最小生成树
 */
class MinTree{

    /**
     * 创建图的矩阵
     * @param graph 图对象
     * @param vertx 顶点
     * @param data 各个顶点的值
     * @param weight 邻接矩阵
     */
    public void createGraph(MGraph graph,int vertx,char[] data,int[][] weight){
        int i,j;
        for (i = 0; i < vertx; i++) {
            graph.data[i] = data[i];
            for (j = 0; j < vertx; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    public void showGraph(MGraph graph){
        System.out.println("show graph~~");
        for (int[] ints : graph.weight) {
            System.out.printf(Arrays.toString(ints)+"\n");
        }
    }

    /**
     * 得到最小生成树 - 权恒定
     * @param graph 图
     * @param vertx 从图的第几个节点开始生成
     */
    public void prim(MGraph graph,int vertx){
        //是否已经访问 标记顶点 default zero -> false
        int[] visited = new int[graph.vertx];
        //把当前这个节点 标记为已经访问
        visited[vertx] = 1;

        //h1 h2 记录俩个顶点的下标
        int h1 =-1;
        int h2 =-1;
        //初始化一个较大的值 后续遍历会被替换
        int minWeight = 10000;
        //最小生成树 n-1 个边 n->vertx
        for (int k = 1; k < graph.vertx; k++) {

            //确定每一次生成的子图 和哪个节点和这次节点的距离最近
            for (int i = 0; i < graph.vertx; i++) {//i顶点 表示被访问过的节点
                for (int j = 0; j < graph.vertx; j++) {//j节点是表示还没有访问过的节点
                    if(visited[i]==1 && visited[j]==0 && graph.weight[i][j]<minWeight){
                        //replace minWeight(寻找已经访问 和 未访问的节点的权值最小的边)
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            //找到一条边是最小的
            System.out.println("边 <"+graph.data[h1]+","+graph.data[h2]+"> 权值："+minWeight);
            //标记已经访问
            visited[h2] = 1;
            //重置
            minWeight = 10000;
        }
    }
}
class MGraph{
    int vertx;
    char[] data;//存放节点数据
    int[][] weight;//存放边 邻接矩阵

    public MGraph(int vertx) {
        this.vertx = vertx;
        data = new char[vertx];
        weight = new int[vertx][vertx];
    }
}
