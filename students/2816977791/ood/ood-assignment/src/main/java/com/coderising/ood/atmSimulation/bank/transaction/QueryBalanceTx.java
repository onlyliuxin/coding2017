package com.coderising.ood.atmSimulation.bank.transaction;

import com.coderising.ood.atmSimulation.bank.Bank;
import com.coderising.ood.atmSimulation.bank.account.Account;

/**
 * @author nvarchar
 *         date 2017/7/14
 */
public class QueryBalanceTx implements Trasaction {

    private String account;
    private String password;

    public QueryBalanceTx(String account, String password) {
        this.account = account;
        this.password = password;
    }

    @Override
    public boolean preProcess(Bank bank) {
        return true;
    }

    @Override
    public boolean postProcess(Bank bank) {
        return false;
    }

    @Override
    public String process(Bank bank) {
        Account queryAccount = bank.getAccount(account, password);
        if (account != null) {
            return String.valueOf(queryAccount.getMoney());
        }
        return null;
    }
}
