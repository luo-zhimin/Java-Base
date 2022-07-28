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

/**
 * Created by IntelliJ IDEA.
 * 炸弹 图片切换
 * @Author : 志敏.罗
 * @create 2022/7/4 17:01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Bomb extends BaseEntry{

    /**
     * 炸弹的生命周期
     */
    private int life = 9;

    public Bomb(int x,int y) {
        setX(x);
        setY(y);
    }

    /**
     * 生命周期减少
     */
    public void lifeDown(){
        if (life>0){
            life --;
        }else {
            setLive(false);
        }
    }
}
