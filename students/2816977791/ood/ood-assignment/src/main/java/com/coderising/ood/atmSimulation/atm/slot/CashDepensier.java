package com.coderising.ood.atmSimulation.atm.slot;

/**
 * @author nvarchar
 *         date 2017/7/14
 */
public class CashDepensier {
    public boolean hasEnoughMoney(int amount) {
        return MoneySlot.getMoney() - amount >= 0;
    }

    public boolean dispenseMoney(int amount) {
        MoneySlot.minusMoney(amount);
        return true;
    }
}
