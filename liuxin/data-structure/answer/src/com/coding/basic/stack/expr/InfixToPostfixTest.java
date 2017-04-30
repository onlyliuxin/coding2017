package com.coding.basic.stack.expr;

import java.util.List;

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
			List<Token> tokens = InfixToPostfix.convert("2+3");
			Assert.assertEquals("[2, 3, +]", tokens.toString());
		}
		{
		
			List<Token> tokens = InfixToPostfix.convert("2+3*4");
			Assert.assertEquals("[2, 3, 4, *, +]", tokens.toString());
		}
		
		{
			
			List<Token> tokens = InfixToPostfix.convert("2-3*4+5");
			Assert.assertEquals("[2, 3, 4, *, -, 5, +]", tokens.toString());
		}
	}

}
