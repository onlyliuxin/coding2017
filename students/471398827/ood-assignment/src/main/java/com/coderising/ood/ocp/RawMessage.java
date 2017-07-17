package com.coderising.ood.ocp;

/**
 * Created by szf on 6/20/17.
 */
public class RawMessage implements IMessage{
    @Override
    public String getMessage(String msg) {
        return msg;
    }
}
