package com.coderising.mydp.responseChain;

/**
 * Created by thomas_young on 11/8/2017.
 */
public class EmailLogger extends AbstractLogger {

    @Override
    public void message(String message, String type) {
        System.out.println("EmailLogger处理:" + message);
        if (!AbstractLogger.NOTICE.equals(type)) {
            getNext().message(message, type);
        }
    }
}
