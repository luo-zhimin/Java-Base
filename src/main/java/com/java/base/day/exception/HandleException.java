/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.exception;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 志敏.罗
 * @create 2022/6/12 15:55
 */
public class HandleException {

    public static void main(String[] args) {
        /*
         * 异常处理
         *   1.try-catch-finally
         *       程序员在代码中捕获发生的异常，自行处理
         *   2）Java提供try和catch块来处理异常。try块用于包含可能出错的代码。catch块用于处理try块中发生的异常。可以根据需要在程序中有多个try…catch块。
         *   3）基本语法
         *   try {
         *      //可疑代码
         *      //将异常生成对应的异常对象，传递给catch块
         *   }catch（异常）{
         *   //对异常的处理
         *   }
         *   //如果没有finally，语法是可以通过
         *  注意事项：
         *   1） 如果异常发生了，则异常发生后面的代码不会执行，直接进入到catch块.
         *   2）如果异常没有发生，则顺序执行try的代码块，不会进入到catch.
         *   3） 如果希望不管是否发生异常，都执行某段代码（比如关闭连接，释放资源等）则使用如下代码-finally{}
         *   4）可以有多个catch语句，捕获不同的异常（进行不同的业务处理），要求父类异常在后，子类异常在前，
         * 比如（Exception 在后，NullPointerException 在前），如果发生异常，只会匹配一个catch
         *   5）可以进行 try-finally 配合使用，这种用法相当于没有捕获异常，因此程序会直接崩掉/退出。
         * 应用场景，就是执行一段代码，不管是否发生异常，都必须执行某个业务逻辑
         * 执行顺序总结：
         *  1）如果没有出现异常，则执行try块中所有语句，不执行catch块中语句，如果有finally，最后还需要执行finally里面的语句
         *  2）如果出现异常，则try块中异常发生后，try块剩下的语句不再执行。将执行catch块中的语句，如果有finally，最后还需要执行finally里面的语句
         *   2.throws
         *       将发生的异常抛出，交给调用者（方法）来处理，最顶级的处理者就是JVM
         *   1) 如果一个方法（中的语句执行时）可能生成某种异常，但是并不能确定如何处理这种异常，则此方法应显示地声明抛出异常，
         * 表明该方法将不对这些异常进行处理，而由该方法的调用者负责处理
         *   2）在方法声明中用throws语句可以声明抛出异常的列表，throws后面的异常类型可以是方法中产生的异常类型，也可以是它的父类
         * 注意事项和使用细节:
         *  1）对于编译异常，程序中必须处理，比如 try-catch 或者 throws
         *  2）对于运行时异常，程序中如果没有处理，默认就是throws的方式处理
         *  3）子类重写父类的方法时，对抛出异常的规定∶子类重写的方法，所抛出的异常类型要么和父类抛出的异常一致，要么为父类抛出的异常的类型的子类型
         *  4）在throws 过程中，如果有方法 try-catch，就相当于处理异常，就可以不必throws
         */

        //可以有多个catch语句，捕获不同的异常（进行不同的业务处理），要求父类异常在后，子类异常在前
        System.out.println("try-catch-finally");
        String[] strings = {"1"};
        try {
            String number = "ss";
            Integer.valueOf(number);
            System.out.println(strings[1]);
        } catch (NumberFormatException e) {
            System.out.println("异常信息是 " + e.getMessage());
        } catch (Exception e) {
            System.out.println("最终异常 " + e.getMessage());
        }finally {
            //不管是否发生异常，都执行某段代码
            System.out.println("my name is finally");
        }
        System.out.println("continue");
        System.out.println("...throws...");
        //抛出给调用者处理
        try {
            exception();//handle
        } catch (Exception e) {
            System.out.println("throws exception®");
        }
    }

    @SuppressWarnings({"all"})
    /**
     * 如果一个方法（中的语句执行时）可能生成某种异常，但是并不能确定如何处理这种异常，则此方法应显示地声明抛出异常，表明该方法将不对这些异常进行处理，而由该方法的调用者负责处理
     * 在方法声明中用throws语句可以声明抛出异常的列表
     */
    public static void exception() throws FileNotFoundException,Exception {
        FileInputStream stream = new FileInputStream("d/1.jpg");
    }
}
class Father{
    protected void show() throws RuntimeException{};
}
class Son extends Father{
    /**
     * 子类重写父类的方法时，对抛出异常的规定∶子类重写的方法，所抛出的异常类型要么和父类抛出的异常一致，要么为父类抛出的异常的类型的子类型
     * @throws NullPointerException 和父类相同 或者是夫类的子类
     */
    @Override
    protected void show() throws NullPointerException {
        super.show();
    }
}
