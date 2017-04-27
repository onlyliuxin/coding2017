package structure.week7;
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
		//InfixExpr expr = new InfixExpr("300*20+12*5-20/4");
		{
			InfixExpr expr = new InfixExpr("2+3*4+5");
			try {
				Assert.assertEquals(19.0, expr.evaluate(), 0.001f);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
        
		{
			InfixExpr expr = new InfixExpr("3*20+12*5-40/2");
			try {
				Assert.assertEquals(100.0, expr.evaluate(), 0.001f);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		{
			InfixExpr expr = new InfixExpr("3*20/2");
			try {
				Assert.assertEquals(30, expr.evaluate(), 0.001f);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		{
			InfixExpr expr = new InfixExpr("20/2*3");
			try {
				Assert.assertEquals(30, expr.evaluate(), 0.001f);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
        
		{
			InfixExpr expr = new InfixExpr("10-30+50");
			try {
				Assert.assertEquals(30, expr.evaluate(), 0.001f);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}