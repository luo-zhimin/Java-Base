package com.java.base.day.binaryAndOperator;

public class LogicOperatorDetail {

    public static void main(String[] args) {
        /*
         * 短路与 && 短路或 || 取反 !
         * 逻辑与 & 逻辑或 | ^ 逻辑异或
         * 对于&逻辑与而言，如果第一个条件为 false ,后面的条件仍然会判断 效率低
         * | 逻辑或：不管第一个条件是否为 true，第二个条件都要判断，效率低
         *
         * a&b : & 叫逻辑与：规则：当 a 和 b 同时为 true ,则结果为 true, 否则为 false
         * a&&b : && 叫短路与：规则：当 a 和 b 同时为 true ,则结果为 true,否则为 false
         * a|b : | 叫逻辑或，规则：当 a 和 b ，有一个为 true ,则结果为 true,否则为 false
         * a||b : || 叫短路或，规则：当 a 和 b ，有一个为 true ,则结果为 true,否则为 false
         * !a : 叫取反，或者非运算。当 a 为 true, 则结果为 false, 当 a 为 false 是，结果为 true
         * a^b: 叫逻辑异或，当 a 和 b 不同时，则结果为 true, 否则为 false
         *
         */

        //&& 并且 & 逻辑与
        byte age = 99;
        if (age <100 && age>90) System.out.println(age);
        if (age <100 & age>90) System.out.println((char) (age+1));

        //对于&&短路与而言，如果第一个条件为 false ,后面的条件不再判断
        //对于&逻辑与而言，如果第一个条件为 false ,后面的条件仍然会判断
        // ||短路或：如果第一个条件为 true 则第二个条件不会判断，最终结果为 true，效率高
        // | 逻辑或：不管第一个条件是否为 true，第二个条件都要判断，效率低

        //^ 异与 true true => false / true false => ture
        System.out.println(1^2);
    }
}
