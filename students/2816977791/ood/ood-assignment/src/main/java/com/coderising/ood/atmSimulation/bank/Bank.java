package com.coderising.ood.atmSimulation.bank;

import com.coderising.ood.atmSimulation.bank.account.Account;
import com.coderising.ood.atmSimulation.bank.transaction.DepositTx;
import com.coderising.ood.atmSimulation.bank.transaction.QueryBalanceTx;
import com.coderising.ood.atmSimulation.bank.transaction.TransferTx;
import com.coderising.ood.atmSimulation.bank.transaction.WithdrawTx;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nvarchar
 *         date 2017/7/14
 */
public class Bank {
    private List<Account> accounts = new ArrayList<>();

    public Bank(List<Account> accounts) {
        this.accounts = accounts;
    }

    //查询
    public String query(String cardNumber, String password) {
        QueryBalanceTx tx = new QueryBalanceTx(cardNumber, password);
        if (tx.preProcess(this)) {
            return tx.process(this);
        }
        tx.postProcess(this);
        return null;
    }

    //转账
    public String transfer(String cardNumber, String password,
                           String toCard, int amount) {
        TransferTx tx = new TransferTx(cardNumber, password,
                toCard, amount);

        if (tx.preProcess(this)) {
            return tx.process(this);
        }
        tx.postProcess(this);
        return null;
    }

    //取款
    public String withDraw(String cardNumber, String password,
                              int amount) {
        WithdrawTx tx = new WithdrawTx(cardNumber, password, amount);

        if (tx.preProcess(this)) {
            return tx.process(this);
        }
        tx.postProcess(this);
        return null;
    }

    //存钱
    public String deposit(String cardNumber, String password,
                        int amount) {
        DepositTx tx = new DepositTx(cardNumber, password, amount);

        if (tx.preProcess(this)) {
            return tx.process(this);
        }
        tx.postProcess(this);
        return null;
    }

    //验证
    public boolean verify(String account, String password) {
        return isExist(account, password);
    }

    //查询是否
    private boolean isExist(String cardNumber, String password) {
        for (Account account : accounts) {
            if (cardNumber.equals(account.getCardNumber())
                    && password.equals(account.getPassword())) {
                return true;
            }
        }
        return false;
    }

    //获取账户
    public Account getAccount(String cardNumber, String password) {
        for (Account account : accounts) {
            if (cardNumber.equals(account.getCardNumber())
                    && password.equals(account.getPassword())) {
                return account;
            }
        }
        return null;
    }

}
