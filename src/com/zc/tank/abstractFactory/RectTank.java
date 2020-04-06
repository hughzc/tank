package com.zc.tank.abstractFactory;

import com.zc.tank.*;

import java.awt.*;
import java.util.Random;

public class RectTank extends BaseTank {
    private int x, y;
    private Dir dir;

    public static final int WIDTH = ResourceMgr.goodTankD.getWidth();
    public static final int HEIGHT = ResourceMgr.goodTankD.getWidth();
    private Random random = new Random();

    private static final int SPEED = Integer.parseInt((String)PropertyMgr.get("tankSpeed"));
    private boolean moving = true;
    private boolean living = true;
    private TankFrame tf;


    public RectTank(int x, int y, Dir dir, Group group, TankFrame tf){
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
    }

    public void paint(Graphics g) {
        if (!living){
            tf.tanks.remove(this);
            return;
        }
        Color c = g.getColor();
        g.setColor(group==Group.GOOD?Color.RED:Color.BLUE);
        g.fillRect(x,y,40,40);
        g.setColor(c);
        move();
    }

    private void move() {
        //如果静态状态，直接返回
        if (!moving)
            return;
        switch (dir){
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            default:
                break;
        }

        //只有敌方坦克在随机打子弹
        if (this.group == Group.BAD && random.nextInt(100) > 95)
            this.fire();
        if (this.group == Group.BAD && random.nextInt(100) > 95)
            //我方坦克不随机移动
            randomDir();
        //碰撞检测
        boundsCheck();
        //更新rect
        rect.x = this.x;
        rect.y = this.y;
    }

    private void boundsCheck() {
        if (this.x < 0) x = 0;
        if (this.y < 30) y = 30;
        if (this.x > TankFrame.GAME_WIDTH - RectTank.WIDTH) x = TankFrame.GAME_WIDTH - RectTank.WIDTH;
        if (this.y > TankFrame.GAME_HEIGHT - RectTank.HEIGHT) y = TankFrame.GAME_HEIGHT - RectTank.HEIGHT;
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public Dir getDir() {
        return dir;
    }

    public static int getSPEED() {
        return SPEED;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void fire() {
//        fs.fire(this);
        int bX = this.getX() + RectTank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = this.getY() + RectTank.HEIGHT/2 - Bullet.HEIGHT/2;
        Dir[] dirs = Dir.values();
        for (Dir dir : dirs) {
            this.getTf().gf.createBullet(bX,bY,dir,getGroup(),getTf());
        }
    }

    public void die() {
        this.living = false;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public TankFrame getTf() {
        return tf;
    }

    public void setTf(TankFrame tf) {
        this.tf = tf;
    }
}
