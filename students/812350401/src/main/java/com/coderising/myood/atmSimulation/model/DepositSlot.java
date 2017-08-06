package com.coderising.myood.atmSimulation.model;

/**
 * Created by thomas_young on 30/7/2017.
 * 入钞口/存款口
 */
public interface DepositSlot {
    /**
     * 存款,可能会口手续费，返回实际存款金额
     * @param amount
     * @return
     */
    Double saveMoney(Double amount);
}
