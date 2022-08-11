package com.java.base.day.tank.entry;

import com.java.base.day.tank.util.AePlayWave;
import lombok.SneakyThrows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.List;
import java.util.Vector;

/**
 * 为了子弹不停的重绘 实现线程
 */
public class TankPanel extends JPanel implements KeyListener, Runnable {

    /**
     * 初始化自己的tank
     */
    static Hero hero = null;

    /**
     * 敌人的tank
     */
    static Vector<EnemyTank> enemyTanks = new Vector<>();

    /**
     * init 敌人tank的数量 panel size
     */
    int size = 3, wight = 1000, height = 750;

    /**
     * 存放炸弹 子弹击中
     */
    static Vector<Bomb> bombs = new Vector<>();

    /**
     * 保存之前的tank
     */
    Vector<EnemyTank> lastGameTanks = new Vector<>();

    /**
     * 定义3张爆炸图片
     */
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;

    @SneakyThrows
    public TankPanel(String key) {
        hero = new Hero(100, 100, "w");
        hero.setSpeed(10);

        if (key.equals("2")) {
            File file = Recorder.getFile();
            if (!file.exists()) {
                throw new RuntimeException("您没有存储，您需要开始新的一局");
            }
            Recorder.readRecord();

            //继续开始 需要判断是否患有tank
            lastGameTanks = Recorder.getEnemyTanks();
            for (int i = 0; i < lastGameTanks.size(); i++) {
                System.out.println("continue last game read save data");
                EnemyTank tank = lastGameTanks.get(i);
                EnemyTank enemyTank = new EnemyTank(tank.getX(), tank.getY(), tank.getDirection());
                setEnemyTank(enemyTank);
            }
        } else if (key.equals("1")) {
            if (size > 0) {
                for (int i = 0; i < size; i++) {
                    //need think out weight and height while x
                    int x = i == 0 ? 10 : 100 * i;
                    int y = 0;
                    //need change
                    if (x >= wight) {
                        //need \n x->0 y... 需要行数 或者重置 while ～
                        x = i == 0 ? 10 : 100 * i;
                        y += 100;
                    }

                    System.out.println("start new game");
                    EnemyTank enemyTank = new EnemyTank(x, y, "w");
                    setEnemyTank(enemyTank);
                }
            }
        }

        //init image
        image1 = Toolkit.getDefaultToolkit().getImage(TankPanel.class.getResource("/tank/bomb_1.gif"));
        image2 = Toolkit.getDefaultToolkit().getImage(TankPanel.class.getResource("/tank/bomb_2.gif"));
        image3 = Toolkit.getDefaultToolkit().getImage(TankPanel.class.getResource("/tank/bomb_3.gif"));

        //播放音乐
        new AePlayWave(TankPanel.class.getResource("/tank/111.wav").getFile()).start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //确认坐标 绘图 绘制tank
        //(需要填充)3 个矩形(2 small 1 big) 1个圆形(circle) 一个线(line)
        g.fillRect(0, 0, wight, height);//游戏的区域
        //画hero tank
        if (hero != null && hero.isLive()) {
            drawTank(hero.getX(), hero.getY(), g, hero.getDirection(), hero.getType());
        }

        //画子弹 需要绘制多个子弹
        //hero
        if (hero != null && hero.isLive() && hero.getShot() != null && hero.getShot().isLive()) {
            drawShots(g, hero.getShots());
        }

        //画敌人的tank
        if (!enemyTanks.isEmpty()) {
            for (EnemyTank enemyTank : enemyTanks) {
                if (enemyTank.isLive()) {
                    drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirection(), enemyTank.getType());
                    //remove flag shot
                    if (!enemyTank.getShots().isEmpty()) {
                        drawShots(g, enemyTank.getShots());
                    }
                }
            }
        }

        //如果 booms 有就开始 画炸弹
        if (!bombs.isEmpty()) {
            for (int i = 0; i < bombs.size(); i++) {
                Bomb bomb = bombs.get(i);
                if (bomb.getLife() > 6) {
                    g.drawImage(image1, bomb.getX(), bomb.getY(), 60, 60, this);
                } else if (bomb.getLife() > 3) {
                    g.drawImage(image2, bomb.getX(), bomb.getY(), 60, 60, this);
                } else {
                    g.drawImage(image3, bomb.getX(), bomb.getY(), 60, 60, this);
                }
                //减少炸弹的生命值
                bomb.lifeDown();
                if (bomb.getLife() == 0) {
                    bombs.remove(bomb);
                }
            }
        }
        showInfo(g);
    }

    //记录击毁的信息
    public void showInfo(Graphics graphics) {
        //画出
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("宋体", Font.BOLD, 18));
        graphics.drawString("您累积击毁敌人的坦克", wight + 5, 30);
        //绘制tank
        drawTank(wight + 5, 60, graphics, "w", 1);
        graphics.setColor(Color.BLACK);
        graphics.drawString(Recorder.getEnemyNumber() + "", wight + 50, 95);
    }


    private void drawShots(Graphics g, Vector<Shot> shots) {
        for (int i = 0; i < shots.size(); i++) {
            Shot shot = shots.get(i);
            if (!shot.isLive()) {
                shots.remove(shot);
            } else {
                // shot
                drawShot(shots, g);
            }
        }
    }

    /**
     * 画子弹 销毁移除
     */
    private void drawShot(List<Shot> shots, Graphics graphics) {
        if (!shots.isEmpty()) {
            for (Shot shot : shots) {
                if (shot != null) {
                    if (shot.isLive()) {
                        graphics.fill3DRect(shot.getX(), shot.getY(), 1, 1, false);
                    }
                }
            }
        }
    }

    /**
     * print tank
     *
     * @param x         左上角x
     * @param y         左上角y
     * @param graphics  画笔
     * @param direction 方向(上下左右)
     * @param type      类型(谁的)
     */
    public void drawTank(int x, int y, Graphics graphics, String direction, int type) {
        //上颜色
        switch (type) {
            //hour
            case 0:
                graphics.setColor(Color.CYAN);
                break;
            //outer
            case 1:
                graphics.setColor(Color.YELLOW);
                break;
            default:
                break;
        }

        //方向(绘制对应 形状的tank )
        switch (direction) {
            case "w"://up
                graphics.fill3DRect(x, y, 10, 60, false);//左轮子
                graphics.fill3DRect(x + 10, y + 10, 20, 40, false);//身体
                graphics.fill3DRect(x + 30, y, 10, 60, false);//右轮子
                graphics.fillOval(x + 10, y + 20, 20, 20);//盖子
                graphics.drawLine(x + 20, y + 30, x + 20, y);//炮筒
                break;
            case "a"://left 逆时针90
                graphics.fill3DRect(x, y, 60, 10, false);//左轮子
                graphics.fill3DRect(x + 10, y + 10, 40, 20, false);//身体
                graphics.fill3DRect(x, y + 30, 60, 10, false);//右轮子
                graphics.fillOval(x + 20, y + 10, 20, 20);//盖子
                graphics.drawLine(x + 30, y + 20, x, y + 20);//炮筒
                break;
            case "s"://down
                //change line 顺时针旋转180 line(x1,y1,x2,y2) x1/y1(start point) x2/y2(end point)
                graphics.fill3DRect(x, y, 10, 60, false);//左轮子
                graphics.fill3DRect(x + 10, y + 10, 20, 40, false);//身体
                graphics.fill3DRect(x + 30, y, 10, 60, false);//右轮子
                graphics.fillOval(x + 10, y + 20, 20, 20);//盖子
                graphics.drawLine(x + 20, y + 30, x + 20, y + 60);//炮筒
                break;
            case "d"://right  顺时针 90
                graphics.fill3DRect(x, y, 60, 10, false);//左轮子
                graphics.fill3DRect(x + 10, y + 10, 40, 20, false);//身体
                graphics.fill3DRect(x, y + 30, 60, 10, false);//右轮子
                graphics.fillOval(x + 20, y + 10, 20, 20);//盖子
                graphics.drawLine(x + 30, y + 20, x + 60, y + 20);//炮筒
                break;
            default:
                System.out.println("暂时没有处理");
        }
    }

    /**
     * @param e event
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * keyDown
     *
     * @param e event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            //change direction
            case KeyEvent.VK_W:
                hero.setDirection(e.getKeyChar() + "");
                hero.moveUp();
                break;
            case KeyEvent.VK_A:
                hero.setDirection(e.getKeyChar() + "");
                hero.moveLeft();
                break;
            case KeyEvent.VK_S:
                hero.setDirection(e.getKeyChar() + "");
                hero.moveDown();
                break;
            case KeyEvent.VK_D:
                hero.setDirection(e.getKeyChar() + "");
                hero.moveRight();
                break;
            case KeyEvent.VK_J:
                //射击 启动线程
                //应该 panel size set /敌人tank的位置
                System.out.println("开始射击..");
                //存活只可以射击一个子弹
//                if ((hero != null && hero.getShot() != null && !hero.getShot().isLive())
//                        || hero != null && hero.getShot() == null) {
                hero.shotEnemyTank();
//                }
        }
        //重绘图
        this.repaint();
    }

    /**
     * keyUp
     *
     * @param e event
     */
    @Override
    public void keyReleased(KeyEvent e) {
    }

    @SneakyThrows
    @Override
    public void run() {//100毫秒 不停的重新绘
        while (true) {
            Thread.sleep(100);
            //判断是否被击中tank
            //多颗子弹
            hitEnemy();
            //die hero
            hitHero();
            this.repaint();
        }
    }

    public static void hitHero() {
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            for (int k = 0; k < enemyTank.getShots().size(); k++) {
                Shot shot = enemyTank.getShots().get(k);
                if (hero.isLive() && shot.isLive()) {
                    //判断是否击中我放的tank
                    hitTank(shot, hero);
                }
            }
        }
    }

    public static void hitEnemy() {
        if (hero != null && hero.getShots().size() > 0 && hero.isLive()) {
            for (int i = 0; i < hero.getShots().size(); i++) {
                Shot shot = hero.getShots().get(i);
                for (int j = 0; j < enemyTanks.size(); j++) {
                    EnemyTank enemyTank = enemyTanks.get(j);
                    hitTank(shot, enemyTank);
                }
            }
        }
    }

    /**
     * 判断坦克是否被击中
     */
    public static void hitTank(Shot shot, Tank tank) {
        //子弹的xy 接触到tank tank的区域
        switch (tank.getDirection()) {
            case "w":
            case "s":
                if (shot.getX() > tank.getX() && shot.getX() < tank.getX() + 40 &&
                        shot.getY() > tank.getY() && shot.getY() < tank.getY() + 60) {
                    died(shot, tank);
                }
                break;
            case "a":
            case "d":
                if (shot.getX() > tank.getX() && shot.getX() < tank.getX() + 60 &&
                        shot.getY() > tank.getY() && shot.getY() < tank.getY() + 40) {
                    died(shot, tank);
                }
                break;
        }
    }

    private static void died(Shot shot, Tank tank) {
        shot.setLive(false);
        tank.setLive(false);
        if (tank instanceof EnemyTank) {
            //移除
            enemyTanks.remove((EnemyTank) tank);
            //击中时候 ++
            Recorder.add();
        }
        //炸弹加入
        Bomb bomb = new Bomb(tank.getX(), tank.getY());
        bombs.add(bomb);
        System.out.println("销毁");
    }

    private void setEnemyTank(EnemyTank enemyTank) {
        new Thread(enemyTank).start();
        enemyTank.shotEnemyTank();
        enemyTanks.add(enemyTank);
        //赋值 碰撞
        enemyTank.setEnemyTanks(enemyTanks);
        //保存
        Recorder.setEnemyTanks(enemyTanks);
    }
}