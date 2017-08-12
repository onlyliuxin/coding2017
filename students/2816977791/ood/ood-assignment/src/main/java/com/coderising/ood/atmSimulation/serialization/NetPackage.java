package com.coderising.ood.atmSimulation.serialization;

import com.coderising.ood.atmSimulation.atm.Atm;

/**
 * @author nvarchar
 *         date 2017/7/16
 */
public class NetPackage {

    public enum Type {Verify, QUERY, TRANSFER, DEPOSIT, WITHDRAW}

    private String account;
    private String password;
    private Integer amount;
    private Type type;
    private String toCard;

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setToCard(String toCard) {
        this.toCard = toCard;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public Integer getAmount() {
        return amount;
    }

    public Type getType() {
        return type;
    }

    public String getToCard() {
        return toCard;
    }

    public NetPackage(Type type, Atm atm, Integer amount, String toCard) {
        this.account = atm.getAccount();
        this.password = atm.getPassword();
        this.amount = amount;
        this.type = type;
        this.toCard = toCard;
    }

    public NetPackage(String account, String password) {
        this.type = Type.Verify;
        this.account = account;
        this.password = password;
    }

    public NetPackage() {
    }
}

