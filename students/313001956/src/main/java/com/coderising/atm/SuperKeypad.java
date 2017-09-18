package com.coderising.atm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JFrame;

import com.coderising.atm.transactions.DepositTx;
import com.coderising.atm.transactions.QueryBalanceTx;
import com.coderising.atm.transactions.Transaction;
import com.coderising.atm.transactions.TransactionBase;
import com.coderising.atm.transactions.TransferTx;
import com.coderising.atm.transactions.WithdrawTx;

public class SuperKeypad extends JFrame {

	Display display;
	KeyBoard keyBoard;
	private boolean whileKey = true;
	private StringBuilder sb = new StringBuilder();

	public SuperKeypad(Display display, KeyBoard keyBoard) {
		this.display = display;
		this.keyBoard = keyBoard;

		// initFrame(keyBoard);
	}

	private void initFrame(KeyBoard keyBoard) {
		this.setSize(0, 0);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("my jframe");
		this.setVisible(true);

	}

	public int getPassword() {
		// ????
		// this.addKeyListener(keyBoard);
		return Integer.valueOf(keyBoard.getUserInput());

	}

	public String reGetPassword() {
		// display.displayMessage(sb.toString());
		// ???????
		return sb.toString();
	}

	public void displayMessage(String message) {
		display.displayMessage(message);
	}

	public void setWhileKey(boolean whileKey) {
		this.whileKey = whileKey;
	}

	public void appendStr(char ch) {
		sb.append(ch);
	}

	public String getTransactionType() {
		displayMessage("???????????   D : ???  W: ???  T: ??? B????????  P:???  E:???");
		String type = keyBoard.getUserInput();
		return type;
	}

	public TransactionBase getTransaction(int account, int password, String type) {
		TransactionBase tx = null;

		if (type.equals("D")) {
			displayMessage("????????");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tx = new DepositTx(account, password, Transaction.DEPOSIT);
		} else if (type.equals("W")) {
			displayMessage("???????????");
			int amount = Integer.valueOf(keyBoard.getUserInput());
			tx = new WithdrawTx(account, password, Transaction.WITHDRAW, amount);
		} else if (type.equals("T")) {
			displayMessage("???????????");
			int toAccount = Integer.valueOf(keyBoard.getUserInput());
			displayMessage("???????????");
			int transferMoney = Integer.valueOf(keyBoard.getUserInput());
			tx = new TransferTx(account, password, Transaction.TRANFER, toAccount, transferMoney);
		} else if (type.equals("B")) {
			tx = new QueryBalanceTx(account, password, Transaction.BALANCE);
		} else if (type.equals("P")) {
			tx = new TransactionBase(account, password, Transaction.PRINT) {

				@Override
				public boolean preProcess(ATM atm) {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public boolean postProcess(ATM atm) {
					// TODO Auto-generated method stub
					return false;
				}
			};
		} else if (type.equals("E")) {
			tx = new TransactionBase(account, password, Transaction.EXIT) {

				@Override
				public boolean preProcess(ATM atm) {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public boolean postProcess(ATM atm) {
					// TODO Auto-generated method stub
					return false;
				}
			};
		} else {
			displayMessage("??????????????????");
		}

		return tx;
	}
}
