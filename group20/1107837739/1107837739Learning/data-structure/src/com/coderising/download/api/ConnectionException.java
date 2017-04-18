package com.coderising.download.api;

public class ConnectionException extends Exception {
    public ConnectionException(Exception e) {
        super(e);
    }

    public ConnectionException(String msg) {
        super(msg);
    }
}