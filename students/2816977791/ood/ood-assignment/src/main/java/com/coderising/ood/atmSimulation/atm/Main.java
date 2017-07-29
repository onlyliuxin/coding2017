package com.coderising.ood.atmSimulation.atm;

import com.coderising.ood.atmSimulation.atm.console.Display;
import com.coderising.ood.atmSimulation.atm.console.KeyBoard;
import com.coderising.ood.atmSimulation.atm.console.SuperKeypad;
import com.coderising.ood.atmSimulation.atm.print.Printer;
import com.coderising.ood.atmSimulation.atm.proxy.BankProxy;
import com.coderising.ood.atmSimulation.atm.proxy.Network;
import com.coderising.ood.atmSimulation.atm.reader.CardReader;
import com.coderising.ood.atmSimulation.atm.slot.CashDepensier;
import com.coderising.ood.atmSimulation.atm.slot.DepositSlot;
import com.coderising.ood.atmSimulation.atm.slot.MoneySlot;
import com.coderising.ood.atmSimulation.bank.Bank;
import com.coderising.ood.atmSimulation.bank.account.Account;
import com.coderising.ood.atmSimulation.bank.proxy.ATMProxy;
import com.coderising.ood.atmSimulation.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author nvarchar
 *         date 2017/7/16
 */
public class Main {

    public static void main(String[] args) {
        //mock bank
        List<Account> accounts = new ArrayList<>();
        Account account = new Account("55005500", "123456", 10000);
        accounts.add(account);
        Bank bank = new Bank(accounts);

        //mock atmproxy
        ATMProxy atmproxy = new ATMProxy(bank);

        //mock network
        Network network = new Network(atmproxy);

        //mock bankProxy
        BankProxy bankProxy = new BankProxy(network);

        //mock atm
        DepositSlot depositSlot = new DepositSlot();
        CashDepensier cashDepensier = new CashDepensier();

        Display display = new Display();
        KeyBoard keyBoard = new KeyBoard();
        SuperKeypad superKeypad = new SuperKeypad(display, keyBoard);
        CardReader cardReader = new CardReader();
        Printer printer = new Printer();
        Atm atm = new Atm(depositSlot, cashDepensier, superKeypad, cardReader, bankProxy, printer);

        MoneySlot.setMoney(1000000);

        //mock card
        Card card = new Card("55005500");


        atm.insertCard(card);
        atm.writePassword();

        atm.showGuide();

        Scanner sca = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            int ch = sca.nextInt();
            switch (ch) {
                case 1:
                    atm.query();
                    break;
                case 2:
                    atm.deposit();
                    break;
                case 3:
                    atm.dependesier();
                    break;
                case 4:
                    atm.transfer();
                    break;
                case 5:
                    atm.ejectCard(card);
                    exit = true;
                    break;
                default:
                    exit = true;
                    break;
            }
        }

        atm.print();

    }
}
