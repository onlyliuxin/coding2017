package com.coderising.ood.ocp.mine;

/**
 * Created by guodongchow on 2017/6/21.
 */
public class PrintProcessor implements Processor {
    public void process(String message) {
        System.out.println("Printing message :"+message);
    }
}
