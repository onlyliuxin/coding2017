package week07.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import week07.basic.InfixToPostfix;

public class InfixToPostfixTest {
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testconvert() {
		
		Assert.assertEquals("[2, 3, 4, *, +, 5, +]", InfixToPostfix.convert("2+3*4+5").toString());
		
		Assert.assertEquals("[3, 20, *, 12, 5, *, +, 40, 2, /, -]", InfixToPostfix.convert("3*20+12*5-40/2").toString());
		
		Assert.assertEquals("[10, 2, 3, *, -, 50, +]", InfixToPostfix.convert("10-2*3+50").toString());
		
		Assert.assertEquals("[3, 20, 2, /, *]", InfixToPostfix.convert("3*20/2").toString());
		
		Assert.assertEquals("[10, 30, 50, +, -]", InfixToPostfix.convert("10-30+50").toString());
		
		Assert.assertEquals("[20, 2, 3, *, /]", InfixToPostfix.convert("20/2*3").toString());
		
	}
}
