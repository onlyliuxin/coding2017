package com.coderising.ood.atmSimulation.bank.account;

/**
 * @author nvarchar
 *         date 2017/7/14
 */
public class Account {
    private String cardNumber;
    private int money;
    private String password;

    public Account() {
    }

    public Account(String cardNumber, String password, int money) {
        this.cardNumber = cardNumber;
        this.money = money;
        this.password = password;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public int getMoney() {
        return money;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean hasEnoughMoney(int amount) {
        return money >= amount;
    }

    public boolean dipenseMoney(int amount) {
        money -= amount;
        return true;
    }

    public void retriveMoney(int amount) {
        money += amount;
    }
}
