package com.coderising.myood.atmSimulation.impl;

import com.coderising.myood.atmSimulation.model.CardReader;

/**
 * Created by thomas_young on 30/7/2017.
 */
public class CardReaderImpl implements CardReader {
    private Integer retryTimes = 0;

    @Override
    public String getAccount() {
        // 模拟读三次才把卡读出
        if (retryTimes <= 2) {
            System.out.println("没有卡哟");
            retryTimes++;
            return null;
        }
        return "yangkaiAccount";
    }

    @Override
    public void ejectCard() {
        System.out.printf("吐出卡片, 请收好");
        retryTimes = 0;
    }

    @Override
    public void eatCard() {
        System.out.printf("吞卡!");
        retryTimes = 0;
    }
}
