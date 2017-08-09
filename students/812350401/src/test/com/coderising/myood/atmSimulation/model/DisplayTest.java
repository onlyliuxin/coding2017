package com.coderising.myood.atmSimulation.model;

import com.coderising.myood.atmSimulation.impl.DisplayImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by thomas_young on 30/7/2017.
 */
public class DisplayTest {
    private static Display display = new DisplayImpl();
    @Test
    public void outputPlainText() throws Exception {
        display.outputPlainText("欢迎你！");
    }

    @Test
    public void outputEncryptedText() throws Exception {
        display.outputEncryptedText("345677");
    }

}