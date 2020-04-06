package com.zc.tank.abstractFactory;

import com.zc.tank.Dir;
import com.zc.tank.Group;
import com.zc.tank.TankFrame;

public abstract class GameFactory {
    //抽象工厂，生产抽象坦克，爆炸与子弹
    public abstract BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf);
    public abstract BaseExplode createExplode(int x, int y, TankFrame tf);
    public abstract BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tf);

}
