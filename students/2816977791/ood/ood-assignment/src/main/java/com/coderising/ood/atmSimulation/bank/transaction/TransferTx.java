package com.coderising.ood.atmSimulation.bank.transaction;

import com.coderising.ood.atmSimulation.bank.Bank;
import com.coderising.ood.atmSimulation.bank.account.Account;

/**
 * @author nvarchar
 *         date 2017/7/14
 */
public class TransferTx implements Trasaction {

    private String account;
    private String password;
    private String toCard;
    private int amount;

    public TransferTx(String account, String password, String toCard, int amount) {
        this.account = account;
        this.password = password;
        this.toCard = toCard;
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
        //mock add money to toCard
        return String.valueOf(queryAccount.getMoney());
    }
}
