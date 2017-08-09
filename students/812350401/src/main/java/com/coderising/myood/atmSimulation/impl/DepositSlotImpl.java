package com.coderising.myood.atmSimulation.impl;

import com.coderising.myood.atmSimulation.model.DepositSlot;

/**
 * Created by thomas_young on 30/7/2017.
 */
public class DepositSlotImpl implements DepositSlot {
    @Override
    public Double saveMoney(Double amount) {
        System.out.println("存款: 请放入钞票"+amount+"元");
        // TODO: 30/7/2017 magic number!
        Double actualAmount = amount - 5d;  // 5元手续费
        return actualAmount < 0d ? 0d:actualAmount;
    }
}
