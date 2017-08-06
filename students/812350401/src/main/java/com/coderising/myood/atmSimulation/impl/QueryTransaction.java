package com.coderising.myood.atmSimulation.impl;

import com.coderising.myood.atmSimulation.model.Transaction;

/**
 * Created by thomas_young on 30/7/2017.
 */
public class QueryTransaction extends Transaction {
    public QueryTransaction(String account, String password) {
        super(account, password);
    }

    @Override
    public boolean preProcess(ATM atm) {
        System.out.println("查询：不做前处理");
        return true;
    }

    @Override
    public boolean postProcess(ATM atm) {
        System.out.println("查询：不做后处理");
        return true;
    }

    @Override
    public String toNetworkPackage() {
        return null;
    }
}
