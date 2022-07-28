package com.java.base.day.commonClass;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 志敏.罗
 * @create 2022/6/15 22:05
 */
public class MathDetail {

    public static void main(String[] args) {
        /*
         * math
         *  包含用于执行基本数学运算的方法，如初等指数、对数、平方根和三角函数
         * abs 绝对值
         * pow 求幂
         * ceil 向上取整,返回>=该参数的最小整数(转成double)
         * floor 向下取整，返回<=该参数的最大整数(转成double)
         * round 四舍五入Math.floor(该参数+0.5)
         * sqrt 求开方 2
         * random 求随机数  a<=x<=b 公式 (int) (a+Math.random()*(b-a+1))
         * max(x,y)
         * min(x,y)
         */
        //绝对值
        int abs = Math.abs(-1);
        System.out.println(abs);
        //pow 求幂
        double pow = Math.pow(2, 3);
        System.out.println(pow);
        //sqrt
        double sqrt = Math.sqrt(4);
        System.out.println(sqrt);
        //ceil 向上取整
        double ceil = Math.ceil(12.56);
        System.out.println(ceil);
        //floor 向下取整
        double floor = Math.floor(12.56);
        System.out.println(floor);
        //四舍五入
        long round = Math.round(12.56);
        System.out.println(round);
        System.out.println(Math.max(1, 3));
        System.out.println(Math.min(1, 3));
        System.out.println("==");
        for (int i = 0; i <10; i++) {
            System.out.println(getBetweenRandomNumber(1,3));
        }
    }

    /**
     * a<=x<=b 公式 (int) (a+Math.random()*(b-a+1))
     * @param a first
     * @param b second
     * @return a<=x<=b
     */
    private static int getBetweenRandomNumber(int a,int b){
        return  (int) (a+Math.random()*(b-a+1));
    }
}
