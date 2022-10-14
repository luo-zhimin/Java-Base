/*
 * Copyright (c) luoZhiMin 2022.10.14.5.51.27
 */

package com.java.base.datastructure.nonlinearstructure.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

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

        所谓图的遍历，即是对结点的访问。一个图有那么多个结点，如何遍历这些结点，需要特定策略，一般有两种 访问策略:
            深度优先遍历
            广度优先遍历


       图的深度优先搜索(Depth First Search)
            选取一个节点开始，沿着一条路一直走到底，然后从这条路尽头的节点回退到上一个节点，再从另一条路开始走到底
           深度优先遍历，从初始访问结点出发，初始访问结点可能有多个邻接结点，深度优先遍历的策略就是首先访问 第一个邻接结点，然后再以这个被访问的邻接结点作为初始结点，访问它的第一个邻接结点，
            可以这样理解： 每次都在访问完当前结点后首先访问当前结点的第一个邻接结点。
           访问策略是优先往纵向挖掘深入，而不是对一个结点的所有邻接结点进行横向访问。
           深度优先搜索是一个递归的过程

       图的广度优先搜索(Broad First Search)
          从图的一个未遍历的节点出发，先遍历这个节点的相邻节点，再依次遍历每个相邻节点的相邻节点
          类似于一个分层搜索的过程，广度优先遍历需要使用一个队列以保持访问过的结点的顺序，以便按这个顺序来 访问这些结点的邻接结点
     */

    //顶点
    private ArrayList<String> vertexes;

    //邻间矩阵
    private int[][] edges;

    //边的数目
    private int numberOfEdges;

    //节点是否访问
    private boolean[] isVisited;

    public GraphDemo(int n){
        edges = new int[n][n];
        vertexes = new ArrayList<>(n);
        isVisited = new boolean[n];
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

    /**
     * 获取第一个邻间点的下标
     * @return 存在返回对应的下标 不然就-1
     */
    public int getFirstNeighbor(int index){
        for (int i = 0; i < vertexes.size(); i++) {
            if (edges[index][i]>0){
                return i;
            }
        }
        return -1;
    }

    //根据前一个临界点的下标获取下个邻间点的下标
    public int getNetNeighbor(int v1,int v2){
        for (int i = v2+1; i < vertexes.size(); i++) {
            if (edges[v1][i]>0){
                return i;
            }
        }
        return -1;
    }

    /**
     * depth first search
     * @param isVisited 是否访问
     * @param index 索引
     */
    private void dfs(boolean[] isVisited,int index){
//        3) 若 w 存在，则继续执行 4，如果 w 不存在，则回到第 1 步，将从 v 的下一个结点继续。
//        5) 查找结点 v 的 w 邻接结点的下一个邻接结点，转到步骤 3。
        //访问初始结点 v，并标记结点 v 为已访问。
        System.out.print(getValue(index)+" -> ");
        //将这个节点设置为已经访问
        isVisited[index] = true;
        //查找结点 v 的第一个邻接结点 w。
        int w = getFirstNeighbor(index);
        while (w!=-1){
            //若 w 未被访问，对 w 进行深度优先遍历递归（即把 w 当做另一个 v，然后进行步骤 123）
            if (!isVisited[w]){
                dfs(isVisited, w);
            }
            //若 w 存在，则继续
            w = getNetNeighbor(index,w);
        }
        //如果 w 不存在，则回到第 1 步，将从 v 的下一个结点继续。
    }

    //重载
    public void dfs(){
        for (int i = 0; i < getNumberOfVertex(); i++) {
            //回溯
            if (!isVisited[i]){
                //没有访问
                dfs(isVisited,i);
            }
        }
    }

    //对一个节点进行广度优先遍历
    private void bfs(boolean[] isVisited,int index){

        int u;//表示队列的头节点对应的下标
        int w;//邻接结点
        //队列 记录节点访问的顺序
        LinkedList<Object> queue = new LinkedList<>();
        //访问节点 输出节点信息
        System.out.print(getValue(index)+"=>");
        //标记访问  访问初始结点 v 并标记结点 v 为已访问
        isVisited[index] = true;
        //结点 v 入队列 先进先出
        queue.addLast(index);

        // 若结点 u 的邻接结点 w 不存在，则转到步骤 3；否则循环执行以下三个步骤：
        //当队列非空时，继续执行，否则算法结束。
        while (!queue.isEmpty()){
            // 出队列，取得队头结点 u。
            u = (Integer) queue.removeFirst();
            // 查找结点 u 的第一个邻接结点 w。
            w = getFirstNeighbor(u);
            while (w!=-1){//找到
                if (!isVisited[w]){
                    System.out.print(getValue(w)+"=>");
                    //标记访问
                    // 6.1 若结点 w 尚未被访问，则访问结点 w 并标记为已访问。
                    isVisited[w]=true;
                    //入队
                    // 6.2 结点 w 入队列
                    queue.addLast(w);
                }
                //找到下一个 以u为前驱 找w后面的下一个邻节点
                // 6.3 查找结点 u 的继 w 邻接结点后的下一个邻接结点 w
                w = getNetNeighbor(u,w);
            }
        }
    }

    public void bfs(){
        for (int i = 0; i < getNumberOfVertex(); i++) {
            if (!isVisited[i]){
                bfs(isVisited,i);
            }
        }
    }

    public static void main(String[] args) {
//        String[] vertexValues = {"A","B","C","D","E"};
        String[] vertexValues = {"1","2","3","4","5","6","7","8"};
        int n = vertexValues.length;
        //创建图对象
        GraphDemo graphDemo = new GraphDemo(n);
        for (String vertexValue : vertexValues) {
            graphDemo.insertVertex(vertexValue);
        }
        //添加边
        //A-B A-C B-C B-D B-E
//        graphDemo.insertEdge(0,1,1);
//        graphDemo.insertEdge(0,2,1);
//        graphDemo.insertEdge(1,2,1);
//        graphDemo.insertEdge(1,3,1);
//        graphDemo.insertEdge(1,4,1);

        graphDemo.insertEdge(0, 1, 1);
        graphDemo.insertEdge(0, 2, 1);
        graphDemo.insertEdge(1, 3, 1);
        graphDemo.insertEdge(1, 4, 1);
        graphDemo.insertEdge(3, 7, 1);
        graphDemo.insertEdge(4, 7, 1);
        graphDemo.insertEdge(2, 5, 1);
        graphDemo.insertEdge(2, 6, 1);
        graphDemo.insertEdge(5, 6, 1);

        System.out.println("show graph~~");
        graphDemo.showGraph();

        System.out.println("depth first search~~");
        graphDemo.dfs();//[1 -> 2 -> 4 -> 8 -> 5 -> 3 -> 6 -> 7]

        //重置 isVisited
        graphDemo.isVisited = new boolean[n];

        System.out.println("\nBroad first search~~");
        graphDemo.bfs();//[1=>2=>3=>4=>5=>6=>7=>8]
        System.out.println();
    }
}
