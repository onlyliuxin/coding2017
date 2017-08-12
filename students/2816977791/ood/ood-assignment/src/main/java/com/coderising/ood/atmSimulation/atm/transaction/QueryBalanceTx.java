package com.coderising.ood.atmSimulation.atm.transaction;

import com.coderising.ood.atmSimulation.atm.Atm;
import com.coderising.ood.atmSimulation.serialization.JsonConvert;
import com.coderising.ood.atmSimulation.serialization.NetPackage;

/**
 * @author nvarchar
 *         date 2017/7/14
 */
public class QueryBalanceTx implements Trasaction {

    private int amount;

    public QueryBalanceTx() {
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toPrint() {
        return "查询账户,余额为:" + amount;
    }

    @Override
    public String toNetWorkPackage(Atm atm) {
        NetPackage nt = new NetPackage(NetPackage.Type.QUERY, atm, null, null);
        return JsonConvert.encode(nt);
    }

    @Override
    public boolean preProcess(Atm atm) {
        return true;
    }

    @Override
    public boolean postProcess(Atm atm) {
        return true;
    }
}
