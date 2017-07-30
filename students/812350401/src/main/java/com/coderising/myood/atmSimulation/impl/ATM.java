package com.coderising.myood.atmSimulation.impl;

import com.coderising.myood.atmSimulation.model.*;

/**
 * Created by thomas_young on 30/7/2017.
 * ATM对象
 */
public class ATM {
    private CardReader cardReader;
    private SuperKeyPad superKeyPad;
    private CashDispenser cashDispenser;
    private DepositSlot depositSlot;
    private Printer printer;
    private BankProxy bankProxy;
    /**
     * 判断取款金额
     * @param amount
     * @return
     */
    public boolean hasEnoughMoney(Double amount) {
        return cashDispenser.hasEnoughMoney(amount);
    }

    /**
     * 取款
     * @param amount
     */
    public void dispenseMoney(Double amount) {
        cashDispenser.dispenseMoney(amount);
    }

    /**
     * 存款
     * @return
     */
    public Double retrieveMoney(Double amount) {
        return depositSlot.saveMoney(amount);
    }

    /**
     * 主流程
     */
    public void start() {
        // step1. 读卡
        String account;
        while(true) {
            account = cardReader.getAccount();
            if(account != null) {
                System.out.println("读卡成功，卡号是"+account);
                break;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }

        // step2. 读取密码，并做校验
        int failedCount = 0;
        boolean verified = false;
        String password = null;
        while (failedCount < 3) {
            password = superKeyPad.getPassword();
            verified = bankProxy.verify(account, password);
            if (verified) {
                break;
            }
            failedCount++;
        }
        if (!verified) {
            System.out.println("输错密码超过3次");
            cardReader.eatCard();
            return;
        }

        // step3. 输入交易类型，进行交易
        Transaction transaction = superKeyPad.getTransaction(account, password);
        boolean valid = transaction.preProcess(this);
        if (!valid) {
            cardReader.ejectCard();
            return;
        }
        boolean success = bankProxy.process(transaction);
        if (!success) {
            System.out.printf("银行处理出错");
        } else {
            System.out.println("银行已经处理完成");
            transaction.postProcess(this);
        }

        // last step. 最后记得把卡吐出
        cardReader.ejectCard();
    }

    public CardReader getCardReader() {
        return cardReader;
    }

    public void setCardReader(CardReader cardReader) {
        this.cardReader = cardReader;
    }

    public SuperKeyPad getSuperKeyPad() {
        return superKeyPad;
    }

    public void setSuperKeyPad(SuperKeyPad superKeyPad) {
        this.superKeyPad = superKeyPad;
    }

    public CashDispenser getCashDispenser() {
        return cashDispenser;
    }

    public void setCashDispenser(CashDispenser cashDispenser) {
        this.cashDispenser = cashDispenser;
    }

    public DepositSlot getDepositSlot() {
        return depositSlot;
    }

    public void setDepositSlot(DepositSlot depositSlot) {
        this.depositSlot = depositSlot;
    }

    public Printer getPrinter() {
        return printer;
    }

    public void setPrinter(Printer printer) {
        this.printer = printer;
    }

    public void setBankProxy(BankProxy bankProxy) {
        this.bankProxy = bankProxy;
    }
}
