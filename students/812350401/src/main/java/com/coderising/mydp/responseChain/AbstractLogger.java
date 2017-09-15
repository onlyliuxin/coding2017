package com.coderising.mydp.responseChain;

/**
 * Created by thomas_young on 11/8/2017.
 */
public abstract class AbstractLogger {
    public static final String DEBUG = "DEBUG";
    public static final String NOTICE = "NOTICE";
    public static final String ERR = "ERR";

    /**
     * 持有下一个处理请求的对象
     */
    private AbstractLogger next = null;

    public AbstractLogger getNext() {
        return next;
    }

    public AbstractLogger setNext(AbstractLogger next) {
        this.next = next;
        return this;
    }

    public abstract void message(String message, String type);
}
