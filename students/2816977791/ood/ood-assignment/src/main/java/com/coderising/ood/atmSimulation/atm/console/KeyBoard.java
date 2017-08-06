package com.coderising.ood.atmSimulation.atm.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author nvarchar
 *         date 2017/7/14
 */
public class KeyBoard {

    public String input() {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String input = "";

        try {
            input = in.readLine();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return input;
    }

}