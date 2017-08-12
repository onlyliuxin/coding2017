package com.coderising.dp.week3.responsibility;

public class EmailLogger extends AbstractLogger {

    private int level;

    public EmailLogger(int level) {
        this.level = level;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    protected void doMessage(String message) {
        System.out.println("email to log : " + message);
    }
}
