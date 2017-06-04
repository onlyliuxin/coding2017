package com.pan.design.strategy;

/**
 * Created by Pan on 2017/6/4.
 */
public class ModelDuck extends Duck {

    public ModelDuck(){

    }

    public ModelDuck(FlyBehavior flyBehavior, QuackBehavior quackBehavior){
        setQuackBehavior(quackBehavior);
        setFlyBehavior(flyBehavior);
    }


    @Override
    public void display() {
        System.out.println("我是模型鸭子");
    }
}
