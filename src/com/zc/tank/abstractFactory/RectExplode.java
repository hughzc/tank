package com.zc.tank.abstractFactory;

import com.zc.tank.ResourceMgr;
import com.zc.tank.TankFrame;

import java.awt.*;

public class RectExplode extends BaseExplode {
    //属性
    private int x, y;
    public static final int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static final int HEIGHT = ResourceMgr.explodes[0].getWidth();
    private TankFrame tf;

    private int step = 0;

    public RectExplode(int x, int y, TankFrame tf){
        this.x = x;
        this.y = y;
        this.tf = tf;
    }
    public void paint(Graphics g) {
        //画出来即可
//        g.drawImage(ResourceMgr.explodes[step++],x,y,null);
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillRect(x,y,10*step,10*step);
        step++;
        //一轮爆炸结束后，清除即可
        if (step >= 5){
            tf.explodes.remove(this);
        }
        g.setColor(c);
    }
}
