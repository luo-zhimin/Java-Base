/*
 * Copyright (c) luoZhiMin 2022.10.9.6.45.11
 */

package com.java.base.datastructure.tree;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * huffman 编码
 * @Author : 镜像
 * @create 2022/10/9 18:45
 */
public class HuffmanCoding {

    /*
        前缀编码
        赫夫曼编码是无损处理方案
        赫夫曼树根据排序方法不同，也可能不太一样，这样对应的赫夫曼编码也不完全一样，但是 wpl 是 一样的，都是最小的, 最后生成的赫夫曼编码的长度是一样

        如果文件本身就是经过压缩处理的，那么使用赫夫曼编码再压缩效率不会有明显变化, 比如视频,ppt 等等文件 [举例压一个 .ppt]
        赫夫曼编码是按字节来处理的，因此可以处理所有的文件(二进制文件、文本文件) [举例压一个.xml 文件]
        如果一个文件中的内容，重复的数据不多，压缩效果也不会很明显.
     */

    /**
     * <img src="https://cdn.nlark.com/yuque/0/2022/png/27601787/1665321462783-09e3f2a5-47e6-49ee-aec2-94ab88f11aa9.png">
     */
    @Test
    void coding(){

        String content = "my name is jack,nice to meet you,I am fan,Thank you";
        byte[] contentBytes = content.getBytes();
//        System.out.println("待处理前 = >"+Arrays.toString(contentBytes)+" "+contentBytes.length);
//        System.out.printf("待处理前[%d]\n",contentBytes.length);//40
//        List<TreeNode> nodes = getNodes(contentBytes);
////        System.out.println("nodes = > "+nodes);
//        TreeNode huffmanTree = createHuffmanTree(nodes);
////        beforeShow(huffmanTree);
//        Map<Byte, String> huffmanCodes = getHuffmanCodes(huffmanTree);//32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101, 121=11010, 106=0010, 107=1111, 108=000, 111=0011
////        System.out.println("codeMap = > "+huffmanCodes);
//        byte[] huffmanByte = zip(contentBytes,huffmanCodes);
//        System.out.println(Arrays.toString(huffmanByte)+" "+huffmanByte.length);

        byte[] huffmanZip = huffmanZip(contentBytes);
        System.out.println(Arrays.toString(huffmanZip)+" "+huffmanZip.length);
        byte[] sourceByte = decode(codeMap, huffmanZip);
//        System.out.println("sourceByte -> "+Arrays.toString(sourceByte)+" "+sourceByte.length);
        System.out.println(new String(sourceByte));
    }

    @Test
    void file(){
//        String src="/Users/luozhimin/Desktop/File/picture/daily/cat.jpg";
//        String target = "/Users/luozhimin/Desktop/File/picture/daily/smallCat.zip";
        String target="/Users/luozhimin/Desktop/File/picture/daily/smallCat.jpg";
        String src = "/Users/luozhimin/Desktop/File/picture/daily/smallCat.zip";
//        zipFile(src,target);
        unZip(src,target);
    }


    /**
     * 解压文件
     * @param src 待解压文件
     * @param target 输出目录
     */
    @SneakyThrows
    private void unZip(String src,String target){
        ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(Paths.get(src)));
        FileOutputStream outputStream = new FileOutputStream(target);
        byte[] huffmanBytes = (byte[]) objectInputStream.readObject();
        Map<Byte,String> huffmanMap = (Map<Byte, String>) objectInputStream.readObject();
        //解码
        byte[] decode = decode(huffmanMap, huffmanBytes);
        outputStream.write(decode);
        objectInputStream.close();
        outputStream.close();
    }

    /**
     * 压缩文件
     * @param src 源文件
     * @param target 目标文件
     */
    @SneakyThrows
    private void zipFile(String src,String target){
//        FileInputStream inputStream = new FileInputStream(src);
        FileOutputStream outputStream = new FileOutputStream(target);
        InputStream inputStream = Files.newInputStream(Paths.get(src));
        //创建一个和源文件大小一样的数组
        byte[] b = new byte[inputStream.available()];
        //读取
        inputStream.read(b);
        //获取文件对应的huffman表
        byte[] huffmanBytes = huffmanZip(b);
        //为了恢复文件使用
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(huffmanBytes);
        //写入huffman编码
        objectOutputStream.writeObject(codeMap);
//        int read;
//        while ((read=inputStream.read(b))!=-1){
//            outputStream.write(b,0,read);
//        }
        inputStream.close();
        outputStream.close();
        objectOutputStream.close();
    }


    /**
     * @param huffmanCodeMap huffman 编码表
     * @param huffmanCodes huffman 压缩后的数组
     * @return 原始byte[]
     */
    private byte[] decode(Map<Byte,String> huffmanCodeMap,byte[] huffmanCodes){
        //先得到huffmanCodes对应的字符串 101011000....
        StringBuilder stringBuilder = new StringBuilder();
        //将byte[]转化成二进制字符串
        for (int i = 0; i < huffmanCodes.length; i++) {
            //判断是否是最后一个字节
            boolean flag = (i==huffmanCodes.length-1);
            stringBuilder.append(byteToBitString(!flag, huffmanCodes[i]));
        }
//        System.out.println("decode = > "+stringBuilder +" "+stringBuilder.length());
        //转换 按照指定编码 解码 把huffman 编码表 进行交换
        Map<String,Byte> decodeMap = new HashMap<>();
        huffmanCodeMap.forEach((k,v)-> decodeMap.put(v,k));
        //System.out.println("decodeMap - > "+decodeMap);
        List<Byte> bytes = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length();) {
            int count = 1;//计数器
            boolean flag = true;
            Byte b = null;
            while (flag){
                String key;
                //取出一个字符 二进制 字符
                if (i+count>stringBuilder.length()){
                    key = stringBuilder.substring(i)+1;
                }else {
                   key = stringBuilder.substring(i, i + count);//i不动 count++ 直到匹配到字符
                }

                b=decodeMap.get(key);
                if (b==null){
                   count++;
                }else {
                    flag = false;
                }
            }
            bytes.add(b);
            //让 i 移动到count
            i+=count;
        }
        byte[] b = new byte[bytes.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = bytes.get(i);
        }
        return b;
    }

    /**
     * 数据解压 将huffman byte[] -> 重新转成huffman code 对应的二进制
     * 将byte 转化成 二进制 string
     * @param flag true 需要补高位 false 不需要 最后一个字节不需要补高位
     * @param b byte
     * @return 返回二进制补码
     */
    private String byteToBitString(boolean flag,byte b){
        int temp = b;
        if (flag){
            temp |= 256;// 按位与 进制内容
        }

        String str = Integer.toBinaryString(temp);//返回的是二进制的补码
//        System.out.println("str = >" +str);
        //正数 需要补高位
        if (flag) {
            return str.substring(str.length() - 8);
        }
        return str;
    }

    /**
     * 处理 压缩 huffman
     * @param bytes 原始数组
     * @return 压缩后的数组 [前缀数组] huffman 编码
     */
    private byte[] huffmanZip(byte[] bytes){
        //得到节点 - > 处理节点
        List<TreeNode> nodes = getNodes(bytes);
        //创建huffman tree
        TreeNode huffmanTree = createHuffmanTree(nodes);
        //生成huffman对应的编码
        Map<Byte, String> huffmanCodes = getHuffmanCodes(huffmanTree);//32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101, 121=11010, 106=0010, 107=1111, 108=000, 111=0011
        return zip(bytes, huffmanCodes);
    }

    /**
     * 将字符串对应的byte[] 通过生成的huffman编码表 返回一个压缩后的byte[]
     * @param huffmanCodes huffman编码集合
     * @param bytes 待处理的byte数组
     * @return 压缩后的 前缀编码(huffman 编码 byte[])
     * 1010 1000(补码) - > (反码) 1010 0111 -> (源码) 1101 1000
     */
    private byte[] zip(byte[] bytes,Map<Byte,String> huffmanCodes){
        //利用编码表 将原始数组 转化成huffman对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        //System.out.println("huffman -> "+stringBuilder+" "+stringBuilder.length());
        //将字符串转换成 byte[] 10101000 1011111....
        //统计返回的长度
        //todo
        int len;
        if (stringBuilder.length()%8==0){
            len = stringBuilder.length()/8;
        }else {
            len = stringBuilder.length()/8+1;
        }
        byte[] result = new byte[len];
        //8位一个bit
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i+=8) {
            String strByte;
            if (i+8>stringBuilder.length()){
                //位数不够
                strByte = stringBuilder.substring(i);
            }else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            //转换成byte[]放入到result里面
            result[index++] = (byte) Integer.parseInt(strByte,2);
        }
        return result;
    }


    //编码集合
    private static HashMap<Byte, String> codeMap = new HashMap<>();

    //存储路径
    private static StringBuilder builder = new StringBuilder();

    //重载
    private Map<Byte,String> getHuffmanCodes(TreeNode node){
        if (node==null){
            return null;
        }
        //处理root左子树
        getHuffmanCodes(node.left,"0",builder);
        getHuffmanCodes(node.right,"1",builder);
        return codeMap;
    }

    /**
     * 生成 huffman 编码表 {32->0/...}
     * @param code  规定左子树 0 右子树 1
     * @param builder 拼接路径
     * @param node 节点
     */
    private void getHuffmanCodes(TreeNode node,String code,StringBuilder builder){
        StringBuilder builder2 = new StringBuilder(builder);
        builder2.append(code);
        if (node!=null){
            //判断当前node是叶子节点还是非叶子节点
            if (node.data==null){
                //非叶子节点 递归
                getHuffmanCodes(node.left,"0",builder2);
                getHuffmanCodes(node.right,"1",builder2);
            }else {
                //叶子节点 put
                codeMap.put(node.data,builder2.toString());
            }
        }
    }

    /**
     * 生成huffman tree
     * @param nodes 处理好的节点
     * @return huffman tree root
     */
    public TreeNode createHuffmanTree(List<TreeNode> nodes){

        while (nodes.size()>1){
            Collections.sort(nodes);
            TreeNode leftNode = nodes.get(0);
            TreeNode rightNode = nodes.get(1);
            TreeNode parent = new TreeNode(null, rightNode.weight + leftNode.weight);
            parent.left= leftNode;
            parent.right = rightNode;
            nodes.removeAll(Arrays.asList(leftNode,rightNode));
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    /**
     * @param bytes 源字节数组
     * @return 处理好的 nodes
     */
    private List<TreeNode> getNodes(byte[] bytes){
        List<TreeNode> nodes = new ArrayList<>();

        //存储每个byte出现的次数 权重
        Map<Byte,Integer> nodeMap = new HashMap<>();
        for (byte b : bytes) {
            //没有就是 从 1开始 有就++
            nodeMap.merge(b, 1, Integer::sum);
        }

        //将每一个键子对 放到node中
        nodeMap.forEach((k,v)->{
            nodes.add(new TreeNode(k,v));
        });
        return nodes;
    }


    public void beforeShow(TreeNode node){
        if (node==null){
            System.out.println("tree is empty");
            return;
        }
        node.beforeShow();
    }
}
class TreeNode implements Comparable<TreeNode>{
    //存放数据本身 'a'...
    Byte data;
    //权值
    int weight;
    TreeNode left;
    TreeNode right;

    public TreeNode(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(TreeNode o) {
        return this.weight-o.weight;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    public void beforeShow(){
        System.out.println(this);
        if (this.left!=null){
            this.left.beforeShow();
        }
        if (this.right!=null){
            this.right.beforeShow();
        }
    }
}
