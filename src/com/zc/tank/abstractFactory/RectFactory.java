package com.zc.tank.abstractFactory;

import com.zc.tank.Dir;
import com.zc.tank.Group;
import com.zc.tank.TankFrame;

public class RectFactory extends GameFactory {
    @Override
    public BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf) {
        return new RectTank(x,y,dir,group,tf);
    }

    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tf) {
        return new RectExplode(x,y,tf);
    }

    @Override
    public BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tf) {
        return new RectBullet(x,y,dir,group,tf);
    }
}
