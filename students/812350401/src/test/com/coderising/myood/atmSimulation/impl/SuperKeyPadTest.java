package com.coderising.myood.atmSimulation.impl;

import com.coderising.myood.atmSimulation.model.Display;
import com.coderising.myood.atmSimulation.model.KeyBoard;
import com.coderising.myood.atmSimulation.model.Transaction;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * Created by thomas_young on 30/7/2017.
 */
public class SuperKeyPadTest {
    private SuperKeyPad superKeyPad;
    {
        superKeyPad = new SuperKeyPad();
        Display display = new DisplayImpl();
        KeyBoard keyBoard = new KeyBoardImpl();
        superKeyPad.setDisplay(display);
        superKeyPad.setKeyBoard(keyBoard);
    }

    @Test
    public void testGetPassword() throws Exception {
        String password = "123456";
        String output;
        InputStream stdin = System.in;
        try {
            System.setIn(new ByteArrayInputStream(password.getBytes()));
            output = superKeyPad.getPassword();
        } finally {
            System.setIn(stdin);
        }
        Assert.assertEquals(password, output);
    }

    @Test
    public void testDisplayMessage() throws Exception {
        superKeyPad.displayMessage("欢迎");
    }

    @Ignore
    @Test
    public void testGetTransaction() throws Exception {

        String lineSeparator = System.getProperty("line.separator");
        String trxInput = "X" + lineSeparator + "D" + lineSeparator + "xx" + lineSeparator + "190";

        InputStream stdin = System.in;
        Transaction transaction;
        try {
            System.setIn(new ByteArrayInputStream(trxInput.getBytes()));
            transaction = superKeyPad.getTransaction("yangkai", "123456");
        } finally {
            System.setIn(stdin);
        }
        System.out.println(transaction);
    }

}