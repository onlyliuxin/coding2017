package com.coderising.mydp.responseChain;

/**
 * Created by thomas_young on 11/8/2017.
 */
public class FileLogger extends AbstractLogger {
    @Override
    public void message(String message, String type) {
        System.out.println("FileLogger处理:" + message);
    }
}
