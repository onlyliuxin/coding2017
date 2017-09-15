package com.coderising.myood.atmSimulation.impl;

import com.coderising.myood.atmSimulation.model.CashDispenser;

/**
 * Created by thomas_young on 30/7/2017.
 * 吐钞口/取款口
 */
public class CashDispenserImpl implements CashDispenser {
    // 方便起见，假设atm机子里有这么多前，而且不会变 todo magic number！
    private static Double totalAmount = 10000d;
    @Override
    public boolean hasEnoughMoney(Double amount) {
        if (amount <= totalAmount)
            return true;
        System.out.println("atm没钱啦");
        return false;
    }

    @Override
    public void dispenseMoney(Double amount) {
        System.out.println("取款：吐出"+amount+"元");
    }
}
