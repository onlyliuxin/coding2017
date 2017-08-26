package com.coderising.dp.week3.command;

public abstract class Command {

    private Cook cook;

    protected Command(Cook cook) {
        this.cook = cook;
    }

    public Cook getCook() {
        return cook;
    }

    protected abstract void cookFood();
}
