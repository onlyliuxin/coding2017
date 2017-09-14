package com.coderising.atm;

import java.util.ArrayList;
import java.util.List;

import com.coderising.atm.transactions.Transaction;
import com.coderising.atm.transactions.TransactionBase;

public class ATM {
	CardReader cardReader;
	CashDepensier cashDepensier;
	DepositSlot depositSlot;
	Printer printer;
	SuperKeypad superKeypad;
	BankProxy bankProxy;

	public ATM(CardReader cardReader, CashDepensier cashDepensier, DepositSlot depositSlot, Printer printer,
			SuperKeypad superKeypad, BankProxy bankProxy) {
		this.cardReader = cardReader;
		this.cashDepensier = cashDepensier;
		this.depositSlot = depositSlot;
		this.printer = printer;
		this.superKeypad = superKeypad;
		this.bankProxy = bankProxy;
	}

	public boolean hashEnoughMoney(int amount) {
		// TODO Auto-generated method stub
		return cashDepensier.hashEnoughMoney(amount);
	}

	public boolean dispenseMoney(int amount) {
		// TODO Auto-generated method stub
		return cashDepensier.dispenseMoney(amount);
	}

	public int retriveMoney() {
		// TODO Auto-generated method stub
		return depositSlot.retriveMoney();
	}

	public SuperKeypad getSuperKeypad() {
		return this.superKeypad;
	}

	public static void main(String[] args) {
		ATM atm = null;
		try {

			atm = new ATM(new CardReader(), new CashDepensier(), new DepositSlot(), new Printer(),
					new SuperKeypad(new Display(), new KeyBoard()), new BankProxy());
			int account = atm.cardReader.getAccount();
			atm.getSuperKeypad().displayMessage("??????ATM");
			Thread.sleep(2000);
			// ??????
			atm.getSuperKeypad().displayMessage("?????" + account);

			String type = "";
			int password = 0;
			List<TransactionBase> transactions = new ArrayList<>();
			TransactionBase tx = null;
			while (true) {
				type = atm.superKeypad.getTransactionType();
				if (type.equals("D") || type.equals("W") || type.equals("T") || type.equals("B")) {
					password = verifyPassword(atm, account);
				}
				tx = atm.superKeypad.getTransaction(account, password, type);
				if (tx.getType().equals("P")) {
					atm.printer.print(transactions);

					break;
				} else if (tx.getType().equals("E")) {

					break;
				} else if (tx != null) {

					if (!tx.preProcess(atm)) {
						Thread.sleep(2000);
						continue;
					}
					if (!atm.bankProxy.process(tx)) {
						Thread.sleep(2000);
						continue;
					}
					if (!tx.postProcess(atm)) {
						Thread.sleep(2000);
						continue;
					}
					// atm.getSuperKeypad().displayMessage("????????");
					transactions.add(tx);
				}
			}

		} catch (Exception ex) {
			atm.superKeypad.displayMessage(ex.getMessage());

		} finally {
			atm.cardReader.ejectCard();
		}
	}

	private static int verifyPassword(ATM atm, int account) {
		int password = 0;
		int failCount = 0;
		boolean verified = false;
		atm.getSuperKeypad().displayMessage("??????????????");
		while (!verified) {
			password = atm.superKeypad.getPassword();
			verified = atm.bankProxy.verify(account, password);
			if (!verified) {

				failCount++;
				if (failCount >= 3) {
					break;
				}

				atm.getSuperKeypad().displayMessage("?????????????????" + (3 - failCount) + "?¦Ë???");
			}
		}
		if (!verified) {
			atm.bankProxy.FreezeAccount(account);
			throw new RuntimeException("????????????3??,?????????????");
		}
		return password;
	}

}
