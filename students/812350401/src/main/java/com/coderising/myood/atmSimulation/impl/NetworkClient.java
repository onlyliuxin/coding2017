package com.coderising.myood.atmSimulation.impl;

/**
 * Created by thomas_young on 30/7/2017.
 */
public class NetworkClient {
    private static final String actualPwd = "123456";
    
    public boolean verify(String account, String password) {
        // TODO: 30/7/2017 暂时写死，以后可以考虑加入银行那边类的交互
        if (account!=null && actualPwd.equals(password)) {
            return true;
        }
        return false;
    }
}
