package com.coderising.ood.atmSimulation.atm.slot;

/**
 * manage atm money
 *
 * @author nvarchar
 *         date 2017/7/15
 */
public class MoneySlot {

    private static long money;

    public static long getMoney() {
        return money;
    }

    public static void setMoney(long amount) {
        MoneySlot.money = amount;
    }

    public static void addMoney(long amount) {
        money += amount;
    }

    public static void minusMoney(long amount) {
        money -= amount;
    }
}
