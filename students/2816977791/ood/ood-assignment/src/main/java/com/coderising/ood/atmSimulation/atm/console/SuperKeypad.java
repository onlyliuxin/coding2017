package com.coderising.ood.atmSimulation.atm.console;

import com.coderising.ood.atmSimulation.atm.transaction.Trasaction;

/**
 * @author nvarchar
 *         date 2017/7/14
 */
public class SuperKeypad {
    private Display display;
    private KeyBoard keyBoard;

    public SuperKeypad() {
    }

    public SuperKeypad(Display display, KeyBoard keyBoard) {
        this.display = display;
        this.keyBoard = keyBoard;
    }

    public void display(String message) {
        display.showMessage(message);
    }

    public String inputPassword() {
        display("input your password: ");
        String password = keyBoard.input();
        display(password.replaceAll("(?s).", "*"));
        return password;
    }

    public String inputCardNumber() {
        display("input your transfer card number");
        return keyBoard.input();
    }

    public int inputAmount() {
        display("input your amount");
        return Integer.valueOf(keyBoard.input());
    }

    public Trasaction getTrasaction() {
        return null;
    }
}
