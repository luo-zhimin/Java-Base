package com.java.base.day.tank.event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by IntelliJ IDEA.
 * java 事件处理机制
 *
 * @Author : 志敏.罗
 * @create 2022/6/28 17:34
 */
public class BallMove extends JFrame {

    BallPanel ballPanel = null;

    /**
     * java 事件处理机制<br/>
     * java事件处理是采取"<strong>委派事件模型<strong/>"。当事件发生时，产生事件的对象，会把此"信息"传递给"事件的监听者"处理，这里所说的"信息"
     * 实际上就是 java.awt.event 事件类库里某个类所创建的对象，把它称为"事件的对象"。<br/>
     * 事件源∶事件源是一个产生事件的对象，比如按钮，窗口等<br/>
     * 事件∶ 事件就是承载事件源状态改变时的对象，比如当键盘事件、鼠标事件、窗口事件等等，会生成一个事件对象，该对象保存着当前事件很多信息，
     * 比如KeyEvent 对象有含有被按下键的Code值。java.awt.event包和javax.swing.event包中定义了各种事件类型<br/>
     * 小球上下左右移动
     */
    public static void main(String[] args) {
        new BallMove();
        System.out.println("exit~");
    }

    public BallMove() {
        ballPanel = new BallPanel();
        //add
        this.add(ballPanel);
        //close
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //see
        this.setVisible(true);
        //jFrame size
        this.setSize(400, 400);
        //监听键盘事件
        this.addKeyListener(ballPanel);
    }
}

/**
 * 画小球
 * 监听 KeyListener 键盘事件
 */
class BallPanel extends JPanel implements KeyListener {

    //为了让 ball move 需要把它的坐标 定义

    int x = 10;
    int y = 10;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillOval(x, y, 30, 30);
        System.out.println("绘图·");
    }

    /**
     * 有字符输入输出时
     *
     * @param e 事件
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * 当某个键按下触发 keyDown
     *
     * @param e 事件
     */
    @Override
    public void keyPressed(KeyEvent e) {

        //根据用户按下的不同的键位处理小球的移动
        switch (e.getKeyCode()) {
            case KeyEvent.VK_DOWN://down
                y++;
                break;
            case KeyEvent.VK_UP:
                y--;
                break;
            case KeyEvent.VK_LEFT:
                x--;
                break;
            case KeyEvent.VK_RIGHT:
                x++;
                break;
            default:
                System.out.println(e.getKeyChar() + "被按下～～");
                break;
        }

        //改变后 需要让面板重新绘制
        this.repaint();
    }

    /**
     * keyUp 当按键被释放
     *
     * @param e 事件
     */
    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println(e.getKeyChar() + "被释放～～");
    }
}