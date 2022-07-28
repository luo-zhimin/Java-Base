/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.file.read;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.io.FileReader;

/**
 * Created by IntelliJ IDEA.
 * 字符(输入)流
 *
 * @Author : 志敏.罗
 * @create 2022/7/7 22:31
 */
@SuppressWarnings({"all"})
public class BaseFileRead {

    private String path = "/Users/luozhimin/Desktop/File/daily";

    /**
     * FileReader相关方法
     * 1) new FileReader(File/String)
     * 2）read∶每次读取单个字符，返回该字符，如果到文件末尾返回-1
     * 3）read（char[]）∶批量读取多个字符到数组，返回读取到的字符数，如果到文件末尾返回-1
     * 相关API∶
     * 1）new String（char[]）∶将char[]转换成String
     * 2）new String（char[]，off，len）∶将char[]的指定部分转换成String
     */
    @SneakyThrows
    @Test
    void fileReadTest() {
        FileReader fileReader = new FileReader(path + "/file.java");
        int read;
        //char 数组读取
        char[] chars = new char[8];
//        char data =' ';
        //read 单个字符读取
        while ((read = fileReader.read(chars)) != -1) {
//            System.out.print((char) read);
            System.out.print(new String(chars,0,read));
        }
        //finally close
        fileReader.close();
    }

//    @Test
//    @SneakyThrows
//    void readExcel(){
//        File file = new File(path+"/website.xls");
//        InputStream inputStream = new FileInputStream(file);
//        Workbook workbook = new XSSFWorkbook(inputStream);
//        Sheet sheet = workbook.getSheet("Sheet1");
//        int rowNum = sheet.getLastRowNum();
//        for (int i = 0; i < rowNum; i++) {
//            Row row = sheet.getRow(i);//行
//            int lastCellNum = row.getLastCellNum();//列
//            for (int j = 0; j < lastCellNum; j++) {
//                Cell rowCell = row.getCell(j); //获取第i,j单元格
//                if (rowCell!=null){
//                    System.out.println(rowCell);
//                }
//            }
//        }
//    }
}
