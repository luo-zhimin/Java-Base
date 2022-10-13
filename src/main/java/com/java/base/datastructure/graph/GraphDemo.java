/*
 * Copyright (c) luoZhiMin 2022.10.13.6.31.9
 */

package com.java.base.datastructure.graph;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * 图
 * @Author : 镜像
 * @create 2022/10/13 18:31
 */
public class GraphDemo {

    /*
        线性表局限于一个直接前驱和一个直接后继的关系
        树也只能有一个直接前驱也就是父节点
        当我们需要表示多对多的关系时， 这里我们就用到了图

        图是一种数据结构，其中结点可以具有零个或多个相邻元素。两个结点之间的连接称为边。 结点也可以称为 顶点

        顶点(vertex)
        边(edge)
        路径
        无向图-没有方向概念
        有向图-有方向概念
        带权图-带距离(权值)

        图的表示方式有两种：
            二维数组表示（邻接矩阵）
            链表表示（邻接表）

        邻接矩阵
            邻接矩阵是表示图形中顶点之间相邻关系的矩阵，对于 n 个顶点的图而言，矩阵是的 row 和 col 表示的是 1....n 个点(直连)

        邻接表
            邻接矩阵需要为每个顶点都分配 n 个边的空间，其实有很多边都是不存在,会造成空间的一定损失.
            邻接表的实现只关心存在的边，不关心不存在的边。因此没有空间浪费，邻接表由数组+链表组成

     */

    //顶点
    private ArrayList<String> vertexes;

    //邻间矩阵
    private int[][] edges;

    //边的数目
    private int numberOfEdges;

    public GraphDemo(int n){
        edges = new int[n][n];
        vertexes = new ArrayList<>(n);
    }

    /**
     * 插入顶点
     * @param vertex 顶点-value
     */
    public void insertVertex(String vertex){
        vertexes.add(vertex);
    }

    /**
     * 无向图
     * @param v1 表示点的下标 第几个点
     * @param v2 边二
     * @param weight 权值
     */
    public void insertEdge(int v1,int v2,int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numberOfEdges++;
    }

    //graph Commonly used methods 返回节点的个数
    public int getNumberOfVertex(){
        return vertexes.size();
    }

    //得到边的数目
    public int getNumberOfEdges(){
        return numberOfEdges;
    }

    //返回节点对应的值
    public String getValue(int index){
        return vertexes.get(index);
    }

    //得到他的下标
    public int getWeight(int v1,int v2){
        return edges[v1][v2];
    }

    //显示图对应的矩阵
    public void showGraph(){
        for (int[] ints : edges) {
            System.out.println(Arrays.toString(ints));
        }
    }

    public static void main(String[] args) {
        int n = 5;
        String[] vertexValues = {"A","B","C","D","E"};
        //创建图对象
        GraphDemo graphDemo = new GraphDemo(n);
        for (String vertexValue : vertexValues) {
            graphDemo.insertVertex(vertexValue);
        }
        //添加边
        //A-B A-C B-C B-D B-E
        graphDemo.insertEdge(0,1,1);
        graphDemo.insertEdge(0,2,1);
        graphDemo.insertEdge(1,2,1);
        graphDemo.insertEdge(1,3,1);
        graphDemo.insertEdge(1,4,1);

        System.out.println("show graph~~");
        graphDemo.showGraph();
    }

}
