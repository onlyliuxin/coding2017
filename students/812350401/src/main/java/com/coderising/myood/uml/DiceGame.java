package com.coderising.myood.uml;

/**
 * Created by thomas_young on 27/6/2017.
 */
public class DiceGame {
    private Player player1, player2;
    private Dice dice1, dice2;
    private static int WIN_POINT = 7;

    public void start() {
        assert player1 != null;
        assert player2 != null;
        assert dice1 != null;
        assert dice2 != null;
        int player1Point;
        int player2Point;
        do {
            player1Point = player1.roll(dice1, dice2);
            player2Point = player2.roll(dice1, dice2);
            System.out.print(player1 + " roll " + player1Point + ", ");
            System.out.println(player2 + " roll " + player2Point + ".");
            if (player1Point == player2Point) {
                continue;
            }
            if (player1Point == WIN_POINT) {
                System.out.println(player1 + " win!");
                break;
            }
            if (player2Point == WIN_POINT) {
                System.out.println(player2 + " win!");
                break;
            }
        } while (true);
    }

    public DiceGame(Player aPlayer1, Player aPlayer2, Dice aDice1, Dice aDice2) {
        player1 = aPlayer1;
        player2 = aPlayer2;
        dice1 = aDice1;
        dice2 = aDice2;
    }

}
