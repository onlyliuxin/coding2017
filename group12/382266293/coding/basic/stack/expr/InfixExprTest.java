package stack.expr;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InfixExprTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEvaluate() {

		{
			InfixExpr expr = new InfixExpr("1*2*2-1");
			Assert.assertEquals(3.0, expr.evaluate(), 0.001f);
		}
		
		{
			InfixExpr expr = new InfixExpr("2+3*4+5");
			Assert.assertEquals(19.0, expr.evaluate(), 0.001f);
		}
		
		{
			InfixExpr expr = new InfixExpr("2+3*4+5*1+1");
			Assert.assertEquals(20.0, expr.evaluate(), 0.001f);
		}
		
		{
			InfixExpr expr = new InfixExpr("2+3*4/2+1");
			Assert.assertEquals(9, expr.evaluate(), 0.001f);
		}
		
		{
			InfixExpr expr = new InfixExpr("2-3*4/2-1");
			Assert.assertEquals(-5.0, expr.evaluate(), 0.001f);
		}

		{
			InfixExpr expr = new InfixExpr("6-1*5+2+0");
			Assert.assertEquals(3.0, expr.evaluate(), 0.001f);
		}

		{
			InfixExpr expr = new InfixExpr("3*20/2");
			Assert.assertEquals(30, expr.evaluate(), 0.001f);
		}
		
		{
			InfixExpr expr = new InfixExpr("3*20/2+1");
			Assert.assertEquals(31, expr.evaluate(), 0.001f);
		}
		
		{
			InfixExpr expr = new InfixExpr("3*20/2+1-1");
			Assert.assertEquals(30, expr.evaluate(), 0.001f);
		}

		{
			InfixExpr expr = new InfixExpr("20/2*3");
			Assert.assertEquals(30, expr.evaluate(), 0.001f);
		}

		{
			InfixExpr expr = new InfixExpr("10-30+50");
			Assert.assertEquals(30, expr.evaluate(), 0.001f);
		}

	}

}
