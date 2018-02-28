package com.coderising.myknowledgepoint.reflect.proxy;

/**
 * Created by thomas_young on 23/9/2017.
 */
public class RealObject implements Interface {

    @Override
    public void doSomething() {
        System.out.println("doSomething.");
    }

    @Override
    public void somethingElse(String arg) {
        System.out.println("somethingElse " + arg);
    }
}
