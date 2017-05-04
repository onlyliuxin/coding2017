package com.github.ipk2015.coding2017.basic.stack.expr;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InfixToPostfixTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testConvert() {
		List<Token> tokens  = InfixToPostfix.convert("3+(2-5)*6/3");
		
		Assert.assertEquals(3, tokens.get(0).getIntValue());
		Assert.assertEquals(2, tokens.get(1).getIntValue());
		Assert.assertEquals(5, tokens.get(2).getIntValue());
		Assert.assertEquals("-", tokens.get(3).toString());
		Assert.assertEquals(6, tokens.get(4).getIntValue());
		Assert.assertEquals("*", tokens.get(5).toString());
		Assert.assertEquals(3, tokens.get(6).getIntValue());
		Assert.assertEquals("/", tokens.get(7).toString());
		Assert.assertEquals("+", tokens.get(8).toString());
	}

}
