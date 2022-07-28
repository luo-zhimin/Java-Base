/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.exception;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 志敏.罗
 * @create 2022/6/12 18:21
 */
public class CustomException {
    public static void main(String[] args) {
        /*
         * 自定义异常
         *  当程序中出现了某些"错误"，但该错误信息并没有在Throwable子类中描述处理，这个时候可以自己设计异常类，用于描述该错误信息
         * 步骤：
         *  1）定义类∶自定义异常类名（程序员自己写）继承Exception或RuntimeException
         *  2）如果继承Exception，属于编译异常
         *  3）如果继承RuntimeException，属于运行异常（一般来说，继承RuntimeException）
         */
        int age = 80;
        if (!(age>=80 && age<=120)){
            throw new ServiceException("age need between 80 and 120");
        }
        System.out.println("normal age..");
        System.out.println(Exercise.cal(args));
    }
}

/**
 * 自定义异常
 */
class ServiceException extends RuntimeException{
    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public ServiceException(String message) {
        super(message);
    }
}
class Exercise{
    public static double cal(String[] args){
        double result = 0;
        double f;
        double s;
        try {
            f = Integer.valueOf(args[0]);
            s = Integer.valueOf(args[1]);
            result = f / s;
        } catch (ArithmeticException e) {
            //算数异常
            System.out.println("arithmetic..");
            throw new ServiceException(e.getMessage());
        }catch (ArrayIndexOutOfBoundsException exception){
            //数组越界
            System.out.println("miss params..");
            throw new ServiceException(exception.getMessage());
        }catch (NumberFormatException exception){
            //转化异常
            System.out.println("transform exception");
            throw new ServiceException(exception.getMessage());
        }
        return result;
    }

    public static void main(String[] args) {
        CustomException.main(new String[]{"1","1"});
    }
}