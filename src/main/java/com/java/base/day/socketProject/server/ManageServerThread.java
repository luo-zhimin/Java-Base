/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.socketProject.server;

import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * 消息 服务端管理
 *
 * @Author : 志敏.罗
 * @create 2022/7/18 22:20
 */
public class ManageServerThread {

    private static HashMap<String, ServerContentClientThread> serverHashMap = new HashMap<>();

    /**
     * 存储
     */
    public static void addThread(String userId, ServerContentClientThread serverContentClientThread) {
        serverHashMap.put(userId, serverContentClientThread);
    }

    public static ServerContentClientThread getServerThread(String userId){
        return serverHashMap.get(userId);
    }

    /**
     * 获取key
     */
    public static String getOnlineUsers(){
        StringBuffer stringBuffer = new StringBuffer();
        Set<String> keys = serverHashMap.keySet();
        keys.forEach(key->{stringBuffer.append(key).append(" ");});
        return new String(stringBuffer);
    }

    /**
     * 移除离线线程
     * @param userId 对应的用户客户端
     */
    public static void removeServerThread(String userId){
        serverHashMap.remove(userId);
    }

    public static Set<String> getKeysExceptOwn(String userId){
        return serverHashMap.keySet().stream().filter(u -> !u.equals(userId)).collect(Collectors.toSet());
    }

    public static Set<String> getKeys(){
        return serverHashMap.keySet();
    }
}
