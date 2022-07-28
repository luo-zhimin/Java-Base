package com.java.base.day.commonClass.date;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 志敏.罗
 * @create 2022/6/16 22:28
 */
public class DateDetail {

    public static void main(String[] args) throws Exception {
        /*
         * 第一代日期 Date
         *   Date 精确到毫秒 代表特定的瞬间
         *   SimpleDateFormat∶格式和解析日期的类SimpleDateFormat 格式化和解析日期的具体类。它允许进行格式化（日期->文本）、
         * 解析（文本->日期）和规范化.
         *
         * 第二代日期 Calendar 日历 --看冷得儿
         *   1）第二代日期类，主要就是Calendar类（日历）。
         *   2）Calendar 类是一个抽象类，它为特定瞬间与一组诸如YEAR、MONTH、DAY_OF_MONTH、HOUR 等之间的转换提供了一些方法，
         * 并为操作日历字段（例如获得下星期的日期）提供了一些方法。getInstance()来获取实例
         *
         * 前面俩代不足分析：
         * JDK 1.0中包含了一个java.util.Date类，但是它的大多数方法已经在JDK1.1 引入Calendar类之后被弃用了。而Calendar也存在问题是∶
         *   1）可变性∶像日期和时间这样的类应该是不可变的。
         *   2）偏移性∶Date中的年份是从1900开始的，而月份都从0开始。
         *   3）格式化∶格式化只对Date有用，Calendar则不行。
         *   4）此外，它们也不是线程安全的;不能处理闰秒等（每隔2天，多出1s）
         * 第三代日期
         *  LocalDate
         *     LocalDate 只包含日期，可以获取日期字段（日期/年月日）
         *     LocalTime 只包含时间，可以获取时间字段（时间/时分秒）
         *     LocalDateTime 包含日期+时间，可以获取日期和时间（日期时间/年月日时分秒）
         * 里面有大量的时间方法 需要可以自己去查询api LocalDateTime.plusDays()...
         * DateTimeFormatter 格式日期类
         *    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")
         * Instant 时间戳
         *  类似于Date提供了一系列和Date类转换的方式
         * Instant-->Date
         *  Date date = Date.from(instant);
         * Date-->Instant:
         *  Instant instant = date.toInstant()
         */
        Date date = new Date(1655390550569L);
        System.out.println("当前时间是 " + date);

        //new SimpleDateFormat();yyyy年MM月dd日 hh时mm分ss秒
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        //Date->String
        System.out.println(simpleDateFormat.format(date));
        //String->Date  使用的simpleDateFormat格式需要和你给的String的格式一样，否则会抛出转换异常
        System.out.println(simpleDateFormat.parse("2023-06-26 11:42:30"));
        System.out.println("calendar....[ˈkælɪndə(r)]");
//        System.out.println(Calendar.getAvailableCalendarTypes());
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar);
        //年
        System.out.println(calendar.get(Calendar.YEAR));
        //月 +1,因为Calendar返回月时候，是按照0开始编号
        System.out.println(calendar.get(Calendar.MONTH) + 1);
        //日
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        //时 正常12小时Hour 24小时Hour_OF_Day
        System.out.println(calendar.get(Calendar.HOUR_OF_DAY));
        //分
        System.out.println(calendar.get(Calendar.MINUTE));
        //秒
        System.out.println(calendar.get(Calendar.SECOND));
        System.out.println("localDate..");
        //now() 当前时间的日期
        LocalDateTime localDateTime = LocalDateTime.now();
        //时间格式化
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        System.out.println("formatter string " + formatter.format(localDateTime));

        System.out.println("localDateTime " + localDateTime);
        System.out.println(localDateTime.getYear());
        System.out.println(localDateTime.getDayOfMonth());
        System.out.println(localDateTime.getMonth() + "\t" + localDateTime.getMonthValue());
        System.out.println(localDateTime.getDayOfWeek().getValue() + "\t" + localDateTime.getDayOfMonth() + "\t" + localDateTime.getDayOfYear());
        System.out.println("MonthDay " + MonthDay.from(localDateTime));
        //提供了大量时间方法 可以对当前时间进行加减
        LocalDateTime nextDay = localDateTime.plusDays(890);
        System.out.println("890 day after " + formatter.format(nextDay));

        //localDate 可以获取年月日
        LocalDate localDate = LocalDate.now();
        System.out.println("localDate " + localDate);
        //localTime 时分秒纳秒
        LocalTime localTime = LocalTime.now();
        System.out.println("localTime " + localTime);
        System.out.println("Instant..");
        //获取当前时间搓的对象
        Instant instant = Instant.now();
        System.out.println(instant);
        //instant -> date
        System.out.println(Date.from(instant));
        //date->instant
        System.out.println(date.toInstant());
    }
}

class Exercise {

    public static void main(String[] args) {
        String s = "12345789";
        String reverse = reverse(1, s.length() - 1, s);
        System.out.println(reverse);
//        register();
        System.out.println(sayName("Luo zhi min"));
        checkCount("121212sdazdDJKJDSKJK  ,,sad");
    }

    private static String reverse(int start, int end, String source) {
        //start - end reverse
//        source = source.substring(start,end);
//        String begin = source.substring(0,start);
//        String over = source.substring(end,source.length()-1);
        if (!(source != null && start >= 0 && end > start && end < source.length())) {
            throw new RuntimeException("参数错误");
        }
        char temp = ' ';
        char[] chars = source.toCharArray();
        for (int i = start, j = end; i < j; i++, j--) {
            temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }
        return new String(chars);
    }

    private static void register() {
        StringBuffer buffer = new StringBuffer();

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名，请在2-4位之间：");
        String next = scanner.next();
        if (next.length() < 2 || next.length() > 4) {
            throw new RuntimeException("userName is error");
        }
        buffer.append(next).append("\t");
        System.out.println("请输入密码，长度6，要求全是数字：");
        next = scanner.next();
        String replaceAll = next.replaceAll("\\d", "");
        if (next.length() != 6 || !replaceAll.equals("")) {
            throw new RuntimeException("password is error");
        }
        buffer.append(next).append("\t");
        System.out.println("请输入邮箱，需要包含@和.而且@要在.后面：");
        next = scanner.next();
        int aIndex = next.indexOf("@");
        int point = next.indexOf(".");
        if ((aIndex!=1 || point!=-1 ) && point < aIndex) {
            throw new RuntimeException("email is error");
        }
        buffer.append(next).append("\t");
        System.out.println(buffer);
        System.out.println("register successful");
    }

    /**
     * work3
     * @param name userName
     * @return XX,xx.S
     */
    private static String sayName(String name){
        if (name==null)return null;
        StringBuilder buffer = new StringBuilder();
        String[] names = name.split(" ");
        if (names.length<3){
            return null;
        }
        buffer.append(names[2]).append(",").append(names[0]).append(".").append(names[1].substring(0,1).toUpperCase(Locale.ROOT));
        return new String(buffer);
    }

    /**
     * regex 最优
     * 多少个大写字母 多个小写字母
     * @param name param
     */
    private static void checkCount(String name){
        if (name == null)return;
        int numCount = 0;
        int lowerCount = 0;
        int upperCount = 0;
        int otherCount = 0;
        char[] chars = name.toCharArray();
        for (char aChar : chars) {
            if (aChar>='0' && aChar<='9'){
                numCount++;
            }else if (aChar>='a' && aChar<='z'){
                lowerCount++;
            }else if (aChar>='A' && aChar<='Z'){
                upperCount++;
            }else {
                otherCount++;
            }
        }
        System.out.println("数字 "+numCount+" 小写 "+lowerCount+" 大写 "+upperCount+" 其他 "+otherCount);
    }

}