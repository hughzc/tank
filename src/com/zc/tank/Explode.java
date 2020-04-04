package com.zc.tank;

import java.awt.*;

public class Explode {
    //属性
    private int x, y;
    public static final int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static final int HEIGHT = ResourceMgr.explodes[0].getWidth();
    private TankFrame tf;

    private int step = 0;

    public Explode(int x, int y, TankFrame tf){
        this.x = x;
        this.y = y;
        this.tf = tf;
    }
    public void paint(Graphics g) {
        //画出来即可
        g.drawImage(ResourceMgr.explodes[step++],x,y,null);
        //一轮爆炸结束后，清除即可
        if (step >= ResourceMgr.explodes.length){
            tf.explodes.remove(this);
        }
    }


}
