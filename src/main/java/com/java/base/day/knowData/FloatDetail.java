package com.java.base.day.knowData;

public class FloatDetail {

    public static void main(String[] args) {
        /*
         * 转义符号
         * \t tab
         * \n 换行
         * \r 替换 replace
         *
         * 浮点数
         * 关于浮点数在机器中存放形式的简单说明,浮点数=符号位+指数位+尾数位
         * 尾数部分可能丢失，造成精度损失(小数都是近似值)。
         *
         * float 4byte 单精度
         * double 8byte 双精度 更精准
         *
         * Java 的浮点型常量(具体值)默认为 double 型，声明 float 型常量，须后加‘f’或‘F'
         * 当我们对运算结果是小数的进行相等判断是，要小心  应该是以两个数的差值的绝对值，在某个精度范围类判断
         * 通常情况下，应该使用 double 型，因为它比 float 型更精确。
         *  在线Api https://www.matools.com
         */

        float b = 1.1f;//4
        double a = 0.000;//8

        double d = 0.0f;//隐式转换 小到大

        //十进制数形式：如：5.12 512.0f .512 (必须有小数点）
        double n1 = .123;
        System.out.println(n1);
        //科学计数法形式:如：5.12e2 [5.12 * 10 的 2 次方 ] 5.12E-2 [5.12 * 10的负二次方]
        System.out.println(5.12e2);//512.0 double
        System.out.println(5.12e-2);

        //[举例说明]double num9 = 2.1234567851;float num10 = 2.1234567851F;
        double num = 2.1234567851;
        float num1 = 2.1234567851f;
        System.out.println("double: "+num);
        System.out.println("flout: "+num1);

        //浮点数使用陷阱: 2.7 和 8.1 / 3 比较
        double num2 = 2.7;
        double num3 = 8.1/3;//近视2.7
        System.out.println(num2+"/"+num3);

        if (Math.abs(num2-num3)<0.001) System.out.println("几乎相等~");
        System.out.println("math: "+Math.abs(num2-num3));
    }
}

