package com.coderising.ood.atmSimulation.atm.proxy;

import com.coderising.ood.atmSimulation.bank.proxy.ATMProxy;

/**
 * @author nvarchar
 *         date 2017/7/14
 */
public class Network {

    private ATMProxy atmProxy;

    public Network(ATMProxy atmProxy) {
        this.atmProxy = atmProxy;
    }

    public String sendData(String data) {
        return atmProxy.getData(data);
    }
}
