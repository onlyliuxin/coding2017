package com.coderising.ood.ocp;

/**
 * Created by john on 2017/6/20.
 */
public class FormatByDate implements FormatLog {
    @Override
    public String format(String msg) {
        String txtDate = DateUtil.getCurrentDateAsString();
        return txtDate + ": " + msg;
    }
}
