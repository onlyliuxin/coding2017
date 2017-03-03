package com.bruce.utils;

/**
 * Created by Bruce.Jiao on 15-6-23.
 */
public class MyException extends Exception{
    private int code;
    private String message;

    public MyException() {
    }

    public MyException(String message) {
        this.message = message;
    }

    public MyException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
