package com.pan.design.strategy;

/**
 * Created by Pan on 2017/6/4.
 */
public class Quack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("呱呱叫！");
    }
}
