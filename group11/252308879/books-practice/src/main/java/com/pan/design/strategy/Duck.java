package com.pan.design.strategy;

/**
 * Created by Pan on 2017/6/4.
 */
public abstract class Duck {

    private QuackBehavior quackBehavior;
    private FlyBehavior flyBehavior;


    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }

    public void preFormFly(){
        flyBehavior.fly();
    }

    public void preFormQuack(){
        quackBehavior.quack();
    }

    public abstract void display();

    public void swim(){
        System.out.println("All Ducks float, event decoys");
    }
}
