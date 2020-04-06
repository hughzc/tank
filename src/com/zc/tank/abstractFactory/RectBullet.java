package com.zc.tank.abstractFactory;

import com.zc.tank.*;

import java.awt.*;

public class RectBullet extends BaseBullet {
    //属性
    private static final int SPEED = Integer.parseInt((String)PropertyMgr.get("bulletSpeed"));
    private int x, y;
    private Dir dir;
    public static final int WIDTH = ResourceMgr.bulletD.getWidth();
    public static final int HEIGHT = ResourceMgr.bulletD.getWidth();
    private boolean liveing = true;
    private TankFrame tf;
    private Group group = Group.BAD;

    Rectangle rect = new Rectangle();


    public RectBullet(int x, int y, Dir dir, Group group, TankFrame tf){
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
        tf.bullets.add(this);//new出来时候直接加入
    }
    public void paint(Graphics g) {
        if (!liveing)
            tf.bullets.remove(this);
        //改变颜色
        Color c = g.getColor();
        g.setColor(Color.yellow);
        g.fillRect(x,y,20,20);
        g.setColor(c);
        move();
    }
    private void move() {
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
        //更新rect
        rect.x = this.x;
        rect.y = this.y;

        //让其消失
        if (this.x < 0 || this.y < 0 || this.x > TankFrame.GAME_WIDTH || this.y > TankFrame.GAME_HEIGHT)
            liveing = false;
    }
    @Override
    public void collideWith(BaseTank tank) {
        if (this.group == tank.getGroup())
            return;
        //子弹与坦克相撞，判断是否相交
        Rectangle rect2 = new Rectangle(tank.getX(),tank.getY(),Tank.WIDTH,Tank.HEIGHT);
        //判断是否相交
        if (rect.intersects(tank.rect)){
            tank.die();
            this.die();
            //加入爆炸
            int bX = tank.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
            int bY = tank.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
            tf.explodes.add(tf.gf.createExplode(bX,bY,tf));
        }

    }

    private void die() {
        this.liveing = false;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
