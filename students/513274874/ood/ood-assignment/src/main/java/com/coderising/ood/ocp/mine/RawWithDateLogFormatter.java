package com.coderising.ood.ocp.mine;

import java.util.Date;

/**
 * Created by guodongchow on 2017/6/21.
 */
public class RawWithDateLogFormatter implements Fomatter {
    public String format(String message) {
        String txtDate = new Date().toString();
        return txtDate + ":" + message;
    }
}
