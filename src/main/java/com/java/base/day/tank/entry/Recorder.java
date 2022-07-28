/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.tank.entry;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * 记录相关信息
 *
 * @Author : 志敏.罗
 * @create 2022/7/12 21:44
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class Recorder extends BaseEntry{

    //击毁敌人的数量
    private static int enemyNumber = 0;

    //定义IO对象  节点流
//    private static FileWriter fileWriter = null;

    //处理流
    private static OutputStreamWriter outputStreamWriter = null;

    private static BufferedReader bufferedReader = null;

    //记录处理文件的路径
    private static String targetFile = "/Users/luozhimin/Desktop/Java/Java-Base/src/main/resources/target.txt";

    //定义tank属性
    static Vector<EnemyTank> enemyTanks = new Vector<>();

    public static int getEnemyNumber() {
        return enemyNumber;
    }

    public static void setEnemyNumber(int enemyNumber) {
        Recorder.enemyNumber = enemyNumber;
    }

    public static Vector<EnemyTank> getEnemyTanks() {
        return enemyTanks;
    }

    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }

    //当game break save 数量+敌人tank的方向+坐标
    public static void keepRecord() {
        try {
            outputStreamWriter = new OutputStreamWriter(Files.newOutputStream(Paths.get(targetFile)), StandardCharsets.UTF_8);
            System.out.println("keepRecord " + getEnemyNumber());
            outputStreamWriter.write(getEnemyNumber()+"\r\n");
            //遍历敌人tank的vector
            for (int i = 0; i < getEnemyTanks().size(); i++) {
                EnemyTank enemyTank = getEnemyTanks().get(i);
                if (enemyTank.isLive()){
//                    System.out.println(enemyTank);
                    //保存信息
                    String data = enemyTank.getX() + "/" + enemyTank.getY() +"/" + enemyTank.getDirection();
                    outputStreamWriter.write(data+"\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStreamWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 继续游戏 需要读取去还原配置
     * @throws IOException IO
     */
    public static void readRecord() throws IOException{
        bufferedReader = new BufferedReader(new FileReader(targetFile));
        Vector<EnemyTank> tanks = new Vector<>();
        String line;
        int count = 0 ;
        while ((line= bufferedReader.readLine())!=null){
            ++count;
            if (count==1) {
                //赋值
                setEnemyNumber(Integer.parseInt(line));
            }else {
                //敌人的tank的坐标 还原
                System.out.println(line);
                String[] lines = line.split("/");
                tanks.add(new EnemyTank(Integer.parseInt(lines[0]),Integer.parseInt(lines[1]),lines[2]));
                setEnemyTanks(tanks);
            }
        }
        bufferedReader.close();
    }

    //当tank被击毁的时候
    public static void add() {
        Recorder.enemyNumber++;
    }

    public static File getFile(){
        return new File(targetFile);
    }
}
