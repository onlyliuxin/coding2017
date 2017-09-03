package com.coderings.dp.chain;

/**
 * @author nvarchar
 *         date 2017/8/11
 */
public interface Logger {
    public static final int DEBUG = 0;
    public static final int NOTICE = 1;
    public static final int ERR = 2;

    public void message(String context, int level);

    public Logger setNext(Logger next);
}
