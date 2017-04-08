package com.coding.week3.download1.api;

import java.io.IOException;

/**
 * Created by Administrator on 2017/4/2 0002.
 */
public class ConnectionRefuseException extends IOException{
    public ConnectionRefuseException() {
    }

    public ConnectionRefuseException(String message) {
        super(message);
    }

    public ConnectionRefuseException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionRefuseException(Throwable cause) {
        super(cause);
    }
}
