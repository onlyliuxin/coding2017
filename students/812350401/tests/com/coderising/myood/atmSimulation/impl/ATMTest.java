package com.coderising.myood.atmSimulation.impl;

import com.coderising.myood.atmSimulation.model.*;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by thomas_young on 30/7/2017.
 */
public class ATMTest {
    private static ATM atm;

    static {
        atm = new ATM();
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
    public void start() throws Exception {
        atm.start();
    }

}