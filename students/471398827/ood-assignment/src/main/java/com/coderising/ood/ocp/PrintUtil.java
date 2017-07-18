package com.coderising.ood.ocp;

/**
 * Created by szf on 6/20/17.
 */
public class PrintUtil implements ILog{
    @Override
    public void printLog(String msg) {
        System.out.println(msg);
    }
}
