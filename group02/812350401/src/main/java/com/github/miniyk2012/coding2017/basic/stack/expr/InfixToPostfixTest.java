package com.github.miniyk2012.coding2017.basic.stack.expr;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.util.List;

/** 
* InfixToPostfix Tester. 
* 
* @author <Authors name> 
* @since <pre>Apr 30, 2017</pre> 
* @version 1.0 
*/ 
public class InfixToPostfixTest { 

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
    *
    * Method: convert(String expr)
    *
    */
    @Test
    public void testConvert() throws Exception {
        {
            String infixExpr = "3+4";
            List<Token> tokens = InfixToPostfix.convert(infixExpr);
            Assert.assertEquals("3 4 +", ExprUtil.tokens2String(tokens));
        }
        {
            String infixExpr = "1 * 3 + 4 * 6";
            List<Token> tokens = InfixToPostfix.convert(infixExpr);
            Assert.assertEquals("1 3 * 4 6 * +", ExprUtil.tokens2String(tokens));
        }
        {
            String infixExpr = "1 * 3 / 4 * 6";
            List<Token> tokens = InfixToPostfix.convert(infixExpr);
            Assert.assertEquals("1 3 * 4 / 6 *", ExprUtil.tokens2String(tokens));
        }
        {
            String infixExpr = "1 + 3 * 4";
            List<Token> tokens = InfixToPostfix.convert(infixExpr);
            Assert.assertEquals("1 3 4 * +", ExprUtil.tokens2String(tokens));
        }
        {
            String infixExpr = "3+2*3+4*5";
            List<Token> tokens = InfixToPostfix.convert(infixExpr);
            Assert.assertEquals("3 2 3 * + 4 5 * +", ExprUtil.tokens2String(tokens));
        }
        {
            String infixExpr = "1-3+5-2/1/2*4";
            List<Token> tokens = InfixToPostfix.convert(infixExpr);
            Assert.assertEquals("1 3 - 5 + 2 1 / 2 / 4 * -", ExprUtil.tokens2String(tokens));
        }
    }


} 
