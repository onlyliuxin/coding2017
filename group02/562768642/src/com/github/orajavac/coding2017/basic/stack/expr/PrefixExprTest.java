package com.github.orajavac.coding2017.basic.stack.expr;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
			// 2*3+4*5 
			PrefixExpr expr = new PrefixExpr("+ * 2 3 * 4 5");
			Assert.assertEquals(26.0, expr.evaluate(),0.001f);
		}
		{
			// 4*2 + 6+9*2/3 -8
			PrefixExpr expr = new PrefixExpr("- + + 6 / * 2 9 3 * 4 2 8");
			Assert.assertEquals(12.0, expr.evaluate(),0.001f);
		}
		{
			//(3+4)*5-6
			PrefixExpr expr = new PrefixExpr("- * + 3 4 5 6");
			Assert.assertEquals(29.0, expr.evaluate(),0.001f);
		}
		{
			//1+((2+3)*4)-5
			PrefixExpr expr = new PrefixExpr("- + 1 * + 2 3 4 5");
			Assert.assertEquals(16.0, expr.evaluate(),0.001f);
		}
		
		
	}
}
