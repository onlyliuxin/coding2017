package com.coderising.myood.atmSimulation.model;

import com.coderising.myood.atmSimulation.impl.NetworkClient;

/**
 * Created by thomas_young on 30/7/2017.
 */
public abstract class BankProxy {
    protected NetworkClient networkClient;

    public abstract boolean verify(String account, String password);

    /**
     * 交易发送到银行做处理
     * @param transaction
     */
    public abstract boolean process(Transaction transaction);

    public void setNetworkClient(NetworkClient networkClient) {
        this.networkClient = networkClient;
    }
}
