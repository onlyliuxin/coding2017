package com.coderising.ood.atmSimulation.atm;

import com.coderising.ood.atmSimulation.atm.console.SuperKeypad;
import com.coderising.ood.atmSimulation.atm.print.Printer;
import com.coderising.ood.atmSimulation.atm.proxy.BankProxy;
import com.coderising.ood.atmSimulation.atm.reader.CardReader;
import com.coderising.ood.atmSimulation.atm.slot.CashDepensier;
import com.coderising.ood.atmSimulation.atm.slot.DepositSlot;
import com.coderising.ood.atmSimulation.atm.transaction.*;
import com.coderising.ood.atmSimulation.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author nvarchar
 *         date 2017/7/14
 */
public class Atm {
    //存钱口
    private DepositSlot depositSlot;
    //取钱口
    private CashDepensier cashDepensier;
    //超级键盘
    private SuperKeypad superKeypad;
    //读卡器
    private CardReader cardReader;
    //bankProxy
    private BankProxy bankProxy;
    //打印器
    private Printer printer;

    public Atm(DepositSlot depositSlot, CashDepensier cashDepensier,
               SuperKeypad superKeypad, CardReader cardReader, BankProxy bankProxy, Printer printer) {
        this.depositSlot = depositSlot;
        this.cashDepensier = cashDepensier;
        this.superKeypad = superKeypad;
        this.cardReader = cardReader;
        this.bankProxy = bankProxy;
        this.printer = printer;
    }

    //账号|密码
    private String account;
    private String password;

    private List<Trasaction> list = new ArrayList<>();

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public boolean hasEnoughMoney(int amount) {
        return cashDepensier.hasEnoughMoney(amount);
    }

    public boolean dipenseMoney(int amount) {
        return cashDepensier.dispenseMoney(amount);
    }

    public int retriveMoney() {
        Random random = new Random();
        int money = random.nextInt(10000);
        return depositSlot.retriveMoney(money);
    }

    //插卡
    public void insertCard(Card card) {
        account = cardReader.readCard(card);
    }

    //输入密码
    public void writePassword() {
        int count = 1;
        while (count <= 3) {
            count++;
            password = superKeypad.inputPassword();
            if (bankProxy.verify(account, password)) {
                return;
            }
        }
        superKeypad.display("吞卡啦，到前台吧~");
        throw new RuntimeException("atm eat card");
    }

    //查询
    public void query() {
        superKeypad.display("查询");
        QueryBalanceTx tx = new QueryBalanceTx();
        if (tx.preProcess(this)) {
            String response = bankProxy.process(tx, this);
            tx.setAmount(Integer.valueOf(response));
            superKeypad.display("查询操作余额为:" + response);
        }
        list.add(tx);
        tx.postProcess(this);
    }

    //转账
    public void transfer() {
        superKeypad.display("转账");
        String toCard = superKeypad.inputCardNumber();
        int amount = superKeypad.inputAmount();
        TransferTx tx = new TransferTx(toCard, amount);
        if (tx.preProcess(this)) {
            String response = bankProxy.process(tx, this);
            superKeypad.display("转账后,余额为:" + response);
        }
        list.add(tx);
        tx.postProcess(this);
    }

    //取款
    public void dependesier() {
        superKeypad.display("取款");
        int amount = superKeypad.inputAmount();
        WithdrawTx tx = new WithdrawTx(amount);
        if (tx.preProcess(this)) {
            String response = bankProxy.process(tx, this);
            superKeypad.display("取款后,余额为:" + response);
        }
        list.add(tx);
        tx.postProcess(this);
    }

    //存钱
    public void deposit() {
        superKeypad.display("存款");
        DepositTx tx = new DepositTx(retriveMoney());
        if (tx.preProcess(this)) {
            String response = bankProxy.process(tx, this);
            superKeypad.display("存钱后，余额为:" + response);
        }
        list.add(tx);
        tx.postProcess(this);
    }

    //打印
    public void print() {
        superKeypad.display("------打印操作-------");
        printer.print(list);
        superKeypad.display("------打印完毕-------");
    }

    //退卡
    public void ejectCard(Card card) {
        cardReader.ejectCard(card);
        superKeypad.display("退卡成功");
    }

    //引导
    public void showGuide() {
        superKeypad.display("请选择操作:");
        superKeypad.display("1:查询");
        superKeypad.display("2:存钱");
        superKeypad.display("3:取款");
        superKeypad.display("4:转账");
        superKeypad.display("5:退卡");
    }

}
