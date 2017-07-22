package com.coderising.ood.ocp;

/**
 * Created by szf on 6/20/17.
 */
public class DateMessage implements IMessage{
    @Override
    public String getMessage(String msg) {
        return DateUtil.getCurrentDateAsString() + msg;
    }
}
