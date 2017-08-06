package com.coderising.myood.atmSimulation.impl;

import com.coderising.myood.atmSimulation.model.Transaction;

/**
 * Created by thomas_young on 30/7/2017.
 */
public class TransferTransaction extends Transaction {
    // 转账金额
    private Double amount;
    // 对方账户
    private String counterAccount;

    public TransferTransaction(String account, String password, Double amount, String counterAccount) {
        super(account, password);
        this.amount = amount;
        this.counterAccount = counterAccount;
    }

    @Override
    public boolean preProcess(ATM atm) {
        System.out.println("转账: 不做前处理");
        return true;
    }

    @Override
    public boolean postProcess(ATM atm) {
        System.out.println("转账: 不做后处理");
        return true;
    }

    @Override
    public String toNetworkPackage() {
        return null;
    }

    @Override
    public String toString() {
        return super.toString() + " TransferTransaction{" +
                "amount=" + amount +
                ", counterAccount='" + counterAccount + '\'' +
                '}';
    }
}
