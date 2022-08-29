/*
 * Copyright (c) luoZhiMin 2022.8.28.9.59.23
 */

package com.java.base.datastructure.linearstructure;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by IntelliJ IDEA.
 * 稀疏数组
 * @Author : 镜像
 * @create 2022/8/28 21:59  <br/> <br/>
 *
 * <img src="https://cdn.nlark.com/yuque/0/2022/png/27601787/1661696130232-b0f09555-cbed-4941-b5ff-1a2738d4bb66.png">
 */
public class SparseArrays {

    /*
        当一个数组中大部分元素为０，或者为同一个值的数组时，可以使用稀疏数组来保存该数组

        稀疏数组的处理方法是:
            1) 记录数组一共有几行几列，有多少个不同的值
            2) 把具有不同值的元素的行列及值记录在一个小规模的数组中，从而缩小程序的规模

        应用实例
            1) 使用稀疏数组，来保留类似前面的二维数组(棋盘、地图等等)
            2) 把稀疏数组存盘，并且可以从新恢复原来的二维数组数

        稀疏数组 (行=>变量值+1 列=3)
            row     col     value
            行       列   多少个有效值
     */

    /**
     * 行
     */
    private static final int row = 11;

    /**
     * 列
     */
    private static final int column = 11;

    /*
        在写完的基础上 将稀疏数组保存到磁盘上，比如 map.data
        恢复原来的数组时，读取 map.data 进行恢复
     */

    @Test
    void sparseArrays(){
        //创建二维数组 11*11
        // 0: 表示没有棋子， 1 表示 黑子 2 表示 蓝子
        int[][] chessBoards = new int[row][column];
        //设置棋子
        chessBoards[2][4] = 1;
        chessBoards[3][4] = 2;
        chessBoards[4][5] = 2;
        //输出原始数组
        System.out.println("===原始数组===");
        showChessBoards(chessBoards);
        //准备转换稀疏数组
        //接收变量个数
        int sum = readySum(chessBoards);
//        System.out.println("变量 sum = "+sum);
        //创建稀疏数组
        int[][] sparseArray = initSparseArrays(sum);

        System.out.println("初始化的稀疏数组");
        showChessBoards(sparseArray);
        //为稀疏数组赋值
        transformSparseArrays(chessBoards,sparseArray);
        //可以保存到磁盘
        System.out.println("稀疏数组赋值完成");
        showChessBoards(sparseArray);
        System.out.println("准备还原二维数组");
        //稀疏数组第一行
        int[][] newChessBoards = new int[sparseArray[0][0]][sparseArray[0][1]];
        //赋值
        for (int i = 1; i <= sparseArray[0][2]; i++) {
            //1 ,2 ,3
            newChessBoards[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        System.out.println("还原后二维数组");
        showChessBoards(newChessBoards);
    }

    /**
     * 数组展示 - 输出
     * @param chessBoards 二维数组
     */
    private void showChessBoards(int[][] chessBoards){
        for (int[] chessBoard : chessBoards) {
            for (int row : chessBoard) {
                System.out.print(row+"\t");
            }
            System.out.println();
        }
        System.out.println("=============");
    }

    /**
     * @return 变量一共出现了多少次
     */
    private int readySum(int[][] chessBoards){
        int sum = 0 ;
        for (int[] chessBoard : chessBoards) {
            for (int i : chessBoard) {
                if (i != 0) {
                    sum++;
                }
            }
        }
        return sum;
    }

    /**
     * init arrays
     * @param sum 变量个数
     * @return sparseArrays
     */
    private int[][] initSparseArrays(int sum){
        int[][] sparseArray = new int[sum+1][3];
        sparseArray[0][0] = row;
        sparseArray[0][1] = column;
        sparseArray[0][2] = sum;
        return sparseArray;
    }

    /**
     * 稀疏数组赋值
     * @param chessBoards 源二维数组
     * @param sparseArrays 稀疏数组
     */
    private void transformSparseArrays(int[][] chessBoards,int[][] sparseArrays){
        int count = 0 ; //统计变量值
        for (int i = 0; i < chessBoards.length; i++) {
            for (int j = 0; j < chessBoards[i].length; j++) {
                if (chessBoards[i][j]!=0){
                    //break first row
                    count ++;
                    //set
                    sparseArrays[count][0] = i;
                    sparseArrays[count][1] = j;
                    sparseArrays[count][2] = chessBoards[i][j];
                }
            }
        }
    }


    @SneakyThrows
    private void saveSparseArraysToFile(int [][] sparseArrays,String fileName){
        //判断目录是否存在 去创建
        File file = new File(fileName);
        if (!file.exists()) {
            file.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
//        fileOutputStream.write(sparseArrays);
        fileOutputStream.flush();
        fileOutputStream.close();
    }
}
