package expr;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InfixToPostfixTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConvert() {
		{
//			InfixExpr expr = new InfixExpr("2+3*4+5");
			String expr = "2+9/3-5";
			Assert.assertEquals("2 9 3 / + 5 -", InfixToPostfix.toString(InfixToPostfix.convert(expr)));
		}
	}

}
