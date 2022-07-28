/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.file;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * 初识文件流  文件-IO流原理/流的分类->节点流/处理流->输入流->输出流->properties
 * @Author : 志敏.罗
 * @create 2022/7/5 22:51
 */
@SuppressWarnings({"all"})
public class BaseFile {

    /**
     * 文件：文件就是保存数据的地方
     * 文件流
     *  常用的文件操作
     *      createNewFile 创建新文件
     *      new File（String pathname）//根据路径构建一个File对象
     *      new File（File parent，String child）//根据父目录文件(前缀)+子路径构建(创建的名字)
     *      new File（String parent，String child）//根据父目录+子路径构建
     *  常用的方法(获取文件的相关信息)
     *  getName、getAbsolutePath、getParent、length、exists、isFile isDirectory
     *  目录的操作和文件删除
     *      mkdir创建一级目录、mkdirs创建多级目录、delete删除空目录或文件
     *      mkdir() mkdirs() delete()
     */
    public static void main(String[] args) {
        // /Users/luozhimin/Desktop/File/daily
        //方式一
//        String filePath = "/Users/luozhimin/Desktop/File/daily";
//        File file = new File(filePath);
        //方式二
//        File file =  new File(filePath,"file2.java");
        File file =  new File("/Users/luozhimin/Desktop/File/daily","file1.text");
        try {
            file.createNewFile();
            System.out.println("创建成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * getName、getAbsolutePath、getParent、length、exists、isFile isDirectory
     */
    @Test
    void fileInfo(){
        File file =  new File("/Users/luozhimin/Desktop/File/daily","file.java");
        System.out.println("fileName "+file.getName());
        System.out.println("fileParent "+file.getParent());
        System.out.println("fileLength(byte) "+file.length());
        System.out.println("file 绝对路径  "+file.getAbsolutePath());
        System.out.println("file 是否存在  "+file.exists());
        System.out.println("file 是否是一个文件  "+file.isFile());
        System.out.println("file 是否是一个目录  "+file.isDirectory());
        System.out.println("file lastModified "+file.lastModified());
    }

    /**
     * file mkdir() mkdirs() delete()
     */
    @Test
    void creatDir(){
        ///Users/luozhimin/Desktop/File/daily
        File file =  new File("/Users/luozhimin/Desktop/File/daily/fileCreate");
        if (file.exists()){//文件 是否存在
            boolean delete = file.delete();//删除 目录 或者 文件 file
            System.out.println("delete file "+delete);
        }else {
            //mkdir
//            file.mkdir();//创建目录 (一级)
            file.mkdirs();//创建多级目录
            System.out.println("flie isNotExists");
        }
    }

    @Test
    void date(){
        Date date = new Date(1657460481044l);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime.getDayOfMonth());
        System.out.println(simpleDateFormat.format(date));
    }
}
