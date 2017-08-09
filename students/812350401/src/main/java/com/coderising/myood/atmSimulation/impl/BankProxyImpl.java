package com.coderising.myood.atmSimulation.impl;

import com.coderising.myood.atmSimulation.model.BankProxy;
import com.coderising.myood.atmSimulation.model.Transaction;

/**
 * Created by thomas_young on 30/7/2017.
 */
public class BankProxyImpl extends BankProxy {

    @Override
    public boolean verify(String account, String password) {
        return networkClient.verify(account, password);
    }

    @Override
    public boolean process(Transaction transaction) {
        System.out.println("开始把transaction发给银行做处理");
        System.out.println(transaction.getClass());
        System.out.println(transaction);
        return true;
    }
}
