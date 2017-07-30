package com.coderising.myood.atmSimulation.model;

import com.coderising.myood.atmSimulation.impl.*;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by thomas_young on 30/7/2017.
 */
public class TransactionTest {
    private static Transaction withdrawTrx;
    private static Transaction noMoneyWithdrawTrx;
    private static Transaction depositTrx;
    private static ATM atm;

    static {
        atm = new ATM();
        withdrawTrx = new WithdrawTransaction("withdraw", "1023", 100d);
        noMoneyWithdrawTrx = new WithdrawTransaction("withdraw", "1023", 10001d);
        depositTrx = new DepositTransaction("deposit", "456", 200d);
        CardReader cardReader = new CardReaderImpl();
        SuperKeyPad superKeyPad = new SuperKeyPad();
        CashDispenser cashDispenser = new CashDispenserImpl();
        DepositSlot depositSlot = new DepositSlotImpl();
        Printer printer;
        BankProxy bankProxy = new BankProxyImpl();
        atm.setBankProxy(bankProxy);
        atm.setCardReader(cardReader);
        atm.setCashDispenser(cashDispenser);
        atm.setDepositSlot(depositSlot);
        atm.setSuperKeyPad(superKeyPad);
    }

    @Test
    public void preProcess() throws Exception {
        assertTrue(withdrawTrx.preProcess(atm));
        assertFalse(noMoneyWithdrawTrx.preProcess(atm));
        depositTrx.preProcess(atm);
        assertEquals((Double)(((DepositTransaction)depositTrx).getAmount()-5d), ((DepositTransaction)depositTrx).getActualAmount());
    }

    @Test
    public void postProcess() throws Exception {
        assertTrue(withdrawTrx.postProcess(atm));
        depositTrx.postProcess(atm);
    }

    @Test
    public void toNetworkPackage() throws Exception {

    }

}