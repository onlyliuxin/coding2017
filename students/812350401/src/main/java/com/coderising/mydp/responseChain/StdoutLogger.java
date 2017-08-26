package com.coderising.mydp.responseChain;

/**
 * Created by thomas_young on 11/8/2017.
 */
public class StdoutLogger extends AbstractLogger {
    @Override
    public void message(String message, String type) {
        System.out.println("StdoutLogger处理:" + message);
        if (!AbstractLogger.DEBUG.equals(type)) {
            getNext().message(message, type);
        }
    }
}
