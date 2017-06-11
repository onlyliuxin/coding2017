package com.coding.basic.stack.expr;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TokenParserTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		
		TokenParser parser = new TokenParser();
		List<Token> tokens  = parser.parse("300*20+12*5-20/4");
		
		Assert.assertEquals(300, tokens.get(0).getIntValue());
		Assert.assertEquals("*", tokens.get(1).toString());
		Assert.assertEquals(20, tokens.get(2).getIntValue());
		Assert.assertEquals("+", tokens.get(3).toString());
		Assert.assertEquals(12, tokens.get(4).getIntValue());
		Assert.assertEquals("*", tokens.get(5).toString());
		Assert.assertEquals(5, tokens.get(6).getIntValue());
		Assert.assertEquals("-", tokens.get(7).toString());
		Assert.assertEquals(20, tokens.get(8).getIntValue());
		Assert.assertEquals("/", tokens.get(9).toString());
		Assert.assertEquals(4, tokens.get(10).getIntValue());
	}

}
