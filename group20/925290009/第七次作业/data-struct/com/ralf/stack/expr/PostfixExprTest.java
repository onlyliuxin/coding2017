package com.ralf.stack.expr;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PostfixExprTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
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
