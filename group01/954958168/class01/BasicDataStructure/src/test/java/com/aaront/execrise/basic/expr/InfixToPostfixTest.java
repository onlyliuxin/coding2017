package com.aaront.execrise.basic.expr;

import com.aaront.exercise.basic.expr.InfixToPostfix;
import com.aaront.exercise.basic.expr.PostfixExpr;
import com.aaront.exercise.basic.expr.Token;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class InfixToPostfixTest {

    @Test
    public void testConvert() {
        {
            String expr = "10-2*3+50";

            List<Token> postFix = InfixToPostfix.convert(expr);
            String postFixExpr = tokenListToString(postFix);
            float postFixExprResult = calculatePostFixExpr(postFixExpr);

            Assert.assertEquals(54, postFixExprResult, 0.001);
        }
    }

    private String tokenListToString(List<Token> tokenList) {
        StringBuilder builder = new StringBuilder();
        for (Token token : tokenList) {
            builder.append(token + " ");
        }
        return builder.toString();
    }

    private float calculatePostFixExpr(String expr) {
        PostfixExpr postfixExpr = new PostfixExpr(expr);
        return postfixExpr.evaluate();
    }
}
