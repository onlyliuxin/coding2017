package datastructure.stack.expr;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class InfixToPostfixTest {

    @Test
    public void testConvert() {
        {
            String expr = "((2+3)*8+5+3)*6";
            
            List<Token> postFix = InfixToPostfix.convert(expr);
            String postFixExpr = tokenListToString(postFix);
            float postFixExprResult = calculatePostFixExpr(postFixExpr);
            
            Assert.assertEquals(288, postFixExprResult, 0.001);
        }
        {
            String expr = "9+(3-1)*3+10/2";
            
            List<Token> postFix = InfixToPostfix.convert(expr);
            String postFixExpr = tokenListToString(postFix);
            float postFixExprResult = calculatePostFixExpr(postFixExpr);
            
            Assert.assertEquals(20, postFixExprResult, 0.001);
        }
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
            builder.append(token.getValue() + " ");
        }
        return builder.toString();
    }
    
    private float calculatePostFixExpr(String expr) {
        PostfixExpr postfixExpr = new PostfixExpr(expr);
        return postfixExpr.evaluate();
    }
}
