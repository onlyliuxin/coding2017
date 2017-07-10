package com.coderising.ood.ocp.formatter;

/**
 * Created by Iden on 2017/6/21.
 */
public class OnlyStringFormatter implements Formatter{

    @Override
    public String format(String msg) {
        return msg;
    }
}
