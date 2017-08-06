package com.coderising.myood.ocp.myocp;


/**
 * Created by thomas_young on 24/6/2017.
 */
public class DateFormatter implements Formatter {

    @Override
    public String format(String msg) {
        String txtDate = DateUtil.getCurrentDateAsString();
        return txtDate + ": " + msg;
    }
}
