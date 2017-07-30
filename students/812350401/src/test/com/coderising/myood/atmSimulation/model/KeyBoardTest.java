package com.coderising.myood.atmSimulation.model;

import com.coderising.myood.atmSimulation.impl.KeyBoardImpl;
import org.junit.Ignore;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Created by thomas_young on 30/7/2017.
 */
public class KeyBoardTest {
    KeyBoard keyBoard = new KeyBoardImpl();

    @Test
    public void testInput() throws Exception {
        String data = "Hello, World!\r\n";
        String input;
        InputStream stdin = System.in;
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            input = keyBoard.input();
        } finally {
            System.setIn(stdin);
        }
        System.out.println("input is----" + input);

        System.setIn(new ByteArrayInputStream("haha\n".getBytes()));
        input = keyBoard.input();
        System.out.println(input);
    }
}