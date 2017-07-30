package com.coderising.myood.atmSimulation.model;

import com.coderising.myood.atmSimulation.impl.CardReaderImpl;
import com.coderising.myood.atmSimulation.model.CardReader;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by thomas_young on 30/7/2017.
 */
public class CardReaderTest {
    CardReader cardReader = new CardReaderImpl();
    @Test
    public void getAccount() throws Exception {
        String account;
        while(true) {
            account = cardReader.getAccount();
            if(account != null) {
                System.out.println("读卡成功，卡号是"+account);
                break;
            }
            System.out.println("读卡失败");
        }
        cardReader.ejectCard();
    }

    @Test
    public void ejectCard() throws Exception {
        cardReader.ejectCard();
    }

}