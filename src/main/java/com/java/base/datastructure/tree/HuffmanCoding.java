/*
 * Copyright (c) luoZhiMin 2022.10.9.6.45.11
 */

package com.java.base.datastructure.tree;

import org.junit.jupiter.api.Test;

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
     */

    /**
     * <img src="https://cdn.nlark.com/yuque/0/2022/png/27601787/1665321462783-09e3f2a5-47e6-49ee-aec2-94ab88f11aa9.png">
     */
    @Test
    void coding(){

        String content = "i like java , do you like a java";
        byte[] contentBytes = content.getBytes();
//        System.out.println("待处理前 = >"+Arrays.toString(contentBytes));
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
//        System.out.println(stringBuilder+" "+stringBuilder.length());
        //将字符串转换成 byte[] 101010001011111....
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
