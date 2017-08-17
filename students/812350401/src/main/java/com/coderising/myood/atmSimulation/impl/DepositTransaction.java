package com.coderising.myood.atmSimulation.impl;

import com.coderising.myood.atmSimulation.model.Transaction;

/**
 * Created by thomas_young on 30/7/2017.
 */
public class DepositTransaction extends Transaction {
    // 该取款交易的存款金额
    private Double amount;
    private Double actualAmount;

    public DepositTransaction(String account, String password, Double amount) {
        super(account, password);
        this.amount = amount;
    }

    @Override
    public boolean preProcess(ATM atm) {
        actualAmount = atm.retrieveMoney(amount);
        System.out.println("实际存款"+actualAmount+"元");
        return true;
    }

    /**
     * 什么都不做
     * @param atm
     * @return
     */
    @Override
    public boolean postProcess(ATM atm) {
        // TODO: 30/7/2017 只是为了调试，建议删除
        System.out.println("存款postProcess: 什么都不做");
        return true;
    }

    @Override
    public String toNetworkPackage() {
        return null;
    }

    @Override
    public String toString() {
        return super.toString() + " DepositTransaction{" +
                "amount=" + amount + ", actualAmount=" + actualAmount +
                '}';
    }

    public Double getAmount() {
        return amount;
    }

    public Double getActualAmount() {
        return actualAmount;
    }
}
