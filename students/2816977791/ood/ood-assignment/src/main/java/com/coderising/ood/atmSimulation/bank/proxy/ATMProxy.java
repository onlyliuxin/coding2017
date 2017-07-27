package com.coderising.ood.atmSimulation.bank.proxy;

import com.coderising.ood.atmSimulation.bank.Bank;
import com.coderising.ood.atmSimulation.serialization.JsonConvert;
import com.coderising.ood.atmSimulation.serialization.NetPackage;

/**
 * @author nvarchar
 *         date 2017/7/14
 */
public class ATMProxy {
    private Bank bank;

    public ATMProxy(Bank bank) {
        this.bank = bank;
    }

    public String getData(String data) {
        NetPackage np = JsonConvert.decode(data);
        switch (np.getType()) {
            case QUERY:
                return bank.query(np.getAccount(), np.getPassword());
            case Verify:
                return String.valueOf(bank.verify(np.getAccount(), np.getPassword()));
            case DEPOSIT:
                return bank.deposit(np.getAccount(), np.getPassword(), np.getAmount());
            case TRANSFER:
                return bank.transfer(np.getAccount(), np.getPassword(), np.getToCard(), np.getAmount());
            case WITHDRAW:
                return bank.withDraw(np.getAccount(), np.getPassword(), np.getAmount());
        }
        return "no such method";
    }
}
