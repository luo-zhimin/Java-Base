/*
 * Copyright (c) luoZhiMin 2022.10.16.10.18.40
 */

package com.java.base.algorithm;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * 贪心算法
 * @Author : 镜像
 * @create 2022/10/16 22:18
 */
public class GreedyAlgorithm {

    /*
        贪婪算法所得到的结果不一定是最优的结果(有时候会是最优解)，但是都是相对近似(接近)最优解的结果
     */

    @Test
    void greedy(){
        //创建广播电台
        HashMap<String, HashSet<String>> boardCasts =  initBoardCats();
        //准备allAreas
        HashSet<String> allAreas = getAllAreas(boardCasts);

        System.out.println("allAreas - > "+allAreas);

        //创建ArrayList 存放选择的电台集合 每次最大的放入(剩余)
        List<String> selects = new ArrayList<>();
        //在遍历过程中需要临时重放电台覆盖的地区  和 没有覆盖地区的交集
        HashSet<String> tempSet = new HashSet<>();
        //存储当前最大key的交集
        HashSet<String> tempSet2 = new HashSet<>();
        //保存再一次循环中保留最多地区的电台key
        String maxKey = null;

        while (allAreas.size()!=0){
            //每进行一次 循环 需要覆盖
            maxKey = null;

            for (Map.Entry<String, HashSet<String>> entry : boardCasts.entrySet()) {
                String key = entry.getKey();
                //需要清空 不然下次会有值
                tempSet.clear();

                if (maxKey!=null){
                    tempSet2.clear();
                    tempSet2.addAll(boardCasts.get(maxKey));
                    tempSet2.retainAll(allAreas);
                }

                tempSet.addAll(entry.getValue());
                //取交集 -> 交集覆盖给tempSet
                tempSet.retainAll(allAreas);

                if (tempSet.size() > 0 &&
                        //tempSet.size() > tempSet2.size() 贪婪 每次选最好的 应该用剔除之后的比较
                        //boardCasts.get(maxKey).size() 应该是交集的size
                        (maxKey == null || tempSet.size() > tempSet2.size())) {
                    maxKey = key;
                }
            }

            if (maxKey!=null){
                selects.add(maxKey);
                //将maxKey 指向电台的覆盖地区 去掉
                allAreas.removeAll(boardCasts.get(maxKey));
            }
        }

        System.out.println("selects => "+selects);
    }


    private HashMap<String, HashSet<String>> initBoardCats(){

        HashMap<String, HashSet<String>> boardCasts = new HashMap<>();

        HashSet<String> areaSet1 = new HashSet<>();
        areaSet1.add("北京");
        areaSet1.add("上海");
        areaSet1.add("天津");

        HashSet<String> areaSet2 = new HashSet<>();
        areaSet2.add("广州");
        areaSet2.add("北京");
        areaSet2.add("深圳");

        HashSet<String> areaSet3 = new HashSet<>();
        areaSet3.add("成都");
        areaSet3.add("上海");
        areaSet3.add("杭州");

        HashSet<String> areaSet4 = new HashSet<>();
        areaSet4.add("上海");
        areaSet4.add("天津");
//        areaSet4.add("大连");

        HashSet<String> areaSet5 = new HashSet<>();
        areaSet5.add("杭州");
        areaSet5.add("大连");
        areaSet5.add("西藏");

        //加入集合
        boardCasts.put("K1", areaSet1);
        boardCasts.put("K2", areaSet2);
        boardCasts.put("K3", areaSet3);
        boardCasts.put("K4", areaSet4);
        boardCasts.put("K5", areaSet5);

        return boardCasts;
    }

    private HashSet<String> getAllAreas(Map<String, HashSet<String>> boardCasts){
        HashSet<String> allAreas = new HashSet<>();
        boardCasts.forEach((k,v)->{
            allAreas.addAll(v);
        });
        return allAreas;
    }

}
