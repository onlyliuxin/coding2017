package com.pan.design.strategy;

/**
 * Created by Pan on 2017/6/4.
 */
public class FlyWithWings implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("一起飞！");
    }

}
