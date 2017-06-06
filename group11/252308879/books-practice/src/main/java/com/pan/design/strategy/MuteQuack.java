package com.pan.design.strategy;

/**
 * Created by Pan on 2017/6/4.
 */
public class MuteQuack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("什么都不做，不会叫！");
    }
}
