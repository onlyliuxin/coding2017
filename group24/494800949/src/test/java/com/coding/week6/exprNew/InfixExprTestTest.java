package com.coding.week6.exprNew;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Administrator on 2017/4/16 0016.
 */
public class InfixExprTestTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testEvaluate() {
        //InfixExpr expr = new InfixExpr("300*20+12*5-20/4");

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
            InfixExpr expr = new InfixExpr("20/2*3|");
            Assert.assertEquals(30, expr.evaluate(), 0.001f);
        }

        {
            InfixExpr expr = new InfixExpr("10-30+50");
            Assert.assertEquals(30, expr.evaluate(), 0.001f);
        }

    }


//    @Test
//    public void testFillStack() {
//        InfixExpr expr = new InfixExpr("10-30+50");
//        expr.fillStack();
//        Assert.assertEquals(expr.printNumberStack(), "50.0,-20.0");
//        Assert.assertEquals(expr.printOperatorStack(), "+");
//        expr = new InfixExpr("3*20+12*5-40/2");
//        expr.fillStack();
//        Assert.assertEquals(expr.printNumberStack(), "2.0,40.0,60.0,60.0");
//        Assert.assertEquals(expr.printOperatorStack(), "/,-,+");
//        expr = new InfixExpr("3*20/2");
//        expr.fillStack();
//        Assert.assertEquals(expr.printNumberStack(), "2.0,60.0");
//        Assert.assertEquals(expr.printOperatorStack(), "/");
//        expr = new InfixExpr("20/2*3");
//        expr.fillStack();
//        Assert.assertEquals(expr.printNumberStack(), "3.0,10.0");
//        Assert.assertEquals(expr.printOperatorStack(), "*");
//        expr = new InfixExpr("10-30+50");
//        expr.fillStack();
//        Assert.assertEquals(expr.printNumberStack(), "50.0,-20.0");
//        Assert.assertEquals(expr.printOperatorStack(), "+");
//    }



}