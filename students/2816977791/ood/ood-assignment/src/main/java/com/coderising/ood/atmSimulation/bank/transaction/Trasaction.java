package com.coderising.ood.atmSimulation.bank.transaction;

import com.coderising.ood.atmSimulation.bank.Bank;

/**
 * @author nvarchar
 *         date 2017/7/14
 */
public interface Trasaction {

    public boolean preProcess(Bank bank);

    public boolean postProcess(Bank bank);

    public String process(Bank bank);
}
