package com.coding.week7;

import com.coding.week6.exprNew.Token;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by Administrator on 2017/4/23 0023.
 */
public class InfixToPostfixTest {

    @Test
    public void testConvert() throws Exception {
        {
            String expr = "9+(3-1)*3+10/2";//"9 3 1-3*+ 10 2/+"
            List<Token> tokens = InfixToPostfix.convert(expr);
            System.out.println(tokens);
            Assert.assertEquals("9", tokens.get(0).toString());
            Assert.assertEquals("3", tokens.get(1).toString());
            Assert.assertEquals("1", tokens.get(2).toString());
            Assert.assertEquals("-", tokens.get(3).toString());
            Assert.assertEquals("3", tokens.get(4).toString());
            Assert.assertEquals("*", tokens.get(5).toString());
            Assert.assertEquals("+", tokens.get(6).toString());
            Assert.assertEquals("10", tokens.get(7).toString());
            Assert.assertEquals("2", tokens.get(8).toString());
            Assert.assertEquals("/", tokens.get(9).toString());
            Assert.assertEquals("+", tokens.get(10).toString());
        }

    }
}