package com.coderising.ood.atmSimulation.atm.transaction;

import com.coderising.ood.atmSimulation.atm.Atm;
import com.coderising.ood.atmSimulation.serialization.JsonConvert;
import com.coderising.ood.atmSimulation.serialization.NetPackage;

/**
 * @author nvarchar
 *         date 2017/7/14
 */
public class TransferTx implements Trasaction {
    private String toCard;
    private int amount;

    public TransferTx(String toCard, int amount) {
        this.toCard = toCard;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toPrint() {
        return "转账" + amount + "元到" + toCard;
    }

    @Override
    public String toNetWorkPackage(Atm atm) {
        NetPackage nt = new NetPackage(NetPackage.Type.TRANSFER, atm, amount, toCard);
        return JsonConvert.encode(nt);
    }

    @Override
    public boolean preProcess(Atm atm) {
        return atm.hasEnoughMoney(getAmount());
    }

    @Override
    public boolean postProcess(Atm atm) {
        return true;
    }
}
