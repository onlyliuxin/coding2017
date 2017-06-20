package com.coding.basic.stack.expr;



import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class PostfixExprTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEvaluate() {
		{
			PostfixExpr expr = new PostfixExpr("6 5 2 3 + 8 * + 3 + *");
			Assert.assertEquals(288, expr.evaluate(),0.0f);
		}
		{
			//9+(3-1)*3+10/2
			PostfixExpr expr = new PostfixExpr("9 3 1-3*+ 10 2/+");
			Assert.assertEquals(20, expr.evaluate(),0.0f);
		}
		
		{
			//10-2*3+50
			PostfixExpr expr = new PostfixExpr("10 2 3 * - 50 +");
			Assert.assertEquals(54, expr.evaluate(),0.0f);
		}
	}

}
