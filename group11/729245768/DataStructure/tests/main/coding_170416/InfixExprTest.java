package main.coding_170416;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by peter on 2017/4/23.
 */
public class InfixExprTest extends TestCase {
    @Test
    public void testEvalute() throws Exception {
        InfixExpr infixExpr = new InfixExpr("2+10*2-14/2");
        Assert.assertEquals(15,infixExpr.evalute(),0.001f);
    }

}