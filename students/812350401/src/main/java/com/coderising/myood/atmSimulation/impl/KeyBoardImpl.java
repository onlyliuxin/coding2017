package com.coderising.myood.atmSimulation.impl;

import com.coderising.myood.atmSimulation.model.KeyBoard;
import com.coderising.myood.atmSimulation.utils.SingletonScanner;

import java.util.Scanner;

/**
 * Created by thomas_young on 30/7/2017.
 */
public class KeyBoardImpl implements KeyBoard {
    @Override
    public String input() {
        Scanner sc = SingletonScanner.getInstance();
        String input = sc.nextLine();
        return input;
    }

    public static void main(String[] args) {
        KeyBoard keyBoard = new KeyBoardImpl();
        String input = keyBoard.input();
        System.out.printf(input);
        input = keyBoard.input();
        System.out.printf(input);

    }
}
