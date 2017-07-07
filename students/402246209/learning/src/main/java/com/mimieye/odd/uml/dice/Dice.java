package com.mimieye.odd.uml.dice;

/**
 * Created by Pierreluo on 2017/6/27.
 */
public class Dice {
    private int[] values;
    private int currentValueIndex;
    private int capacity;

    public Dice(int capacity) {
        this.capacity = capacity;
        init();
    }

    private void init() {
        this.values = new int[capacity];
        int i = 0;
        int judge = capacity - 1;
        while(true) {
            values[i] = i;
            if(i == judge) {
                break;
            }
            i++;
        }
        this.currentValueIndex = 0;
    }

    public int getCurrentValue() {
        return values[currentValueIndex];
    }

    public void setCurrentValueIndex(int currentValueIndex) {
        this.currentValueIndex = currentValueIndex;
    }
}
