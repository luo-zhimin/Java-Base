package com.java.base.day.houseSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 *
 * @author  : luozhimin
 * @create 2022/6/4 16:23
 * 房租出租系统
 */
public class HouseView {
    private boolean loop = true;
    private char next = ' ';
    private int nextInt = 0;

    /**
     * 显示主菜单
     */
    public void mainMenu() {
        do {
            System.out.println("======================房屋出租系统============================");
            System.out.println("1.新增房源");
            System.out.println("2.查找房屋");
            System.out.println("3.删除房屋");
            System.out.println("4.修改房租信息");
            System.out.println("5.房屋列表");
            System.out.println("6.退出");
            System.out.println("请选择(1-6)");
            Scanner scanner = new Scanner(System.in);
            next = scanner.next().charAt(0);
            switch (next) {
                case '1':
                    System.out.println("======================添加房源============================");
                    System.out.println(HouseService.insertHouse());
                    break;
                case '2':
                    System.out.println("======================查找房屋============================");
                    System.out.println("请输入查找的id：");
                    nextInt = scanner.nextInt();
                    System.out.println(HouseService.findHouseById(nextInt));
                    break;
                case '3':
                    System.out.println("======================删除房屋============================");
                    nextInt = ScannerUtil.update(false);
                    if (-1 == nextInt) {
                        break;
                    }
                    System.out.println("确认是否删除(y/n)：请小心选择");
                    next = scanner.next().charAt(0);
                    boolean forBreak = ScannerUtil.forBreak();
                    if (forBreak) {
                        HouseService.removeHouseById(nextInt);
                        System.out.println("删除完成");
                    }
                    break;
                case '4':
                    System.out.println("======================修改房租信息============================");
                    nextInt = ScannerUtil.update(true);
                    if (-1 == nextInt) {
                        break;
                    }
                    System.out.println(HouseService.updateHouseById(nextInt));
                    break;
                case '5':
                    System.out.println("======================房屋列表============================");
                    HouseService.showHouses();
                    break;
                case '6':
                    loop = ScannerUtil.forBreak();
                    loop = !loop;
                default:
                    break;
            }
        } while (loop);
    }
}

class HouseRentApp {
    public static void main(String[] args) {
        new HouseView().mainMenu();
        System.out.println("退出房租出租系统");
    }
}

class House {
    private int id;
    private String name;
    private String mobile;
    private String address;
    private int monthlyRent;
    private String status;

    public House() {
    }

    public House(int id, String name, String mobile, String address, int monthlyRent, String status) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.address = address;
        this.monthlyRent = monthlyRent;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getMonthlyRent() {
        return monthlyRent;
    }

    public void setMonthlyRent(int monthlyRent) {
        this.monthlyRent = monthlyRent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id +
                "\t" + name +
                "\t" + mobile +
                "\t" + address +
                "\t" + monthlyRent +
                "\t" + status;
    }
}

class HouseService {

    private final static List<House> HOUSES = new ArrayList<>();

    private static final String[] NAMES = {"姓名", "电话", "地址", "月租", "状态(未出租/已出租)"};

    private static int count = 0;

    /**
     * init
     */
    protected static void initHouses() {
        HOUSES.add(new House(1, "Jack", "13482726697", "上海市", 2000, "已出租"));
    }

    /**
     * show
     */
    protected static void showHouses() {
        if (HOUSES.size() < 1) {
            initHouses();
        }
        //sheet
        System.out.println("编号" + "\t姓名" + "\t\t电话" + "\t\t\t地址" + "\t\t月租" + "\t\t状态(未出租/已出租)");
        HOUSES.forEach(System.out::println);
        System.out.println("房屋列表完成");
    }

    /**
     * insert
     * @return newHouse
     */
    protected static String insertHouse() {
        House house = new House();
        house.setId(HOUSES.size() + 1);
        for (String name : NAMES) {
            count++;
            System.out.print(name + ":");
            String receiveName = ScannerUtil.sayString();
            setHouse(count, receiveName, house);
        }
        freshCount();
        HOUSES.add(house);
        return "添加成功";
    }

    private static void setHouse(int count, String name, House house) {
        System.out.println("switch count " + count + "\t" + name);
        switch (count) {
            case 1:
                house.setName(name);
                break;
            case 2:
                house.setMobile(name);
                break;
            case 3:
                house.setAddress(name);
                break;
            case 4:
                int money = Integer.parseInt(name);
                house.setMonthlyRent(money);
                break;
            case 5:
                house.setStatus(name);
                break;
            default:
                System.out.println("输入有误，请重新输入！！");
                break;
        }
    }

    protected static void removeHouseById(int id) {
        System.out.println("removeHouseById " + id);
        if (HOUSES.size() < 1) {
            initHouses();
        }
        House house = findHouseById(id);
        checkHouse(house);
        HOUSES.remove(house);
    }

    protected static String updateHouseById(int id) {
        System.out.println("updateHouseById " + id);
        if (HOUSES.size() < 1) {
            initHouses();
        }
        House house = findHouseById(id);
        for (String name : NAMES) {
            count++;
            sayHouse(count, name, house);
            String receiveName = ScannerUtil.sayString();
            setHouse(count, receiveName, house);
        }
        freshCount();
        return "修改成功";
    }

    private static void sayHouse(int count, String name, House house) {
        switch (count) {
            case 1:
                System.out.print(name + "(" + house.getName() + ")" + ":");
                break;
            case 2:
                System.out.print(name + "(" + house.getMobile() + ")" + ":");
                break;
            case 3:
                System.out.print(name + "(" + house.getAddress() + ")" + ":");
                break;
            case 4:
                System.out.print(name + "(" + house.getMonthlyRent() + ")" + ":");
                break;
            case 5:
                System.out.print("状态" + "(" + house.getStatus() + ")" + ":");
                break;
            default:
                System.out.println("输入有误，请重新输入！！");
                break;
        }
    }

    protected static House findHouseById(int id) {
        System.out.println("findHouseById " + id);
        if (HOUSES.size() < 1) {
            initHouses();
        }
        House myHouse = HOUSES.stream().filter(house -> house.getId() == id).findFirst().orElse(new House());
        checkHouse(myHouse);
        return myHouse;
    }

    /**
     * 重置count
     */
    private static void freshCount(){
        count=0;
    }

    /**
     * check
     * @param house house
     */
    private static void checkHouse(House house){
        if (house == null) {
            throw new RuntimeException("该房屋不存在，请重新选择！！！");
        }else if (house.getName() == null){
            throw new RuntimeException("该房屋不存在，请重新选择！！！");
        }
    }
}

class ScannerUtil {
    private final static Scanner SCANNER = new Scanner(System.in);

    protected static boolean forBreak() {
        while (true) {
            System.out.println("请输入你的选择(y/n)");
            char next = SCANNER.next().charAt(0);
            if ('y' == next) {
                return true;
            } else if ('n' == next) {
                break;
            }
        }
        return false;
    }

    protected static int update(boolean isUpdate) {
        String name = "删除";
        if (isUpdate) {
            name = "修改";
        }
        System.out.println("请选择" + name + "的房屋编号(-1退出)");
        return SCANNER.nextInt();
    }

    protected static String sayString() {
        //回车结束
        String nextString = SCANNER.nextLine();
        if (nextString.length() > 0) {
            return nextString;
        }
        return null;
    }
}
