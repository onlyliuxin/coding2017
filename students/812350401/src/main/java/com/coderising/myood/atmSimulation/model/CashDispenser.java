package com.coderising.myood.atmSimulation.model;


/**
 * Created by thomas_young on 30/7/2017.
 * 吐钞口/取款口
 * 判断金额是否足够(我们用它来负责管理atm的金额，不再专门搞一个储钱箱了)
 */
public interface CashDispenser {
    /**
     * 取款amount,判断余额是否足够
     * @param amount
     * @return
     */
    boolean hasEnoughMoney(Double amount);

    /**
     * 吐出amount的钞票
     * @param amount
     */
    void dispenseMoney(Double amount);
}
