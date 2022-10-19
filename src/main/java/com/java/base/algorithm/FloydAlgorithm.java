/*
 * Copyright (c) luoZhiMin 2022.10.18.11.22.11
 */

package com.java.base.algorithm;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
* Created by IntelliJ IDEA.
* 弗洛伊德算法
* @Author : 镜像
* @create 2022/10/18 23:22
*/
@SuppressWarnings({"all"})
public class FloydAlgorithm {

    /*
        弗洛伊德算法(Floyd)计算图中各个顶点之间的最短路径
        迪杰斯特拉算法用于计算图中某一个顶点到其他顶点的最短路径。
        弗洛伊德算法 VS 迪杰斯特拉算法：迪杰斯特拉算法通过选定的被访问顶点，求出从出发访问顶点到其他顶点 的最短路径；弗洛伊德算法中每一个顶点都是出发访问点，所以需要将每一个顶点看做被访问顶点，求出从每 一个顶点到其他顶点的最短路径
     */
    
    final int IN = 65535;

    /**
     * <img src="https://cdn.nlark.com/yuque/0/2022/png/27601787/1665974757761-a7bd8c01-a49f-41bb-beac-062820517169.png"><br>
     */
    @Test
    void algorithm(){
        //顶点
        char[] vertex = {'A','B','C','D','E','F','G'};
        //邻间矩阵
        int[][] matrix = new int[][]{
                //'A','B','C','D','E','F','G'
                {0, 5, 7, IN, IN, IN, 2},
                {5, 0, IN, 9, IN, IN, 3},
                {7, IN, 0, IN, 8, IN, IN},
                {IN, 9, IN, 0, IN, 4, IN},
                {IN, IN, 8, IN, 0, 5, 4},
                {IN, IN, IN, 4, 5, 0, 6},
                {2, 3, IN, IN, 4, 6, 0}};
        FloydGraph floydGraph = new FloydGraph(vertex.length, matrix, vertex);
        System.out.println("before floyd~~~");
        floydGraph.showGraph();
        floydGraph.floyd();
        System.out.println();
        System.out.println("floyd after~~~");
        floydGraph.showGraph();
    }
}
/**
 * graph 创建
 */
class FloydGraph{
    char[] vertex;
    //记录最短路径 各个顶点到其他顶点的距离
    int[][] dis;
    //前驱节点
    int[][] pre;

    /**
     * @param length 长度大小
     * @param matrix 邻间矩阵
     * @param vertex 顶点数组
     */
    public FloydGraph(int length,int[][] matrix,char[] vertex) {
        this.vertex=vertex;
        this.dis = matrix;
        this.pre = new int[length][length];
        //init pre pre Array => pre index
        for (int i = 0; i < length; i++) {
            Arrays.fill(pre[i],i);
        }
    }
    
    public void showGraph(){
        char[] vertex = {'A','B','C','D','E','F','G'};
        //dis pre
//        System.out.println("dis~~");
//        for (int[] di : dis) {
//            System.out.println(Arrays.toString(di));
//        }
        for (int i = 0; i < dis.length; i++) {
            for (int j = 0; j < dis.length; j++) {
                System.out.print(vertex[pre[i][j]]+"\t");
            }
            System.out.println();
            for (int j = 0; j < dis.length; j++) {
                System.out.print(vertex[i]+"到"+vertex[j]+"的最短路径是"+dis[i][j]+"\t");
            }
            System.out.println();
        }

//        System.out.println("pre~~");
//        for (int[] ints : pre) {
//            System.out.println(Arrays.toString(ints));
//        }
    }

    public void floyd(){
        int len;
        //从中间顶点出发 k是中间顶点的下标
        for (int k = 0; k < vertex.length; k++) {
            //从i顶点开始出发
            for (int i = 0; i < vertex.length; i++) {
                //到达的顶点
                for (int j = 0; j < vertex.length; j++) {
                    //min((Lik+Lkj),Lij)，
                    len = dis[i][k]+dis[k][j];
                    if (len<dis[i][j]){
                        //重置
                        dis[i][j]=len;
                        //更新前驱节点
                        pre[i][j] = pre[k][j];
                    }
                }
            }
        }
    }
}