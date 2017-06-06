package com.pan.design.strategy;

/**
 * Created by Pan on 2017/6/4.
 */
public class FlyNoWay implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("什么都不做，因为我不会飞！");
    }
}
