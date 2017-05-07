package FixEXpr;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PreFixExprTest {

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
			PreFixExpr expr = new PreFixExpr("+ * 2 3* 4 5");
			Assert.assertEquals(26, expr.evaluate(),0.001f);
		}
		{
			// 4*2 + 6+9*2/3 -8
			PreFixExpr expr = new PreFixExpr("-++6/*2 9 3 * 4 2 8");
			Assert.assertEquals(12, expr.evaluate(),0.001f);
		}
		{
			//(3+4)*5-6
			PreFixExpr expr = new PreFixExpr("- * + 3 4 5 6");
			Assert.assertEquals(29, expr.evaluate(),0.001f);
		}
		{
			//1+((2+3)*4)-5
			PreFixExpr expr = new PreFixExpr("- + 1 * + 2 3 4 5");
			Assert.assertEquals(16, expr.evaluate(),0.001f);
		}
	}

}
