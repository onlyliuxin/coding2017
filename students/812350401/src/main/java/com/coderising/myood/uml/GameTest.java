package com.coderising.myood.uml;

/**
 * Created by thomas_young on 27/6/2017.
 */
public class GameTest {
    public static void main(String[] args) {
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        Dice dice1 = new Dice();
        Dice dice2 = new Dice();
        DiceGame game = new DiceGame(player1, player2, dice1, dice2);
        game.start();
    }
}
