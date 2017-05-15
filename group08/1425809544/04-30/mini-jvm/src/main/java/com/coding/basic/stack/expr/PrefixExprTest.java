package com.coding.basic.stack.expr;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 测试前缀表达式
 *
 * @author xyy
 * @create 2017-04-25 8:37
 **/
public class PrefixExprTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testEvaluate() {
        {
//            2*3+4*5
            PrefixExpr expr = new PrefixExpr("+ * 2 3 * 4 5");
            Assert.assertEquals(26, expr.evaluate(), 0.001f);

        }

        {
//            (3+4)*5-6
            PrefixExpr expr = new PrefixExpr("- * + 3 4 5 6 ");
            Assert.assertEquals(12,expr.evaluate(),0.001f);
        }

        {
//            4*2+6+9*2/3 -8
            PrefixExpr expr = new PrefixExpr("-++/*2 9 3 ");
            Assert.assertEquals(12, expr.evaluate(),0.001f);
        }

        {
//          1+((2+3)*4)-5
            PrefixExpr expr = new PrefixExpr("- + 1 * +2 3 4 5");
            Assert.assertEquals(16, expr.evaluate(),0.001f);
        }



    }
}
