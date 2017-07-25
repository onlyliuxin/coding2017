package com.coderising.ood.atmSimulation.atm.proxy;

import com.coderising.ood.atmSimulation.atm.Atm;
import com.coderising.ood.atmSimulation.atm.transaction.Trasaction;
import com.coderising.ood.atmSimulation.serialization.JsonConvert;
import com.coderising.ood.atmSimulation.serialization.NetPackage;

/**
 * @author nvarchar
 *         date 2017/7/14
 */
public class BankProxy {
    private Network network;

    public BankProxy(Network network) {
        this.network = network;
    }

    public String process(Trasaction tx, Atm atm) {
        String response = network.sendData(tx.toNetWorkPackage(atm));
        return response;
    }

    public boolean verify(String account, String password) {
        NetPackage netPackage = new NetPackage(account, password);
        String response = network.sendData(JsonConvert.encode(netPackage));
        return "true".equals(response);
    }
}
