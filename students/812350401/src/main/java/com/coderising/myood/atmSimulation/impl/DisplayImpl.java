package com.coderising.myood.atmSimulation.impl;

import com.coderising.myood.atmSimulation.model.Display;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by thomas_young on 30/7/2017.
 */
public class DisplayImpl implements Display {
    @Override
    public void outputPlainText(String message) {
        System.out.printf(message);
    }

    @Override
    public void outputEncryptedText(String message) {
        System.out.println(StringUtils.repeat("*", message.length()));
    }

    public static void main(String[] args) {
        System.out.printf(StringUtils.repeat("*", "abc".length()));
    }
}
