package com.coderising.ood.ocp.myocp;


/**
 * Created by thomas_young on 24/6/2017.
 */
public class DateMsg implements Msg {

    @Override
    public String msg(String msg) {
        String txtDate = DateUtil.getCurrentDateAsString();
        return txtDate + ": " + msg;
    }
}
