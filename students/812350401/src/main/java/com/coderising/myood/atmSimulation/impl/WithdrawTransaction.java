package com.coderising.myood.atmSimulation.impl;

import com.coderising.myood.atmSimulation.model.Transaction;

/**
 * Created by thomas_young on 30/7/2017.
 * 取款交易
 */
public class WithdrawTransaction extends Transaction {
    // 该取款交易的取款金额
    private Double amount;

    public WithdrawTransaction(String account, String password, Double amount) {
        super(account, password);
        this.amount = amount;
    }

    /**
     * 判断atm里的钱是否足够
     * @param atm
     * @return
     */
    @Override
    public boolean preProcess(ATM atm) {
        return atm.hasEnoughMoney(amount);
    }

    @Override
    public boolean postProcess(ATM atm) {
        atm.dispenseMoney(amount);
        return true;
    }

    @Override
    public String toNetworkPackage() {
        return null;
    }

    public Double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return super.toString() + " WithdrawTransaction{" +
                "amount=" + amount +
                '}';
    }
}
