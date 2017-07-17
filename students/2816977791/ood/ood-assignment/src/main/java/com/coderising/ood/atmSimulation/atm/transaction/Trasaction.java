package com.coderising.ood.atmSimulation.atm.transaction;

import com.coderising.ood.atmSimulation.atm.Atm;

/**
 * @author nvarchar
 *         date 2017/7/14
 */
public interface Trasaction {
    public String toNetWorkPackage(Atm atm);

    public boolean preProcess(Atm atm);

    public boolean postProcess(Atm atm);

    public String toPrint();
}
