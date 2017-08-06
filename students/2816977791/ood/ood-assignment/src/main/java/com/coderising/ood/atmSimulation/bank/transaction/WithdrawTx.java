package com.coderising.ood.atmSimulation.bank.transaction;

import com.coderising.ood.atmSimulation.bank.Bank;
import com.coderising.ood.atmSimulation.bank.account.Account;

/**
 * @author nvarchar
 *         date 2017/7/14
 */
public class WithdrawTx implements Trasaction{

    private String account;
    private String password;
    private int amount;

    public WithdrawTx(String account, String password, int amount) {
        this.account = account;
        this.password = password;
        this.amount = amount;
    }

    @Override
    public boolean preProcess(Bank bank) {
        Account queryAccount = bank.getAccount(account, password);
        return queryAccount.hasEnoughMoney(amount);
    }

    @Override
    public boolean postProcess(Bank bank) {
        return true;
    }

    @Override
    public String process(Bank bank) {
        Account queryAccount = bank.getAccount(account, password);
        queryAccount.dipenseMoney(amount);
        return String.valueOf(queryAccount.getMoney());
    }
}
