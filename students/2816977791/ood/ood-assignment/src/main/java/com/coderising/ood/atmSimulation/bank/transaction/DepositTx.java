package com.coderising.ood.atmSimulation.bank.transaction;

import com.coderising.ood.atmSimulation.bank.Bank;
import com.coderising.ood.atmSimulation.bank.account.Account;

/**
 * @author nvarchar
 *         date 2017/7/14
 */
public class DepositTx implements Trasaction {

    private String account;
    private String password;
    private int amount;

    public DepositTx(String account, String password, int amount) {
        this.account = account;
        this.password = password;
        this.amount = amount;
    }

    @Override
    public boolean preProcess(Bank bank) {
        return true;
    }

    @Override
    public boolean postProcess(Bank bank) {
        return true;
    }

    @Override
    public String process(Bank bank) {
        Account queryAccount = bank.getAccount(account, password);
        queryAccount.retriveMoney(amount);
        return String.valueOf(queryAccount.getMoney());
    }
}
