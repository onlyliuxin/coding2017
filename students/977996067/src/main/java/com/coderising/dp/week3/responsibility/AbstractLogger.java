package com.coderising.dp.week3.responsibility;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class AbstractLogger implements Logger {

    private List<AbstractLogger> loggers = new CopyOnWriteArrayList<>();

    public AbstractLogger setNext(AbstractLogger nextLogger) {
        if (!loggers.contains(this))
            loggers.add(this);
        loggers.add(nextLogger);
        return this;
    }

    @Override
    public void message(String message, int level) {
        loggers
                .stream()
                .filter(logger -> logger.getLevel() <= level)
                .forEach(logger -> logger.doMessage(message));
    }

    protected abstract int getLevel();

    protected abstract void doMessage(String message);
}
