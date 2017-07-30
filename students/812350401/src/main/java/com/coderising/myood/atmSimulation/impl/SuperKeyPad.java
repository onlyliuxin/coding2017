package com.coderising.myood.atmSimulation.impl;

import com.coderising.myood.atmSimulation.model.Display;
import com.coderising.myood.atmSimulation.model.KeyBoard;
import com.coderising.myood.atmSimulation.model.Transaction;

/**
 * Created by thomas_young on 30/7/2017.
 * 超级键盘协调用户输入和显示星号的关系
 */
public class SuperKeyPad {
    private Display display;
    private KeyBoard keyBoard;

    /**
     * 提示用户输入密码，待用户输入完成后，显示星号
     * 并把明文密码返回(为了简单起见)
     * @return
     */
    public String getPassword() {
        System.out.println("请输入密码：");
        String input = keyBoard.input();
        display.outputEncryptedText(input);
        return input;
    }

    public void displayMessage(String message) {
        display.outputPlainText(message);
    }

    public Transaction getTransaction(String account, String password) {
        display.outputPlainText("请输入交易类型:");
        display.outputPlainText("W: 取款, ");
        display.outputPlainText("D: 存款, ");
        display.outputPlainText("T: 转账, ");
        display.outputPlainText("Q: 查询余额");
        while(true) {
            String input = keyBoard.input();
            switch (input) {
                case "W":
                    return getWithdrawTrx(account, password);
                case "D":
                    return getDepositTrx(account, password);
                case "T":
                    return getTransferTrx(account, password);
                case "Q":
                    return getQueryTrx(account, password);
                default:
                    System.out.printf("输入有误，请重新输入");
            }
        }
    }

    private Transaction getWithdrawTrx(String account, String password) {
        while(true) {
            display.outputPlainText("请输入取现金额：");
            String input = keyBoard.input();
            try {
                Double amount = Double.valueOf(input);
                return new WithdrawTransaction(account, password, amount);
            } catch (NumberFormatException e) {
                System.out.printf("格式有误，请重新输入");
            }
        }
    }

    private Transaction getDepositTrx(String account, String password) {
        while(true) {
            display.outputPlainText("请输入存款金额：");
            String input = keyBoard.input();
            try {
                Double amount = Double.valueOf(input);
                return new DepositTransaction(account, password, amount);
            } catch (NumberFormatException e) {
                System.out.printf("格式有误，请重新输入");
            }
        }
    }

    private Transaction getTransferTrx(String account, String password) {
        while(true) {
            display.outputPlainText("请输入对方账户：");
            String counterAccount = keyBoard.input();
            if ("".equals(counterAccount)) {
                System.out.printf("输入账户有误，请重新输入");
                continue;
            }
            display.outputPlainText("请输入转账金额：");
            String input = keyBoard.input();
            try {
                Double amount = Double.valueOf(input);
                return new TransferTransaction(account, password, amount, counterAccount);
            } catch (NumberFormatException e) {
                System.out.printf("格式有误，请重新输入");
            }
        }
    }

    private Transaction getQueryTrx(String account, String password) {
        return new QueryTransaction(account, password);
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public void setKeyBoard(KeyBoard keyBoard) {
        this.keyBoard = keyBoard;
    }

    public static void main(String[] args) {
        SuperKeyPad superKeyPad = new SuperKeyPad();
        Display display = new DisplayImpl();
        KeyBoard keyBoard = new KeyBoardImpl();
        superKeyPad.setKeyBoard(keyBoard);
        superKeyPad.setDisplay(display);
        Transaction transaction = superKeyPad.getTransaction("yangkai", "123456");
        System.out.println(transaction);

        String password = superKeyPad.getPassword();
        System.out.println(password);
    }
}
