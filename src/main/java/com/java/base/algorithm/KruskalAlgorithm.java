/*
 * Copyright (c) luoZhiMin 2022.10.17.10.4.49
 */

package com.java.base.algorithm;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * 克鲁斯卡尔
 * @Author : 镜像
 * @create 2022/10/17 22:04
 */
public class KruskalAlgorithm {

    /*
        克鲁斯卡尔(Kruskal)算法，是用来求加权连通图的最小生成树的算法。
        基本思想：按照权值从小到大的顺序选择 n-1 条边，并保证这 n-1 条边不构成回路
        具体做法：首先构造一个只含 n 个顶点的森林，然后依权值从小到大从连通网中选择边加入到森林中，并使森 林中不产生回路，直至森林变成一棵树为止

        回路
           记录顶点在"最小生成树"中的终点，顶点的终点是"在最小生成树中与它连通的最大顶点"。 然后每次需要将一条边添加到最小生存树时，判断该边的两个顶点的终点是否重合，重合的话则会构成回路

       我们加入的边的两个顶点不能都指向同一 个终点，否则将构成回路

       获取全部边 -> 排序 -> (边的起点/终点)->找终点
     */

    private int edgeNumber;//边的个数
    private char[] vertexes;//顶点
    private int[][] matrix;//邻间矩阵
    private static final int INT = Integer.MAX_VALUE;

    public KruskalAlgorithm(char[] vertexes,int[][] matrix) {
        //初始化顶点
//        this.vertexes = vertexes;
//        this.matrix = matrix;
        //copy array for no INTluence other array
        int vertex = vertexes.length;
        this.vertexes = new char[vertex];
        System.arraycopy(vertexes, 0, this.vertexes, 0, vertexes.length);

        //init matrix
        this.matrix = new int[vertex][vertex];
        for (int i = 0; i < vertex; i++) {
            System.arraycopy(matrix[i], 0, this.matrix[i], 0, vertex);
        }

        //统计边
        for (int i = 0; i < vertex; i++) {
            for (int j = 0; j < vertex; j++) {
                if (matrix[i][j]==0) break;
                if (matrix[i][j]!=INT){
                    edgeNumber++;
                }
            }
        }
    }


    /**
     * <img src="https://cdn.nlark.com/yuque/0/2022/png/27601787/1666018929132-adf429ce-c05e-4b7e-8d10-a47a015ba270.png">
     */
    public static void main(String[] args) {
        char[] vertexes = {'A','B','C','D','E','F','G'};
        //自己和自己连同0 不能Int
        int[][] matrix = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {0, 12, INT, INT, INT, 16, 14},
                /*B*/ {12, 0, 10, INT, INT, 7, INT},
                /*C*/ {INT, 10, 0, 3, 5, 6, INT},
                /*D*/ {INT, INT, 3, 0, 4,INT,INT},
                /*E*/ { INT, INT, 5, 4, 0, 2, 8},
                /*F*/ { 16, 7, 6, INT, 2, 0, 9},
                /*G*/ { 14, INT, INT, INT, 8, 9, 0}};

        KruskalAlgorithm kruskalAlgorithm = new KruskalAlgorithm(vertexes, matrix);

        kruskalAlgorithm.show();
        System.out.println();
        //edge show
        EdgeData[] edges = kruskalAlgorithm.getEdges();
        System.out.println("no sort = >"+Arrays.toString(edges));
//        kruskalAlgorithm.sortEdge(edges);
        System.out.println();
//        System.out.println("sort = >"+Arrays.toString(edges));
        kruskalAlgorithm.kruskal();
    }

    private void show(){
        System.out.println("邻间矩阵～～");
        for (int[] ints : matrix) {
            System.out.print(Arrays.toString(ints)+"\n");
        }
    }

    /**
     * sort
     * @param edges sort edges
     */
    private void sortEdge(EdgeData[] edges){
        EdgeData temp;
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length-1-i; j++) {
                if (edges[j].weight>edges[j+1].weight){
                    temp = edges[j];
                    edges[j] = edges[j+1];
                    edges[j+1] = temp;
                }
            }
        }
    }

    /**
     * @param data vertex data
     * @return 返回顶点的下标 找不到就-1
     */
    private int getPosition(char data){
        for (int i = 0; i < vertexes.length; i++) {
            if (vertexes[i]==data){
                return i;
            }
        }
        return -1;
    }


    /**
     * 获取图中的边 放到 edge[] 中
     * @return edges
     */
    private EdgeData[] getEdges(){
        int index = 0;
        EdgeData[] edges = new EdgeData[edgeNumber];
        for (int i = 0; i < vertexes.length; i++) {
            for (int j = i+1; j < vertexes.length; j++) {
                if (matrix[i][j]!=INT){
                    edges[index++] = new EdgeData(vertexes[i],vertexes[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * 功能: 获取下标为i的顶点的终点 用于判断俩个顶点的终点是否相同
     * @param ends 记录了各个顶点终点是哪个 动态生成
     * @param index 传入顶点的对应下标
     * @return 终点对应的下标
     */
    private int getEnd(int[] ends,int index){
        //todo 需要代入 进行穷举
        while (ends[index]!=0){
            index = ends[index];
        }
        return index;
    }

    public void kruskal(){
        //表示最后结果的索引
        int index =0;
        //保存已有最小生成树的每个顶点的终点
        int[] ends = new int[edgeNumber];
        //创建结果数组 最小生成树
        EdgeData[] results = new EdgeData[edgeNumber];

        //获取图中所有的边的集合
        EdgeData[] edges = getEdges();
        //sort weight small -> big
        sortEdge(edges);
        //遍历edges 将边添加到最小生成树中时，怎么样判断是否形成了回路
        for (EdgeData edge : edges) {
            //获取到第i条边的第一个顶点
            int start = getPosition(edge.start);
            //end
            int end = getPosition(edge.end);

            //获取终点 已有最小生成树
            int m = getEnd(ends, start);
            int n = getEnd(ends, end);

            //判断是否构成回路
            if (m != n) {
                //设置 已有最小生成树 的终点 <E,F> -> [0,0....] 穷举 -> E 4 ...
                ends[m] = n;
                results[index++] = edge;
            }
        }

        //统计 打印
        System.out.println("最小生成树->");
        for (EdgeData result : results) {
            if (result!=null){
                System.out.println(result);
            }
        }
    }

}

/**
 * 创建一个边类
 */
class EdgeData{
    //start
    char start;
    //end
    char end;
    //weight value
    int weight;

    public EdgeData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EdgeData [<" +start + ","+ end +
                ">, weight=" + weight +
                ']';
    }
}