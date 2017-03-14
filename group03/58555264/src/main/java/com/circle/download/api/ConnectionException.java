package com.circle.download.api;

/**
 * Created by keweiyang on 2017/3/10.
 */
public class ConnectionException extends Exception {

    public ConnectionException() {

    }

    /**
     * 描述异常
     * @param msg
     */
    public ConnectionException(String msg) {
        super(msg);

    }
}




