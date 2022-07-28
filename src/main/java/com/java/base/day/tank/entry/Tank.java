package com.java.base.day.tank.entry;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * Tank
 *
 * @Author : 志敏.罗
 * @create 2022/6/28 13:55
 */
@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Tank extends BaseEntry {

    /**
     * 定义shot 表示射击行为
     */
    private Shot shot = null;

    Vector<Shot> shots = new Vector<>();

//    Vector<Tank> tanks = new Vector<>();

    /**
     * 类型 0 own 1 other
     */
    private int type;

    public Tank(int x, int y, String direction) {
        setX(x);
        setY(y);
        setDirection(direction);
    }

    public void moveUp() {
        int y = getY() - getSpeed();
        if (y < 0 ) return;
        setY(y);
    }

    public void moveDown() {
        int y = getY() + getSpeed();
        if (y > 660) return;
        setY(y);
    }

    public void moveLeft() {
        int x = getX() - getSpeed();
        if (x < 0 ) return;
        setX(x);
    }

    public void moveRight() {
        int x = getX() + getSpeed();
        if (x > 940) return;
        setX(x);
    }

    public void shotEnemyTank() {
        changeSwitch();
        shot.setWeight(1000);
        shot.setHeight(750);
        // 5 课子弹 存活
        if (shots.size() >= 5) {
            return;
        }
        //add
        shots.add(shot);
        //启动
        new Thread(shot).start();
    }

    public void changeSwitch() {
        switch (getDirection()) {
            //change direction
            case "w":
                shot = new Shot(getX() + 20, getY(), getDirection());
                break;
            case "a":
                shot = new Shot(getX(), getY() + 20, getDirection());
                break;
            case "s":
                shot = new Shot(getX() + 20, getY() + 60, getDirection());
                break;
            case "d":
                shot = new Shot(getX() + 60, getY() + 20, getDirection());
                break;
        }
    }

    /**
     * 判断this是否和enemyTanks里面的tank发生了碰撞
     * @return 是否碰撞
     */
//    public boolean isTouchTank() {
//        //每种tank俩种情况({上下}/{左右})
//        switch (getDirection()) {
//            case "w":
//                //this tank -> 和其他所有的敌人的tank比较
//                for (Tank tank : tanks) {
//                    //不和自己比较
//                    if (tank != this) {
//                        //其他人({上下}/{左右})
//                        //上下 其他人tank 的x坐标[tank.getX(),tank.getX()+40] y[tank.getY(),tank.getY()+60]
//                        if (Objects.equals(tank.getDirection(), "w") || Objects.equals(tank.getDirection(), "s")) {
//                            //当前tank的左上角 和 右上角的坐标(this.getX,this.getY)
//                            //当前tank的右上角 和 右上角的坐标(this.getX+40,this.getY)
//                            if (getX() >= tank.getX()
//                                    && getX() <= tank.getX() + 40
//                                    && getY() >= tank.getY()
//                                    && getY() <= tank.getY() + 60) {
//                                return true;
//                            }
//                            if (getX() + 40 >= tank.getX()
//                                    && getX() + 40 <= tank.getX() + 40
//                                    && getY() >= tank.getY()
//                                    && getY() <= tank.getY() + 60) {
//                                return true;
//                            }
//                        } else if (Objects.equals(tank.getDirection(), "a") || Objects.equals(tank.getDirection(), "d")) {
//                            //左右 其他人tank 的x坐标[tank.getX(),tank.getX()+60] y[tank.getY(),tank.getY()+40]
//                            //当前tank的左上角 和 右上角的坐标(this.getX,this.getY)
//                            if (getX() >= tank.getX()
//                                    && getX() <= tank.getX() + 60
//                                    && getY() >= tank.getY()
//                                    && getY() <= tank.getY() + 40) {
//                                return true;
//                            }
//                            //当前tank的右上角 和 右上角的坐标(this.getX+60,this.getY)
//                            if (getX() + 40 >= tank.getX()
//                                    && getX() + 40 <= tank.getX() + 60
//                                    && getY() >= tank.getY()
//                                    && getY() <= tank.getY() + 40) {
//                                return true;
//                            }
//                        }
//                    }
//                }
//                break;
//            case "s":
//                for (Tank tank : tanks) {
//                    //不和自己比较
//                    if (tank != this) {
//                        //其他人({上下}/{左右})
//                        //上下 其他人tank 的x坐标[tank.getX(),tank.getX()+40] y[tank.getY(),tank.getY()+60]
//                        if (Objects.equals(tank.getDirection(), "w") || Objects.equals(tank.getDirection(), "s")) {
//                            //当前tank的左下角 (this.getX,this.getY+60)
//                            if (getX() >= tank.getX()
//                                    && getX() <= tank.getX() + 40
//                                    && getY() + 60 >= tank.getY()
//                                    && getY() + 60 <= tank.getY() + 60) {
//                                return true;
//                            }
//                            //当前tank的右下角 (this.getX+40,this.getY+60)
//                            if (getX() + 40 >= tank.getX()
//                                    && getX() + 40 <= tank.getX() + 40
//                                    && getY() + 60 >= tank.getY()
//                                    && getY() + 60 <= tank.getY() + 60) {
//                                return true;
//                            }
//                        } else if (Objects.equals(tank.getDirection(), "a") || Objects.equals(tank.getDirection(), "d")) {
//                            //左右 其他人tank 的x坐标[tank.getX(),tank.getX()+60] y[tank.getY(),tank.getY()+40]
//                            //当前tank的左下角(this.getX,this.getY+60)
//                            if (getX() >= tank.getX()
//                                    && getX() <= tank.getX() + 60
//                                    && getY() + 60 >= tank.getY()
//                                    && getY() + 60 <= tank.getY() + 40) {
//                                return true;
//                            }
//                            //当前tank的右下角 (this.getX+40,this.getY+60)
//                            if (getX() + 40 >= tank.getX()
//                                    && getX() + 40 <= tank.getX() + 60
//                                    && getY() + 60 >= tank.getY()
//                                    && getY() + 60 <= tank.getY() + 40) {
//                                return true;
//                            }
//                        }
//                    }
//                }
//                break;
//            case "a":
//                for (Tank tank : tanks) {
//                    //不和自己比较
//                    if (tank != this) {
//                        //其他人({上下}/{左右})
//                        //上下 其他人tank 的x坐标[tank.getX(),tank.getX()+40] y[tank.getY(),tank.getY()+60]
//                        if (Objects.equals(tank.getDirection(), "w") || Objects.equals(tank.getDirection(), "s")) {
//                            //当前tank的左上角 (this.getX,this.getY)
//                            if (getX() >= tank.getX()
//                                    && getX() <= tank.getX() + 40
//                                    && getY() >= tank.getY()
//                                    && getY() <= tank.getY() + 60) {
//                                return true;
//                            }
//                            //当前tank的左下角 (this.getX,this.getY+40)
//                            if (getX() >= tank.getX()
//                                    && getX() <= tank.getX() + 40
//                                    && getY() + 40 >= tank.getY()
//                                    && getY() + 40 <= tank.getY() + 60) {
//                                return true;
//                            }
//                        } else if (Objects.equals(tank.getDirection(), "a") || Objects.equals(tank.getDirection(), "d")) {
//                            //左右 其他人tank 的x坐标[tank.getX(),tank.getX()+60] y[tank.getY(),tank.getY()+40]
//                            //当前tank的左上角(this.getX,this.getY)
//                            if (getX() >= tank.getX()
//                                    && getX() <= tank.getX() + 60
//                                    && getY() >= tank.getY()
//                                    && getY() <= tank.getY() + 40) {
//                                return true;
//                            }
//                            //当前tank的左下角 (this.getX+60,this.getY)
//                            if (getX() >= tank.getX()
//                                    && getX() <= tank.getX() + 60
//                                    && getY() + 40 >= tank.getY()
//                                    && getY() + 40 <= tank.getY() + 40) {
//                                return true;
//                            }
//                        }
//                    }
//                }
//                break;
//            case "d":
//                for (Tank tank : tanks) {
//                    //不和自己比较
//                    if (tank != this) {
//                        //其他人({上下}/{左右})
//                        //上下 其他人tank 的x坐标[tank.getX(),tank.getX()+40] y[tank.getY(),tank.getY()+60]
//                        if (Objects.equals(tank.getDirection(), "w") || Objects.equals(tank.getDirection(), "s")) {
//                            //当前tank的右上角 (this.getX+60,this.getY)
//                            //当前tank的右下角 (this.getX+60,this.getY+40)
//                            if (getX() + 60 >= tank.getX()
//                                    && getX() + 60 <= tank.getX() + 40
//                                    && getY() >= tank.getY()
//                                    && getY() <= tank.getY() + 60) {
//                                return true;
//                            }
//                            if (getX() + 60 >= tank.getX()
//                                    && getX() + 60 <= tank.getX() + 40
//                                    && getY() + 40 >= tank.getY()
//                                    && getY() + 40 <= tank.getY() + 60) {
//                                return true;
//                            }
//                        } else if (Objects.equals(tank.getDirection(), "a") || Objects.equals(tank.getDirection(), "d")) {
//                            //左右 其他人tank 的x坐标[tank.getX(),tank.getX()+60] y[tank.getY(),tank.getY()+40]
//                            //右上角(this.getX+60,this.getY)
//                            if (getX() + 60 >= tank.getX()
//                                    && getX() + 60 <= tank.getX() + 60
//                                    && getY() >= tank.getY()
//                                    && getY() <= tank.getY() + 40) {
//                                return true;
//                            }
//                            //当前tank的右上角 和 右上角的坐标(this.getX+60,this.getY)
//                            if (getX() + 60 >= tank.getX()
//                                    && getX() + 60 <= tank.getX() + 60
//                                    && getY() + 40 >= tank.getY()
//                                    && getY() + 40 <= tank.getY() + 40) {
//                                return true;
//                            }
//                        }
//                    }
//                }
//                break;
//        }
//        return false;
//    }
}