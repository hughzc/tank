package com.zc.tank.abstractFactory;

import java.awt.*;

public abstract class BaseBullet {
    public abstract void paint(Graphics g);

    public abstract void collideWith(BaseTank baseTank);
}
