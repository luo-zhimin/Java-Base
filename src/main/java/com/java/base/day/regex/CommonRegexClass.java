/*
 * Copyright (c) luoZhiMin 2022.8.12.10.37.40
 */

package com.java.base.day.regex;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * regex common class
 * @Author : 志敏.罗
 * @create 2022/8/12 22:37
 */
public class CommonRegexClass {

    /*
        pattern
            pattern对象是一个正则表达式对象。Pattern类没有公共构造方法。要创建一个Pattern对象，调用其公共静态方法，
            它返回一个Pattern对象。该方法接受一个正则表达式作为它的第一个参数，比如∶Pattern r=Pattern.compile（pattern）
        matcher
            Matcher 对象是对输入字符串进行解释和匹配的引擎。与Pattern类一样，Matcher也没有公共构造方法。你需要调用Pattern对象的matcher方法来获得一个Matcher对象
        patternSyntaxException
            PatternSyntaxException是一个非强制异常类，它表示一个正则表达式模式中的语法错误
     */

    /**
     * 整体匹配 Pattern.matches()
     */
    @Test
    void patternMethod(){
        String content ="hello abc sunday,i love live";
        String regex ="hello.*";
        /*
            public static boolean matches(String regex, CharSequence input) {
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(input);
                return m.matches();
            }
            真正执行的是matcher.matches()
         */
        boolean matches = Pattern.matches(regex,content);
        System.out.println("整体匹配 "+matches);
    }

    /**
     * matcher
     * start() end() matches() ...
     */
    @Test
    void matcherMethod(){
        String content = "hello java edu jack hello smith hello";
        String regex="hello";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            System.out.println(matcher.start());
            System.out.println(matcher.end());
            System.out.println("=======");
            //自己截取
            System.out.println("找到 "+content.substring(matcher.start(),matcher.end()));
        }
        System.out.println("matches "+matcher.matches());
        //替换 hello -> 你好 不影响你现有的字符串
        String newContent = matcher.replaceAll("你好");
        System.out.println(newContent);
        //替换第一次子序列
        String firstContent = matcher.replaceFirst("你好");
        System.out.println(firstContent);
    }
}
