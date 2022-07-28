/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.socketProject.entry;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * 消息体
 *
 * @Author : 志敏.罗
 * @create 2022/7/17 22:10
 */
@Data
@ToString
public class Message implements Serializable {

    private static final long serialVersionUID = 3561685465897444075L;

    /**
     * 发送人
     */
    private String sender;

    /**
     * 接收人
     */
    private String receiver;

    /**
     * 内容
     */
    private String content;

    /**
     * 发送时间
     */
    private String sendTime;

    /**
     * 消息类型
     */
    private String type;

    //------------------------------------------扩展和文件相关的-----------------------------------

    private byte[] fileBytes;

    private Integer fileLength = 0;

    private String sourcePath;

    private String targetPath;
}
