package com.coding.basic.stack.expr;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InfixExprTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testEvaluate() {
        {
            InfixExpr expr = new InfixExpr("2+3*4+5");
            Assert.assertEquals(19.0, expr.evaluate(), 0.001f);
        }
        {
            InfixExpr expr = new InfixExpr("3*20+12*5-40/2");
            Assert.assertEquals(100.0, expr.evaluate(), 0.001f);
        }

        {
            InfixExpr expr = new InfixExpr("3*20/2");
            Assert.assertEquals(30, expr.evaluate(), 0.001f);
        }

        {
            InfixExpr expr = new InfixExpr("20/2*3");
            Assert.assertEquals(30, expr.evaluate(), 0.001f);
        }

        {
            InfixExpr expr = new InfixExpr("10-30+50");
            Assert.assertEquals(30, expr.evaluate(), 0.001f);
        }
    }
}
