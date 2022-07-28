package com.java.base.day.knowData;

public class StringToBasicDetail {

    public static void main(String[] args) {
        /*
         *  基本类型转String 相互 转换
         *  基本转换String +""....
         *  使用 基本数据类型对应的包装类，的相应方法，得到基本数据类型
         */
        int a = 11;
        System.out.println(a + "");

        String s5 = "12";
        byte b = Byte.parseByte(s5);
        int i = Integer.parseInt(s5);
        short s = Short.parseShort(s5);
        long l = Long.parseLong(s5);
        double d = Double.parseDouble(s5);
        float f = Float.parseFloat(s5);
        boolean aBoolean = Boolean.parseBoolean(s5);
        System.out.println(b+"/"+i+"/"+s+"/"+l+"/"+d+"/"+f+"/"+aBoolean);
        System.out.println(1+"\r"+"2");
    }
}
