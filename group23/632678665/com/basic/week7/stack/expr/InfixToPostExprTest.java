package com.ralf.stack.expr;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InfixToPostExprTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		//10-2*3+50
		String string = "10-2*3+50";
		List<Token> tokens = InfixToPostExpr.convert(string);
		
		Assert.assertEquals(10, tokens.get(0).getValue());
		Assert.assertEquals(2, tokens.get(1).getValue());
		Assert.assertEquals(3, tokens.get(2).getValue());
		Assert.assertEquals("*", tokens.get(3).toString());
		Assert.assertEquals("-", tokens.get(4).toString());
		Assert.assertEquals(50, tokens.get(5).getValue());
		Assert.assertEquals("+", tokens.get(6).toString());
	}

}
