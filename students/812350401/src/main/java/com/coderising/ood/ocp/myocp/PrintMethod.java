package com.coderising.ood.ocp.myocp;

/**
 * Created by thomas_young on 24/6/2017.
 */
public class PrintMethod implements Method {
    @Override
    public void action(String msg) {
        System.out.println(msg);
    }
}
