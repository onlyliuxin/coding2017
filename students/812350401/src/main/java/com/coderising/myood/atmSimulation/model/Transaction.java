package com.coderising.myood.atmSimulation.model;

import com.coderising.myood.atmSimulation.impl.ATM;

/**
 * Created by thomas_young on 30/7/2017.
 * 该对象并不持有ATM的实例，它只是依赖ATM罢了
 */
public abstract class Transaction {
    private String account;
    private String password;

    public Transaction(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public abstract boolean preProcess(ATM atm);
    public abstract boolean postProcess(ATM atm);
    public abstract String toNetworkPackage();

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
