package com.coderising.ood.atmSimulation.atm.transaction;

import com.coderising.ood.atmSimulation.atm.Atm;
import com.coderising.ood.atmSimulation.serialization.JsonConvert;
import com.coderising.ood.atmSimulation.serialization.NetPackage;

/**
 * @author nvarchar
 *         date 2017/7/14
 */
public class DepositTx implements Trasaction {

    private int amount;

    public int getAmount() {
        return amount;
    }

    public DepositTx(int amount) {
        this.amount = amount;
    }

    @Override
    public String toPrint() {
        return "存款" + amount;
    }

    @Override
    public String toNetWorkPackage(Atm atm) {
        NetPackage nt = new NetPackage(NetPackage.Type.DEPOSIT, atm, amount, null);
        return JsonConvert.encode(nt);
    }

    @Override
    public boolean preProcess(Atm atm) {
        return true;
    }

    @Override
    public boolean postProcess(Atm atm) {
        return false;
    }
}
