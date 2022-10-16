/*
 * Copyright (c) luoZhiMin 2022.10.15.11.53.18
 */

package com.java.base.algorithm;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * KMP - 暴力匹配算法
 * @Author : 镜像
 * @create 2022/10/15 23:53
 */
public class KMPAlgorithm {

    /*
        KMP 方法算法就利用之前判断过信息，通过一个 next 数组，保存模式串中前后最长公共子序列的长度，每次 回溯时，通过 next 数组找到，前面匹配过的位置，省去了大量的计算时间
        部分匹配”的实质是，有时候，字符串头部和尾部会有重复。比如，”ABCDAB”之中有两个”AB”，那么 它的”部分匹配值”就是 2（”AB”的长度）。搜索词移动的时候，第一个”AB”向后移动 4 位（字符串长度- 部分匹配值），就可以来到第二个”AB”的位置

        https://www.cnblogs.com/ZuoAndFutureGirl/p/9028287.html 详解

        ABCDAB
            前缀为[A, AB，ABC,ABCD,ABCDA]，后缀为[AB, DAB,CDAB,BCDAB]
     */

    @Test
    void match(){
        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String str2 = "尚硅谷你尚硅你";
        System.out.println(violenceMatch(str1, str2));
    }


    /**
     * 暴力破解
     * @param str1 待匹配的值
     * @param str2 要匹配的值
     * @return 找到出现开始的索引 没有就是-1
     */
    private int violenceMatch(String str1,String str2){
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int s1Len = s1.length;
        int s2Len = s2.length;

        int i = 0;//index - > s1
        int j = 0;//j(index) - > s2

        //保证数组不越界
        while (i < s1Len && j < s2Len) {
            if (s1[i]==s2[j]){
                i++;
                j++;
            }else {
                //fill recall i j -> ++ 失败回溯
                i = i - (j-1);
                j=0;
            }
        }

        //判断是否匹配成功
        if (j==s2Len){
            return i-j;
        }
        return -1;
    }


    @Test
    void kmp(){
        //algorithm test
        String s1 = "BBC ABCDAB ABCDABCDABDE";
        String s2 = "ABCDABD";
//        String s2 = "BBC";

        int[] kmpNext = kmpNext(s2);
        System.out.println("kmpNext "+ Arrays.toString(kmpNext));

        int index = kmpSearch(s1, s2, kmpNext);
        System.out.println("search index = > "+index);
    }

    //获取到一个字符串的部分匹配值
    private int[] kmpNext(String dest){
        //保存target array
        int[] next = new int[dest.length()];
        next[0] = 0;//如果字符串长度为1 index->0 部分匹配值->0
        //j 部分匹配值大小
        for (int i = 1,j=0; i < dest.length(); i++) {
            //满足时说明 有了 部分匹配值 需要 + 1
            //dest.charAt(i)!=dest.charAt(j) 需要从next[j-1]获取新的j 直到找到新的满足条件时退出
            while (j>0 && dest.charAt(i)!=dest.charAt(j)){
                j = next[j-1];
            }
            if (dest.charAt(i)==dest.charAt(j)){
                j++;
            }
            next[i]=j;
        }
        return next;
    }

    /**
     * kmp搜索
     * @param s1 源字符串
     * @param s2 目标字符串
     * @param next 部分匹配表 - 目标字符串对应的部分匹配表
     * @return -1就是没有匹配到的 否则就是第一个出现的index
     */
    private int kmpSearch(String s1,String s2,int[] next){
        //遍历 源字符串
        for (int i = 0,j=0; i < s1.length(); i++) {

            //s1.charAt(i)!=s2.charAt(j)  核心点 调整 j 位置
            while (j>0 && s1.charAt(i)!=s2.charAt(j)){
                j = next[j-1];
            }

            if (s1.charAt(i)==s2.charAt(j)){
                j++;
            }
            if (j==s2.length()){// i < j
                return i-j+1;
            }
        }
        return -1;
    }
}
