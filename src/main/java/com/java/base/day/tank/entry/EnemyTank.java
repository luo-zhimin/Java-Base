package com.java.base.day.tank.entry;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * 敌人的tank 需要让敌人的tank 动起来
 *
 * @Author : 志敏.罗
 * @create 2022/6/28 23:59
 */
//@ToString
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class EnemyTank extends Tank implements Runnable {

    //可以得到敌人的tanks
    Vector<EnemyTank> enemyTanks = new Vector<>();

    //判断this是否和enemyTanks里面的tank发生了碰撞
    public boolean isTouchTank() {
        //每种tank俩种情况({上下}/{左右})
        switch (getDirection()) {
            case "w":
                //this tank -> 和其他所有的敌人的tank比较
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和自己比较
                    if (enemyTank != this) {
                        //其他人({上下}/{左右})
                        //上下 其他人tank 的x坐标[enemyTank.getX(),enemyTank.getX()+40] y[enemyTank.getY(),enemyTank.getY()+60]
                        if (Objects.equals(enemyTank.getDirection(), "w") || Objects.equals(enemyTank.getDirection(), "s")) {
                            //当前tank的左上角 和 右上角的坐标(this.getX,this.getY)
                            //当前tank的右上角 和 右上角的坐标(this.getX+40,this.getY)
                            if (getX() >= enemyTank.getX()
                                    && getX() <= enemyTank.getX() + 40
                                    && getY() >= enemyTank.getY()
                                    && getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            if (getX() + 40 >= enemyTank.getX()
                                    && getX() + 40 <= enemyTank.getX() + 40
                                    && getY() >= enemyTank.getY()
                                    && getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                        } else if (Objects.equals(enemyTank.getDirection(), "a") || Objects.equals(enemyTank.getDirection(), "d")) {
                            //左右 其他人tank 的x坐标[enemyTank.getX(),enemyTank.getX()+60] y[enemyTank.getY(),enemyTank.getY()+40]
                            //当前tank的左上角 和 右上角的坐标(this.getX,this.getY)
                            if (getX() >= enemyTank.getX()
                                    && getX() <= enemyTank.getX() + 60
                                    && getY() >= enemyTank.getY()
                                    && getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                            //当前tank的右上角 和 右上角的坐标(this.getX+60,this.getY)
                            if (getX() + 40 >= enemyTank.getX()
                                    && getX() + 40 <= enemyTank.getX() + 60
                                    && getY() >= enemyTank.getY()
                                    && getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case "s":
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和自己比较
                    if (enemyTank != this) {
                        //其他人({上下}/{左右})
                        //上下 其他人tank 的x坐标[enemyTank.getX(),enemyTank.getX()+40] y[enemyTank.getY(),enemyTank.getY()+60]
                        if (Objects.equals(enemyTank.getDirection(), "w") || Objects.equals(enemyTank.getDirection(), "s")) {
                            //当前tank的左下角 (this.getX,this.getY+60)
                            if (getX() >= enemyTank.getX()
                                    && getX() <= enemyTank.getX() + 40
                                    && getY() + 60 >= enemyTank.getY()
                                    && getY() + 60 <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //当前tank的右下角 (this.getX+40,this.getY+60)
                            if (getX() + 40 >= enemyTank.getX()
                                    && getX() + 40 <= enemyTank.getX() + 40
                                    && getY() + 60 >= enemyTank.getY()
                                    && getY() + 60 <= enemyTank.getY() + 60) {
                                return true;
                            }
                        } else if (Objects.equals(enemyTank.getDirection(), "a") || Objects.equals(enemyTank.getDirection(), "d")) {
                            //左右 其他人tank 的x坐标[enemyTank.getX(),enemyTank.getX()+60] y[enemyTank.getY(),enemyTank.getY()+40]
                            //当前tank的左下角(this.getX,this.getY+60)
                            if (getX() >= enemyTank.getX()
                                    && getX() <= enemyTank.getX() + 60
                                    && getY() + 60 >= enemyTank.getY()
                                    && getY() + 60 <= enemyTank.getY() + 40) {
                                return true;
                            }
                            //当前tank的右下角 (this.getX+40,this.getY+60)
                            if (getX() + 40 >= enemyTank.getX()
                                    && getX() + 40 <= enemyTank.getX() + 60
                                    && getY() + 60 >= enemyTank.getY()
                                    && getY() + 60 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case "a":
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和自己比较
                    if (enemyTank != this) {
                        //其他人({上下}/{左右})
                        //上下 其他人tank 的x坐标[enemyTank.getX(),enemyTank.getX()+40] y[enemyTank.getY(),enemyTank.getY()+60]
                        if (Objects.equals(enemyTank.getDirection(), "w") || Objects.equals(enemyTank.getDirection(), "s")) {
                            //当前tank的左上角 (this.getX,this.getY)
                            if (getX() >= enemyTank.getX()
                                    && getX() <= enemyTank.getX() + 40
                                    && getY() >= enemyTank.getY()
                                    && getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //当前tank的左下角 (this.getX,this.getY+40)
                            if (getX() >= enemyTank.getX()
                                    && getX() <= enemyTank.getX() + 40
                                    && getY() + 40 >= enemyTank.getY()
                                    && getY() + 40 <= enemyTank.getY() + 60) {
                                return true;
                            }
                        } else if (Objects.equals(enemyTank.getDirection(), "a") || Objects.equals(enemyTank.getDirection(), "d")) {
                            //左右 其他人tank 的x坐标[enemyTank.getX(),enemyTank.getX()+60] y[enemyTank.getY(),enemyTank.getY()+40]
                            //当前tank的左上角(this.getX,this.getY)
                            if (getX() >= enemyTank.getX()
                                    && getX() <= enemyTank.getX() + 60
                                    && getY() >= enemyTank.getY()
                                    && getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                            //当前tank的左下角 (this.getX+60,this.getY)
                            if (getX() >= enemyTank.getX()
                                    && getX() <= enemyTank.getX() + 60
                                    && getY() + 40 >= enemyTank.getY()
                                    && getY() + 40 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case "d":
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和自己比较
                    if (enemyTank != this) {
                        //其他人({上下}/{左右})
                        //上下 其他人tank 的x坐标[enemyTank.getX(),enemyTank.getX()+40] y[enemyTank.getY(),enemyTank.getY()+60]
                        if (Objects.equals(enemyTank.getDirection(), "w") || Objects.equals(enemyTank.getDirection(), "s")) {
                            //当前tank的右上角 (this.getX+60,this.getY)
                            //当前tank的右下角 (this.getX+60,this.getY+40)
                            if (getX() + 60 >= enemyTank.getX()
                                    && getX() + 60 <= enemyTank.getX() + 40
                                    && getY() >= enemyTank.getY()
                                    && getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            if (getX() + 60 >= enemyTank.getX()
                                    && getX() + 60 <= enemyTank.getX() + 40
                                    && getY() + 40 >= enemyTank.getY()
                                    && getY() + 40 <= enemyTank.getY() + 60) {
                                return true;
                            }
                        } else if (Objects.equals(enemyTank.getDirection(), "a") || Objects.equals(enemyTank.getDirection(), "d")) {
                            //左右 其他人tank 的x坐标[enemyTank.getX(),enemyTank.getX()+60] y[enemyTank.getY(),enemyTank.getY()+40]
                            //右上角(this.getX+60,this.getY)
                            if (getX() + 60 >= enemyTank.getX()
                                    && getX() + 60 <= enemyTank.getX() + 60
                                    && getY() >= enemyTank.getY()
                                    && getY() <= enemyTank.getY() + 40) {
                                return true;
                            }
                            //当前tank的右上角 和 右上角的坐标(this.getX+60,this.getY)
                            if (getX() + 60 >= enemyTank.getX()
                                    && getX() + 60 <= enemyTank.getX() + 60
                                    && getY() + 40 >= enemyTank.getY()
                                    && getY() + 40 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
        }
        return false;
    }


    public EnemyTank(int x, int y, String direction) {
        super(x, y, direction);
        setType(1);
    }

    @SneakyThrows
    @Override
    public void run() {

        //根据方向来 继续移动 随机
        while (isLive()) {
            //敌人的子弹
            if (shots.size() < 1) {
                shotEnemyTank();
            }
            switch (getDirection()) {
                case "w":
                    for (int i = 0; i < 30; i++) {
                        if (!isTouchTank()) {
                            moveUp();
                        }
                        Thread.sleep(50);
                    }
                    break;
                case "s":
                    for (int i = 0; i < 30; i++) {
                        if (!isTouchTank()) {
                            moveDown();
                        }
                        Thread.sleep(50);
                    }
                    break;
                case "a":
                    for (int i = 0; i < 30; i++) {
                        if (!isTouchTank()) {
                            moveLeft();
                        }
                        Thread.sleep(50);
                    }
                    break;
                case "d":
                    for (int i = 0; i < 30; i++) {
                        if (!isTouchTank()) {
                            moveRight();
                        }
                        Thread.sleep(50);
                    }
                    break;
            }

            //随机改变方向
            List<String> strings = Arrays.asList("w", "a", "s", "d");
            setDirection(strings.get((int) (Math.random() * 4)));
        }
    }
}