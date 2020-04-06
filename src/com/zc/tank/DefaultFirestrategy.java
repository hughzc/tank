package com.zc.tank;



public class DefaultFirestrategy implements FireStrategy {
    @Override
    public void fire(Tank t){
        int bX = t.getX() + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = t.getY() + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        new Bullet(bX,bY,t.getDir(),t.getGroup(),t.getTf());
    }
}
