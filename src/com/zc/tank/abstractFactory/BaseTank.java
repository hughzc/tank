package com.zc.tank.abstractFactory;

import com.zc.tank.Dir;
import com.zc.tank.Group;

import java.awt.*;

public abstract class BaseTank {
    protected Group group = Group.BAD;//默认是坏
    public Rectangle rect = new Rectangle();

    public abstract void paint(Graphics g);

    public Group getGroup(){
        return this.group;
    }
    public abstract void die();

    public abstract int getX();

    public abstract int getY();

    public abstract void setMoving(boolean b);

    public abstract void setDir(Dir left);

    public abstract void fire();
}
