package com.coderising.ood.atmSimulation.atm.transaction;

import com.coderising.ood.atmSimulation.atm.Atm;
import com.coderising.ood.atmSimulation.serialization.JsonConvert;
import com.coderising.ood.atmSimulation.serialization.NetPackage;

/**
 * @author nvarchar
 *         date 2017/7/14
 */
public class WithdrawTx implements Trasaction {
    private int amount;

    public WithdrawTx(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toPrint() {
        return "取款" + amount;
    }

    @Override
    public String toNetWorkPackage(Atm atm) {
        NetPackage nt = new NetPackage(NetPackage.Type.WITHDRAW, atm, amount, null);
        return JsonConvert.encode(nt);
    }

    @Override
    public boolean preProcess(Atm atm) {
        return atm.hasEnoughMoney(getAmount());
    }

    @Override
    public boolean postProcess(Atm atm) {
        return atm.dipenseMoney(getAmount());
    }
}
