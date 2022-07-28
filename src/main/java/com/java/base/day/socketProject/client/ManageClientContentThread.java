/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.socketProject.client;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * 管理客户端 链接到服务器的 线程
 * @Author : 志敏.罗
 * @create 2022/7/18 22:08
 */

public class ManageClientContentThread {
    //id/thread
    private static HashMap<String,ClientContentServerThread> threadClientHashMap = new HashMap<>();

    /**
     * 将线程加入到集合
     */
    public static void addClientServerThread(String userId,ClientContentServerThread clientContentServerThread){
        threadClientHashMap.put(userId,clientContentServerThread);
    }

    /**
     * 取出对应的线程
     */
    public static ClientContentServerThread getClientThread(String userId){
        return threadClientHashMap.get(userId);
    }

    public static Set<String> getKeys(){
        return threadClientHashMap.keySet();
    }
}
