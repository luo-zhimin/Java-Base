/*
 * Copyright (c) luoZhiMin 2022.9.21.10.56.33
 */

package com.java.base.datastructure.linearstructure.stack;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Created by IntelliJ IDEA.
 * 栈 实现 综合计算器
 * @Author : 镜像
 * @create 2022/9/21 22:56 <br><br>
 *
 */
public class Calculator {

    /*
        表达式
            前缀 -- 波兰表达式 没有括号的算术表达式(右到左扫描)
                [- * + 3 1 4 2]

            中缀 -- 通用的算术或者逻辑公式 [3+1*4-2]

            后缀 -- 逆波兰表达式 严格从左到右(不包含括号)

        逆波兰计算器

     */

    /**
     * 中缀<br><br>
     * <img src="https://cdn.nlark.com/yuque/0/2022/png/27601787/1663817444394-8c0d548a-d28f-4baa-b101-cb6005061145.png">
     */
    private void infixExpression(String expression) {
        //todo 去判断是否当前值是否有括号包裹 从左括号找到右 依次计算
        //完成表达式运算
//        String expression = "3+2*6-2";
        //maxSize 可以使用正则计算 也可以使用表达式的 length/2 在模拟里面 拓展grow()可以 进行 自动拓展
        //数栈 存放数字
        ArrayStack numberStack = new ArrayStack(10);
        //符号栈 存放运算符号
        ArrayStack operationStack = new ArrayStack(10);
        //扫描的索引
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int operation = 0;
        int result = 0;
        //将每次扫描的char保存到ch里
        char ch = ' ';
        //定义变量进行拼接 多位数
        String keepNumber = "";
        //循环扫描
        while (index!=expression.length()) {
            //依次扫描得到 expression 里面的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            //判断ch是什么 进行对应处理 数 or 符号
            if (operationStack.isOperation(ch)) {
                //如果是运算符
                if (!operationStack.isEmpty()) {
                    //不为空需要判断 栈里面和当前符号的优先级  如果符号栈有操作符，就进行比较,如果当前的操作符的优先级小于或者等于栈中的操作符, 就需要从数栈中 pop 出两个数
                    //在从符号栈中 pop 出一个符号，进行运算，将得到结果，入数栈，然后将当前的操作符入符 号栈
                    if (operationStack.priority(ch) <= operationStack.priority(operationStack.peek())) {
                        //todo 例如 当前符号是 + stack里面是 *
                        num1 = numberStack.pop();
                        num2 = numberStack.pop();
                        operation = operationStack.pop();
                        result = numberStack.calculation(num1, num2, operation);
                        //把运算结果入数栈
                        numberStack.push(result);
                        //当前符号入符号栈
                        operationStack.push(ch);
                    } else {
                        //符号栈里面最后的符号是同一优先级的 直接进行运算
                        operationStack.push(ch);
                    }
                } else {
                    //为空直接入栈
                    operationStack.push(ch);
                }
            }else {
                //数栈 --> char 需要转换 int
//                numberStack.push(ch-'0');
                //当处理多位数时候 不可以发现就入栈 处理数时 需要再往后看一位是否是符号
                keepNumber += ch;
                //是否是最后一位
                if (index==expression.length()-1){
                    numberStack.push(Integer.parseInt(keepNumber));
                }else if (numberStack.isOperation(expression.substring(index+1,index+2).charAt(0))){
                    numberStack.push(Integer.parseInt(keepNumber));
                    //清空keepNumber
                    keepNumber = "";
                }
            }
            //让index+1 判断 是否 扫描到 expression 最后
            index ++;
        }

        //输出数栈
        numberStack.showStack();
        System.out.println("数栈结束～");
        operationStack.showStack();
        System.out.println("符号栈结束～");

        //扫描完毕 需要清栈 运算 符号栈里同一级别
        while (!operationStack.isEmpty()){
            //符号栈为空 数栈里面是result
            num1 = numberStack.pop();
            num2 = numberStack.pop();
            operation = operationStack.pop();
            if (operationStack.getTop()<=expression.length()-1 && operationStack.getTop()!=-1){
                if (operationStack.peek()=='-') {
                    //如果符号栈中的下一个符号是 - 则数栈第一个数 加上符号进行运算
                    num1 = -num1;
                }
            }
            result = numberStack.calculation(num1, num2, operation);
            //把运算结果入数栈
            System.out.println("while result = "+result);
            numberStack.push(result);
        }

        System.out.printf("表达式[%s] 计算结束 result[%d]\n",expression,numberStack.pop());
    }

    @Test
    void calculation(){
        //3+2*6-2  300+2*6-2 7*2*2-5+1-5+3-4
        // ==> 7+2*6-4/2+8*3-1(计算有问题) -9 =>(后面的数值大)
        // 7 + 12 - 2 + 24 -1 如果式 - 就取前一个数字进行计算 算作一个整体 3-2*6+2
        infixExpression("3-2*6+2");
    }

    /**
     * 后缀表达式 - 逆波兰表达式<br><br>
     * <img src="https://cdn.nlark.com/yuque/0/2022/png/27601787/1663901394345-16d1d16e-7936-4795-95d2-d4b94969ccc3.png">
     */
    public void postFixExpression(String suffixExpression){
        //输入一个逆波兰表达式(后缀表达式)，使用栈(Stack), 计算其结果
        //支持小括号和多位数整数 只支持对整数的计算
        //定义一个逆波兰表达式
        //(3+4)*5-6  3 4 + 5 * 6 -
//        String suffixExpression = "3 4 + 5 * 6 - ";
        //先将 表达式 装入 ArrayList里面
        //ArrayList => stack
        List<String> expressions = getExpressions(suffixExpression);
        System.out.printf("expressions {%s}",expressions);
        int result = calculation(expressions);
        System.out.printf("表达式[%s],最终结果计算结果是[%s]\n",suffixExpression ,result);
    }

    /**
     * 将逆波兰表达式 放入 arrayList
     */
    public List<String> getExpressions(String suffixExpression){
        String[] suffixes = suffixExpression.split(" ");
        return new ArrayList<>(Arrays.asList(suffixes));
    }

    /**
     * 逆波兰表达式的计算
     */
    public int calculation(List<String> expressions){
        //创建一个栈 只需要一个栈
        Stack<String> stack = new Stack<>();
        //遍历list
        expressions.forEach(expression->{
            //正则表达式 取出数字
            if (expression.matches("\\d+")){
                //多位数匹配 入栈
                stack.push(expression);
            }else {
                //符号 pop出俩个数字 运算 并且 在入栈 次顶 栈顶
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int result = ArrayStack.calculation(num1,num2,expression.charAt(0));
                //结果入栈
                System.out.printf("逆波兰表达式计算[%s]\n",result);
                stack.push(result+"");
            }
        });
        return Integer.parseInt(stack.pop());
    }


    @Test
    void postFixExpressionTest(){
        //(3+4)*5-6
        postFixExpression("3 4 + 5 * 6 - ");//29
        //(30+4)*5-6
        postFixExpression("30 4 + 5 * 6 - ");//164
        //4*5-8+60+8/2 => 76  4 5 * 8 - 60 + 8 2 / +
        postFixExpression("4 5 * 8 - 60 + 8 2 / + ");
    }


}
class ArrayStack extends ArraySimulationStack{

    public ArrayStack(int maxSize) {
        super(maxSize);
    }

    /**
     * 返回运算符的优先级 优先级使用数字表示
     * @param operation 操作符号
     */
    public int priority(int operation) {
        int value;
        switch (operation) {
            case '*':
            case '/':
                value = 2;
                break;
            case '+':
            case '-':
                value = 1;
                break;
            default:
                value = -1;
        }
        return value;
    }

    /**
     * @param value 值
     * @return 判断值是否是操作符号
     */
    public Boolean isOperation(int value){
        return value == '+' || value == '-' || value == '*' || value == '/';
    }

    /**
     * 计算值
     * @param num1 值1
     * @param num2 值2
     * @param operation 运算符
     * @return 结果
     */
    public static int calculation(int num1,int num2,int operation){
        int result = 0;
        switch (operation){
            case '+':
                result = num1+num2;
                break;
            case '-':
                result = num2-num1;
                break;
            case '*':
                result = num1*num2;
                break;
            case '/':
                result = num2/num1;
                break;
            default:
                break;
        }
        return result;
    }

    /**
     * 增加返回当前栈顶的值 但不是真的pop
     */
    public int peek(){
        return getStack()[getTop()];
    }
}
