package com.pan.design.strategy;

import org.junit.Test;

/**
 * Created by Pan on 2017/6/4.
 */
public class DuckTest {

    @Test
    public void testMiniDuckSimulator(){
        Duck duck = new ModelDuck(new FlyWithWings(), new MuteQuack());

        duck.preFormFly();
        duck.preFormQuack();


        duck.setFlyBehavior(new FlyNoWay());
        duck.setQuackBehavior(new Squeak());

        duck.preFormFly();
        duck.preFormQuack();
    }

}
