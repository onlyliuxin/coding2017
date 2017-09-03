package com.coderising.dp.week3.responsibility;

public interface Logger {

    int DEBUG = 1;
    int NOTICE = 2;
    int ERR = 3;

    void message(String message, int level);
}
