package com.donaldy.basic.expr;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by DonaldY on 2017/4/18.
 */
public class InfixToPostfixTest {

    @Test
    public void test() {

        String expr = "9 + 3-1 *3+10/2";
        //Assert.assertEquals("9 3 1-3*+ 10 2/+", InfixToPostfix.convert(expr));

        expr = "10-2*3+50";
        Assert.assertEquals("[10, 2, 3, *, -, 50, +]", InfixToPostfix.convert(expr).toString());

    }

}
