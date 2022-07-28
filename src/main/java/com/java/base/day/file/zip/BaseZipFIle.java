/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.file.zip;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 志敏.罗
 * @create 2022/7/15 22:07
 */
@SuppressWarnings({"all"})
public class BaseZipFIle {

    private String path = "/Users/luozhimin/Desktop/File/daily";

    private static final int DEFAULT_SIZE = 1024 * 1024;

    @Test
    @SneakyThrows
    void learnZip() {
//        ZipFile zipFile = new ZipFile(path+"/java.zip");
//        System.out.println(zipFile.getName());

        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(path + "/java.zip"));
        //创建压缩文件 new zipEntry(new fileName)
        ZipEntry zipEntry = new ZipEntry("zip.txt");
        //创建压缩文件的内容
        zipOutputStream.putNextEntry(zipEntry);

        FileInputStream fileInputStream = new FileInputStream(path + "/file.java");
        byte[] bytes = new byte[1024];
        int read;
        while ((read = fileInputStream.read(bytes)) != -1) {
            System.out.println(new String(bytes, 0, read));
            //写入压缩文件的 文件中
            zipOutputStream.write(bytes, 0, read);
        }
        zipOutputStream.closeEntry();
        fileInputStream.close();
        zipOutputStream.close();
    }

    @Test
    @SneakyThrows
    void zip() {
        String target = new String(path + "/api.zip");
        List<File> files = getFileList(path + "/javaAPI文档");
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(target));
        compress(files, zipOutputStream);
        zipOutputStream.close();
        System.out.println("压缩成功");
        decompression(path + "/api.zip","utf-8",path);
    }

    private List<File> getFileList(String fileName) {
        List<File> files = new ArrayList<>();
        if (StringUtils.isNotBlank(fileName)) {
            File file = new File(fileName);
            if (file.exists()) {
                String[] fileArray = file.list();
                if (fileArray != null && fileArray.length > 0) {
                    for (String ff : fileArray) {
                        files.add(new File(fileName + "/" + ff));
                    }
                }
            }
        }
        System.out.println(files);
        return files;
    }

    /**
     * 递归压缩
     *
     * @param files 来源文件
     * @param zos   zip输出流
     * @throws Exception 异常
     */
    private static void compress(List<File> files, ZipOutputStream zos) throws Exception {
        byte[] buf = new byte[DEFAULT_SIZE];
        for (File sourceFile : files) {
            if (sourceFile.isFile()) {
                zos.putNextEntry(new ZipEntry(sourceFile.getName()));
                int len;
                FileInputStream in = new FileInputStream(sourceFile);
                while ((len = in.read(buf)) != -1) {
                    zos.write(buf, 0, len);
                }
                // Complete the entry
                zos.closeEntry();
                in.close();
            } else {
                File[] listFiles = sourceFile.listFiles();
                if (listFiles == null || listFiles.length == 0) {
                    zos.putNextEntry(new ZipEntry(sourceFile.getName() + "/"));
                    zos.closeEntry();

                } else {
                    for (File file : listFiles) {
                        compress(Arrays.asList(file), zos);
                    }
                }
            }
        }
    }

    /**
     * @param zipPath    zip路径
     * @param charset    编码
     * @param outPutPath 输出路径
     * @Description (解压)
     */
    @SneakyThrows
    public static void decompression(String zipPath, String charset, String outPutPath) {
        long startTime = System.currentTimeMillis();
        byte[] buf = new byte[DEFAULT_SIZE];
        ZipInputStream Zin = new ZipInputStream(new FileInputStream(zipPath), Charset.forName(charset));//输入源zip路径
        BufferedInputStream Bin = new BufferedInputStream(Zin);
        String Parent = outPutPath; //输出路径（文件夹目录）
        File Fout = null;
        ZipEntry entry;
        while ((entry = Zin.getNextEntry()) != null && !entry.isDirectory()) {
            Fout = new File(Parent, entry.getName());
            if (!Fout.exists()) {
                (new File(Fout.getParent())).mkdirs();
            }
            FileOutputStream out = new FileOutputStream(Fout);
            BufferedOutputStream Bout = new BufferedOutputStream(out);
            int b;
            while ((b = Bin.read(buf)) != -1) {
                Bout.write(buf,0,b);
            }
            Bout.close();
            out.close();
            System.out.println(Fout + "解压成功");
        }
        Bin.close();
        Zin.close();
        long endTime = System.currentTimeMillis();
        System.out.println("耗费时间： " + (endTime - startTime) + " ms");
    }

}
