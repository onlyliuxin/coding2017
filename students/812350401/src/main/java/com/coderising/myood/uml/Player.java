package com.coderising.myood.uml;

/**
 * Created by thomas_young on 27/6/2017.
 */
public class Player {
    private String name;

    public Player(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public int roll(Dice dice1, Dice dice2) {
        int first = dice1.roll();
        int second = dice2.roll();
        return first + second;
    }
}
