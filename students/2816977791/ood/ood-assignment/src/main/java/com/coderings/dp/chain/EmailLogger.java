package com.coderings.dp.chain;

/**
 * @author nvarchar
 *         date 2017/8/11
 */
public class EmailLogger implements Logger{
    private int level;
    private Logger next;

    public EmailLogger(int level) {
        this.level = level;
    }

    @Override
    public void message(String context, int level) {
        if (level >= this.level) {
            System.out.println("email :" + context);
        }
        if (this.next != null) {
            this.next.message(context, level);
        }
    }

    @Override
    public Logger setNext(Logger next) {
        this.next = next;
        return this;
    }
}
