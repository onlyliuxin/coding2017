package com.mimieye.odd.uml.dice;

/**
 * Created by Pierreluo on 2017/6/27.
 */
public class DiceGame {
    private int winValue;

    public DiceGame(int winValue) {
        this.winValue = winValue;
    }

    public boolean result(Dice... dices) {
        int resultValue = 0;
        for(Dice dice : dices) {
            resultValue += dice.getCurrentValue();
        }
        if(resultValue == winValue) {
            return true;
        } else {
            return false;
        }
    }

}
