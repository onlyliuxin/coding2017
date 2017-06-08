package com.pan.design.strategy;

/**
 * Created by Pan on 2017/6/4.
 */
public class Squeak implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("吱吱叫！");
    }
}
