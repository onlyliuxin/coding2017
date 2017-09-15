package com.coderising.dp.week3.responsibility;

public class StdoutLogger extends AbstractLogger {

    private int level;

    public StdoutLogger(int level) {
        this.level = level;
    }

    @Override
    protected int getLevel() {
        return level;
    }

    @Override
    protected void doMessage(String message) {
        System.out.println("stdout : " + message);
    }

}
