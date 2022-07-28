/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.java.base.day.tank.entry;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.SneakyThrows;

/**
 * Created by IntelliJ IDEA.
 * 射击 + 子弹
 *
 * @Author : 志敏.罗
 * @create 2022/7/1 22:48
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Shot extends BaseEntry implements Runnable {

    private int height, weight;

    public Shot(int x, int y, String direction) {
       setX(x);
       setY(y);
       setDirection(direction);
    }

    @Override
    public void run() {//射击行为
        //边界+遇到敌人销毁 false  是否需要获取子弹的轨迹
        while (isLive()) {
            if (!shotBullet()) {
                break;
            }
        }
    }

    /**
     * 根据方向改变
     */
    @SneakyThrows
    private synchronized boolean shotBullet() {
        Thread.sleep(100);
        System.out.println(Thread.currentThread().getName()+ " shot bullet x= " + getX() + " y= " + getY());
        //边界 + 敌人的tank
        if (!(getX() >= 0 && getX() <= getWeight() && getY() >= 0 && getY() <= getHeight() && isLive())) {
            //die
            System.out.println("子弹退出");
            setLive(false);
            return isLive();
        }
        switch (getDirection()) {
            //change direction
            case "w":
                setY(getY() - getSpeed());
                break;
            case "a":
                setX(getX()-getSpeed());
                break;
            case "s":
                setY(getY()+getSpeed());
                break;
            case "d":
                setX(getX()+getSpeed());
                break;
        }
        //敌人的tank
        return true;
    }
}
