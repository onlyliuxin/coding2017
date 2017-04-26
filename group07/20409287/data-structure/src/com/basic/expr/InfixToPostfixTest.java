package com.basic.expr;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by xudanxia on 2017/4/17.
 */
public class InfixToPostfixTest {

    @Test
    public void testConvert() {

        {
            String infix = "1+1";
            StringBuilder expr = new StringBuilder();
            for (Token token : InfixToPostfix.convert(infix)) {
                expr.append(token.value).append(" ");
            }
            PostfixExpr postfixExpr = new PostfixExpr(expr.toString());
            Assert.assertEquals(2, postfixExpr.evaluate(),0.0f);
        }
        {
            String infix = "1+1*4-5+9";
            StringBuilder expr = new StringBuilder();
            for (Token token : InfixToPostfix.convert(infix)) {
                expr.append(token.value).append(" ");
            }
            System.out.println(infix + " 的后缀表达式为: " + expr);
            PostfixExpr postfixExpr = new PostfixExpr(expr.toString());
            Assert.assertEquals(9, postfixExpr.evaluate(),0.0f);
        }
        {
            String infix = "50+2*25-90";
            StringBuilder expr = new StringBuilder();
            for (Token token : InfixToPostfix.convert(infix)) {
                expr.append(token.value).append(" ");
            }
            System.out.println(infix + " 的后缀表达式为: " + expr);
            PostfixExpr postfixExpr = new PostfixExpr(expr.toString());
            Assert.assertEquals(10, postfixExpr.evaluate(),0.0f);
        }
        {
            String infix = "50*(5+1)-90";
            StringBuilder expr = new StringBuilder();
            for (Token token : InfixToPostfix.convert(infix)) {
                expr.append(token.value).append(" ");
            }
            System.out.println(infix + " 的后缀表达式为: " + expr);
            PostfixExpr postfixExpr = new PostfixExpr(expr.toString());
            Assert.assertEquals(210, postfixExpr.evaluate(),0.0f);
        }
        {
            String infix = "5*(5+(9-4)*(1+2))-90";
            StringBuilder expr = new StringBuilder();
            for (Token token : InfixToPostfix.convert(infix)) {
                expr.append(token.value).append(" ");
            }
            System.out.println(infix + " 的后缀表达式为: " + expr);
            PostfixExpr postfixExpr = new PostfixExpr(expr.toString());
            Assert.assertEquals(10, postfixExpr.evaluate(),0.0f);
        }
    }
}
