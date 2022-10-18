/*
 * Copyright (c) luoZhiMin 2022.10.18.5.31.24
 */

package com.java.base.algorithm;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.java.base.algorithm.DijkstraAlgorithm.N;

/**
 * Created by IntelliJ IDEA.
 * 迪杰斯特拉
 * @Author : 镜像
 * @create 2022/10/18 17:31
 */
public class DijkstraAlgorithm {

    /*

        单源点最短路径 - 迪杰斯特拉(Dijkstra)
        迪杰斯特拉(Dijkstra)算法是典型最短路径算法，用于计算一个结点到其他结点的最短路径。它的主要特点是以 起始点为中心向外层层扩展(广度优先搜索思想)，直到扩展到终点为止

        设置出发顶点为 v，顶点集合 V{v1,v2,vi...}，v 到 V 中各顶点的距离构成距离集合 Dis，Dis{d1,d2,di...}，Dis 集合记录着 v 到图中各顶点的距离(到自身可以看作 0，v 到 vi 距离对应为 di)
        从 Dis 中选择值最小的 di 并移出 Dis 集合，同时移出 V 集合中对应的顶点 vi，此时的 v 到 vi 即为最短路径
        更新 Dis 集合，更新规则为：比较 v 到 V 集合中顶点的距离值，与 v 通过 vi 到 V 集合中顶点的距离值，保留 值较小的一个(同时也应该更新顶点的前驱节点为 vi，表明是通过 vi 到达的)
        重复执行两步骤，直到最短路径顶点为目标顶点即可结束
     */

    static final int N = 65535;

    /**
     * <img src="https://cdn.nlark.com/yuque/0/2022/png/27601787/1665974757761-a7bd8c01-a49f-41bb-beac-062820517169.png"><br>
     * <img src="https://cdn.nlark.com/yuque/0/2022/png/27601787/1666099794832-d498ef46-9e04-45f9-b31c-007c73da1235.png"><br>
     */
    @Test
    void algorithm(){
        //顶点
        char[] vertex = {'A','B','C','D','E','F','G'};
        //邻间矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        //'A','B','C','D','E','F','G'
        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, N, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, N, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, N, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, N};
        //构建图
        Graph graph = new Graph(vertex, matrix);
        //显示
        graph.showGraph();
        graph.dijkstra(6);
        graph.show();
    }


}

/**
 * 记录访问顶点的集合
 */
class VisitedVertex{
    // 记录各个顶点是否访问过 1 表示访问过,0 未访问,会动态更新
    int[] already_arr;
    // 每个下标对应的值为前一个顶点下标, 会动态更新
    int[] pre_visited;
    // 记录出发顶点到其他所有顶点的距离,比如 G 为出发顶点，就会记录 G 到其它顶点的距离，会动态更新，求 的最短距离就会存放到 dis
    int[] dis;

    /**
     * @param length 顶点的个数
     * @param index 出发顶点-对应的下标
     */
    public VisitedVertex(int length,int index){
        this.already_arr = new int[length];
        this.pre_visited = new int[length];
        this.dis = new int[length];
        //init dist array
        Arrays.fill(dis,N);
        this.dis[index] = 0;//自己到自己 访问距离是 0
        //设置出发顶点被访问
        this.already_arr[index] = 1;
    }

    /**
     * 判断index顶点是否被访问
     * @param index 下标
     * @return 访问过true/false
     */
    public boolean in(int index){
        return already_arr[index]==1;
    }

    /**
     * 更新出发顶点的到index顶点的距离
     * @param index 下标
     * @param len value
     */
    public void updateDis(int index,int len){
        dis[index] = len;
    }

    /**
     * 更新 顶点的前驱 为 index的 节点
     * @param pre 前驱节点
     * @param index 下标
     */
    public void updatePre(int pre,int index){
        pre_visited[pre] = index;
    }

    /**
     * 返回出发顶点到index的距离
     * @param index index
     */
    public int getDis(int index){
        return dis[index];
    }

    /**
     * 继续选择并返回新的访问顶点， 比如这里的 G 完后，就是 A 点作为新的访问顶点(注意不是出发顶点)
     * @return 新访问节点的下标
     */
    public int updateArray(){
        int min = N,index = 0;
        for (int i = 0; i < already_arr.length; i++) {
            if (already_arr[i]==0 && dis[i]<min){
                min = dis[i];
                index = i;
            }
        }
        //更新被访问
        already_arr[index] = 1;
        return index;
    }

    public void show(){
        System.out.println("=============");
        System.out.println("already_arr~~");
        for (int i : already_arr) {
            System.out.print(i+"\t");
        }
        System.out.println("\npre_visited~~");
        for (int i : pre_visited) {
            System.out.print(i+"\t");
        }

        char[] vertex = {'A','B','C','D','E','F','G'};
        int count = 0;
        System.out.println("\ndis~~");
        for (int di : dis) {
            if (di!=N){
                System.out.print(vertex[count]+"("+di+")"+" ");
            }else {
                System.out.println("N");
            }
            count++;
        }
        System.out.println();
    }
}

class Graph{
    //顶点
    private char[] vertex;
    //邻接矩阵
    private int[][] matrix;

    //已经访问的顶点的集合
    private VisitedVertex vv;

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    public void show(){
        vv.show();
    }

    //显示
    public void showGraph(){
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }
    }

    /**
     * @param index 出发顶点对应的下标
     */
    public void dijkstra(int index){
        //init v
        vv = new VisitedVertex(vertex.length, index);
        //更新index顶点到周围顶点的距离和前驱顶点 -> 广度第一层
        update(index);

        for (int j = 1; j < vertex.length; j++) {
            //选择返回新的访问节点
            index = vv.updateArray();
            update(index);
        }
    }

    //更新index下标顶点到周围顶点的距离 和 周围顶点前驱顶点
    private void update(int index) {
        int len;
        //bfs 广度优先 一层
        for (int i = 0; i < matrix[index].length; i++) {
            //出发顶点到index顶点的距离 + 从index顶点到j顶点的距离的和 GK -> GA + AK
            //matrix[index][i] = > 0 + [2, 3, 2147483647, 2147483647, 4, 6, 2147483647]
            len = vv.getDis(index) + matrix[index][i];
            //i 没有被访问 len < 出发顶点到i顶点的距离
            if (!vv.in(i) && len < vv.getDis(i)){
                vv.updatePre(i,index);
                vv.updateDis(i,len);
            }
        }
    }
}