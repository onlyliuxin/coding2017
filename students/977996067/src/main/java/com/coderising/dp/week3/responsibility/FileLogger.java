package com.coderising.dp.week3.responsibility;

public class FileLogger extends AbstractLogger {

    private int level;

    public FileLogger(int level) {
        this.level = level;
    }

    @Override
    protected int getLevel() {
        return level;
    }

    @Override
    protected void doMessage(String message) {
        System.out.println("log to file : " + message);
    }
}
