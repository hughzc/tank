package com.zc.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {
    Tank myTank = new Tank(200,400,Dir.DOWN,Group.GOOD,this);//主战坦克
    List<Bullet> bullets = new ArrayList<>();
    List<Tank> tanks = new ArrayList<>();
    List<Explode> explodes = new ArrayList<>();
    static final int GAME_WIDTH = Integer.parseInt((String)PropertyMgr.get("gameWidth"));
    static final int GAME_HEIGHT = Integer.parseInt((String)PropertyMgr.get("gameHeight"));
    Explode e = new Explode(100,100,this);

    public TankFrame(){
        setSize(GAME_WIDTH,GAME_HEIGHT);
        setResizable(false);
        setTitle("tank war");
        setVisible(true);
        this.addKeyListener(new MyKeyListener());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    //双缓冲
    Image offScreenImage = null;
    //update在paint之前调用
    @Override
    public void update(Graphics g){
        if (offScreenImage == null){
            offScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);
        }
        //图片画笔
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        gOffScreen.setColor(c);
        //将坦克与子弹画在内存中
        paint(gOffScreen);
        //用屏幕画笔将内存中图片画在屏幕上
        g.drawImage(offScreenImage,0,0,null);
    }


    @Override
    public void paint(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量:"+bullets.size(),10,60);
        g.drawString("敌人的数量:"+tanks.size(),10,80);
        g.drawString("爆炸的数量:"+explodes.size(),10,100);
        if (tanks.size() == 0){
            g.drawString("你赢了！！！:",GAME_WIDTH/2,GAME_HEIGHT/2);
        }
        g.setColor(c);
        //要画出坦克，坦克自己的属性坦克自己最清楚
        myTank.paint(g);
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }
        //画出敌方坦克
        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }
        //画出爆炸效果
        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }
        //collision detect
        //看每个子弹与每个坦克是否相撞
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < tanks.size(); j++) {
                bullets.get(i).collideWith(tanks.get(j));
            }
        }
//        new Audio("audio.explode.wav").loop();
    }
    //建立内部类，处理键盘事件
    class MyKeyListener extends KeyAdapter{
        //用键盘按下的状态，来确定坦克方向，再处理其速度
        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e){
            int key =  e.getKeyCode();
            switch(key){
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }
        @Override
        public void keyReleased(KeyEvent e){
            int key =  e.getKeyCode();
            switch(key){
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }
        //根据状态改变方向
        private void setMainTankDir() {
            //如果没有按方向键，不移动
            if (!bL && !bR && !bU && !bD){
                myTank.setMoving(false);
            }else {
                //让坦克移动起来
                myTank.setMoving(true);
                if (bL) myTank.setDir(Dir.LEFT);
                if (bR) myTank.setDir(Dir.RIGHT);
                if (bU) myTank.setDir(Dir.UP);
                if (bD) myTank.setDir(Dir.DOWN);
            }
        }
    }

}
