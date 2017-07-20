package com.company;

/**
 * Created by kenhuang on 2017/6/20.
 */
public class DateFormatter extends Formatter {
    @Override
    public String formatMessage(String msg) {
        String txtDate = DateUtil.getCurrentDateAsString();
        return txtDate + " : " + msg;

    }
}
