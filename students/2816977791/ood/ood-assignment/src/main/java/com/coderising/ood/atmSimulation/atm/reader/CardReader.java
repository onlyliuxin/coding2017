package com.coderising.ood.atmSimulation.atm.reader;

import com.coderising.ood.atmSimulation.card.Card;

/**
 * @author nvarchar
 *         date 2017/7/14
 */
public class CardReader {

    public String readCard(Card card) {
        return card.getAccount();
    }

    public void ejectCard(Card card){
        System.out.println("退卡");
    }
}
