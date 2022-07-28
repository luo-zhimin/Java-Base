package com.java.base.day.commonClass.big;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 志敏.罗
 * @create 2022/6/16 17:39
 */
public class BigIntegerAndBinDecimalDetail {
    public static void main(String[] args) {
        /*
         * 1）BigInteger 适合保存比较大的整型
         * 2）BigDecimal 适合保存精度更高的浮点型（小数）
         *     BigDecimal.ROUND_CEILING 保留分子的精度
         *
         * 常用方法：
         *   1）add 加
         *   2）subtract 减
         *   3）multiply 乘
         *   4) divide 除
         */

//        long l = 222222222222222222222222L;
//        System.out.println(l);
        System.out.println("bigInteger....");
        BigInteger bigInteger = new BigInteger("222222222222222222222222");
        BigInteger bigInteger2 = new BigInteger("1121");
        //加
        System.out.println(bigInteger.add(bigInteger2));
        //减
        System.out.println(bigInteger.subtract(bigInteger2));
        //乘
        System.out.println(bigInteger.multiply(bigInteger2));
        //除
        System.out.println("divide="+bigInteger.divide(bigInteger2));
        System.out.println("bigDecimal...");
        BigDecimal bigDecimal = new BigDecimal("12222.111111111111111111111111343442211");
        BigDecimal bigDecimal2 = new BigDecimal("1.111");
        System.out.println(bigDecimal.add(bigDecimal2));
        System.out.println(bigDecimal.subtract(bigDecimal2));
        System.out.println(bigDecimal.multiply(bigDecimal2));
        //BigDecimal.ROUND_CEILING 保留分子的精度
        System.out.println(bigDecimal.divide(bigDecimal2, RoundingMode.CEILING));//可能出现异常 算术异常 需要在调用divide时 指定精度
    }
}
