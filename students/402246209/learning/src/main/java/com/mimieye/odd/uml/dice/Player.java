package com.mimieye.odd.uml.dice;

import org.apache.commons.lang3.RandomUtils;

/**
 * Created by Pierreluo on 2017/6/27.
 */
public class Player {
    private Dice[] dices;
    private DiceGame diceGame;

    public Player(int diceSize, int diceCapacity, int winValue) {
        init(diceSize, diceCapacity, winValue);
    }

    private void init(int diceSize, int diceCapacity, int winValue) {
        dices = new Dice[diceSize];
        for(int i = 0; i < diceSize; i++) {
            dices[i] = new Dice(diceCapacity);
        }
        diceGame = new DiceGame(winValue);
    }

    public boolean play() {
        for(Dice dice : dices) {
            dice.setCurrentValueIndex(RandomUtils.nextInt(0,6));
        }
        return diceGame.result(dices);
    }

    public static void main(String[] args) {
        Player player = new Player(2, 6, 7);
        System.out.println(player.play());
        System.out.println(player.play());
        System.out.println(player.play());
        System.out.println(player.play());
        System.out.println(player.play());
        System.out.println(player.play());
        System.out.println(player.play());
        System.out.println(player.play());
        System.out.println(player.play());
        System.out.println(player.play());
        System.out.println(player.play());
        System.out.println(player.play());
    }

}
